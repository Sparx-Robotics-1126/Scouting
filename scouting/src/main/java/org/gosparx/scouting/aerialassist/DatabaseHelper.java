package org.gosparx.scouting.aerialassist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.util.Log;

import org.gosparx.scouting.aerialassist.dto.Alliance;
import org.gosparx.scouting.aerialassist.dto.Alliances;
import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.dto.Match;
import org.gosparx.scouting.aerialassist.dto.Scouting;
import org.gosparx.scouting.aerialassist.dto.ScoutingAuto;
import org.gosparx.scouting.aerialassist.dto.ScoutingGeneral;
import org.gosparx.scouting.aerialassist.dto.ScoutingTele;
import org.gosparx.scouting.aerialassist.dto.Team;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jbass on 3/1/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "arialAssist";

    // Table Names
    private static final String TABLE_EVENTS = "events";
    private static final String TABLE_TEAMS = "teams";
    private static final String TABLE_E2T = "events_to_teams";
    private static final String TABLE_MATCHES = "matches";
    private static final String TABLE_SCOUTING = "scouting";

    // Events Column Names
    private static final String TABLE_EVENTS_KEY = "key";
    private static final String TABLE_EVENTS_NAME = "name";
    private static final String TABLE_EVENTS_SHORT_NAME = "short_name";
    private static final String TABLE_EVENTS_EVENT_CODE = "event_code";
    private static final String TABLE_EVENTS_EVENT_TYPE_STRING = "event_type_string";
    private static final String TABLE_EVENTS_EVENT_TYPE = "event_type";
    private static final String TABLE_EVENTS_YEAR = "year";
    private static final String TABLE_EVENTS_LOCATION = "location";
    private static final String TABLE_EVENTS_OFFICIAL = "official";
    private static final String TABLE_EVENTS_START_DATE = "start_date";
    private static final String TABLE_EVENTS_END_DATE = "end_date";

    // Teams Column Names
    private static final String TABLE_TEAMS_KEY = "key";
    private static final String TABLE_TEAMS_NAME = "name";
    private static final String TABLE_TEAMS_NICKNAME = "nickname";
    private static final String TABLE_TEAMS_TEAM_NUMBER = "team_number";
    private static final String TABLE_TEAMS_WEBSITE = "website";
    private static final String TABLE_TEAMS_CITY = "city";
    private static final String TABLE_TEAMS_STATE = "state";
    private static final String TABLE_TEAMS_COUNTRY = "county";
    private static final String TABLE_TEAMS_LOCATION = "location";

    // Events to Teams Column Names
    private static final String TABLE_E2T_KEY = "key";
    private static final String TABLE_E2T_EVENT = "event";
    private static final String TABLE_E2T_TEAM = "team";

    // Matches Column Names
    private static final String TABLE_MATCHES_KEY = "key";
    private static final String TABLE_MATCHES_EVENT_KEY = "event_key";
    private static final String TABLE_MATCHES_MATCH_NUMBER = "match_number";
    private static final String TABLE_MATCHES_SET_NUMBER = "set_number";
    private static final String TABLE_MATCHES_COMP_LEVEL = "comp_level";
    private static final String TABLE_MATCHES_BLUE_SCORE = "blue_score";
    private static final String TABLE_MATCHES_BLUE_ONE = "blue_one";
    private static final String TABLE_MATCHES_BLUE_TWO = "blue_two";
    private static final String TABLE_MATCHES_BLUE_THREE = "blue_three";
    private static final String TABLE_MATCHES_RED_SCORE = "red_score";
    private static final String TABLE_MATCHES_RED_ONE = "red_one";
    private static final String TABLE_MATCHES_RED_TWO = "red_two";
    private static final String TABLE_MATCHES_RED_THREE = "red_three";

    // Scouting Column Names
    private static final String TABLE_SCOUTING_KEY = "key";
    private static final String TABLE_SCOUTING_LAST_UPDATE = "last_updated";
    private static final String TABLE_SCOUTING_LAST_SYNC = "last_sync";
    private static final String TABLE_SCOUTING_TEAM_KEY = "team_key";
    private static final String TABLE_SCOUTING_EVENT_KEY = "event_key";
    private static final String TABLE_SCOUTING_MATCH_KEY = "match_key";
    private static final String TABLE_SCOUTING_NAME = "scouter_name";
    private static final String TABLE_SCOUTING_AUTO_STARTING_LOCATION_X = "auto_starting_location_x";
    private static final String TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y = "auto_starting_location_y";
    private static final String TABLE_SCOUTING_AUTO_BALLS_ACQUIRED = "auto_balls_acquired";
    private static final String TABLE_SCOUTING_AUTO_STARTED_WITH_BALL = "auto_started_with_ball";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SHOT = "auto_balls_shot";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SCORED = "auto_balls_scored";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_HIGH = "auto_balls_scored_hot_high";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_LOW = "auto_balls_scored_hot_low";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SCORED_HIGH = "auto_balls_scored_high";
    private static final String TABLE_SCOUTING_AUTO_BALLS_SCORED_LOW = "auto_balls_scored_low";
    private static final String TABLE_SCOUTING_AUTO_ENDING_LOCATION_X = "auto_ending_location_x";
    private static final String TABLE_SCOUTING_AUTO_ENDING_LOCATION_Y = "auto_ending_location_y";
    private static final String TABLE_SCOUTING_TELE_ACQUIRED_FROM_FLOOR = "tele_acq_floor";
    private static final String TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_FLOOR = "tele_assist_floor";
    private static final String TABLE_SCOUTING_TELE_ACQUIRED_FROM_HUMAN = "tele_acq_human";
    private static final String TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_HUMAN = "tele_assist_human";
    private static final String TABLE_SCOUTING_TELE_SHOT_HIGH = "tele_shot_high";
    private static final String TABLE_SCOUTING_TELE_SCORED_HIGH = "tele_scored_high";
    private static final String TABLE_SCOUTING_TELE_SHOT_LOW = "tele_shot_low";
    private static final String TABLE_SCOUTING_TELE_SCORED_LOW = "tele_scored_low";
    private static final String TABLE_SCOUTING_TELE_CAUGHT_TRUSS = "tele_caught_truss";
    private static final String TABLE_SCOUTING_TELE_THROWN_TRUSS = "tele_thrown_truss";
    private static final String TABLE_SCOUTING_TELE_STAYED_ZONE = "tele_stayed_zone";
    private static final String TABLE_SCOUTING_GENERAL_PLAYS_DEFENSE = "plays_defense";
    private static final String TABLE_SCOUTING_GENERAL_NUM_PENALITES = "num_penalties";
    private static final String TABLE_SCOUTING_GENERAL_COMMENTS_PENALTIES = "comment_penalties";
    private static final String TABLE_SCOUTING_GENERAL_NUM_TECH_FOULS = "num_tech_fouls";
    private static final String TABLE_SCOUTING_GENERAL_COMMENTS_TECH_FOULS = "comment_tech_fouls";
    private static final String TABLE_SCOUTING_GENERAL_COMMENTS = "general_comments";

    // Create Tables
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_EVENTS + "("
            + TABLE_EVENTS_KEY + " TEXT PRIMARY KEY, "
            + TABLE_EVENTS_NAME + " TEXT, "
            + TABLE_EVENTS_SHORT_NAME + " TEXT, "
            + TABLE_EVENTS_EVENT_CODE + " TEXT, "
            + TABLE_EVENTS_EVENT_TYPE_STRING + " TEXT, "
            + TABLE_EVENTS_EVENT_TYPE + " INTEGER, "
            + TABLE_EVENTS_YEAR + " INTEGER, "
            + TABLE_EVENTS_LOCATION + " TEXT, "
            + TABLE_EVENTS_OFFICIAL + " INTEGER, "
            + TABLE_EVENTS_START_DATE + " TEXT, "
            + TABLE_EVENTS_END_DATE + " TEXT)";

    private static final String CREATE_TABLE_TEAMS = "CREATE TABLE " + TABLE_TEAMS + "("
            + TABLE_TEAMS_KEY + " TEXT PRIMARY KEY, "
            + TABLE_TEAMS_TEAM_NUMBER + " INTEGER, "
            + TABLE_TEAMS_NAME + " TEXT, "
            + TABLE_TEAMS_NICKNAME + " TEXT, "
            + TABLE_TEAMS_WEBSITE + " TEXT, "
            + TABLE_TEAMS_CITY + " TEXT, "
            + TABLE_TEAMS_STATE + " TEXT, "
            + TABLE_TEAMS_COUNTRY + " TEXT, "
            + TABLE_TEAMS_LOCATION + " TEXT)";

    private static final String CREATE_TABLE_E2T = "CREATE TABLE " + TABLE_E2T + "("
            + TABLE_E2T_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_E2T_EVENT + " TEXT, "
            + TABLE_E2T_TEAM + " TEXT)";

    private static final String CREATE_TABLE_MATCH = "CREATE TABLE " + TABLE_MATCHES + "("
            + TABLE_MATCHES_KEY + " TEXT PRIMARY KEY, "
            + TABLE_MATCHES_EVENT_KEY + " TEXT, "
            + TABLE_MATCHES_MATCH_NUMBER + " INTEGER, "
            + TABLE_MATCHES_SET_NUMBER + " INTEGER, "
            + TABLE_MATCHES_COMP_LEVEL + " TEXT, "
            + TABLE_MATCHES_RED_SCORE + " INTEGER, "
            + TABLE_MATCHES_RED_ONE + " TEXT, "
            + TABLE_MATCHES_RED_TWO + " TEXT, "
            + TABLE_MATCHES_RED_THREE + " TEXT, "
            + TABLE_MATCHES_BLUE_SCORE + " INTEGER, "
            + TABLE_MATCHES_BLUE_ONE + " TEXT, "
            + TABLE_MATCHES_BLUE_TWO + " TEXT, "
            + TABLE_MATCHES_BLUE_THREE + " TEXT)";

    private static final String CREATE_TABLE_SCOUTING = "CREATE TABLE " + TABLE_SCOUTING + "("
            + TABLE_SCOUTING_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_SCOUTING_LAST_UPDATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + TABLE_SCOUTING_LAST_SYNC + " DATETIME, "
            + TABLE_SCOUTING_TEAM_KEY + " TEXT, "
            + TABLE_SCOUTING_EVENT_KEY + " TEXT, "
            + TABLE_SCOUTING_MATCH_KEY + " TEXT, "
            + TABLE_SCOUTING_NAME + " TEXT, "
            + TABLE_SCOUTING_AUTO_STARTING_LOCATION_X + " INTEGER, "
            + TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y + " INTEGER, "
            + TABLE_SCOUTING_AUTO_STARTED_WITH_BALL + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_ACQUIRED + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SHOT + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SCORED + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_HIGH + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_LOW + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SCORED_HIGH + " INTEGER, "
            + TABLE_SCOUTING_AUTO_BALLS_SCORED_LOW + " INTEGER, "
            + TABLE_SCOUTING_AUTO_ENDING_LOCATION_X + " INTEGER, "
            + TABLE_SCOUTING_AUTO_ENDING_LOCATION_Y + " INTEGER, "
            + TABLE_SCOUTING_TELE_ACQUIRED_FROM_FLOOR + " INTEGER, "
            + TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_FLOOR + " INTEGER, "
            + TABLE_SCOUTING_TELE_ACQUIRED_FROM_HUMAN + " INTEGER, "
            + TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_HUMAN + " INTEGER, "
            + TABLE_SCOUTING_TELE_SHOT_HIGH + " INTEGER, "
            + TABLE_SCOUTING_TELE_SCORED_HIGH + " INTEGER, "
            + TABLE_SCOUTING_TELE_SHOT_LOW + " INTEGER, "
            + TABLE_SCOUTING_TELE_SCORED_LOW + " INTEGER, "
            + TABLE_SCOUTING_TELE_CAUGHT_TRUSS + " INTEGER, "
            + TABLE_SCOUTING_TELE_THROWN_TRUSS + " INTEGER, "
            + TABLE_SCOUTING_TELE_STAYED_ZONE + " TEXT, "
            + TABLE_SCOUTING_GENERAL_PLAYS_DEFENSE + " INTEGER, "
            + TABLE_SCOUTING_GENERAL_NUM_PENALITES + " INTEGER, "
            + TABLE_SCOUTING_GENERAL_COMMENTS_PENALTIES + " TEXT, "
            + TABLE_SCOUTING_GENERAL_NUM_TECH_FOULS + " INTEGER, "
            + TABLE_SCOUTING_GENERAL_COMMENTS_TECH_FOULS + " TEXT, "
            + TABLE_SCOUTING_GENERAL_COMMENTS + " TEXT)";

    public static SimpleDateFormat ISO6701_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getDateTime(){return ISO6701_FORMAT.format(new Date());}

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_E2T);
        db.execSQL(CREATE_TABLE_MATCH);
        db.execSQL(CREATE_TABLE_SCOUTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        throw new NoSuchMethodError();
    }

    private ContentValues mapEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(TABLE_EVENTS_KEY, event.getKey());
        values.put(TABLE_EVENTS_NAME, event.getName());
        values.put(TABLE_EVENTS_SHORT_NAME, event.getShortName());
        values.put(TABLE_EVENTS_EVENT_CODE, event.getEventCode());
        values.put(TABLE_EVENTS_EVENT_TYPE_STRING, event.getEventTypeString());
        values.put(TABLE_EVENTS_EVENT_TYPE, event.getEventType());
        values.put(TABLE_EVENTS_YEAR, event.getYear());
        values.put(TABLE_EVENTS_LOCATION, event.getLocation());
        values.put(TABLE_EVENTS_OFFICIAL, event.isOfficial());
        values.put(TABLE_EVENTS_START_DATE, ISO6701_FORMAT.format(event.getStartDate()));
        values.put(TABLE_EVENTS_END_DATE, ISO6701_FORMAT.format(event.getEndDate()));

        return values;
    }

    public void createEvent(Event event){
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_EVENTS, null, mapEvent(event));
    }

    public boolean doesEventExist(Event event){
        boolean retVal = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_EVENTS, new String[]{TABLE_EVENTS_KEY},
                TABLE_EVENTS_KEY + " = ?", new String[]{event.getKey()}, null, null, null);

        if(c != null && c.getCount() > 0)
            retVal = true;

        return retVal;
    }

    public void updateEvent(Event event){
        SQLiteDatabase db = getWritableDatabase();

        db.update(TABLE_EVENTS, mapEvent(event), TABLE_EVENTS_KEY + " = ?", new String[]{event.getKey()});
    }

    public Event getEvent(String eventKey){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS
                + " WHERE " + TABLE_EVENTS_KEY + " = ?";

        Cursor c = db.rawQuery(selectQuery, new String[]{eventKey});

        Event event = new Event();
        if(c != null && c.moveToNext()){
            event.setKey(c.getString(c.getColumnIndex(TABLE_EVENTS_KEY)));
            event.setName(c.getString(c.getColumnIndex(TABLE_EVENTS_NAME)));
            event.setShortName(c.getString(c.getColumnIndex(TABLE_EVENTS_SHORT_NAME)));
            event.setEventCode(c.getString(c.getColumnIndex(TABLE_EVENTS_EVENT_CODE)));
            event.setEventTypeString(c.getString(c.getColumnIndex(TABLE_EVENTS_EVENT_TYPE_STRING)));
            event.setEventType(c.getInt(c.getColumnIndex(TABLE_EVENTS_EVENT_TYPE)));
            event.setYear(c.getInt(c.getColumnIndex(TABLE_EVENTS_YEAR)));
            event.setLocation(c.getString(c.getColumnIndex(TABLE_EVENTS_LOCATION)));
            event.setOfficial(c.getInt(c.getColumnIndex(TABLE_EVENTS_OFFICIAL)) == 1);
            try{
                event.setStartDate(ISO6701_FORMAT.parse(c.getString(c.getColumnIndex(TABLE_EVENTS_START_DATE))));
            } catch (ParseException e) {
                Log.e(TAG, "Could not parse Event's start date.", e);
            }try{
                event.setEndDate(ISO6701_FORMAT.parse(c.getString(c.getColumnIndex(TABLE_EVENTS_END_DATE))));
            } catch (ParseException e) {
                Log.e(TAG, "Could not parse Event's end date.", e);
            }
        }

        return event;
    }

    public Cursor createEventNameCursor(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(true, TABLE_EVENTS, new String[]{"rowid AS _id, key", TABLE_EVENTS_SHORT_NAME, TABLE_EVENTS_START_DATE},
                null, null, null, null, TABLE_EVENTS_START_DATE + " ASC", null);
    }

    private ContentValues mapTeam(Team team){
        ContentValues values = new ContentValues();
        values.put(TABLE_TEAMS_KEY, team.getKey());
        values.put(TABLE_TEAMS_NAME, team.getName());
        values.put(TABLE_TEAMS_NICKNAME, team.getNickname());
        values.put(TABLE_TEAMS_TEAM_NUMBER, team.getTeamNumber());
        values.put(TABLE_TEAMS_WEBSITE, team.getWebsite());
        values.put(TABLE_TEAMS_CITY, team.getCity());
        values.put(TABLE_TEAMS_STATE, team.getState());
        values.put(TABLE_TEAMS_COUNTRY, team.getCountry());
        values.put(TABLE_TEAMS_LOCATION, team.getLocation());
        return values;
    }

    public void createTeam(Team team){
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_TEAMS, null, mapTeam(team));
    }

    public boolean doesTeamExist(Team team){
        boolean retVal = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_TEAMS, new String[]{TABLE_TEAMS_KEY},
                TABLE_TEAMS_KEY + " = ?", new String[]{team.getKey()}, null, null, null);

        if(c != null && c.getCount() >0)
            return true;

        return retVal;
    }

    public void updateTeam(Team team){
        getWritableDatabase().update(TABLE_TEAMS, mapTeam(team), TABLE_TEAMS_KEY + " = ?", new String[]{team.getKey()});
    }

    public Team getTeam(String teamKey){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_TEAMS + " WHERE " + TABLE_TEAMS_KEY + " = ?";

        Cursor c = db.rawQuery(selectQuery, new String[]{teamKey});

        Team team = new Team();
        if(c != null && c.moveToNext()){
            team.setKey(c.getString(c.getColumnIndex(TABLE_TEAMS_KEY)));
            team.setName(c.getString(c.getColumnIndex(TABLE_TEAMS_NAME)));
            team.setNickname(c.getString(c.getColumnIndex(TABLE_TEAMS_NICKNAME)));
            team.setTeamNumber(c.getInt(c.getColumnIndex(TABLE_TEAMS_TEAM_NUMBER)));
            team.setWebsite(c.getString(c.getColumnIndex(TABLE_TEAMS_WEBSITE)));
            team.setCity(c.getString(c.getColumnIndex(TABLE_TEAMS_CITY)));
            team.setCountry(c.getString(c.getColumnIndex(TABLE_TEAMS_COUNTRY)));
            team.setLocation(c.getString(c.getColumnIndex(TABLE_TEAMS_LOCATION)));
            team.setEvents(new ArrayList<Event>());
        }

        selectQuery = "SELECT * FROM " +TABLE_E2T + " WHERE " + TABLE_E2T_TEAM + " = ?";
        c = db.rawQuery(selectQuery, new String[]{team.getKey()});

        while(c != null && c.moveToNext()){
            team.getEvents().add(getEvent(c.getString(c.getColumnIndex(TABLE_E2T_EVENT))));
        }

        return team;
    }

    public Cursor createTeamCursor(Event event){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_TEAMS+","+TABLE_E2T,
                new String[]{"*", TABLE_TEAMS+".rowid AS _id"},
                TABLE_TEAMS+"."+TABLE_TEAMS_KEY+" = "+TABLE_E2T+"."+TABLE_E2T_TEAM
                +" AND "+TABLE_E2T+"."+TABLE_E2T_EVENT+" = ?",
                new String[]{event.getKey()},
                null, null, TABLE_TEAMS+"."+TABLE_TEAMS_TEAM_NUMBER+" ASC");

        return c;
    }

    public void createE2TAssociation(String eventKey, String teamKey){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_E2T_EVENT, eventKey);
        values.put(TABLE_E2T_TEAM, teamKey);

        db.insert(TABLE_E2T, null, values);
    }

    public boolean doesE2TAssociationExist(String eventKey, String teamKey){
        SQLiteDatabase db = getReadableDatabase();
        boolean retVal = false;

        Cursor c = db.query(TABLE_E2T, new String[]{"*"},
                TABLE_E2T_EVENT+" = ? AND "+TABLE_E2T_TEAM+" = ?",
                new String[]{eventKey, teamKey}, null, null, null);

        if(c != null && c.getCount() > 0)
            retVal = true;

        return retVal;
    }

    private ContentValues mapMatch(Match match){
        ContentValues values = new ContentValues();
        values.put(TABLE_MATCHES_KEY, match.getKey());
        values.put(TABLE_MATCHES_EVENT_KEY, match.getEventKey());
        values.put(TABLE_MATCHES_MATCH_NUMBER, match.getMatchNumber());
        values.put(TABLE_MATCHES_SET_NUMBER, match.getSetNumber());
        values.put(TABLE_MATCHES_COMP_LEVEL, match.getCompetitionLevel());
        values.put(TABLE_MATCHES_BLUE_SCORE, match.getAlliances().getBlue().getScore());
        values.put(TABLE_MATCHES_BLUE_ONE, match.getAlliances().getBlue().getTeams().get(0));
        values.put(TABLE_MATCHES_BLUE_TWO, match.getAlliances().getBlue().getTeams().get(1));
        values.put(TABLE_MATCHES_BLUE_THREE, match.getAlliances().getBlue().getTeams().get(2));
        values.put(TABLE_MATCHES_RED_SCORE, match.getAlliances().getRed().getScore());
        values.put(TABLE_MATCHES_RED_ONE, match.getAlliances().getRed().getTeams().get(0));
        values.put(TABLE_MATCHES_RED_TWO, match.getAlliances().getRed().getTeams().get(1));
        values.put(TABLE_MATCHES_RED_THREE, match.getAlliances().getRed().getTeams().get(2));

        return values;
    }

    public void createMatch(Match match){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = mapMatch(match);

        db.insert(TABLE_MATCHES, null, values);
    }

    public boolean doesMatchExist(Match match){
        boolean retVal = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_MATCHES, new String[]{TABLE_MATCHES_KEY},
                TABLE_MATCHES_KEY+" = ?", new String[]{match.getKey()}, null, null, null);

        if(c != null && c.getCount() > 0)
            retVal = true;

        return retVal;
    }

    public void updateMatch(Match match){
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_MATCHES, mapMatch(match), TABLE_MATCHES_KEY+" = ?", new String[]{match.getKey()});
    }

    public Match getMatch(String matchKey){
        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "SELECT * FROM " + TABLE_MATCHES
                + " WHERE " + TABLE_MATCHES_KEY + " = ?";

        Cursor c = db.rawQuery(selectStatement, new String[]{matchKey});

        Match match = new Match();

        if(c!=null && c.moveToNext()){
            match.setKey(c.getString(c.getColumnIndex(TABLE_MATCHES_KEY)));
            match.setEventKey(c.getString(c.getColumnIndex(TABLE_MATCHES_EVENT_KEY)));
            match.setMatchNumber(c.getInt(c.getColumnIndex(TABLE_MATCHES_MATCH_NUMBER)));
            match.setSetNumber(c.getInt(c.getColumnIndex(TABLE_MATCHES_SET_NUMBER)));
            match.setCompetitionLevel(c.getString(c.getColumnIndex(TABLE_MATCHES_COMP_LEVEL)));

            Alliances alliances = new Alliances();
            Alliance red = new Alliance();
            red.setTeams(new ArrayList<String>(3));
            Alliance blue = new Alliance();
            blue.setTeams(new ArrayList<String>(3));
            alliances.setBlue(blue);
            alliances.setRed(red);

            blue.setScore(c.getInt(c.getColumnIndex(TABLE_MATCHES_BLUE_SCORE)));
            blue.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_BLUE_ONE)));
            blue.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_BLUE_TWO)));
            blue.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_BLUE_THREE)));

            red.setScore(c.getInt(c.getColumnIndex(TABLE_MATCHES_RED_SCORE)));
            red.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_RED_ONE)));
            red.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_RED_TWO)));
            red.getTeams().add(c.getString(c.getColumnIndex(TABLE_MATCHES_RED_THREE)));
        }

        return match;
    }

    public Cursor createMatchCursor(Event event){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_MATCHES,
                new String[]{"*", "rowid As _id"},
                TABLE_MATCHES_EVENT_KEY + " = ?", new String[]{event.getKey()},
                null, null,
                "(CASE " + TABLE_MATCHES_COMP_LEVEL + " "
                        + "WHEN 'qm' THEN 1 "
                        + "WHEN 'qf' THEN 2 "
                        + "WHEN 'sf' THEN 3 "
                        + "WHEN 'f'  THEN 4 "
                        + "ELSE 5 END), "
                        + TABLE_MATCHES_SET_NUMBER +", "
                        + TABLE_MATCHES_MATCH_NUMBER + " ASC",
                null);
    }

    public int getMatchCount(Event event){
        int retVal = 0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MATCHES +" WHERE "+TABLE_MATCHES_EVENT_KEY +" = ?", new String[]{event.getKey()});

        if(c != null && c.moveToNext())
            retVal = c.getInt(0);

        return retVal;
    }

    private ContentValues mapScouting(Scouting scouting){
        ScoutingAuto scoutingAuto = scouting.getAuto();
        ScoutingTele scoutingTele = scouting.getTele();
        ScoutingGeneral scoutingGeneral = scouting.getGeneral();

        ContentValues values = new ContentValues();
        values.put(TABLE_SCOUTING_LAST_UPDATE, getDateTime());
        values.put(TABLE_SCOUTING_TEAM_KEY, scouting.getTeamKey());
        values.put(TABLE_SCOUTING_EVENT_KEY, scouting.getEventKey());
        values.put(TABLE_SCOUTING_MATCH_KEY, scouting.getMatchKey());
        values.put(TABLE_SCOUTING_NAME, scouting.getNameOfScouter());

        if (scoutingAuto != null) {
            if (scoutingAuto.getStartingLocation() != null) {
                values.put(TABLE_SCOUTING_AUTO_STARTING_LOCATION_X, scoutingAuto.getStartingLocation().x);
                values.put(TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y, scoutingAuto.getStartingLocation().y);
            }
            values.put(TABLE_SCOUTING_AUTO_BALLS_ACQUIRED, scoutingAuto.getBallsAcquired());
            values.put(TABLE_SCOUTING_AUTO_STARTED_WITH_BALL, scoutingAuto.isStartedWithBall());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SHOT, scoutingAuto.getBallsShot());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SCORED, scoutingAuto.getBallsScored());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_HIGH, scoutingAuto.getBallsScoredHotHigh());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_LOW, scoutingAuto.getBallsScoredHotLow());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SCORED_HIGH, scoutingAuto.getBallsScoredHigh());
            values.put(TABLE_SCOUTING_AUTO_BALLS_SCORED_LOW, scoutingAuto.getBallsScoredLow());
            if (scoutingAuto.getEndingLocation() != null) {
                values.put(TABLE_SCOUTING_AUTO_ENDING_LOCATION_X, scoutingAuto.getEndingLocation().x);
                values.put(TABLE_SCOUTING_AUTO_ENDING_LOCATION_Y, scoutingAuto.getEndingLocation().y);
            }
        }
        if (scoutingTele != null) {
            values.put(TABLE_SCOUTING_TELE_ACQUIRED_FROM_FLOOR, scoutingTele.getBallsAcquiredFromFloor());
            values.put(TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_FLOOR, scoutingTele.getCompletedAssistsFromFloor());
            values.put(TABLE_SCOUTING_TELE_ACQUIRED_FROM_HUMAN, scoutingTele.getBallsAcquiredFromHuman());
            values.put(TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_HUMAN, scoutingTele.getCompletedAssistsFromHuman());
            values.put(TABLE_SCOUTING_TELE_SHOT_HIGH, scoutingTele.getShotHigh());
            values.put(TABLE_SCOUTING_TELE_SCORED_HIGH, scoutingTele.getScoredHigh());
            values.put(TABLE_SCOUTING_TELE_SHOT_LOW, scoutingTele.getShotLow());
            values.put(TABLE_SCOUTING_TELE_SCORED_LOW, scoutingTele.getScoredLow());
            values.put(TABLE_SCOUTING_TELE_CAUGHT_TRUSS, scoutingTele.getBallsCaughtOverTruss());
            values.put(TABLE_SCOUTING_TELE_THROWN_TRUSS, scoutingTele.getBallsThrownOverTruss());
            values.put(TABLE_SCOUTING_TELE_STAYED_ZONE, scoutingTele.getStayedInZone());
        }
        if (scoutingGeneral != null) {
            values.put(TABLE_SCOUTING_GENERAL_PLAYS_DEFENSE, scoutingGeneral.isPlaysDefense());
            values.put(TABLE_SCOUTING_GENERAL_NUM_PENALITES, scoutingGeneral.getNumberOfPenalties());
            values.put(TABLE_SCOUTING_GENERAL_COMMENTS_PENALTIES, scoutingGeneral.getCommentsOnPenalties());
            values.put(TABLE_SCOUTING_GENERAL_NUM_TECH_FOULS, scoutingGeneral.getNumberOfTechnicalFouls());
            values.put(TABLE_SCOUTING_GENERAL_COMMENTS_TECH_FOULS, scoutingGeneral.getCommentsOnTechnicalFouls());
            values.put(TABLE_SCOUTING_GENERAL_COMMENTS, scoutingGeneral.getGeneralComments());
        }
        return values;
    }

    public void createScouting(Scouting scouting){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = mapScouting(scouting);
        values.put(TABLE_SCOUTING_LAST_SYNC, "2000-01-01 00:00:00");
        db.insert(TABLE_SCOUTING, null, values);
    }

    public boolean doesScoutingExist(Scouting scouting){
        boolean retVal = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_SCOUTING,
                new String[]{TABLE_SCOUTING_TEAM_KEY, TABLE_SCOUTING_MATCH_KEY, TABLE_SCOUTING_NAME, TABLE_SCOUTING_EVENT_KEY},
                TABLE_SCOUTING_TEAM_KEY + " = ? AND " + TABLE_SCOUTING_MATCH_KEY + " = ? AND "
                + TABLE_SCOUTING_NAME +" = ? AND " + TABLE_SCOUTING_EVENT_KEY + " = ?",
                new String[]{scouting.getTeamKey(), scouting.getMatchKey(), scouting.getNameOfScouter(), scouting.getEventKey()},
                null, null, null);

        if(c != null && c.getCount() > 0)
            retVal = true;

        return retVal;
    }

    public void updateScouting(Scouting scouting){
        SQLiteDatabase db = getWritableDatabase();

        db.update(TABLE_SCOUTING, mapScouting(scouting),
                TABLE_SCOUTING_TEAM_KEY + " = ? AND " + TABLE_SCOUTING_MATCH_KEY + " = ? AND " + TABLE_SCOUTING_NAME +" = ?",
                new String[]{scouting.getTeamKey(), scouting.getMatchKey(), scouting.getNameOfScouter()});
    }

    public List<Scouting> getScouting(String eventKey, String teamKey, String matchKey, String scouterName){
        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "SELECT * FROM " + TABLE_SCOUTING
                + " WHERE " + TABLE_SCOUTING_EVENT_KEY + " = ?"
                + " AND " + TABLE_SCOUTING_MATCH_KEY + " = ?"
                + " AND " + TABLE_SCOUTING_TEAM_KEY + " = ?"
                + " AND " + TABLE_SCOUTING_NAME + " = ?";

        Cursor c = db.rawQuery(selectStatement, new String[]{eventKey, matchKey, teamKey, scouterName});

        List<Scouting> scouting = new ArrayList<Scouting>();

        while (c != null && c.moveToNext()){
            scouting.add(mapScouting(c));
        }

        return scouting;
    }

    private static Scouting mapScouting(Cursor c){
        Scouting data = new Scouting();
        data.setNameOfScouter(c.getString(c.getColumnIndex(TABLE_SCOUTING_NAME)));
        data.setEventKey(c.getString(c.getColumnIndex(TABLE_SCOUTING_EVENT_KEY)));
        data.setTeamKey(c.getString(c.getColumnIndex(TABLE_SCOUTING_TEAM_KEY)));
        data.setMatchKey(c.getString(c.getColumnIndex(TABLE_SCOUTING_MATCH_KEY)));

        ScoutingAuto auto = new ScoutingAuto();
        data.setAuto(auto);
        auto.setStartingLocation(new Point(
                c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_STARTING_LOCATION_X)),
                c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y))));
        auto.setStartedWithBall(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y)) == 1);
        auto.setBallsAcquired(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_ACQUIRED)));
        auto.setBallsShot(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SHOT)));
        auto.setBallsScored(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SCORED)));
        auto.setBallsScoredHotHigh(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_HIGH)));
        auto.setBallsScoredHotLow(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SCORED_HOT_LOW)));
        auto.setBallsScoredHigh(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SCORED_HIGH)));
        auto.setBallsScoredLow(c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_BALLS_SCORED_LOW)));
        auto.setEndingLocation(new Point(
                c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_ENDING_LOCATION_X)),
                c.getInt(c.getColumnIndex(TABLE_SCOUTING_AUTO_ENDING_LOCATION_Y))));

        ScoutingTele tele = new ScoutingTele();
        data.setTele(tele);
        tele.setBallsAcquiredFromFloor(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_ACQUIRED_FROM_FLOOR)));
        tele.setBallsAcquiredFromFloor(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_FLOOR)));
        tele.setBallsAcquiredFromFloor(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_ACQUIRED_FROM_HUMAN)));
        tele.setBallsAcquiredFromFloor(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_COMPLETED_ASSISTS_FROM_HUMAN)));
        tele.setShotHigh(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_SHOT_HIGH)));
        tele.setScoredHigh(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_SCORED_HIGH)));
        tele.setShotLow(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_SHOT_LOW)));
        tele.setScoredLow(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_SCORED_LOW)));
        tele.setBallsCaughtOverTruss(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_CAUGHT_TRUSS)));
        tele.setBallsThrownOverTruss(c.getInt(c.getColumnIndex(TABLE_SCOUTING_TELE_THROWN_TRUSS)));

        ScoutingGeneral general = new ScoutingGeneral();
        data.setGeneral(general);
        general.setPlaysDefense(c.getInt(c.getColumnIndex(TABLE_SCOUTING_GENERAL_PLAYS_DEFENSE)) == 1);
        general.setNumberOfPenalties(c.getInt(c.getColumnIndex(TABLE_SCOUTING_GENERAL_NUM_PENALITES)));
        general.setCommentsOnPenalties(c.getString(c.getColumnIndex(TABLE_SCOUTING_GENERAL_COMMENTS_PENALTIES)));
        general.setNumberOfTechnicalFouls(c.getInt(c.getColumnIndex(TABLE_SCOUTING_GENERAL_NUM_TECH_FOULS)));
        general.setCommentsOnTechnicalFouls(c.getString(c.getColumnIndex(TABLE_SCOUTING_GENERAL_COMMENTS_TECH_FOULS)));
        general.setGeneralComments(c.getString(c.getColumnIndex(TABLE_SCOUTING_GENERAL_COMMENTS)));

        return data;
    }

    public List<Scouting> getAllScoutingNeedingSyncing(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_SCOUTING, new String[]{"*"},
                TABLE_SCOUTING_LAST_UPDATE +" > "+TABLE_SCOUTING_LAST_SYNC,
                null, null, null, null);

        List<Scouting> retVal = new ArrayList<Scouting>();
        while (c != null && c.moveToNext())
            retVal.add(mapScouting(c));

        return retVal;
    }

    public void setDoneSyncing(Scouting scouting){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_SCOUTING_LAST_SYNC, getDateTime());
        db.update(TABLE_SCOUTING, cv, TABLE_SCOUTING_TEAM_KEY+" = ? AND "+TABLE_SCOUTING_EVENT_KEY
                +" = ? AND "+TABLE_SCOUTING_MATCH_KEY+" = ? AND "+TABLE_SCOUTING_NAME+" = ?",
                new String[]{scouting.getTeamKey(), scouting.getEventKey(), scouting.getMatchKey(), scouting.getNameOfScouter()});
    }
}
