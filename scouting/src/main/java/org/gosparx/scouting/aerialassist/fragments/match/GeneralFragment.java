package org.gosparx.scouting.aerialassist.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import org.gosparx.scouting.aerialassist.R;
import org.gosparx.scouting.aerialassist.controls.HorizontalNumberPicker;
import org.gosparx.scouting.aerialassist.dto.ScoutingGeneral;

/**
 * Created by jbass on 2/20/14.
 */
public class GeneralFragment extends Fragment{

    private Switch switchPlaysDefence;

    private HorizontalNumberPicker npPenalties;
    private EditText editTextPenaltyComments;

    private HorizontalNumberPicker npTechnicalFouls;
    private EditText editTextTechnicalFoulComments;

    private EditText editTextGeneralComments;

    private ScoutingGeneral sg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_general, container, false);

        switchPlaysDefence = (Switch) retVal.findViewById(R.id.switchPlaysDefense);

        npPenalties = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerPenalties);
        editTextPenaltyComments = (EditText) retVal.findViewById(R.id.editTextPenaltyComments);

        npTechnicalFouls = (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerTechnicalFouls);
        editTextTechnicalFoulComments = (EditText) retVal.findViewById(R.id.editTextTechnicalFoulComments);

        editTextGeneralComments = (EditText) retVal.findViewById(R.id.editTextGeneralComments);

        if(sg != null){
            switchPlaysDefence.setChecked(sg.isPlaysDefense());
            npPenalties.setValue(sg.getNumberOfPenalties());
            editTextPenaltyComments.setText(sg.getCommentsOnPenalties());
            npTechnicalFouls.setValue(sg.getNumberOfTechnicalFouls());
            editTextTechnicalFoulComments.setText(sg.getCommentsOnTechnicalFouls());
            editTextGeneralComments.setText(sg.getGeneralComments());
        }

        return retVal;
    }

    public void setScoutingGeneral(ScoutingGeneral sg){
        this.sg = sg;
    }

    public ScoutingGeneral getScoutingGeneral(){
        if(sg == null)
            sg = new ScoutingGeneral();

        if (switchPlaysDefence != null) {
            sg.setPlaysDefense(switchPlaysDefence.isChecked());
            sg.setNumberOfPenalties(npPenalties.getValue());
            sg.setCommentsOnPenalties(editTextPenaltyComments.getText().toString());
            sg.setNumberOfTechnicalFouls(npTechnicalFouls.getValue());
            sg.setCommentsOnTechnicalFouls(editTextTechnicalFoulComments.getText().toString());
            sg.setGeneralComments(editTextGeneralComments.getText().toString());
        }
        return sg;
    }
}
