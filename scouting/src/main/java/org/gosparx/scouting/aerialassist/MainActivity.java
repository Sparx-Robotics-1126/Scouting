package org.gosparx.scouting.aerialassist;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import org.gosparx.scouting.aerialassist.fragments.MatchOverviewFragment;
import org.gosparx.scouting.aerialassist.fragments.NavigationDrawerFragment;
import org.gosparx.scouting.aerialassist.networking.BlueAlliance;

public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String NAME_PREFERENCE = "Name of Scouter";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_download_data) {
            BlueAlliance ba = new BlueAlliance(this);
            ba.loadEvents(2013);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScoutingTeamSelected(String matchId, String teamId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MatchOverviewFragment matchOverviewFragment = new MatchOverviewFragment();
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        Bundle args = new Bundle();
        args.putString(MatchOverviewFragment.ARG_SCOUTER_NAME, sp.getString(NAME_PREFERENCE, "Unknown"));
        args.putString(MatchOverviewFragment.ARG_MATCH_ID, matchId);
        args.putString(MatchOverviewFragment.ARG_TEAM_ID, teamId);
        matchOverviewFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.container, matchOverviewFragment).commit();
    }

    @Override
    public void onMatchSelected(String matchId) {
        Toast.makeText(this, "Match "+matchId+" selected.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTeamSelected(String teamId) {
        Toast.makeText(this, "Team "+teamId+" selected.", Toast.LENGTH_SHORT).show();
    }
}
