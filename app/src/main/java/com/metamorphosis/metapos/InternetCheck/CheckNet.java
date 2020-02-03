package com.metamorphosis.metapos.InternetCheck;


import com.metamorphosis.metapos.Activity.Application.MyApplication;

public class CheckNet {

    private static MobileInternetConnectionDetector cd;
    private static WIFIInternetConnectionDetector wcd;
    private static Boolean isConnectionExist = false;
    private static Boolean isConnectionExistWifi = false;
    private static boolean netStatus = false;

    public static boolean isNetConnected() {

        cd = new MobileInternetConnectionDetector(MyApplication.getAppContext());
        wcd = new WIFIInternetConnectionDetector(MyApplication.getAppContext());
        isConnectionExist = cd.checkMobileInternetConn();
        isConnectionExistWifi = wcd.checkMobileInternetConn();

        if (isConnectionExistWifi || isConnectionExist) {
            netStatus = true;

        } else {
            netStatus = false;
            //Toast.makeText(MyApplication.getAppContext(), "No Internet!", Toast.LENGTH_LONG).show();
        }
        return netStatus;
    }
}
