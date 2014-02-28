package com.normandy.aerialassist.scouting.fragments.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

import com.normandy.aerialassist.scouting.R;

/**
 * Created by jbass on 2/20/14.
 */
public class AutoFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE = 1126;

    private FieldLocationSelectionFragment fieldLocationSelectionFragment;

    private Button buttonSelectStartLocation;
    private Button buttonSelectEndLocation;
    private Switch switchStartWithBall;
    private NumberPicker numberPickerBallsAcq;
    private NumberPicker numberPickerBallsShot;
    private NumberPicker numberPickerBallsScored;
    private NumberPicker numberPickerBallsScoredHotHigh;
    private NumberPicker numberPickerBallsScoredHotLow;
    private NumberPicker numberPickerBallsScoredHigh;
    private NumberPicker numberPickerBallsScoredLow;

    public AutoFragment(){
        super();

        fieldLocationSelectionFragment = new FieldLocationSelectionFragment();
        fieldLocationSelectionFragment.setTargetFragment(this, REQUEST_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_auto, container, false);

        buttonSelectStartLocation = (Button) retVal.findViewById(R.id.buttonAutoStartLocationSelect);
        buttonSelectEndLocation = (Button) retVal.findViewById(R.id.buttonAutoEndLocationSelect);
        switchStartWithBall = (Switch) retVal.findViewById(R.id.switchStartWithBall);
        numberPickerBallsAcq = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsAcq);
        numberPickerBallsShot = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsShot);
        numberPickerBallsScored  = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsScored);
        numberPickerBallsScoredHotHigh = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHotHigh);
        numberPickerBallsScoredHotLow = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHotLow);
        numberPickerBallsScoredHigh = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHigh);
        numberPickerBallsScoredLow = (NumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsLow);

        buttonSelectStartLocation.setOnClickListener(this);
        buttonSelectEndLocation.setOnClickListener(this);

        numberPickerBallsAcq.setMinValue(0);
        numberPickerBallsAcq.setMaxValue(3);

        numberPickerBallsShot.setMinValue(0);
        numberPickerBallsShot.setMaxValue(3);

        numberPickerBallsScored.setMinValue(0);
        numberPickerBallsScored.setMaxValue(3);

        numberPickerBallsScoredHotHigh.setMinValue(0);
        numberPickerBallsScoredHotHigh.setMaxValue(3);

        numberPickerBallsScoredHotLow.setMinValue(0);
        numberPickerBallsScoredHotLow.setMaxValue(3);

        numberPickerBallsScoredHigh.setMinValue(0);
        numberPickerBallsScoredHigh.setMaxValue(3);

        numberPickerBallsScoredLow.setMinValue(0);
        numberPickerBallsScoredLow.setMaxValue(3);

        return retVal;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAutoStartLocationSelect:
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelection");
                break;
            case R.id.buttonAutoEndLocationSelect:

                break;
            default:
                throw new RuntimeException("Bad view.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent){
        Toast.makeText(getActivity(), "We have returned, responseCode("+responseCode+")", Toast.LENGTH_LONG).show();

    }

}
