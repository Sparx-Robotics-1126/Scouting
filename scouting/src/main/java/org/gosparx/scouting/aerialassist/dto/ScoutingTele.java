package org.gosparx.scouting.aerialassist.dto;

/**
 * Created by jbass on 3/1/14.
 */
public class ScoutingTele {

    private int totesStacked1 = -1;
    private int totesStacked2 = -1;
    private int totesStacked3 = -1;
    private int totesStacked4 = -1;

    private boolean rcStacked1 = false;
    private boolean rcStacked2 = false;
    private boolean rcStacked3 = false;
    private boolean rcStacked4 = false;

    private boolean litter1 = false;
    private boolean litter2 = false;
    private boolean litter3 = false;
    private boolean litter4 = false;

    public void setTotesStacked1(int stacked){
        totesStacked1 = stacked;
    }
    public int getTotesStacked1(){return totesStacked1;}

    public void setTotesStacked2(int stacked){
        totesStacked2 = stacked;
    }
    public int getTotesStacked2(){return totesStacked2;}

    public void setTotesStacked3(int stacked){
        totesStacked3 = stacked;
    }
    public int getTotesStacked3(){return totesStacked3;}

    public void setTotesStacked4(int stacked){
        totesStacked4 = stacked;
    }
    public int getTotesStacked4(){return totesStacked4;}

    public void setRCStacked1(boolean stacked){ rcStacked1 = stacked;}
    public boolean getRCStacked1(){ return rcStacked1;}

    public void setRCStacked2(boolean stacked){ rcStacked2 = stacked;}
    public boolean getRCStacked2(){ return rcStacked2;}

    public void setRCStacked3(boolean stacked){ rcStacked3 = stacked;}
    public boolean getRCStacked3(){ return rcStacked3;}

    public void setRCStacked4(boolean stacked){ rcStacked4 = stacked;}
    public boolean getRCStacked4(){ return rcStacked4;}

    public void setLitter1(boolean litterOn){ litter1 = litterOn;}
    public boolean getLitter1(){ return litter1;}

    public void setLitter2(boolean litterOn){ litter2 = litterOn;}
    public boolean getLitter2(){ return litter2;}

    public void setLitter3(boolean litterOn){ litter3 = litterOn;}
    public boolean getLitter3(){ return litter3;}

    public void setLitter4(boolean litterOn){ litter4 = litterOn;}
    public boolean getLitter4(){ return litter4;}
}
