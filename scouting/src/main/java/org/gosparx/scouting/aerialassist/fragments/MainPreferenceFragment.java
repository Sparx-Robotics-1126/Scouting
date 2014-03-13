package org.gosparx.scouting.aerialassist.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import org.gosparx.scouting.aerialassist.R;

/**
 * Created by jbass on 3/10/14.
 */
public class MainPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }
}
