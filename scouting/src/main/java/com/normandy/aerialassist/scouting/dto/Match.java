package com.normandy.aerialassist.scouting.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jbass on 3/1/14.
 */
public class Match {

    private String key;
    @SerializedName("event_key")
    private String eventKey;
    @SerializedName("match_number")
    private int matchNumber;
    @SerializedName("set_number")
    private int setNumber;
    @SerializedName("comp_level")
    private String competitionLevel;
    private Alliances alliances;

    public String getKey() {
        return key;
    }

    public String getEventKey() {
        return eventKey;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public String getCompetitionLevel() {
        return competitionLevel;
    }

    public Alliances getAlliances() {
        return alliances;
    }
}
