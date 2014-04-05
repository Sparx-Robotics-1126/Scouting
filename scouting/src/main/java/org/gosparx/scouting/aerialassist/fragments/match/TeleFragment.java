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

import org.gosparx.scouting.aerialassist.controls.HorizontalNumberPicker;
import org.gosparx.scouting.aerialassist.dto.ScoutingTele;

/**
 * Created by jbass on 2/20/14.
 */
public class TeleFragment extends Fragment{

    private HorizontalNumberPicker npBallsAcqFromFloor;
    private HorizontalNumberPicker npCompletedAssistFromFloor;
    private HorizontalNumberPicker npBallsAcqFromHuman;;
    private HorizontalNumberPicker npCompletedAssistFromHuman;
    private HorizontalNumberPicker npShotHigh;
    private HorizontalNumberPicker npScoredHigh;
    private HorizontalNumberPicker npShotLow;
    private HorizontalNumberPicker npScoredLow;
    private HorizontalNumberPicker npBallCaughtOverTruss;
    private HorizontalNumberPicker npBallThrownOverTruss;
    private Spinner spinnerZoneSpentMostTime;

    private ScoutingTele st;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_tele, container, false);

        if(retVal == null) throw new ExceptionInInitializerError("Could not inflate a view.");

        npBallsAcqFromFloor = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerBallsAcqFromFloor);
        npCompletedAssistFromFloor = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAssistCompletedFromFloor);
        npBallsAcqFromHuman = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerBallsAcqFromHuman);
        npCompletedAssistFromHuman = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAssistCompletedFromHuman);
        npShotHigh = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerShotHigh);
        npScoredHigh = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerScoredHigh);
        npShotLow = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerShotLow);
        npScoredLow = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerScoredLow);
        npBallCaughtOverTruss = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerCaughtOverTruss);
        npBallThrownOverTruss = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerThrownOverTruss);

        spinnerZoneSpentMostTime = (Spinner) retVal.findViewById(R.id.spinnerZoneMostPlayed);

        return retVal;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(st != null){
            npBallsAcqFromFloor.setValue(st.getBallsAcquiredFromFloor());
            npCompletedAssistFromFloor.setValue(st.getCompletedAssistsFromFloor());
            npBallsAcqFromHuman.setValue(st.getBallsAcquiredFromHuman());
            npCompletedAssistFromHuman.setValue(st.getCompletedAssistsFromHuman());
            npShotHigh.setValue(st.getShotHigh());
            npScoredHigh.setValue(st.getScoredHigh());
            npShotLow.setValue(st.getShotLow());
            npScoredLow.setValue(st.getScoredLow());
            npBallCaughtOverTruss.setValue(st.getBallsCaughtOverTruss());
            npBallThrownOverTruss.setValue(st.getBallsThrownOverTruss());
            String[] zones = getResources().getStringArray(R.array.zones);
            for (int i = 0; i < zones.length; i++) {
                if (zones[i].equals(st.getStayedInZone())) {
                    spinnerZoneSpentMostTime.setSelection(i);
                    break;
                }
            }
        }
    }

    public void setScoutingTele(ScoutingTele st){
        this.st = st;
    }

    public ScoutingTele getScoutingTele(){
        if(st == null)
            st = new ScoutingTele();

        if (npBallsAcqFromFloor != null) {
            st.setBallsAcquiredFromFloor(npBallsAcqFromFloor.getValue());
            st.setCompletedAssistsFromFloor(npCompletedAssistFromFloor.getValue());
            st.setBallsAcquiredFromHuman(npBallsAcqFromHuman.getValue());
            st.setCompletedAssistsFromHuman(npCompletedAssistFromHuman.getValue());
            st.setShotHigh(npShotHigh.getValue());
            st.setScoredHigh(npScoredHigh.getValue());
            st.setShotLow(npShotLow.getValue());
            st.setScoredLow(npScoredLow.getValue());
            st.setBallsCaughtOverTruss(npBallCaughtOverTruss.getValue());
            st.setBallsThrownOverTruss(npBallThrownOverTruss.getValue());
            if(spinnerZoneSpentMostTime != null && spinnerZoneSpentMostTime.getSelectedItem() != null)
                st.setStayedInZone(spinnerZoneSpentMostTime.getSelectedItem().toString());
        }
        return st;
    }
}
