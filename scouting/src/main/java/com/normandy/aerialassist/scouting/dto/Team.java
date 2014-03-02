package com.normandy.aerialassist.scouting.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jbass on 3/1/14.
 */
public class Team {

    private String key;
    private String name;
    private String nickname;
    @SerializedName("team_number")
    private int teamNumber;
    private String website;
    @SerializedName("locality")
    private String city;
    @SerializedName("region")
    private String state;
    private String country;
    private String location;
    private List<Event> events;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public List<Event> getEvents() {
        return events;
    }
}
