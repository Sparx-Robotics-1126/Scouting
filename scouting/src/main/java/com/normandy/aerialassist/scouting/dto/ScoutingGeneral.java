package com.normandy.aerialassist.scouting.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingGeneral {

    private boolean playsDefense;
    private int numberOfPenalties;
    private String commentsOnPenalties;
    private int numberOfTechnicalFouls;
    private String commentsOnTechnicalFouls;
    private String generalComments;

    public boolean isPlaysDefense() {
        return playsDefense;
    }

    public void setPlaysDefense(boolean playsDefense) {
        this.playsDefense = playsDefense;
    }

    public int getNumberOfPenalties() {
        return numberOfPenalties;
    }

    public void setNumberOfPenalties(int numberOfPenalties) {
        this.numberOfPenalties = numberOfPenalties;
    }

    public String getCommentsOnPenalties() {
        return commentsOnPenalties;
    }

    public void setCommentsOnPenalties(String commentsOnPenalties) {
        this.commentsOnPenalties = commentsOnPenalties;
    }

    public int getNumberOfTechnicalFouls() {
        return numberOfTechnicalFouls;
    }

    public void setNumberOfTechnicalFouls(int numberOfTechnicalFouls) {
        this.numberOfTechnicalFouls = numberOfTechnicalFouls;
    }

    public String getCommentsOnTechnicalFouls() {
        return commentsOnTechnicalFouls;
    }

    public void setCommentsOnTechnicalFouls(String commentsOnTechnicalFouls) {
        this.commentsOnTechnicalFouls = commentsOnTechnicalFouls;
    }

    public String getGeneralComments() {
        return generalComments;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }
}
