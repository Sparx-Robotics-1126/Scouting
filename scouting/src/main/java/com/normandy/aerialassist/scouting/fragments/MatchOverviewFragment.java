package com.normandy.aerialassist.scouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.normandy.aerialassist.scouting.DatabaseHelper;
import com.normandy.aerialassist.scouting.R;
import com.normandy.aerialassist.scouting.dto.Scouting;
import com.normandy.aerialassist.scouting.fragments.match.AutoFragment;
import com.normandy.aerialassist.scouting.fragments.match.GeneralFragment;
import com.normandy.aerialassist.scouting.fragments.match.TeleFragment;

/**
 * Created by jbass on 2/17/14.
 */
public class MatchOverviewFragment extends Fragment implements View.OnClickListener {

    private Button buttonAuto;
    private Button buttonTele;
    private Button buttonGeneral;

    private AutoFragment autoFragment;
    private TeleFragment teleFragment;
    private GeneralFragment generalFragment;

    private String matchId;
    private String teamId;

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        autoFragment = new AutoFragment();
        teleFragment = new TeleFragment();
        generalFragment = new GeneralFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_match_overview, container, false);

        // Linking
        buttonAuto = (Button) retVal.findViewById(R.id.buttonAuto);
        buttonTele = (Button) retVal.findViewById(R.id.buttonTele);
        buttonGeneral = (Button) retVal.findViewById(R.id.buttonGeneral);

        // Setup handlers
        buttonAuto.setOnClickListener(this);
        buttonTele.setOnClickListener(this);
        buttonGeneral.setOnClickListener(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.matchOverviewContent, autoFragment);
        fragmentTransaction.commit();

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
        fragmentTransaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        Scouting scouting = new Scouting();
        scouting.setNameOfScouter("TEST");
        scouting.setTeamKey(teamId);
        scouting.setMatchKey(matchId);
        scouting.setAuto(autoFragment.getScoutingAuto());
        scouting.setTele(teleFragment.getScoutingTele());
        scouting.setGeneral(generalFragment.getScoutingGeneral());

        if(dbHelper.doesScoutingExist(scouting))
            dbHelper.updateScouting(scouting);
        else
            dbHelper.createScouting(scouting);
    }
}
