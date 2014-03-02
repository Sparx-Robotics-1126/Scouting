package com.normandy.aerialassist.scouting.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingTele {

    private int ballsAcquiredFromFloor;
    private int completedAssistsFromFloor;
    private int ballsAcquiredFromHuman;
    private int completedAssistsFromHuman;
    private int shotHigh;
    private int scoredHigh;
    private int shotLow;
    private int scoredLow;
    private int ballsCaughtOverTruss;
    private int ballsThrownOverTruss;
    private String stayedInZone;

    public int getBallsAcquiredFromFloor() {
        return ballsAcquiredFromFloor;
    }

    public void setBallsAcquiredFromFloor(int ballsAcquiredFromFloor) {
        this.ballsAcquiredFromFloor = ballsAcquiredFromFloor;
    }

    public int getCompletedAssistsFromFloor() {
        return completedAssistsFromFloor;
    }

    public void setCompletedAssistsFromFloor(int completedAssistsFromFloor) {
        this.completedAssistsFromFloor = completedAssistsFromFloor;
    }

    public int getBallsAcquiredFromHuman() {
        return ballsAcquiredFromHuman;
    }

    public void setBallsAcquiredFromHuman(int ballsAcquiredFromHuman) {
        this.ballsAcquiredFromHuman = ballsAcquiredFromHuman;
    }

    public int getCompletedAssistsFromHuman() {
        return completedAssistsFromHuman;
    }

    public void setCompletedAssistsFromHuman(int completedAssistsFromHuman) {
        this.completedAssistsFromHuman = completedAssistsFromHuman;
    }

    public int getShotHigh() {
        return shotHigh;
    }

    public void setShotHigh(int shotHigh) {
        this.shotHigh = shotHigh;
    }

    public int getScoredHigh() {
        return scoredHigh;
    }

    public void setScoredHigh(int scoredHigh) {
        this.scoredHigh = scoredHigh;
    }

    public int getShotLow() {
        return shotLow;
    }

    public void setShotLow(int shotLow) {
        this.shotLow = shotLow;
    }

    public int getScoredLow() {
        return scoredLow;
    }

    public void setScoredLow(int scoredLow) {
        this.scoredLow = scoredLow;
    }

    public int getBallsCaughtOverTruss() {
        return ballsCaughtOverTruss;
    }

    public void setBallsCaughtOverTruss(int ballsCaughtOverTruss) {
        this.ballsCaughtOverTruss = ballsCaughtOverTruss;
    }

    public int getBallsThrownOverTruss() {
        return ballsThrownOverTruss;
    }

    public void setBallsThrownOverTruss(int ballsThrownOverTruss) {
        this.ballsThrownOverTruss = ballsThrownOverTruss;
    }

    public String getStayedInZone() {
        return stayedInZone;
    }

    public void setStayedInZone(String stayedInZone) {
        this.stayedInZone = stayedInZone;
    }
}
