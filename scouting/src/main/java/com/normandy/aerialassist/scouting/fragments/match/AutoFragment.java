package com.normandy.aerialassist.scouting.fragments.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.normandy.aerialassist.scouting.R;

/**
 * Created by jbass on 2/20/14.
 */
public class AutoFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final int START_REQUEST_CODE = 1;
    private static final int END_REQUEST_CODE = 2;

    private FieldLocationSelectionFragment fieldLocationSelectionFragment;

    private Button buttonSelectStartLocation;
    private Button buttonSelectEndLocation;
    private Switch switchStartWithBall;

    private SeekBar seekBarBallsAcq;
    private SeekBar seekBarBallsShot;
    private SeekBar seekBarBallsScored;
    private SeekBar seekBarBallsScoredHotHigh;
    private SeekBar seekBarBallsScoredHotLow;
    private SeekBar seekBarBallsScoredHigh;
    private SeekBar seekBarBallsScoredLow;

    private EditText editTextBallsAcq;
    private EditText editTextBallsShot;
    private EditText editTextBallsScored;
    private EditText editTextBallsScoredHotHigh;
    private EditText editTextBallsScoredHotLow;
    private EditText editTextBallsScoredHigh;
    private EditText editTextBallsScoredLow;

    public AutoFragment(){
        fieldLocationSelectionFragment = new FieldLocationSelectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_auto, container, false);

        buttonSelectStartLocation = (Button) retVal.findViewById(R.id.buttonAutoStartLocationSelect);
        buttonSelectEndLocation =   (Button) retVal.findViewById(R.id.buttonAutoEndLocationSelect);
        switchStartWithBall =       (Switch) retVal.findViewById(R.id.switchStartWithBall);

        seekBarBallsAcq =           (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsAcq);
        seekBarBallsShot =          (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsShot);
        seekBarBallsScored  =       (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsScored);
        seekBarBallsScoredHotHigh = (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsHotHigh);
        seekBarBallsScoredHotLow =  (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsHotLow);
        seekBarBallsScoredHigh =    (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsHigh);
        seekBarBallsScoredLow =     (SeekBar) retVal.findViewById(R.id.seekBarAutoNumBallsLow);

        editTextBallsAcq =           (EditText) retVal.findViewById(R.id.editTextAutoNumBallsAcq);
        editTextBallsShot =          (EditText) retVal.findViewById(R.id.editTextAutoNumBallsShot);
        editTextBallsScored  =       (EditText) retVal.findViewById(R.id.editTextAutoNumBallsScored);
        editTextBallsScoredHotHigh = (EditText) retVal.findViewById(R.id.editTextAutoNumBallsHotHigh);
        editTextBallsScoredHotLow =  (EditText) retVal.findViewById(R.id.editTextAutoNumBallsHotLow);
        editTextBallsScoredHigh =    (EditText) retVal.findViewById(R.id.editTextAutoNumBallsHigh);
        editTextBallsScoredLow =     (EditText) retVal.findViewById(R.id.editTextAutoNumBallsLow);

        buttonSelectStartLocation.setOnClickListener(this);
        buttonSelectEndLocation.setOnClickListener(this);

        seekBarBallsAcq.setOnSeekBarChangeListener(this);
        seekBarBallsAcq.setTag(editTextBallsAcq);
        seekBarBallsShot.setOnSeekBarChangeListener(this);
        seekBarBallsShot.setTag(editTextBallsShot);
        seekBarBallsScored.setOnSeekBarChangeListener(this);
        seekBarBallsScored.setTag(editTextBallsScored);
        seekBarBallsScoredHotHigh.setOnSeekBarChangeListener(this);
        seekBarBallsScoredHotHigh.setTag(editTextBallsScoredHotHigh);
        seekBarBallsScoredHotLow.setOnSeekBarChangeListener(this);
        seekBarBallsScoredHotLow.setTag(editTextBallsScoredHotLow);
        seekBarBallsScoredHigh.setOnSeekBarChangeListener(this);
        seekBarBallsScoredHigh.setTag(editTextBallsScoredHigh);
        seekBarBallsScoredLow.setOnSeekBarChangeListener(this);
        seekBarBallsScoredLow.setTag(editTextBallsScoredLow);

        return retVal;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAutoStartLocationSelect:
                fieldLocationSelectionFragment.setTargetFragment(this, START_REQUEST_CODE);
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelectionStart");
                break;
            case R.id.buttonAutoEndLocationSelect:
                fieldLocationSelectionFragment.setTargetFragment(this, END_REQUEST_CODE);
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelectionEnd");
                break;
            default:
                throw new RuntimeException("Bad view.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent){
        Toast.makeText(getActivity(), "We have returned, responseCode("+responseCode+")", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        EditText et = (EditText) seekBar.getTag();
        if(et != null)
            et.setText("" + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
