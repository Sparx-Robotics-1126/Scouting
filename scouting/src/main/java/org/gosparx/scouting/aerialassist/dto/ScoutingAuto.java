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
    private int rcMovedToAutoZone = -1;
    private int rcTakenFromStep = -1;
    private double endingLocationX;
    private double endingLocationY;

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

    public void setRcMovedToAutoZone(int moved){
        rcMovedToAutoZone = moved;
    }
    public int getRcMovedToAutoZone(){return rcMovedToAutoZone;}

    public void setRCTakenFromStep(int taken){
        rcTakenFromStep = taken;
    }
    public int getRCTakenFromStep(){return rcTakenFromStep;}

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
