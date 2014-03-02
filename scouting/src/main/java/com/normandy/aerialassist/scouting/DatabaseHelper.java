package com.normandy.aerialassist.scouting;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.normandy.aerialassist.scouting.dto.Event;

/**
 * Created by jbass on 3/1/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

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
    private static final String TABLE_SCOUTING_MATCH_KEY = "match_key";
    private static final String TABLE_SCOUTING_TEAM_KEY = "team_key";
    private static final String TABLE_SCOUTING_NAME = "scouter_name";
    private static final String TABLE_SCOUTING_AUTO_STARTING_LOCATION_X = "auto_starting_location_x";
    private static final String TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y = "auto_starting_location_y";
    private static final String TABLE_SCOUTING_AUTO_BALLS_ACQUIRED = "auto_balls_acquired";
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
    private static final String TABLE_SCOUTING_TELE_AQCUIRED_FROM_HUMAN = "tele_acq_human";
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
            + TABLE_SCOUTING_NAME + " TEXT, "
            + TABLE_SCOUTING_MATCH_KEY + " TEXT, "
            + TABLE_SCOUTING_TEAM_KEY + " TEXT, "
            + TABLE_SCOUTING_AUTO_STARTING_LOCATION_X + " INTEGER, "
            + TABLE_SCOUTING_AUTO_STARTING_LOCATION_Y + " INTEGER, "
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
            + TABLE_SCOUTING_TELE_AQCUIRED_FROM_HUMAN + " INTEGER, "
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
}