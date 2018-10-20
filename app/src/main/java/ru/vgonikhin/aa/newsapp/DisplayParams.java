package ru.vgonikhin.aa.newsapp;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

class DisplayParams {
    static int getDisplayWidthInDp(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float density = activity.getResources().getDisplayMetrics().density;
        return (int) (outMetrics.widthPixels / density);
    }

}
