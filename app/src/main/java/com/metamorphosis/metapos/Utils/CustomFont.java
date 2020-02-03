package com.metamorphosis.metapos.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by metakave-android-PC on 23-Jan-19.
 */

public class CustomFont {


    public static Typeface setSemiBold(Context mContext) {

        Typeface fontSemiBold = Typeface.createFromAsset(mContext.getAssets(), "TitilliumWeb_SemiBold.ttf");

        return fontSemiBold;
    }

    public static Typeface setRegular(Context mContext) {

        Typeface fontRegular = Typeface.createFromAsset(mContext.getAssets(), "TitilliumWeb_Regular.ttf");

        return fontRegular;
    }

//    public static Typeface setVerdana(Context mContext) {
//
//        Typeface fontRegular = Typeface.createFromAsset(mContext.getAssets(), "verdana.ttf");
//
//        return fontRegular;
//    }
//
//    public static Typeface setVerdanaBold(Context mContext) {
//
//        Typeface fontRegular = Typeface.createFromAsset(mContext.getAssets(), "verdana_bold.ttf");
//
//        return fontRegular;
//    }

}
