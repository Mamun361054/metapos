package com.metamorphosis.metapos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.metamorphosis.metapos.Api.ApiClient;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.InternetCheck.CheckNet;
import com.metamorphosis.metapos.Model.LogInPojo;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.session.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    private EditText etPhone, etPass, etShop;
    private Button btnLogIn;
    private TextInputLayout textInputEmail, textInputShopName;
    private TextInputLayout textInputPass;

    private ApiInterface apiInterface, apiInterface2;

    private String login, passKey, shopName, status;

    private String TAG = "LogInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textInputEmail = (TextInputLayout) findViewById(R.id.textInputEmail);
        textInputPass = (TextInputLayout) findViewById(R.id.textInputPass);
        textInputShopName = (TextInputLayout) findViewById(R.id.textInputShop);
        etPhone = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPassword);
        etShop = (EditText) findViewById(R.id.etShopName);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);

        isLoggedIn();
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            login = emailInput;
            textInputEmail.setError(null);
            return true;
        }
    }
    private boolean validateShopName() {
        String shopInput = textInputShopName.getEditText().getText().toString().trim();
        if (shopInput.isEmpty()) {
            textInputShopName.setError("Field can't be empty");
            return false;
        } else {
            shopName = shopInput;
            textInputShopName.setError(null);
            return true;
        }
    }


    private boolean validatePass() {
        String passInput = textInputPass.getEditText().getText().toString().trim();
        if (passInput.isEmpty()) {
            textInputPass.setError("Field can't be empty");
            return false;
        } else {
            passKey = passInput;
            textInputPass.setError(null);
            return true;
        }
    }

    public void confirmInput(View view) {

        if (!validateEmail() | !validatePass() | !validateShopName()) {
            return;
        }

        //check internet connection
//        if (CheckNet.isNetConnected()) {

            String email = login;
            String password = passKey;
            String shop_name = shopName;
            //String inputParams = "[('login','=','swapon.tee@gmail.com'),('passkey','=','$pbkdf2-sha512$25000$I4Sw9l4LASBESIkxBsDY2w$Z5XvqprAjiKgJw799aHhJ1GJRGdgQZAI1XXPQG/NPH8rRmx/XI9WfI/l6koltzsaff1WvEEoGsZFluXWAu.XXQ'),('customer','=',True)]";
            try {

                apiInterface2 = ApiClient.getApiClient().create(ApiInterface.class);
                Call<List<LogInPojo>> call2 = apiInterface2.getUser(email, password, shop_name);
                call2.enqueue(new Callback<List<LogInPojo>>() {
                    @Override
                    public void onResponse(Call<List<LogInPojo>> call2, Response<List<LogInPojo>> response) {
                        Log.i(TAG, "Status: " + response.body().get(0).getStatus());

                        status = response.body().get(0).getStatus();
                        Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_STATUS, response.body().get(0).getStatus());


                        Log.i(TAG, "Header: " + response.headers());
                        Log.i(TAG, "Error Body: " + response.errorBody());
                        Log.i(TAG, "Message: " + response.message());
                        Log.i(TAG, "Raw: " + response.raw());
//                                Log.i(TAG, "Body: " + response.body().string());
                        Log.i(TAG, "email: " + response.body().get(0).getData().get(0).getEmail());
                        Log.i(TAG, "RoleId: " + response.body().get(0).getData().get(0).getRoleId());
                        Log.i(TAG, "Title: " + response.body().get(0).getData().get(0).getTitle());
                        Log.i(TAG, "BaseUrl: " + response.body().get(0).getData().get(0).getBaseurl());
                        Log.i(TAG, "StoreId: " + response.body().get(0).getData().get(0).getStoreId());


                        if(status.equals("200")) {
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.IS_LOGGED_IN, true);
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.IS_FIRST_TIME, true);
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_EMAIL, response.body().get(0).getData().get(0).getEmail());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_ROLE_ID, response.body().get(0).getData().get(0).getRoleId());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_SHOPNAME, response.body().get(0).getData().get(0).getShopname());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_NAME, response.body().get(0).getData().get(0).getTitle());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_BASE_URL, response.body().get(0).getData().get(0).getBaseurl());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_ACTIVATE_DATE, response.body().get(0).getData().get(0).getActivedate());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_EXPIRY_DATE, response.body().get(0).getData().get(0).getExpirydate());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_MONTHLY_FEE, response.body().get(0).getData().get(0).getMonthlyfee());
                            Session.saveDataAtSharePreference(getApplicationContext(), AppConstant.LOGIN_RESPONSE_STORE_ID, response.body().get(0).getData().get(0).getStoreId());

                            Intent intent = new Intent(LogInActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(LogInActivity.this, "Please enter valid password and email!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<LogInPojo>> call, Throwable t) {

                    }
                });
            } catch (Exception e) {
                Toast.makeText(LogInActivity.this, "Failed to connect!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failed to playing game!");
            }
//        }
//        else {
//            Toast.makeText(this, "No internet connection! Please turn on internet.", Toast.LENGTH_SHORT).show();
//        }

    }

    private void isLoggedIn() {

        boolean isLoggedIn = Session.getBooleanFromSharePreference(this, AppConstant.IS_LOGGED_IN);
        if (isLoggedIn) {
            Log.d(TAG, "Logged in status true");

//            if (key.equals("account")) {
//                Intent intent = new Intent(this, AccountActivity.class);
//                intent.putExtra("sub_total", String.valueOf(subTotal));
//                startActivity(intent);
//            } else {
//                Intent intent = new Intent(this, CheckoutActivity.class);
//                intent.putExtra("sub_total", String.valueOf(subTotal));
//                startActivity(intent);
//            }
            Intent intent = new Intent(this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d(TAG, "Logged in status false");
            //AuthKeyAndSecret.getAuthKeyAndSecret();
        }
    }
}
