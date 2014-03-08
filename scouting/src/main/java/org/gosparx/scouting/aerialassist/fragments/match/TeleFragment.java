package org.gosparx.scouting.aerialassist.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import org.gosparx.scouting.aerialassist.R;

import org.gosparx.scouting.aerialassist.dto.ScoutingTele;

/**
 * Created by jbass on 2/20/14.
 */
public class TeleFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBarNumberBallsAcqFromFloor;
    private EditText editTextNumberBallsAcqFromFloor;

    private SeekBar seekBarCompletedAssistFromFloor;
    private EditText editTextCompletedAssistFromFloor;

    private SeekBar seekBarNumberBallsAcqFromHuman;
    private EditText editTextNumberBallsAcqFromHuman;

    private SeekBar seekBarCompletedAssistFromHuman;
    private EditText editTextCompletedAssistFromHuman;

    private SeekBar seekBarShotHigh;
    private EditText editTextShotHigh;

    private SeekBar seekBarScoredHigh;
    private EditText editTextScoredHigh;

    private SeekBar seekBarShotLow;
    private EditText editTextShotLow;

    private SeekBar seekBarScoredLow;
    private EditText editTextScoredLow;

    private SeekBar seekBarBallCaughtOverTruss;
    private EditText editTextBallCaughtOverTruss;

    private SeekBar seekBarBallThrownOverTruss;
    private EditText editTextBallThrownOverTruss;

    private Spinner spinnerZoneSpentMostTime;

    private ScoutingTele st;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_tele, container, false);

        seekBarNumberBallsAcqFromFloor = (SeekBar) retVal.findViewById(R.id.seekBarNumBallsAcqFromFloor);
        editTextNumberBallsAcqFromFloor = (EditText) retVal.findViewById(R.id.editTextNumBallsAcqFromFloor);
        seekBarNumberBallsAcqFromFloor.setTag(editTextNumberBallsAcqFromFloor);
        seekBarNumberBallsAcqFromFloor.setOnSeekBarChangeListener(this);


        seekBarCompletedAssistFromFloor = (SeekBar) retVal.findViewById(R.id.seekBarAssistCompletedFromFloor);
        editTextCompletedAssistFromFloor = (EditText) retVal.findViewById(R.id.editTextAssistCompletedFromFloor);
        seekBarCompletedAssistFromFloor.setTag(editTextCompletedAssistFromFloor);
        seekBarCompletedAssistFromFloor.setOnSeekBarChangeListener(this);


        seekBarNumberBallsAcqFromHuman = (SeekBar) retVal.findViewById(R.id.seekBarNumBallsAcqFromHuman);
        editTextNumberBallsAcqFromHuman = (EditText) retVal.findViewById(R.id.editTextNumBallsAcqFromHuman);
        seekBarNumberBallsAcqFromHuman.setTag(editTextNumberBallsAcqFromHuman);
        seekBarNumberBallsAcqFromHuman.setOnSeekBarChangeListener(this);


        seekBarCompletedAssistFromHuman = (SeekBar) retVal.findViewById(R.id.seekBarAssistCompletedFromHuman);
        editTextCompletedAssistFromHuman = (EditText) retVal.findViewById(R.id.editTextAssistCompletedFromHuman);
        seekBarCompletedAssistFromHuman.setTag(editTextCompletedAssistFromHuman);
        seekBarCompletedAssistFromHuman.setOnSeekBarChangeListener(this);


        seekBarShotHigh = (SeekBar) retVal.findViewById(R.id.seekBarShotHigh);
        editTextShotHigh = (EditText) retVal.findViewById(R.id.editTextShotHigh);
        seekBarShotHigh.setTag(editTextShotHigh);
        seekBarShotHigh.setOnSeekBarChangeListener(this);


        seekBarShotHigh = (SeekBar) retVal.findViewById(R.id.seekBarShotHigh);
        editTextShotHigh = (EditText) retVal.findViewById(R.id.editTextShotHigh);
        seekBarShotHigh.setTag(editTextShotHigh);
        seekBarShotHigh.setOnSeekBarChangeListener(this);


        seekBarScoredHigh = (SeekBar) retVal.findViewById(R.id.seekBarScoredHigh);
        editTextScoredHigh = (EditText) retVal.findViewById(R.id.editTextScoredHigh);
        seekBarScoredHigh.setTag(editTextScoredHigh);
        seekBarScoredHigh.setOnSeekBarChangeListener(this);


        seekBarShotLow = (SeekBar) retVal.findViewById(R.id.seekBarShotLow);
        editTextShotLow = (EditText) retVal.findViewById(R.id.editTextShotLow);
        seekBarShotLow.setTag(editTextShotLow);
        seekBarShotLow.setOnSeekBarChangeListener(this);


        seekBarScoredLow = (SeekBar) retVal.findViewById(R.id.seekBarScoredLow);
        editTextScoredLow = (EditText) retVal.findViewById(R.id.editTextScoredLow);
        seekBarScoredLow.setTag(editTextScoredLow);
        seekBarScoredLow.setOnSeekBarChangeListener(this);


        seekBarBallCaughtOverTruss = (SeekBar) retVal.findViewById(R.id.seekBarCaughtOverTruss);
        editTextBallCaughtOverTruss  = (EditText) retVal.findViewById(R.id.editTextCaughtOverTruss);
        seekBarBallCaughtOverTruss.setTag(editTextBallCaughtOverTruss);
        seekBarBallCaughtOverTruss.setOnSeekBarChangeListener(this);


        seekBarBallThrownOverTruss = (SeekBar) retVal.findViewById(R.id.seekBarThrownOverTruss);
        editTextBallThrownOverTruss = (EditText) retVal.findViewById(R.id.editTextThrownOverTruss);
        seekBarBallThrownOverTruss.setTag(editTextBallThrownOverTruss);
        seekBarBallThrownOverTruss.setOnSeekBarChangeListener(this);

        spinnerZoneSpentMostTime = (Spinner) retVal.findViewById(R.id.spinnerZoneMostPlayed);

        if(st != null){
            seekBarNumberBallsAcqFromFloor.setProgress(st.getBallsAcquiredFromFloor());
            seekBarCompletedAssistFromFloor.setProgress(st.getCompletedAssistsFromFloor());
            seekBarNumberBallsAcqFromHuman.setProgress(st.getBallsAcquiredFromHuman());
            seekBarCompletedAssistFromHuman.setProgress(st.getCompletedAssistsFromHuman());
            seekBarShotHigh.setProgress(st.getShotHigh());
            seekBarScoredHigh.setProgress(st.getScoredHigh());
            seekBarShotLow.setProgress(st.getShotLow());
            seekBarScoredLow.setProgress(st.getScoredLow());
            seekBarBallCaughtOverTruss.setProgress(st.getBallsCaughtOverTruss());
            seekBarBallThrownOverTruss.setProgress(st.getBallsThrownOverTruss());
            String[] zones = getResources().getStringArray(R.array.zones);
            for (int i = 0; i < zones.length; i++) {
                if (zones[i].equals(st.getStayedInZone())) {
                    spinnerZoneSpentMostTime.setSelection(i);
                    break;
                }
            }
        }

        return retVal;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        EditText et = (EditText) seekBar.getTag();
        if(et != null)
            et.setText(Integer.toString(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    public void setScoutingTele(ScoutingTele st){
        this.st = st;
    }

    public ScoutingTele getScoutingTele(){
        if(st == null)
            st = new ScoutingTele();

        if (editTextNumberBallsAcqFromFloor != null) {
            st.setBallsAcquiredFromFloor(Integer.parseInt(editTextNumberBallsAcqFromFloor.getText().toString()));
            st.setCompletedAssistsFromFloor(Integer.parseInt(editTextCompletedAssistFromFloor.getText().toString()));
            st.setBallsAcquiredFromHuman(Integer.parseInt(editTextNumberBallsAcqFromHuman.getText().toString()));
            st.setCompletedAssistsFromHuman(Integer.parseInt(editTextCompletedAssistFromHuman.getText().toString()));
            st.setShotHigh(Integer.parseInt(editTextShotHigh.getText().toString()));
            st.setScoredHigh(Integer.parseInt(editTextScoredHigh.getText().toString()));
            st.setShotLow(Integer.parseInt(editTextShotLow.getText().toString()));
            st.setScoredLow(Integer.parseInt(editTextScoredLow.getText().toString()));
            st.setBallsCaughtOverTruss(Integer.parseInt(editTextBallCaughtOverTruss.getText().toString()));
            st.setBallsThrownOverTruss(Integer.parseInt(editTextBallThrownOverTruss.getText().toString()));
            st.setStayedInZone(spinnerZoneSpentMostTime.getSelectedItem().toString());
        }
        return st;
    }
}
