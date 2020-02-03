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

import com.metamorphosis.metapos.Adapter.AccountsListAdapter;
import com.metamorphosis.metapos.Api.ApiClient;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.Model.AccountsPojo;
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
public class FragmentLastWeek extends Fragment {
    private RecyclerView recyclerView;
    private AccountsListAdapter accountsListAdapter;
    private GridLayoutManager mLayoutManager;
    private ApiInterface apiInterface;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String strDate, endDate;
    private String storeId, shopName;

    private String TAG = "FragmentLastWeek";


    public static FragmentLastWeek newInstance() {
        // Required empty public constructor

        return new FragmentLastWeek();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_last_week, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.d(TAG, "width: " + width + " height: " + height);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yyyy");


        calendar.setTime(new Date());


        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        strDate = mdformat.format(calendar.getTime());

        calendar.add(Calendar.DAY_OF_WEEK,6);
        endDate =mdformat.format(calendar.getTime());
        Log.d(TAG,"Last Friday: " +endDate);



        Log.e(TAG,"Last Saturday: " +strDate);

        storeId = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_STORE_ID);
        shopName = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_SHOPNAME);

//        Date newDate = calendar.getTime();
//        SimpleDateFormat fday = new SimpleDateFormat("MM/dd/yyyy");
//        String friday = fday.format(newDate);
//        Log.e(TAG,"Friday: " +friday);


        getAccountsList(strDate, endDate, storeId, shopName);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAccountsList(strDate, endDate,storeId, shopName);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void getAccountsList(String start, String end, String StoreId, String ShopName){

        String startDate = start;
        String endDate = end;
        String storeId = StoreId;
        String shopName = ShopName;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Log.d(TAG, "api: " + apiInterface);
        Call<List<AccountsPojo>> call = apiInterface.getAccountsGrowth(startDate, endDate, storeId, shopName);
        Log.d(TAG, "call: " + call);
        call.enqueue(new Callback<List<AccountsPojo>>() {
            @Override
            public void onResponse(Call<List<AccountsPojo>> call, Response<List<AccountsPojo>> response) {
                try{
                    Log.d(TAG, "response_new: " + response.body());

                    if(response.isSuccessful() && response.body() !=null){
                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status: " +status);

                        if(status.equals("200")) {
                            List<AccountsPojo.AccountsData> accountList = response.body().get(0).getData();
                            Log.d(TAG, String.valueOf(accountList.size()));

                            accountsListAdapter = new AccountsListAdapter(getContext(), accountList);
                            recyclerView.setAdapter(accountsListAdapter);

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
            public void onFailure(Call<List<AccountsPojo>> call, Throwable t) {
                Log.d(TAG, String.valueOf(t.getCause()));
                Log.d(TAG, String.valueOf(t.getMessage()));

            }
        });
    }

}
