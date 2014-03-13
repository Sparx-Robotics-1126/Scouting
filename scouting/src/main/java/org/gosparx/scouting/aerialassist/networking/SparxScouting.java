package org.gosparx.scouting.aerialassist.networking;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.dto.Scouting;

import java.util.List;

/**
 * Created by jbass on 3/7/14.
 */
public class SparxScouting {
    private static final String TAG = "SparxScouting";
    private static final String BASE_URL = "http://sparx-scouting-service.appspot.com";
    private static final String POST_SCOUTING = "/api/2014/v1/ScoutingData";
    private static final String GET_SCOUTING_BY_TEAM = "/api/2014/v1/ScoutingData/{TEAM_KEYs}";
    private static final String GET_SCOUTING_BY_TEAM_EVENT = "/api/2014/v1/ScoutingData/{TEAM_KEY}/{EVENT_KEY}";
    private static final String GET_SCOUTING_BY_TEAM_EVENT_MATCH = "/api/2014/v1/ScoutingData/{TEAM_KEY}/{EVENT_KEY}/{MATCH_KEY}";

    private Context context;
    private Ion ion;
    private DatabaseHelper dbHelper;

    public SparxScouting(Context context){
        this.context = context;
        ion = Ion.getInstance(context, TAG);
        ion.configure().setLogging(TAG, Log.DEBUG);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        ion.configure().setGson(gson);
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void postAllScouting() {
        List<Scouting> scoutingList = dbHelper.getAllScoutingNeedingSyncing();
        String request = (BASE_URL + POST_SCOUTING);

        for (final Scouting scouting : scoutingList) {
            ion.build(context, request)
                    .setJsonObjectBody(scouting)
                    .asString().setCallback(new FutureCallback<String>() {
                @Override
                public void onCompleted(Exception e, String result) {
                    if (e != null) {
                        Log.e(TAG, "Issue saving to Server!", e);
                    }else {
                        dbHelper.setDoneSyncing(scouting);
                    }
                }
            });
        }
    }
}
