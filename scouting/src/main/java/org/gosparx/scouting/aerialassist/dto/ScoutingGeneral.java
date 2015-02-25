package org.gosparx.scouting.aerialassist.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingGeneral {

    private boolean playsDefense;
    private int numberOfTechnicalFouls = -1;
    private String commentsOnTechnicalFouls;

    private int numberOfPenalties = -1;
    private int numberOfStacksTipped = -1;
    private int numberOfFailedAttemptsOfRC = -1;
    private int numberOfRCTakenFromStep = -1;
    private boolean isDead = false;
    private boolean isTipped = false;
    private int numberOfTotesAcquiredFromHP = -1;
    private int numberOfTotesAttemptedFromHP = -1;
    private int numberOfTotesFromLandfill = -1;
    private String commentsOnPenalties;
    private String generalComments;

    public void setNumberOfStacksTipped(int numberTipped){
        numberOfStacksTipped = numberTipped;
    }
    public int getNumberOfStacksTipped(){
        return numberOfStacksTipped;
    }

    public void setNumberOfFailedAttemptsOfRC(int failures){
        numberOfFailedAttemptsOfRC = failures;
    }
    public int getNumberOfFailedAttemptsOfRC(){return numberOfFailedAttemptsOfRC;}

    public void setNumberOfRCTakenFromStep(int taken){
        numberOfRCTakenFromStep = taken;
    }
    public int getNumberOfRCTakenFromStep(){return numberOfRCTakenFromStep;}

    public void setIsDead(boolean dead){
        isDead = dead;
    }
    public boolean getIsDead(){return isDead;}

    public void setIsTipped(boolean tipped){
        isTipped = tipped;
    }
    public boolean getIsTipped(){return isTipped;}

    public void setNumberOfTotesAcquiredFromHP(int acquired){
        numberOfTotesAcquiredFromHP = acquired;
    }
    public int getNumberOfTotesAcquiredFromHP(){return numberOfTotesAcquiredFromHP;}

    public void setNumberOfTotesAttemptedFromHP(int attempt){
        numberOfTotesAttemptedFromHP = attempt;
    }
    public int getNumberOfTotesAttemptedFromHP(){return numberOfTotesAttemptedFromHP;}

    public void setNumberOfTotesFromLandfill(int acquired){
        numberOfTotesFromLandfill = acquired;
    }
    public int getNumberOfTotesFromLandfill(){return numberOfTotesFromLandfill;}

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
