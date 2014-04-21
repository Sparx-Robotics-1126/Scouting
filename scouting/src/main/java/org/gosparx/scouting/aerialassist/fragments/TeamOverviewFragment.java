package org.gosparx.scouting.aerialassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.R;
import org.gosparx.scouting.aerialassist.dto.Scouting;
import org.gosparx.scouting.aerialassist.dto.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jbass on 3/8/14.
 */
public class TeamOverviewFragment extends Fragment {

    public static final String ARG_EVENT_KEY = "event_key";
    public static final String ARG_TEAM_KEY = "team_key";

    private TextView textViewTeamNickname;
    private TextView textViewTeamName;
    private ImageView imageViewRobot;
    private TextView textViewOverallRecord;
    private TextView textViewStartWithBall;
    private TextView textViewNumberOfBallsAcquired;
    private TextView textViewNumberOfBallsShot;
    private TextView textViewNumberOfBallsScored;
    private TextView textViewNumberOfBallsScoredHotHigh;
    private TextView textViewNumberOfBallsScoredHotLow;
    private TextView textViewNumberOfBallsScoredHigh;
    private TextView textViewNumberOfBallsScoredLow;
    private TextView textViewNumberBallsAcquiredFloor;
    private TextView textViewNumberCompletionFloor;
    private TextView textViewNumberBallsAcquiredHuman;
    private TextView textViewNumberCompletionHuman;
    private TextView textViewShotHigh;
    private TextView textViewScoredHigh;
    private TextView textViewShotLow;
    private TextView textViewScoredLow;
    private TextView textViewCaughtOverTruss;
    private TextView textViewThrownOverTruss;
    private TextView textViewAllianceZone;
    private TextView textViewMiddleZone;
    private TextView textViewOpponentZone;
    private TextView textViewPlaysDefense;
    private TextView textViewNumberPenalties;
    private EditText editTextPenaltyComments;
    private TextView textViewNumberTechnicalFouls;
    private EditText editTextTechnicalFoulsComments;
    private EditText editTextGeneralComments;

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retVal = inflater.inflate(R.layout.fragment_team_overview, container, false);

        textViewTeamNickname = (TextView) retVal.findViewById(R.id.textViewTeamNickname);
        textViewTeamName = (TextView) retVal.findViewById(R.id.textViewTeamName);
        imageViewRobot = (ImageView) retVal.findViewById(R.id.networkImageViewRobot);
        textViewOverallRecord = (TextView) retVal.findViewById(R.id.textViewOverallRecord);
        textViewStartWithBall = (TextView) retVal.findViewById(R.id.textViewStartWithBall);
        textViewNumberOfBallsAcquired = (TextView) retVal.findViewById(R.id.textViewNumberBallsAcquired);
        textViewNumberOfBallsShot = (TextView) retVal.findViewById(R.id.textViewNumberBallsShot);
        textViewNumberOfBallsScored = (TextView) retVal.findViewById(R.id.textViewNumberBallsScored);
        textViewNumberOfBallsScoredHotHigh = (TextView) retVal.findViewById(R.id.textViewNumberBallsScoredHotHigh);
        textViewNumberOfBallsScoredHotLow = (TextView) retVal.findViewById(R.id.textViewNumberBallsScoredHotLow);
        textViewNumberOfBallsScoredHigh = (TextView) retVal.findViewById(R.id.textViewNumberBallsScoredHigh);
        textViewNumberOfBallsScoredLow = (TextView) retVal.findViewById(R.id.textViewNumberBallsScoredLow);
        textViewNumberBallsAcquiredFloor = (TextView) retVal.findViewById(R.id.textViewNumberBallsAcquiredFloor);
        textViewNumberCompletionFloor = (TextView) retVal.findViewById(R.id.textViewNumberCompletionFloor);
        textViewNumberBallsAcquiredHuman = (TextView) retVal.findViewById(R.id.textViewNumberBallsAcquiredHuman);
        textViewNumberCompletionHuman = (TextView) retVal.findViewById(R.id.textViewNumberCompletionHuman);
        textViewShotHigh = (TextView) retVal.findViewById(R.id.textViewShotHigh);
        textViewScoredHigh = (TextView) retVal.findViewById(R.id.textViewScoredHigh);
        textViewShotLow = (TextView) retVal.findViewById(R.id.textViewShotLow);
        textViewScoredLow = (TextView) retVal.findViewById(R.id.textViewScoredLow);
        textViewCaughtOverTruss = (TextView) retVal.findViewById(R.id.textViewCaughtOverTruss);
        textViewThrownOverTruss = (TextView) retVal.findViewById(R.id.textViewThrownOverTruss);
        textViewAllianceZone = (TextView) retVal.findViewById(R.id.textViewAllianceZone);
        textViewMiddleZone = (TextView) retVal.findViewById(R.id.textViewMiddleZone);
        textViewOpponentZone = (TextView) retVal.findViewById(R.id.textViewOpponentZone);
        textViewPlaysDefense = (TextView) retVal.findViewById(R.id.textViewPlaysDefence);
        textViewNumberPenalties = (TextView) retVal.findViewById(R.id.textViewNumberPenalties);
        editTextPenaltyComments = (EditText) retVal.findViewById(R.id.editTextPenaltyComments);
        textViewNumberTechnicalFouls = (TextView) retVal.findViewById(R.id.textViewNumberTechFouls);
        editTextTechnicalFoulsComments = (EditText) retVal.findViewById(R.id.editTextTechnicalFoulComments);
        editTextGeneralComments = (EditText) retVal.findViewById(R.id.editTextGeneralComments);

