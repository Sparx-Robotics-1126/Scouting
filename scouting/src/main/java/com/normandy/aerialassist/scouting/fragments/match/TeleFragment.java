package com.normandy.aerialassist.scouting.fragments.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.normandy.aerialassist.scouting.R;

/**
 * Created by jbass on 2/20/14.
 */
public class TeleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View retVal =  inflater.inflate(R.layout.fragment_tele, container, false);

        return retVal;
    }

}
