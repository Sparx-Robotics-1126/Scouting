package org.gosparx.scouting.aerialassist.fragments.match;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import org.gosparx.scouting.aerialassist.R;

import org.gosparx.scouting.aerialassist.controls.HorizontalNumberPicker;
import org.gosparx.scouting.aerialassist.dto.ScoutingAuto;

/**
 * Created by jbass on 2/20/14.
 */
public class AutoFragment extends Fragment implements View.OnClickListener {

    private static final int START_REQUEST_CODE = 1;
    private static final int END_REQUEST_CODE = 2;

    private FieldLocationSelectionFragment fieldLocationSelectionFragment;

    private Button buttonSelectStartLocation;
    private Point startingLocation;
    private Button buttonSelectEndLocation;
    private Point endingLocation;
    private Switch switchStartWithBall;

    private HorizontalNumberPicker npBallsAcq;
    private HorizontalNumberPicker npBallsShot;
    private HorizontalNumberPicker npBallsScored;
    private HorizontalNumberPicker npBallsScoredHotHigh;
    private HorizontalNumberPicker npBallsScoredHotLow;
    private HorizontalNumberPicker npBallsScoredHigh;
    private HorizontalNumberPicker npBallsScoredLow;

    private ScoutingAuto sa;

    public AutoFragment(){
        fieldLocationSelectionFragment = new FieldLocationSelectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_auto, container, false);

        buttonSelectStartLocation = (Button) retVal.findViewById(R.id.buttonAutoStartLocationSelect);
        buttonSelectEndLocation =   (Button) retVal.findViewById(R.id.buttonAutoEndLocationSelect);
        switchStartWithBall =       (Switch) retVal.findViewById(R.id.switchStartWithBall);

        npBallsAcq =            (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsAcq);
        npBallsShot =           (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsShot);
        npBallsScored  =        (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsScored);
        npBallsScoredHotHigh =  (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHotHigh);
        npBallsScoredHotLow =   (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHotLow);
        npBallsScoredHigh =     (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsHigh);
        npBallsScoredLow =      (HorizontalNumberPicker) retVal.findViewById(R.id.numberPickerAutoNumBallsLow);

        buttonSelectStartLocation.setOnClickListener(this);
        buttonSelectEndLocation.setOnClickListener(this);

        return retVal;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(sa != null){
            startingLocation = sa.getStartingLocation();
            npBallsAcq.setValue(sa.getBallsAcquired());
            npBallsShot.setValue(sa.getBallsShot());
            npBallsScored.setValue(sa.getBallsScored());
            npBallsScoredHotHigh.setValue(sa.getBallsScoredHotHigh());
            npBallsScoredHotLow.setValue(sa.getBallsScoredHotLow());
            npBallsScoredHigh.setValue(sa.getBallsScoredHigh());
            npBallsScoredLow.setValue(sa.getBallsScoredLow());
            endingLocation = sa.getEndingLocation();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()){
            case R.id.buttonAutoStartLocationSelect:
                if(startingLocation != null && startingLocation.x > 0 && startingLocation.y > 0){
                    args.putInt(FieldLocationSelectionFragment.X_BUNDLE_KEY, startingLocation.x);
                    args.putInt(FieldLocationSelectionFragment.Y_BUNDLE_KEY, startingLocation.y);
                    fieldLocationSelectionFragment.setArguments(args);
                }
                fieldLocationSelectionFragment.setTargetFragment(this, START_REQUEST_CODE);
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelectionStart");
                break;
            case R.id.buttonAutoEndLocationSelect:
                if(endingLocation != null && endingLocation.x > 0 && endingLocation.y > 0){
                    args.putInt(FieldLocationSelectionFragment.X_BUNDLE_KEY, endingLocation.x);
                    args.putInt(FieldLocationSelectionFragment.Y_BUNDLE_KEY, endingLocation.y);
                    fieldLocationSelectionFragment.setArguments(args);
                }
                fieldLocationSelectionFragment.setTargetFragment(this, END_REQUEST_CODE);
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelectionEnd");
                break;
            default:
                throw new RuntimeException("Bad view.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent){
        if(responseCode < 0) {
            return;
        }
        switch (requestCode){
            case START_REQUEST_CODE:
                startingLocation = new Point(
                        intent.getIntExtra(FieldLocationSelectionFragment.X_BUNDLE_KEY, -1),
                        intent.getIntExtra(FieldLocationSelectionFragment.Y_BUNDLE_KEY, -1));
                break;
            case END_REQUEST_CODE:
                endingLocation = new Point(
                        intent.getIntExtra(FieldLocationSelectionFragment.X_BUNDLE_KEY, -1),
                        intent.getIntExtra(FieldLocationSelectionFragment.Y_BUNDLE_KEY, -1));
                break;
        }
    }

    public void setScoutingAuto(ScoutingAuto sa){
        this.sa = sa;
    }

    public ScoutingAuto getScoutingAuto(){
        if(sa == null)
            sa = new ScoutingAuto();

        if (npBallsAcq != null) {
            sa.setStartingLocation(startingLocation);
            sa.setStartedWithBall(switchStartWithBall.isChecked());
            sa.setBallsAcquired(npBallsAcq.getValue());
            sa.setBallsShot(npBallsShot.getValue());
            sa.setBallsScored(npBallsScored.getValue());
            sa.setBallsScoredHotHigh(npBallsScoredHotHigh.getValue());
            sa.setBallsScoredHotLow(npBallsScoredHotLow.getValue());
            sa.setBallsScoredHigh(npBallsScoredHigh.getValue());
            sa.setBallsScoredLow(npBallsScoredLow.getValue());
            sa.setEndingLocation(endingLocation);
        }
        return sa;
    }
}
