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

        return retVal;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getArguments() != null)
            loadData();
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
                .placeholder(R.drawable.icon_launcher)
                .load("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2009/12/2/1259769084987/Pingpong-playing-robot--i-001.jpg");

        Map<String, List<Scouting>> scoutingData = createScoutingMap(dbHelper.getScouting(eventKey, teamKey));

        List<Double> startWithBall = new ArrayList<Double>();
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
        List<String> penaltiesComments = new ArrayList<String>();
        List<Double> numberTechnicalFouls = new ArrayList<Double>();
        List<String> technicalFoulComment = new ArrayList<String>();
        List<String> generalComments = new ArrayList<String>();

        final String allianceString = getString(R.string.alliance);
        final String middleString = getString(R.string.middle);
        final String opponentString = getString(R.string.opponent);

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
            List<Boolean> playsDefenceMatch = new ArrayList<Boolean>();
            List<Integer> numberPenaltiesMatch = new ArrayList<Integer>();
            List<Integer> numberTechnicalFoulsMatch = new ArrayList<Integer>();
            for(Scouting scouting : entry){
                startWithBallMatch.add(scouting.getAuto().isStartedWithBall());
                if(scouting.getAuto().getBallsAcquired() != -1)
                    numberOfBallsAcquiredMatch.add(scouting.getAuto().getBallsAcquired());
                if(scouting.getAuto().getBallsShot() != -1)
                    numberOfBallsShotMatch.add(scouting.getAuto().getBallsShot());
                if(scouting.getAuto().getBallsScored() != -1)
                    numberOfBallsScoredMatch.add(scouting.getAuto().getBallsScored());
                if(scouting.getAuto().getBallsScoredHotHigh() != -1)
                    numberOfBallsScoredHotHighMatch.add(scouting.getAuto().getBallsScoredHotHigh());
                if(scouting.getAuto().getBallsScoredHotLow() != -1)
                    numberOfBallsScoredHotLowMatch.add(scouting.getAuto().getBallsScoredHotLow());
                if(scouting.getAuto().getBallsScoredHigh() != -1)
                    numberOfBallsScoredHighMatch.add(scouting.getAuto().getBallsScoredHigh());
                if(scouting.getAuto().getBallsScoredLow() != -1)
                    numberOfBallsScoredLowMatch.add(scouting.getAuto().getBallsScoredLow());
                if(scouting.getTele().getBallsAcquiredFromFloor() != -1)
                    numberOfBallsAcquiredFloorMatch.add(scouting.getTele().getBallsAcquiredFromFloor());
                if(scouting.getTele().getCompletedAssistsFromFloor() != -1)
                    numberOfBallsCompletionFloorMatch.add(scouting.getTele().getCompletedAssistsFromFloor());
                if(scouting.getTele().getBallsAcquiredFromHuman() != -1)
                    numberOfBallsAcquiredHumanMatch.add(scouting.getTele().getBallsAcquiredFromHuman());
                if(scouting.getTele().getCompletedAssistsFromHuman() != -1)
                    numberOfBallsCompletionHumanMatch.add(scouting.getTele().getCompletedAssistsFromHuman());
                if(scouting.getTele().getShotHigh() != -1)
                    shotHighMatch.add(scouting.getTele().getShotHigh());
                if(scouting.getTele().getScoredHigh() != -1)
                    scoredHighMatch.add(scouting.getTele().getScoredHigh());
                if(scouting.getTele().getShotLow() != -1)
                    shotLowMatch.add(scouting.getTele().getShotLow());
                if(scouting.getTele().getScoredLow() != -1)
                    scoredLowMatch.add(scouting.getTele().getScoredLow());
                if(scouting.getTele().getBallsCaughtOverTruss() != -1)
                    caughtOverTrussMatch.add(scouting.getTele().getBallsCaughtOverTruss());
                if(scouting.getTele().getBallsThrownOverTruss() != -1)
                    thrownOverTrussMatch.add(scouting.getTele().getBallsThrownOverTruss());
                allianceZoneMatch.add(allianceString.equals(scouting.getTele().getStayedInZone())?1:0);
                middleZoneMatch.add(middleString.equals(scouting.getTele().getStayedInZone())?1:0);
                opponentZoneMatch.add(opponentString.equals(scouting.getTele().getStayedInZone())?1:0);
                playsDefenceMatch.add(scouting.getGeneral().isPlaysDefense());
                if(scouting.getGeneral().getNumberOfPenalties() != -1)
                    numberPenaltiesMatch.add(scouting.getGeneral().getNumberOfPenalties());
                penaltiesComments.add(scouting.getGeneral().getCommentsOnPenalties());
                if(scouting.getGeneral().getNumberOfTechnicalFouls() != -1)
                    numberTechnicalFoulsMatch.add(scouting.getGeneral().getNumberOfTechnicalFouls());
                technicalFoulComment.add(scouting.getGeneral().getCommentsOnTechnicalFouls());
                generalComments.add(scouting.getGeneral().getGeneralComments());
            }
            startWithBall.add(averageBoolean(startWithBallMatch));
            numberOfBallsAcquired.add(average(numberOfBallsAcquiredMatch));
            numberOfBallsShot.add(average(numberOfBallsShotMatch));
            numberOfBallsScored.add(average(numberOfBallsScoredMatch));
            numberOfBallsScoredHotHigh.add(average(numberOfBallsScoredHotHighMatch));
            numberOfBallsScoredHotLow.add(average(numberOfBallsScoredHotLowMatch));
            numberOfBallsScoredHigh.add(average(numberOfBallsScoredHighMatch));
            numberOfBallsScoredLow.add(average(numberOfBallsScoredLowMatch));
            numberOfBallsAcquiredFloor.add(average(numberOfBallsAcquiredFloorMatch));
            numberOfBallsCompletionFloor.add(average(numberOfBallsCompletionFloorMatch));
            numberOfBallsAcquiredHuman.add(average(numberOfBallsAcquiredHumanMatch));
            numberOfBallsCompletionHuman.add(average(numberOfBallsCompletionHumanMatch));
            shotHigh.add(average(shotHighMatch));
            scoredHigh.add(average(scoredHighMatch));
            shotLow.add(average(shotLowMatch));
            scoredLow.add(average(scoredLowMatch));
            caughtOverTruss.add(average(caughtOverTrussMatch));
            thrownOverTruss.add(average(thrownOverTrussMatch));
            allianceZone.add(average(allianceZoneMatch));
            middleZone.add(average(middleZoneMatch));
            opponentZone.add(average(opponentZoneMatch));
            playsDefence.add(averageBoolean(playsDefenceMatch));
            numberPenalties.add(average(numberPenaltiesMatch));
            numberTechnicalFouls.add(average(numberTechnicalFoulsMatch));
        }


        double tempDouble = average(startWithBall);
        int tempInt = ((int) tempDouble * startWithBall.size());
        textViewStartWithBall.setText(tempInt + " of " + startWithBall.size() + " (" + tempDouble + ")");
        textViewNumberOfBallsAcquired.setText(Double.toString(average(numberOfBallsAcquired)));
        textViewNumberOfBallsShot.setText(Double.toString(average(numberOfBallsShot)));
        textViewNumberOfBallsScored.setText(Double.toString(average(numberOfBallsScored)));
        textViewNumberOfBallsScoredHotHigh.setText(Double.toString(average(numberOfBallsScoredHotHigh)));
        textViewNumberOfBallsScoredHotLow.setText(Double.toString(average(numberOfBallsScoredHotLow)));
        textViewNumberOfBallsScoredHigh.setText(Double.toString(average(numberOfBallsScoredHigh)));
        textViewNumberOfBallsScoredLow.setText(Double.toString(average(numberOfBallsScoredLow)));
        textViewNumberBallsAcquiredFloor.setText(Double.toString(average(numberOfBallsAcquiredFloor)));
        textViewNumberCompletionFloor.setText(Double.toString(average(numberOfBallsCompletionFloor)));
        textViewNumberBallsAcquiredHuman.setText(Double.toString(average(numberOfBallsAcquiredHuman)));
        textViewNumberCompletionHuman.setText(Double.toString(average(numberOfBallsCompletionHuman)));
        textViewShotHigh.setText(Double.toString(average(shotHigh)));
        textViewScoredHigh.setText(Double.toString(average(scoredHigh)));
        textViewShotLow.setText(Double.toString(average(shotLow)));
        textViewScoredLow.setText(Double.toString(average(scoredLow)));
        textViewCaughtOverTruss.setText(Double.toString(average(caughtOverTruss)));
        textViewThrownOverTruss.setText(Double.toString(average(thrownOverTruss)));
        tempDouble = average(allianceZone);
        tempInt = ((int) tempDouble * allianceZone.size());
        textViewAllianceZone.setText(tempInt + " of " + allianceZone.size() + " (" + tempDouble + ")");
        tempDouble = average(middleZone);
        tempInt = ((int) tempDouble * middleZone.size());
        textViewMiddleZone.setText(tempInt + " of " + middleZone.size() + " (" + tempDouble + ")");
        tempDouble = average(opponentZone);
        tempInt = ((int) tempDouble * opponentZone.size());
        textViewOpponentZone.setText(tempInt + " of " + opponentZone.size() + " (" + tempDouble + ")");
        tempDouble = average(playsDefence);
        tempInt = ((int) tempDouble * playsDefence.size());
        textViewPlaysDefense.setText(tempInt + " of " + playsDefence.size() + " (" + tempDouble + ")");
        textViewNumberPenalties.setText(Double.toString(average(numberPenalties)));
        StringBuilder sb = new StringBuilder();
        for(String comment : penaltiesComments){
            sb.append(comment).append("\n");
        }
        editTextPenaltyComments.setText(sb.toString());
        textViewNumberTechnicalFouls.setText(Double.toString(average(numberTechnicalFouls)));
        sb = new StringBuilder();
        for(String comment : technicalFoulComment){
            sb.append(comment).append("\n");
        }
        editTextTechnicalFoulsComments.setText(sb.toString());
        sb = new StringBuilder();
        for(String comment : generalComments){
            sb.append(comment).append("\n");
        }
        editTextGeneralComments.setText(sb.toString());
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

    private double average(List<? extends Number> list){
        double retVal = 0;
        for(Number dub : list)
            retVal += dub.doubleValue();
        return list.isEmpty() ? 0 : retVal / list.size();
    }

    private double averageBoolean(List<Boolean> list){
        double retVal = 0;
        for(Boolean bool : list){
            retVal += bool ? 1:0;
        }

        return list.isEmpty()? 0:retVal / list.size();
    }

}
