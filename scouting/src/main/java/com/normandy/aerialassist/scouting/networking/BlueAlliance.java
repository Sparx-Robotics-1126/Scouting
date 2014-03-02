package com.normandy.aerialassist.scouting.networking;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by jbass on 3/1/14.
 */
public class BlueAlliance {
    private static final String TAG = "";
    private static final String BASE_URL = "www.thebluealliance.com";
    private static final String GET_EVENT_LIST = "/api/v2/events/{YEAR}";


    private Context context;
    private Ion ion;
    private String versionName;

    public BlueAlliance(Context context){
        this.context = context;
        ion = Ion.getDefault(context);
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            Log.e(TAG, "Could not find version name.", e);
            versionName = "UNKNOWN";
        }
    }

    public void getEvents(int year){
        String request = (BASE_URL+GET_EVENT_LIST).replace("{YEAR}", Integer.toString(year));
        ion.build(context, request)
                .addHeader("X-TBA-App-Id", "frc1126:scouting-app-2014:" + versionName)
                .as(new TypeToken<List<String>>(){})
                .setCallback(new FutureCallback<List<String>>() {
                    @Override
                    public void onCompleted(Exception e, List<String> result) {

                    }
                });

    }

}
