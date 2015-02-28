package org.gosparx.scouting.aerialassist.networking;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.dto.Match;
import org.gosparx.scouting.aerialassist.dto.Scouting;
import org.gosparx.scouting.aerialassist.dto.Team;

import java.util.List;

/**
 * Created by jbass on 3/7/14.
 */
public class SparxScouting {
    private static final String TAG = "SparxScouting";
    private static final String BASE_URL = "http://sparx-scouting-2015.appspot.com";
    private static final String POST_SCOUTING = "/api/2015/v1/ScoutingData";
    private static final String GET_SCOUTING_BY_TEAM = "/api/2015/v1/ScoutingData/{TEAM_KEY}";
    private static final String GET_SCOUTING_BY_TEAM_EVENT = "/api/2015/v1/ScoutingData/{TEAM_KEY}/{EVENT_KEY}";
    private static final String GET_SCOUTING_BY_TEAM_EVENT_MATCH = "/api/2015/v1/ScoutingData/{TEAM_KEY}/{EVENT_KEY}/{MATCH_KEY}";

    private Context context;
    private Ion ion;
    private DatabaseHelper dbHelper;

    public static SparxScouting sparxScouting;
    public static synchronized SparxScouting getInstance(Context context){
        if(sparxScouting == null)
            sparxScouting = new SparxScouting(context);
        return sparxScouting;
    }

    private SparxScouting(Context context){
        this.context = context;
        ion = Ion.getInstance(context, TAG);
        ion.configure().setLogging(TAG, Log.DEBUG);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        ion.configure().setGson(gson);
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void postAllScouting(final NetworkCallback callback) {
        final List<Scouting> scoutingList = dbHelper.getAllScoutingNeedingSyncing();
        String request = (BASE_URL + POST_SCOUTING);
        if(scoutingList.isEmpty())
            callback.handleFinishDownload(true);
        final NetworkCallback subCallback = new NetworkCallback() {
            int size = scoutingList.size();
            boolean hasFailed = false;
            @Override
            public void handleFinishDownload(boolean success) {
                if(hasFailed) {
                    return;
                }
                if(!success) {
                    callback.handleFinishDownload(false);
                    hasFailed = true;
                    return;
                }
                size -= 1;
                if(size <= 0) {
                    callback.handleFinishDownload(true);
                }
            }
        };

        for (final Scouting scouting : scoutingList) {
            ion.build(context, request)
                    .setJsonObjectBody(scouting)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            if (e != null) {
                                Log.e(TAG, "Issue saving to Server!", e);
                                System.out.println("Server Error");
                                subCallback.handleFinishDownload(false);
                            } else {
                                dbHelper.setDoneSyncing(scouting);
                                System.out.println("Uploading");
                                subCallback.handleFinishDownload(true);
                            }
                        }
                    });
        }
    }

    public void getScouting(Team team, NetworkCallback callback){
        String request = (BASE_URL + GET_SCOUTING_BY_TEAM).replace("{TEAM_KEY}", team.getKey());
        getScouting(request, callback);
    }

    public void getScouting(Team team, Event event){
        String request = (BASE_URL + GET_SCOUTING_BY_TEAM_EVENT)
                .replace("{TEAM_KEY}", team.getKey())
                .replace("{EVENT_KEY}", event.getKey());
        getScouting(request, null);
    }

    public void getScouting(Team team, Event event, Match match){
        String request = (BASE_URL + GET_SCOUTING_BY_TEAM_EVENT_MATCH)
                .replace("{TEAM_KEY}", team.getKey())
                .replace("{EVENT_KEY}", event.getKey())
                .replace("{MATCH_KEY}", match.getKey());
        getScouting(request, null);
    }

    private void getScouting(String request, final NetworkCallback callback){
        ion.build(context, request)
        .as(new TypeToken<List<Scouting>>(){})
        .setCallback(new FutureCallback<List<Scouting>>() {
            @Override
            public void onCompleted(Exception e, List<Scouting> result) {
                if (e != null) {
                    Log.e(TAG, "Issue getting scouting data.", e);
                    callback.handleFinishDownload(false);
                    return;
                }

                for (Scouting sd : result) {
                    if (dbHelper.doesScoutingExist(sd))
                        dbHelper.updateScouting(sd);
                    else
                        dbHelper.createScouting(sd);
                }
                callback.handleFinishDownload(true);
            }
        });
    }
}
