package com.normandy.aerialassist.scouting.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by jbass on 3/1/14.
 */
public class Event {

    private String key;
    private String name;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("event_code")
    private String eventCode;
    @SerializedName("event_type_string")
    private String eventTypeString;
    @SerializedName("event_type")
    private int eventType;
    private int year;
    private String location;
    private boolean official;
    @SerializedName("start_date")
    private Calendar startDate;
    @SerializedName("end_date")
    private Calendar endDate;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventTypeString() {
        return eventTypeString;
    }

    public int getEventType() {
        return eventType;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public boolean isOfficial() {
        return official;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }
}
