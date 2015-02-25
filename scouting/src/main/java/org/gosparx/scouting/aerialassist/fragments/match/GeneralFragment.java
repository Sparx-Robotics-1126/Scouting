package org.gosparx.scouting.aerialassist.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import org.gosparx.scouting.aerialassist.R;
import org.gosparx.scouting.aerialassist.controls.HorizontalNumberPicker;
import org.gosparx.scouting.aerialassist.dto.ScoutingGeneral;

/**
 * Created by jbass on 2/20/14.
 */
public class GeneralFragment extends Fragment{

    private HorizontalNumberPicker npPenalties;
    private HorizontalNumberPicker npStacksTippedOver;
    private HorizontalNumberPicker npFailedAttemptsRConStack;
    private HorizontalNumberPicker npRCTakenFromStep;
    private Switch switchDead;
    private Switch switchTippedRobot;
    private HorizontalNumberPicker npTotesFromHP;
    private HorizontalNumberPicker npTotesAttemptFromHP;
    private HorizontalNumberPicker npTotesFromLandfill;
    private EditText editTextPenaltyComments;
    private EditText editTextGeneralComments;

    private ScoutingGeneral sg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_general, container, false);

        npPenalties = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerPenalties);
        npStacksTippedOver = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerStacksTipped);
        npFailedAttemptsRConStack = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerFailedRC);
        npRCTakenFromStep = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerRCTakenFromStep);
        switchDead = (Switch) retVal.findViewById(R.id.switchDead);
        switchTippedRobot = (Switch) retVal.findViewById(R.id.switchFlipped);
        npTotesFromHP = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerTotesAcquiredFromHP);
        npTotesAttemptFromHP = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerTotesAttemptFromHP);
        npTotesFromLandfill = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerTotesFromLandfill);
        editTextPenaltyComments = (EditText) retVal.findViewById(R.id.editTextPenaltiesComments);
        editTextGeneralComments = (EditText) retVal.findViewById(R.id.editTextGeneralComments);

        return retVal;
    }

    @Override
    public void onResume(){
        super.onResume();
        
        if(sg != null){
            npPenalties.setValue(sg.getNumberOfPenalties());
            npStacksTippedOver.setValue(sg.getNumberOfStacksTipped());
            npFailedAttemptsRConStack.setValue(sg.getNumberOfFailedAttemptsOfRC());
            npRCTakenFromStep.setValue(sg.getNumberOfRCTakenFromStep());
            switchDead.setChecked(sg.getIsDead());
            switchTippedRobot.setChecked(sg.getIsTipped());
            npTotesFromHP.setValue(sg.getNumberOfTotesAcquiredFromHP());
            npTotesAttemptFromHP.setValue(sg.getNumberOfTotesAttemptedFromHP());
            npTotesFromLandfill.setValue(sg.getNumberOfTotesFromLandfill());
            editTextPenaltyComments.setText(sg.getCommentsOnPenalties());
            editTextGeneralComments.setText(sg.getGeneralComments());
        }
    }

    public void setScoutingGeneral(ScoutingGeneral sg){
        this.sg = sg;
    }

    public ScoutingGeneral getScoutingGeneral(){
        if(sg == null)
            sg = new ScoutingGeneral();

        if(npPenalties != null) {
            sg.setNumberOfPenalties(npPenalties.getValue());
            sg.setCommentsOnPenalties(editTextPenaltyComments.getText().toString());
            sg.setNumberOfStacksTipped(npStacksTippedOver.getValue());
            sg.setNumberOfFailedAttemptsOfRC(npFailedAttemptsRConStack.getValue());
            sg.setNumberOfRCTakenFromStep(npRCTakenFromStep.getValue());
            sg.setIsDead(switchDead.isChecked());
            sg.setIsTipped(switchTippedRobot.isChecked());
            sg.setNumberOfTotesAcquiredFromHP(npTotesFromHP.getValue());
            sg.setNumberOfTotesAttemptedFromHP(npTotesAttemptFromHP.getValue());
            sg.setNumberOfTotesFromLandfill(npTotesFromLandfill.getValue());
            sg.setGeneralComments(editTextGeneralComments.getText().toString());
        }
        return sg;
    }
}
