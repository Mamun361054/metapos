package com.metamorphosis.metapos.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.tabs.TabLayout;
import com.metamorphosis.metapos.Activity.More.MoreActivity;
import com.metamorphosis.metapos.Adapter.TabFragmentAdapter;
import com.metamorphosis.metapos.Fragment.FragmentLastMonth;
import com.metamorphosis.metapos.Fragment.FragmentLastWeek;
import com.metamorphosis.metapos.Fragment.FragmentSevenDays;
import com.metamorphosis.metapos.Fragment.FragmentToday;
import com.metamorphosis.metapos.R;

public class AccountsActivity extends AppCompatActivity {

    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;

    private int indicatorWidth;
    private AHBottomNavigation ahBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        createBottomNavigation();


        //Assign view reference
        mTabs = findViewById(R.id.tab);
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);

        //Set up the view pager and fragments
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentLastWeek.newInstance(), "Last Week");
        adapter.addFragment(FragmentLastMonth.newInstance(), "Last Month");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        //Determine indicator width at runtime
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();
                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

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
        ahBottomNavigation.setCurrentItem(2);
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
                        Intent intent = new Intent(AccountsActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                        return true;

                    case 1:
                        Intent intent2 = new Intent(AccountsActivity.this, SalesActivity.class);
                        startActivity(intent2);
                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        finish();
                        return true;

                    case 3:
                        Intent intent4 = new Intent(AccountsActivity.this, MoreActivity.class);
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
