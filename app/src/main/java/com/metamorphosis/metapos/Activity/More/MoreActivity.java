package com.metamorphosis.metapos.Activity.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.metamorphosis.metapos.Activity.AccountsActivity;
import com.metamorphosis.metapos.Activity.DashBoardActivity;
import com.metamorphosis.metapos.Activity.SalesActivity;
import com.metamorphosis.metapos.Adapter.AccountsListAdapter;
import com.metamorphosis.metapos.Adapter.StoreListAdapter;
import com.metamorphosis.metapos.Api.ApiClient;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.Model.AccountsPojo;
import com.metamorphosis.metapos.Model.StoreListPojo;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.session.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private TextView tvAboutUs, tvFAQ, tvTermsOfUse, tvPrivacyPolicy, tvProfile;
    private RecyclerView recyclerView;
    private String TAG = "MoreActivity";
    private ApiInterface apiInterface;
    private String roleId, shopName;
    private StoreListAdapter storeListAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        createBottomNavigation();


        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvAboutUs = (TextView) findViewById(R.id.tvAboutUs);
        tvFAQ = (TextView) findViewById(R.id.tvFAQ);
        tvTermsOfUse = (TextView) findViewById(R.id.tvTermsOfUse);
        tvPrivacyPolicy = (TextView) findViewById(R.id.tvPrivacyPolicy);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

//        tvReturns.setTypeface(CustomFont.setVerdana(getApplicationContext()));
//        tvAboutUs.setTypeface(CustomFont.setVerdana(getApplicationContext()));
//        tvFAQ.setTypeface(CustomFont.setVerdana(getApplicationContext()));
//        tvTermsOfUse.setTypeface(CustomFont.setVerdana(getApplicationContext()));
//        tvPrivacyPolicy.setTypeface(CustomFont.setVerdana(getApplicationContext()));

        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, AboutUsActivity.class));
            }
        });


        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        tvFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, FAQActivity.class));
            }
        });

        tvTermsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, TermsOfUseActivity.class));
            }
        });

        tvPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, PrivacyPolicyActivity.class));
            }
        });

        roleId = Session.getStringFromSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_ROLE_ID);
        shopName = Session.getStringFromSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_SHOPNAME);

        getStoreList(roleId, shopName);

    }

    private void getStoreList(String RoleId, String ShopName){

        String storeId = RoleId;
        String shopName = ShopName;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<StoreListPojo>> call = apiInterface.getStoreList(storeId, shopName);
        call.enqueue(new Callback<List<StoreListPojo>>() {
            @Override
            public void onResponse(Call<List<StoreListPojo>> call, Response<List<StoreListPojo>> response) {
                try{
                    Log.d(TAG, "response_new: " + response.body());

                    if(response.isSuccessful() && response.body() !=null){
                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status: " +status);

                        if(status.equals("200")) {
                            List<StoreListPojo.StoreListData> storeList = response.body().get(0).getData();
                            Log.d(TAG, String.valueOf(storeList.size()));

                            storeListAdapter = new StoreListAdapter(getApplicationContext(), storeList);
                            recyclerView.setAdapter(storeListAdapter);

                        }

                    }
                    else {
                        if (response.code() == 401) {
                            Log.i(TAG, "401");
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
            public void onFailure(Call<List<StoreListPojo>> call, Throwable t) {
                Log.d(TAG, String.valueOf(t.getCause()));
                Log.d(TAG, String.valueOf(t.getMessage()));

            }
        });
    }



    private void createBottomNavigation() {
        ahBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.item_home, R.drawable.ic_home, R.color.navigationBarColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.item_sales, R.drawable.ic_sales, R.color.navigationBarColor);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.item_account, R.drawable.ic_accounts, R.color.navigationBarColor);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.item_more, R.drawable.ic_more, R.color.navigationBarColor);

        // Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);
        ahBottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.light_gray_2));
//        ahBottomNavigation.setBehaviorTranslationEnabled(false);
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahBottomNavigation.setCurrentItem(3);
        ahBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        //change color of title in active state and inactive state
        ahBottomNavigation.setAccentColor(getResources().getColor(R.color.bottom_nav_title_color_active));//active
        //ahBottomNavigation.setInactiveColor(getResources().getColor(R.color.bottom_nav_title_color_inactive));//inactive

        // Add or remove notification for each item
        //ahBottomNavigation.setNotification("1", 3);
// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.badge_bottom))
//                .setTextColor(ContextCompat.getColor(DashBoardActivity.this, R.color.white))
//                .build();
//        ahBottomNavigation.setNotification(notification, 2);



        // Set listeners
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MoreActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                        return true;

                    case 1:
                        Intent intent2 = new Intent(MoreActivity.this, SalesActivity.class);
                        startActivity(intent2);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        finish();
                        return true;

                    case 2:
                        Intent intent4 = new Intent(MoreActivity.this, AccountsActivity.class);
                        startActivity(intent4);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                        return true;
                }
                return true;
            }
        });
        ahBottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }
}
