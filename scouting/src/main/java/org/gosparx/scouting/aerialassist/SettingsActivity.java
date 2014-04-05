package org.gosparx.scouting.aerialassist;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jbass on 3/29/14.
 */
public class SettingsActivity extends PreferenceActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
