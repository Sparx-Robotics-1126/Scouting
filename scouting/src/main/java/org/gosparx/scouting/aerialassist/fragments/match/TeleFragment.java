package org.gosparx.scouting.aerialassist.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

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
    private Switch swRCStacked1;
    private Switch swRCStacked2;
    private Switch swRCStacked3;
    private Switch swRCStacked4;
    private Switch swLitter1;
    private Switch swLitter2;
    private Switch swLitter3;
    private Switch swLitter4;

    private ScoutingTele st;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_tele, container, false);

        if(retVal == null) throw new ExceptionInInitializerError("Could not inflate a view.");

        npTotesStacked1 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked1);
        npTotesStacked2 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked2);
        npTotesStacked3 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked3);
        npTotesStacked4 = (HorizontalNumberPicker)retVal.findViewById(R.id.totesStacked4);

        swRCStacked1 = (Switch)retVal.findViewById(R.id.rcStacked1);
        swRCStacked2 = (Switch)retVal.findViewById(R.id.rcStacked2);
        swRCStacked3 = (Switch)retVal.findViewById(R.id.rcStacked3);
        swRCStacked4 = (Switch)retVal.findViewById(R.id.rcStacked4);

        swLitter1 = (Switch)retVal.findViewById(R.id.litter1);
        swLitter2 = (Switch)retVal.findViewById(R.id.litter2);
        swLitter3 = (Switch)retVal.findViewById(R.id.litter3);
        swLitter4 = (Switch)retVal.findViewById(R.id.litter4);

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

            swRCStacked1.setChecked(st.getRCStacked1());
            swRCStacked2.setChecked(st.getRCStacked2());
            swRCStacked3.setChecked(st.getRCStacked3());
            swRCStacked4.setChecked(st.getRCStacked4());

            swLitter1.setChecked(st.getLitter1());
            swLitter2.setChecked(st.getLitter2());
            swLitter3.setChecked(st.getLitter3());
            swLitter4.setChecked(st.getLitter4());
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

            st.setRCStacked1(swRCStacked1.isChecked());
            st.setRCStacked2(swRCStacked2.isChecked());
            st.setRCStacked3(swRCStacked3.isChecked());
            st.setRCStacked4(swRCStacked4.isChecked());

            st.setLitter1(swLitter1.isChecked());
            st.setLitter2(swLitter2.isChecked());
            st.setLitter3(swLitter3.isChecked());
            st.setLitter4(swLitter4.isChecked());
        }
        return st;
    }
}
