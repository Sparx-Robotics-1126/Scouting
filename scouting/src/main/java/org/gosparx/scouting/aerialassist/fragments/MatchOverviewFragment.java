package org.gosparx.scouting.aerialassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.gosparx.scouting.aerialassist.R;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.dto.Match;
import org.gosparx.scouting.aerialassist.dto.Scouting;
import org.gosparx.scouting.aerialassist.dto.Team;
import org.gosparx.scouting.aerialassist.fragments.match.AutoFragment;
import org.gosparx.scouting.aerialassist.fragments.match.GeneralFragment;
import org.gosparx.scouting.aerialassist.fragments.match.TeleFragment;

import java.util.List;

/**
 * Created by jbass on 2/17/14.
 */
public class MatchOverviewFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MatchOverviewFragment";

    public static final String ARG_SCOUTER_NAME = "Scouter Name";
    public static final String ARG_MATCH_ID = "Match Id";
    public static final String ARG_TEAM_ID = "Team Id";
    public static final String ARG_EVENT_ID = "Event Id";

    private TextView textViewScoutingTitle;

    private Button buttonAuto;
    private Button buttonTele;
    private Button buttonGeneral;

    private AutoFragment autoFragment;
    private TeleFragment teleFragment;
    private GeneralFragment generalFragment;

    private DatabaseHelper dbHelper;
    private Scouting scouting;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String teamId = getArguments().getString(ARG_TEAM_ID);
        String eventId = getArguments().getString(ARG_EVENT_ID);
        String matchId = getArguments().getString(ARG_MATCH_ID);
        String scouterName = getArguments().getString(ARG_SCOUTER_NAME);

        dbHelper = DatabaseHelper.getInstance(getActivity());
        List<Scouting> scoutingList = dbHelper.getScouting(
                eventId,
                teamId,
                matchId,
                scouterName);

        if (scoutingList.size() > 1)
            Log.w(TAG, "Multiple sets of scouting data by same scouter!");

        // Grab the last element, should be latest
        if (scoutingList != null && !scoutingList.isEmpty())
            scouting = scoutingList.get(scoutingList.size() - 1);

        if(scouting == null){
            Log.d(TAG, "Creating new scouting data.");
            scouting = new Scouting();
            scouting.setMatchKey(matchId);
            scouting.setTeamKey(teamId);
            scouting.setEventKey(eventId);
            scouting.setNameOfScouter(scouterName);
        }

        autoFragment = new AutoFragment();
        autoFragment.setScoutingAuto(scouting.getAuto());
        teleFragment = new TeleFragment();
        teleFragment.setScoutingTele(scouting.getTele());
        generalFragment = new GeneralFragment();
        generalFragment.setScoutingGeneral(scouting.getGeneral());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_match_overview, container, false);

        textViewScoutingTitle = (TextView) retVal.findViewById(R.id.textViewScoutingTitle);
        Team team = dbHelper.getTeam(scouting.getTeamKey());
        textViewScoutingTitle.setText(team.getTeamNumber() + " | " + team.getNickname());

        // Linking
        buttonAuto = (Button) retVal.findViewById(R.id.buttonAuto);
        buttonTele = (Button) retVal.findViewById(R.id.buttonTele);
        buttonGeneral = (Button) retVal.findViewById(R.id.buttonGeneral);

        // Setup handlers
        buttonAuto.setOnClickListener(this);
        buttonTele.setOnClickListener(this);
        buttonGeneral.setOnClickListener(this);

        onClick(buttonAuto);

        return retVal;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.buttonAuto:
                fragmentTransaction.replace(R.id.matchOverviewContent, autoFragment);
                break;
            case R.id.buttonTele:
                fragmentTransaction.replace(R.id.matchOverviewContent, teleFragment);
                break;
            case R.id.buttonGeneral:
                fragmentTransaction.replace(R.id.matchOverviewContent, generalFragment);
                break;
            default:
                throw new RuntimeException("Invalid button handle!");
        }
        saveData();

        fragmentTransaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData(){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity());
        scouting.setAuto(autoFragment.getScoutingAuto());
        scouting.setTele(teleFragment.getScoutingTele());
        scouting.setGeneral(generalFragment.getScoutingGeneral());

        if(dbHelper.doesScoutingExist(scouting)) {
            dbHelper.updateScouting(scouting);
            Log.d(TAG, "Updating scouting data.  Team("+scouting.getTeamKey()+") Match("+scouting.getMatchKey()+")");
        }
        else {
            dbHelper.createScouting(scouting);
            Log.d(TAG, "Saving scouting data.  Team("+scouting.getTeamKey()+") Match("+scouting.getMatchKey()+")");
        }
    }
}
