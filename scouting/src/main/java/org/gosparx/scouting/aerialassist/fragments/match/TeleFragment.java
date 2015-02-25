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

    private HorizontalNumberPicker npTotesStacked1;
    private HorizontalNumberPicker npTotesStacked2;
    private HorizontalNumberPicker npTotesStacked3;
    private HorizontalNumberPicker npTotesStacked4;

    private ScoutingTele st;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_tele, container, false);

        if(retVal == null) throw new ExceptionInInitializerError("Could not inflate a view.");

        npTotesStacked1 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked1);
        npTotesStacked2 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked2);
        npTotesStacked3 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked3);
        npTotesStacked4 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked4);

        return retVal;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(st != null){
            npTotesStacked1.setValue(st.getTotesStacked1());
            npTotesStacked2.setValue(st.getTotesStacked2());
            npTotesStacked3.setValue(st.getTotesStacked3());
            npTotesStacked4.setValue(st.getTotesStacked4());
        }
    }

    public void setScoutingTele(ScoutingTele st){
        this.st = st;
    }

    public ScoutingTele getScoutingTele(){
        if(st == null)
            st = new ScoutingTele();

        if (npTotesStacked1 != null) {
            st.setTotesStacked1(npTotesStacked1.getValue());
            st.setTotesStacked2(npTotesStacked2.getValue());
            st.setTotesStacked3(npTotesStacked3.getValue());
            st.setTotesStacked4(npTotesStacked4.getValue());
        }
        return st;
    }
}
