package org.gosparx.scouting.aerialassist.networking;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.dto.Match;
import org.gosparx.scouting.aerialassist.dto.Team;

import java.util.List;

/**
 * Created by jbass on 3/1/14.
 */
public class BlueAlliance {
    private static final String TAG = "BlueAlliance";
    private static final String BASE_URL = "http://www.thebluealliance.com";
    private static final String GET_EVENT_LIST = "/api/v2/events/{YEAR}";
    private static final String GET_EVENT = "/api/v2/event/{EVENT_KEY}";
    private static final String GET_MATCH_LIST = "/api/v2/event/{EVENT_KEY}/matches";
    private static final String GET_TEAM_LIST = "/api/v2/event/{EVENT_KEY}/teams";

    private Context context;
    private Ion ion;
    private String versionName;
    private DatabaseHelper dbHelper;

    public BlueAlliance(Context context){
        this.context = context;
        ion = Ion.getDefault(context);
        ion.configure().setLogging(TAG, Log.DEBUG);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        ion.configure().setGson(gson);
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            Log.e(TAG, "Could not find version name.", e);
            versionName = "UNKNOWN";
        }
        dbHelper = new DatabaseHelper(context);
    }

    public void loadEvents(final int year){
        String request = (BASE_URL+GET_EVENT_LIST).replace("{YEAR}", Integer.toString(year));
        ion.build(context, request)
                .addHeader("X-TBA-App-Id", "frc1126:scouting-app-2014:" + versionName)
                .as(new TypeToken<List<String>>(){})
                .setCallback(new FutureCallback<List<String>>() {
                    @Override
                    public void onCompleted(Exception e, List<String> eventKeys) {
                        if(e != null){
                            Log.e(TAG, "Issue getting event list", e);
                            return;
                        }
                        for(String eventKey : eventKeys){
                            loadEvent(eventKey);
                        }
                    }
                });
    }

    public void loadEvent(final String eventCode){
        String request = (BASE_URL+GET_EVENT).replace("{EVENT_KEY}", eventCode);
        ion.build(context, request)
                .addHeader("X-TBA-App-Id", "frc1126:scouting-app-2014:" + versionName)
                .as(new TypeToken<Event>(){})
                .setCallback(new FutureCallback<Event>() {
                    @Override
                    public void onCompleted(Exception e, Event event) {
                        if(e != null){
                            Log.e(TAG, "Issue getting event("+eventCode+")", e);
                            return;
                        }
                        if(dbHelper.doesEventExist(event))
                            dbHelper.updateEvent(event);
                        else
                            dbHelper.createEvent(event);
                    }
                });
    }

    public void loadMatches(final Event event){
        String request = (BASE_URL+GET_MATCH_LIST).replace("{EVENT_KEY}", event.getKey());
        ion.build(context, request)
                .addHeader("X-TBA-App-Id", "frc1126:scouting-app-2014:" + versionName)
                .as(new TypeToken<List<Match>>(){})
                .setCallback(new FutureCallback<List<Match>>() {
                    @Override
                    public void onCompleted(Exception e, List<Match> result) {
                        if( e != null){
                            Log.e(TAG, "Issue getting matches from event("+event.getKey()+")", e);
                            return;
                        }
                        for (Match match : result) {
                            if (dbHelper.doesMatchExist(match))
                                dbHelper.updateMatch(match);
                            else
                                dbHelper.createMatch(match);
                        }
                    }
                });
    }

    public void loadTeams(final Event event){
        String request = (BASE_URL+GET_TEAM_LIST).replace("{EVENT_KEY}", event.getKey());
        ion.build(context, request)
                .addHeader("X-TBA-App-Id", "frc1126:scouting-app-2014:" + versionName)
                .as(new TypeToken<List<Team>>(){})
                .setCallback(new FutureCallback<List<Team>>() {
                    @Override
                    public void onCompleted(Exception e, List<Team> result) {
                        if(e != null || result == null){
                            Log.e(TAG, "Issue getting teams from event("+event.getKey()+")", e);
                            return;
                        }
                        for(Team team : result){
                            if(dbHelper.doesTeamExist(team))
                                dbHelper.updateTeam(team);
                            else
                                dbHelper.createTeam(team);

                            dbHelper.createE2TAssociation(event.getKey(), team.getKey());
                        }
                    }
                });
    }

}
