package com.metamorphosis.metapos.Api;

import com.metamorphosis.metapos.Model.AccountsPojo;
import com.metamorphosis.metapos.Model.BarChartPojo;
import com.metamorphosis.metapos.Model.LogInPojo;
import com.metamorphosis.metapos.Model.PieChartPojo;
import com.metamorphosis.metapos.Model.SalesPojo;
import com.metamorphosis.metapos.Model.StoreListPojo;
import com.metamorphosis.metapos.Utils.UrlSettings;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(UrlSettings.BAR_GROWTH)
    Call<List<BarChartPojo>> getBarGrowth(@Query("storeid") String storeId,
                                          @Query("shopname") String shopName);

    @GET(UrlSettings.PIE_GROWTH)
    Call<List<PieChartPojo>> getPieGrowth(@Query("storeid") String storeId,
                                          @Query("shopname") String shopName);

    @GET(UrlSettings.SALES_GROWTH)
    Call<List<SalesPojo>> getSalesGrowth(@Query("startdate") String startDate,
                                         @Query("enddate") String endDate,
                                         @Query("storeid") String storeId,
                                         @Query("shopname") String shopName);
    @GET(UrlSettings.STORE_LIST)
    Call<List<StoreListPojo>> getStoreList(@Query("roleId") String roleId,
                                           @Query("shopname") String shopName);
    @GET(UrlSettings.ACCOUNTS_GROWTH)
    Call<List<AccountsPojo>> getAccountsGrowth(@Query("startdate") String startDate,
                                               @Query("enddate") String endDate,
                                               @Query("storeid") String storeId,
                                               @Query("shopname") String shopName);

    @GET(UrlSettings.LOGIN_USER)
    Call<List<LogInPojo>> getUser(@Query("email") String email,
                                  @Query("password") String password,
                                  @Query("shopname") String shop_name);
}
