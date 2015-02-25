package org.gosparx.scouting.aerialassist.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingAuto {

    private double startingLocationX;
    private double startingLocationY;
    private boolean isRobotInAutoZone = false;
    private boolean isRobotCreateStack = false;
    private int totesMovedToAutoZone = -1;
    private int rCMovedToAutoZone = -1;
    private int rCTakenFromStep = -1;
    private double endingLocationX;
    private double endingLocationY;

    private boolean startedWithBall;
    private int ballsAcquired = -1;
    private int ballsShot = -1;
    private int ballsScored = -1;
    private int ballsScoredHotHigh = -1;
    private int ballsScoredHotLow = -1;
    private int ballsScoredHigh = -1;
    private int ballsScoredLow = -1;


    public double getStartingLocationX() {
        return startingLocationX;
    }

    public void setStartingLocationX(double startingLocationX) {
        this.startingLocationX = startingLocationX;
    }

    public double getStartingLocationY() {
        return startingLocationY;
    }

    public void setStartingLocationY(double startingLocationY) {
        this.startingLocationY = startingLocationY;
    }

    public void setRobotInAutoZone(boolean inZone){
        isRobotInAutoZone = inZone;
    }
    public boolean getRobotInAutoZone(){return isRobotInAutoZone;}

    public void setRobotCreateStack(boolean createdStack){
        isRobotCreateStack = createdStack;
    }
    public boolean getRobotCreateStack(){return isRobotInAutoZone;}

    public void setTotesMovedToAutoZone(int totes){
        totesMovedToAutoZone = totes;
    }
    public int getTotesMovedToAutoZone(){return totesMovedToAutoZone;}

    public void setrCMovedToAutoZone(int moved){
        rCMovedToAutoZone = moved;
    }
    public int getrCMovedToAutoZone(){return rCMovedToAutoZone;}

    public void setRCTakenFromStep(int taken){
        rCTakenFromStep = taken;
    }
    public int getRCTakenFromStep(){return rCTakenFromStep;}

    public boolean isStartedWithBall() {
        return startedWithBall;
    }

    public void setStartedWithBall(boolean startedWithBall) {
        this.startedWithBall = startedWithBall;
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

    public double getEndingLocationX() {
        return endingLocationX;
    }

    public void setEndingLocationX(double endingLocationX) {
        this.endingLocationX = endingLocationX;
    }

    public double getEndingLocationY() {
        return endingLocationY;
    }

    public void setEndingLocationY(double endingLocationY) {
        this.endingLocationY = endingLocationY;
    }
}
