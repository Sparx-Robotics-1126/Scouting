package org.gosparx.scouting.aerialassist.fragments.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private double startingLocationX;
    private double startingLocationY;
    private Button buttonSelectEndLocation;
    private double endingLocationX;
    private double endingLocationY;

    private Switch switchInAutoZone;
    private Switch switchCreatedStack;
    private HorizontalNumberPicker npTotesMovedToAutoZone;
    private HorizontalNumberPicker npRCMovedToAutoZone;
    private HorizontalNumberPicker npRCTakenFromStep;

    private ScoutingAuto sa;

    public AutoFragment(){
        fieldLocationSelectionFragment = new FieldLocationSelectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_auto, container, false);

        buttonSelectStartLocation = (Button) retVal.findViewById(R.id.buttonAutoStartLocationSelect);
        buttonSelectEndLocation =   (Button) retVal.findViewById(R.id.buttonAutoEndLocationSelect);

        switchInAutoZone = (Switch) retVal.findViewById(R.id.autoFullyContainedInZone);
        switchCreatedStack = (Switch) retVal.findViewById(R.id.autoCreatedStack);
        npTotesMovedToAutoZone = (HorizontalNumberPicker) retVal.findViewById(R.id.autoTotesToAutoZone);
        npRCMovedToAutoZone = (HorizontalNumberPicker) retVal.findViewById(R.id.autoRCToAutoZone);
        npRCTakenFromStep = (HorizontalNumberPicker) retVal.findViewById(R.id.autoRCFromStep);

        buttonSelectStartLocation.setOnClickListener(this);
        buttonSelectEndLocation.setOnClickListener(this);

        return retVal;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(sa != null){
            startingLocationX = sa.getStartingLocationX();
            startingLocationY = sa.getStartingLocationY();
            switchInAutoZone.setChecked(sa.getRobotInAutoZone());
            switchCreatedStack.setChecked(sa.getRobotCreateStack());
            npTotesMovedToAutoZone.setValue(sa.getTotesMovedToAutoZone());
            npRCMovedToAutoZone.setValue(sa.getrCMovedToAutoZone());
            npRCTakenFromStep.setValue(sa.getRCTakenFromStep());
            endingLocationX = sa.getEndingLocationX();
            endingLocationY = sa.getEndingLocationY();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()){
            case R.id.buttonAutoStartLocationSelect:
                if(startingLocationX > 0 && startingLocationY > 0){
                    args.putDouble(FieldLocationSelectionFragment.X_BUNDLE_KEY, startingLocationX);
                    args.putDouble(FieldLocationSelectionFragment.Y_BUNDLE_KEY, startingLocationY);
                    fieldLocationSelectionFragment.setArguments(args);
                }
                fieldLocationSelectionFragment.setTargetFragment(this, START_REQUEST_CODE);
                fieldLocationSelectionFragment.show(getFragmentManager(), "FieldLocationSelectionStart");
                break;
            case R.id.buttonAutoEndLocationSelect:
                if(endingLocationX > 0 && endingLocationY > 0){
                    args.putDouble(FieldLocationSelectionFragment.X_BUNDLE_KEY, endingLocationX);
                    args.putDouble(FieldLocationSelectionFragment.Y_BUNDLE_KEY, endingLocationY);
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
                startingLocationX = intent.getDoubleExtra(FieldLocationSelectionFragment.X_BUNDLE_KEY, -1);
                startingLocationY = intent.getDoubleExtra(FieldLocationSelectionFragment.Y_BUNDLE_KEY, -1);
                break;
            case END_REQUEST_CODE:
                endingLocationX = intent.getDoubleExtra(FieldLocationSelectionFragment.X_BUNDLE_KEY, -1);
                endingLocationY = intent.getDoubleExtra(FieldLocationSelectionFragment.Y_BUNDLE_KEY, -1);
                break;
        }
    }

    public void setScoutingAuto(ScoutingAuto sa){
        this.sa = sa;
    }

    public ScoutingAuto getScoutingAuto(){
        if(sa == null)
            sa = new ScoutingAuto();

        if (switchInAutoZone != null) {
            sa.setStartingLocationX(startingLocationX);
            sa.setStartingLocationY(startingLocationY);
            sa.setRobotInAutoZone(switchInAutoZone.isChecked());
            sa.setRobotCreateStack(switchCreatedStack.isChecked());
            sa.setTotesMovedToAutoZone(npTotesMovedToAutoZone.getValue());
            sa.setrCMovedToAutoZone(npRCMovedToAutoZone.getValue());
            sa.setRCTakenFromStep(npRCTakenFromStep.getValue());
            sa.setEndingLocationX(endingLocationX);
            sa.setEndingLocationY(endingLocationY);
        }
        return sa;
    }
}
