package com.normandy.aerialassist.scouting.dto;

import android.graphics.Point;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingAuto {

    private Point startingLocation;
    private int ballsAcquired;
    private int ballsShot;
    private int ballsScored;
    private int ballsScoredHotHigh;
    private int ballsScoredHotLow;
    private int ballsScoredHigh;
    private int ballsScoredLow;
    private Point endingLocation;

    public Point getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Point startingLocation) {
        this.startingLocation = startingLocation;
    }

    public int getBallsAcquired() {
        return ballsAcquired;
    }

    public void setBallsAcquired(int ballsAcquired) {
        this.ballsAcquired = ballsAcquired;
    }

    public int getBallsShot() {
        return ballsShot;
    }

    public void setBallsShot(int ballsShot) {
        this.ballsShot = ballsShot;
    }

    public int getBallsScored() {
        return ballsScored;
    }

    public void setBallsScored(int ballsScored) {
        this.ballsScored = ballsScored;
    }

    public int getBallsScoredHotHigh() {
        return ballsScoredHotHigh;
    }

    public void setBallsScoredHotHigh(int ballsScoredHotHigh) {
        this.ballsScoredHotHigh = ballsScoredHotHigh;
    }

    public int getBallsScoredHotLow() {
        return ballsScoredHotLow;
    }

    public void setBallsScoredHotLow(int ballsScoredHotLow) {
        this.ballsScoredHotLow = ballsScoredHotLow;
    }

    public int getBallsScoredHigh() {
        return ballsScoredHigh;
    }

    public void setBallsScoredHigh(int ballsScoredHigh) {
        this.ballsScoredHigh = ballsScoredHigh;
    }

    public int getBallsScoredLow() {
        return ballsScoredLow;
    }

    public void setBallsScoredLow(int ballsScoredLow) {
        this.ballsScoredLow = ballsScoredLow;
    }

    public Point getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(Point endingLocation) {
        this.endingLocation = endingLocation;
    }
}
