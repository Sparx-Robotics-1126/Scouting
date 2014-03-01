package com.normandy.aerialassist.scouting.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import com.normandy.aerialassist.scouting.R;

/**
 * Created by jbass on 2/20/14.
 */
public class GeneralFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private Switch switchPlaysDefence;

    private SeekBar seekBarNumberPenalties;
    private EditText editTextNumberPenalties;
    private EditText editTextPenaltyComments;

    private SeekBar seekBarNumberTechnicalFouls;
    private EditText editTextNumberTechnicalFouls;
    private EditText editTextTechnicalFoulsComments;

    private EditText editTextGeneralComments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_general, container, false);

        switchPlaysDefence = (Switch) retVal.findViewById(R.id.switchPlaysDefense);

        seekBarNumberPenalties = (SeekBar) retVal.findViewById(R.id.seekBarPenalties);
        editTextNumberPenalties = (EditText) retVal.findViewById(R.id.editTextPenalties);
        editTextPenaltyComments = (EditText) retVal.findViewById(R.id.editTextPenaltyComments);
        seekBarNumberPenalties.setTag(editTextNumberPenalties);
        seekBarNumberPenalties.setOnSeekBarChangeListener(this);


        seekBarNumberTechnicalFouls = (SeekBar) retVal.findViewById(R.id.seekBarTechnicalFouls);
        editTextNumberTechnicalFouls = (EditText) retVal.findViewById(R.id.editTextTechnicalFouls);
        editTextTechnicalFoulsComments= (EditText) retVal.findViewById(R.id.editTextTechnicalFoulComments);
        seekBarNumberTechnicalFouls.setTag(editTextNumberTechnicalFouls);
        seekBarNumberTechnicalFouls.setOnSeekBarChangeListener(this);

        editTextGeneralComments = (EditText) retVal.findViewById(R.id.editTextGeneralComments);

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
}