        if(getArguments() != null)
            loadData();

        return retVal;
    }

    private void loadData(){
        Bundle args = getArguments();
        String eventKey = "";
        String teamKey = "";

        if(args != null){
            eventKey = args.getString(ARG_EVENT_KEY);
            teamKey = args.getString(ARG_TEAM_KEY);
        }

        Team team = dbHelper.getTeam(teamKey);
        textViewTeamName.setText(team.getName());
        textViewTeamNickname.setText(team.getNickname());
        Ion.with(imageViewRobot)
                .placeholder(R.drawable.ic_launcher)
                .load("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2009/12/2/1259769084987/Pingpong-playing-robot--i-001.jpg");

        Map<String, List<Scouting>> scoutingData = createScoutingMap(dbHelper.getScouting(eventKey, teamKey));

        List<Boolean> startWithBall = new ArrayList<Boolean>();
        List<Double> numberOfBallsAcquired = new ArrayList<Double>();
        List<Double> numberOfBallsShot = new ArrayList<Double>();
        List<Double> numberOfBallsScored = new ArrayList<Double>();
        List<Double> numberOfBallsScoredHotHigh = new ArrayList<Double>();
        List<Double> numberOfBallsScoredHotLow = new ArrayList<Double>();
        List<Double> numberOfBallsScoredHigh = new ArrayList<Double>();
        List<Double> numberOfBallsScoredLow = new ArrayList<Double>();
        List<Double> numberOfBallsAcquiredFloor = new ArrayList<Double>();
        List<Double> numberOfBallsCompletionFloor = new ArrayList<Double>();
        List<Double> numberOfBallsAcquiredHuman = new ArrayList<Double>();
        List<Double> numberOfBallsCompletionHuman = new ArrayList<Double>();
        List<Double> shotHigh = new ArrayList<Double>();
        List<Double> scoredHigh = new ArrayList<Double>();
        List<Double> shotLow = new ArrayList<Double>();
        List<Double> scoredLow = new ArrayList<Double>();
        List<Double> caughtOverTruss = new ArrayList<Double>();
        List<Double> thrownOverTruss = new ArrayList<Double>();
        List<Double> allianceZone = new ArrayList<Double>();
        List<Double> middleZone = new ArrayList<Double>();
        List<Double> opponentZone = new ArrayList<Double>();
        List<Double> playsDefence = new ArrayList<Double>();
        List<Double> numberPenalties = new ArrayList<Double>();
        List<Double> numberTechnicalFouls = new ArrayList<Double>();

        for (List<Scouting> entry : scoutingData.values()) {
            List<Boolean> startWithBallMatch = new ArrayList<Boolean>();
            List<Integer> numberOfBallsAcquiredMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsShotMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsScoredMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsScoredHotHighMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsScoredHotLowMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsScoredHighMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsScoredLowMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsAcquiredFloorMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsCompletionFloorMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsAcquiredHumanMatch = new ArrayList<Integer>();
            List<Integer> numberOfBallsCompletionHumanMatch = new ArrayList<Integer>();
            List<Integer> shotHighMatch = new ArrayList<Integer>();
            List<Integer> scoredHighMatch = new ArrayList<Integer>();
            List<Integer> shotLowMatch = new ArrayList<Integer>();
            List<Integer> scoredLowMatch = new ArrayList<Integer>();
            List<Integer> caughtOverTrussMatch = new ArrayList<Integer>();
            List<Integer> thrownOverTrussMatch = new ArrayList<Integer>();
            List<Integer> allianceZoneMatch = new ArrayList<Integer>();
            List<Integer> middleZoneMatch = new ArrayList<Integer>();
            List<Integer> opponentZoneMatch = new ArrayList<Integer>();
            List<Integer> playsDefenceMatch = new ArrayList<Integer>();
            List<Integer> numberPenaltiesMatch = new ArrayList<Integer>();
            List<Integer> numberTechnicalFoulsMatch = new ArrayList<Integer>();
            for(Scouting scouting : entry){
                startWithBallMatch.add(scouting.getAuto().isStartedWithBall());
                if(scouting.getAuto().getBallsAcquired() != -1)
                    numberOfBallsAcquiredMatch.add(scouting.getAuto().getBallsAcquired());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsShotMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsScoredMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsScoredHotHighMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsScoredHotLowMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsScoredHighMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsScoredLowMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsAcquiredFloorMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsCompletionFloorMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsAcquiredHumanMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsCompletionHumanMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    shotHighMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    scoredHighMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    shotLowMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    scoredLowMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    caughtOverTrussMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    thrownOverTrussMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    allianceZoneMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    middleZoneMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    opponentZoneMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    playsDefenceMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberPenaltiesMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberTechnicalFoulsMatch.add(scouting.getAuto().getBallsShot());
            }

        }


    }

    /**
     * Creates a Map<Matches,Scouting[]>
     *
     * @param scoutingData
     * @return
     */
    private Map<String, List<Scouting>> createScoutingMap(List<Scouting> scoutingData) {
        Map<String, List<Scouting>> map = new HashMap<String, List<Scouting>>();

        for(Scouting scout : scoutingData){

            List<Scouting> matchList = map.get(scout.getMatchKey());
            if(matchList == null) {
                matchList = new ArrayList<Scouting>();
                map.put(scout.getMatchKey(), matchList);
            }

            matchList.add(scout);
        }

        return map;
    }
}
