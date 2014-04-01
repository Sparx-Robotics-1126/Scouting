package org.gosparx.scouting.aerialassist;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.fragments.MatchOverviewFragment;
import org.gosparx.scouting.aerialassist.fragments.NavigationDrawerFragment;
import org.gosparx.scouting.aerialassist.networking.BlueAlliance;
import org.gosparx.scouting.aerialassist.networking.NetworkCallback;
import org.gosparx.scouting.aerialassist.networking.SparxScouting;

public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String PREFERENCE_KEY = "AerialAssist";
    public static final String NAME_PREFERENCE = "Name of Scouter";

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

        final Dialog alert = createDialog();
        alert.show();
        BlueAlliance ba = BlueAlliance.getInstance(this);
        ba.loadEventList(2014, new NetworkCallback(){
            @Override
            public void handleFinishDownload(final boolean success) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!success)
                            Toast.makeText(MainActivity.this, "Did not successfully download event list!", Toast.LENGTH_LONG).show();
                        alert.dismiss();
                        mNavigationDrawerFragment.updateDrawerData();
                    }
                });
            }
        });
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
        switch (item.getItemId()){
            case R.id.action_download_data:
                downloadData();
                break;

            case R.id.action_upload_data:
                SparxScouting.getInstance(this).postAllScouting();
                break;

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScoutingTeamSelected(String eventId, String teamId, String matchId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MatchOverviewFragment matchOverviewFragment = new MatchOverviewFragment();
        SharedPreferences sp = getSharedPreferences(PREFERENCE_KEY, MODE_PRIVATE);
        Bundle args = new Bundle();
        args.putString(MatchOverviewFragment.ARG_SCOUTER_NAME, sp.getString(NAME_PREFERENCE, "Unknown"));
        args.putString(MatchOverviewFragment.ARG_MATCH_ID, matchId);
        args.putString(MatchOverviewFragment.ARG_TEAM_ID, teamId);
        args.putString(MatchOverviewFragment.ARG_EVENT_ID, eventId);
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

    private void downloadData(){
        Event event = mNavigationDrawerFragment.getSelectedEvent();
        if(event != null) {
            final AlertDialog dialog = createDialog();
            dialog.show();
            NetworkCallback subBack = new NetworkCallback() {
                int numCalls = 2;
                @Override
                public void handleFinishDownload(boolean success) {
                    if(!success) {
                        Toast.makeText(MainActivity.this, "Issue downloading data", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                    else
                        numCalls--;

                    if(numCalls <= 0) {
                        dialog.dismiss();
                        mNavigationDrawerFragment.updateDrawerData();
                    }
                }
            };
            BlueAlliance.getInstance(this).loadTeams(event, subBack);
            BlueAlliance.getInstance(this).loadMatches(event, subBack);
        }else
            Toast.makeText(this, "No event selected!", Toast.LENGTH_LONG).show();
    }

    private AlertDialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.downloading_data);
        builder.setMessage(R.string.please_wait_while_data_downloads);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BlueAlliance.getInstance(MainActivity.this).cancelAll();
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}
