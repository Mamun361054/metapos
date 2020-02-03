package com.metamorphosis.metapos.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.metamorphosis.metapos.Activity.More.MoreActivity;
import com.metamorphosis.metapos.Fragment.FragmentHome;
import com.metamorphosis.metapos.R;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private static final String TAG = "Dashboard";

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        createToolbar();
        createViewPager();
        createBottomNavigation();

    }

    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void createViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFrag(new FragmentHome(), " ");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
        ahBottomNavigation.setCurrentItem(0);
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
                    case 1:
                        Intent intent = new Intent(DashBoardActivity.this, SalesActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                        return true;

                    case 2:
                        Intent intent2 = new Intent(DashBoardActivity.this, AccountsActivity.class);
                        startActivity(intent2);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        finish();
                        return true;

                    case 3:
                        Intent intent4 = new Intent(DashBoardActivity.this, MoreActivity.class);
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
