package com.metamorphosis.metapos.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metamorphosis.metapos.Adapter.SalesListAdapter;
import com.metamorphosis.metapos.Api.ApiClient;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.Model.SalesPojo;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.session.Session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSevenDays extends Fragment {
    private RecyclerView recyclerView;
    private SalesListAdapter salesListAdapter;
    private GridLayoutManager mLayoutManager;
    private ApiInterface apiInterface;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String strDate, endDate;
    private String storeId, shopName;

    private String TAG = "FragmentSevenDays";


    public static FragmentSevenDays newInstance() {
        return new FragmentSevenDays();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seven_days, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.d(TAG, "width: " + width + " height: " + height);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yyyy");
        endDate =mdformat.format(calendar.getTime());
        Log.d(TAG,"Today: " +endDate);



        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date last7days = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        strDate = df.format(last7days);

        Log.e(TAG,"Days: " +strDate);

//        calendar.setTime(myDate);
//        calendar.add(Calendar.DAY_OF_YEAR, -7);
//        Date newDate = calendar.getTime();

        storeId = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_STORE_ID);
        shopName = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_SHOPNAME);


        getSalesList(strDate, endDate, storeId, shopName);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSalesList(strDate, endDate, storeId, shopName);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void getSalesList(String start, String end, String StoreId, String ShopName){

        String startDate = start;
        String endDate = end;
        String storeId = StoreId;
        String shopName = ShopName;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Log.d(TAG, "api: " + apiInterface);
        Call<List<SalesPojo>> call = apiInterface.getSalesGrowth(startDate, endDate, storeId, shopName);
        Log.d(TAG, "call: " + call);
        call.enqueue(new Callback<List<SalesPojo>>() {
            @Override
            public void onResponse(Call<List<SalesPojo>> call, Response<List<SalesPojo>> response) {
                try{
                    Log.d(TAG, "response_new: " + response.body());

                    if(response.isSuccessful() && response.body() !=null){

                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status: " +status);

                        if(status.equals("200")) {
                            List<SalesPojo.SalesData> salesList = response.body().get(0).getSalesData();
                            Log.d(TAG, String.valueOf(salesList.size()));

                            salesListAdapter = new SalesListAdapter(getContext(), salesList);
                            recyclerView.setAdapter(salesListAdapter);

                        }


                    }
                    else {
                        if (response.code() == 401) {
                            Log.i(TAG, "401");
                            getActivity().finish();
                        }

                        if (response.code() == 404) {
                            Log.i(TAG, "404");
//                                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception e){
                    Log.d(TAG, "Something Wrong: " + e.getCause());

                }
            }

            @Override
            public void onFailure(Call<List<SalesPojo>> call, Throwable t) {
                Log.d(TAG, String.valueOf(t.getCause()));
                Log.d(TAG, String.valueOf(t.getMessage()));

            }
        });
    }

}
