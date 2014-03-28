package org.gosparx.scouting.aerialassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.ion.NetworkImageView;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.R;
import org.gosparx.scouting.aerialassist.dto.Scouting;

import java.util.List;

/**
 * Created by jbass on 3/8/14.
 */
public class TeamOverviewFragment extends Fragment {

    public String ARG_EVENT_KEY = "event_key";
    public String ARG_TEAM_KEY = "team_key";
    public String ARG_MATCH_KEY = "match_key";
    public String ARG_SCOUTER_KEY = "scouter_key";

    private TextView textViewTeamNickname;
    private TextView textViewTeamName;
    private NetworkImageView networkImageViewRobot;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retVal = inflater.inflate(R.layout.fragment_team_overview, container, false);

        textViewTeamNickname = (TextView) retVal.findViewById(R.id.textViewTeamNickname);
        textViewTeamName = (TextView) retVal.findViewById(R.id.textViewTeamName);
        networkImageViewRobot = (NetworkImageView) retVal.findViewById(R.id.networkImageViewRobot);
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
        String matchKey = "";
        String scouterName = "";

        if(args != null){
            eventKey = args.getString(ARG_EVENT_KEY);
            teamKey = args.getString(ARG_TEAM_KEY);
            matchKey = args.getString(ARG_MATCH_KEY);
            scouterName = args.getString(ARG_SCOUTER_KEY);
        }

        List<Scouting> scoutingData = DatabaseHelper.getInstance(getActivity()).getScouting(eventKey, teamKey, matchKey, scouterName);
    }
}
