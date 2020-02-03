package com.metamorphosis.metapos.Activity.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.metamorphosis.metapos.Activity.Application.MyApplication;
import com.metamorphosis.metapos.Activity.LogInActivity;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.Utils.CustomFont;
import com.metamorphosis.metapos.session.Session;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvShopName, tvActiveDate, tvExpiryDate, tvMonthlyFee, tvBaseUrl;
    private Button btnLogOut;
    //private Context context = this;

    public ProfileActivity activity;
    private String TAG = "ProfileActivity";
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setTypeface(CustomFont.setRegular(getApplicationContext()));
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvShopName = (TextView) findViewById(R.id.tvShop);
        tvActiveDate =(TextView) findViewById(R.id.tvActivateDate);
        tvExpiryDate =(TextView) findViewById(R.id.tvExpiryDate);
        tvMonthlyFee =(TextView) findViewById(R.id.tvMonthlyFee);
        //tvBaseUrl = (TextView) findViewById(R.id.tvBaseUrl);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        //activity = this;

        getDetails();

        Log.d(TAG, "getAppContext: " +MyApplication.getAppContext());


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Session.logoutUser(MyApplication.getAppContext());
//                Intent intent = new Intent(ProfileActivity.this, LogInActivity.class);
//                startActivity(intent);
//                finish();

                final Dialog dialog = new Dialog(ProfileActivity.this);
                dialog.setContentView(R.layout.custom_dialog_warning_for_logout);

                TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
                TextView tvDetails = (TextView) dialog.findViewById(R.id.tvDetails);
                TextView tvYes = (TextView) dialog.findViewById(R.id.tvYes);
                TextView tvNo = (TextView) dialog.findViewById(R.id.tvNo);


                tvTitle.setTypeface(CustomFont.setSemiBold(getApplicationContext()));
                tvDetails.setTypeface(CustomFont.setRegular(getApplicationContext()));
                tvYes.setTypeface(CustomFont.setSemiBold(getApplicationContext()));
                tvNo.setTypeface(CustomFont.setSemiBold(getApplicationContext()));

                tvYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        Session.logoutUser(MyApplication.getAppContext());
                        Intent intent = new Intent(ProfileActivity.this, LogInActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                tvNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });

    }

    private void getDetails() {

        tvName.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_NAME));
        tvEmail.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_EMAIL));
        tvShopName.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_SHOPNAME));
        tvActiveDate.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_ACTIVATE_DATE));
        tvExpiryDate.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_EXPIRY_DATE));
        tvMonthlyFee.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_MONTHLY_FEE));
        //tvBaseUrl.setText(Session.getStringFromSharePreference(getApplicationContext(),AppConstant.LOGIN_RESPONSE_BASE_URL));

    }
}
