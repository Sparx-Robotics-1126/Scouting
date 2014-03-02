package com.normandy.aerialassist.scouting.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class Scouting {

    private String nameOfScouter;

    private String matchKey;
    private String teamKey;

    private ScoutingAuto auto;
    private ScoutingTele tele;
    private ScoutingGeneral general;

    public String getNameOfScouter() {
        return nameOfScouter;
    }

    public void setNameOfScouter(String nameOfScouter) {
        this.nameOfScouter = nameOfScouter;
    }

    public String getMatchKey() {
        return matchKey;
    }

    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public ScoutingAuto getAuto() {
        return auto;
    }

    public void setAuto(ScoutingAuto auto) {
        this.auto = auto;
    }

    public ScoutingTele getTele() {
        return tele;
    }

    public void setTele(ScoutingTele tele) {
        this.tele = tele;
    }

    public ScoutingGeneral getGeneral() {
        return general;
    }

    public void setGeneral(ScoutingGeneral general) {
        this.general = general;
    }
}
