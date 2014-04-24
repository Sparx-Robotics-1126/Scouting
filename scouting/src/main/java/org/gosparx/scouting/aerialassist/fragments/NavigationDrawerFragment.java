package org.gosparx.scouting.aerialassist.fragments;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.gosparx.scouting.aerialassist.R;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.adapters.ScoutingDrawerAdapter;
import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.networking.BlueAlliance;
import org.gosparx.scouting.aerialassist.networking.NetworkCallback;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private ScoutingDrawerAdapter scoutingDrawerAdapter;
    private SimpleCursorAdapter teamsDrawerAdapter;
    private SimpleCursorAdapter matchesDrawerAdapter;

    private DrawerLayout mDrawerLayout;
    private Spinner spinnerRegional;
    private SimpleCursorAdapter cursorAdapterRegionalNames;
    private Spinner spinnerTaskSelection;
    private ExpandableListView mDrawerExpandableListView;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private BlueAlliance blueAlliance;
    private DatabaseHelper dbHelper;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        blueAlliance = BlueAlliance.getInstance(getActivity());
        dbHelper = DatabaseHelper.getInstance(getActivity());

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        scoutingDrawerAdapter = new ScoutingDrawerAdapter(getActivity(), dbHelper);
        teamsDrawerAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_2,
                null,
                new String[]{"team_number", "nickname"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        teamsDrawerAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                view.setTag(R.id.team_key, cursor.getString(0));
                return false;
            }
        });

        matchesDrawerAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                null,
                new String[]{"key"},
                new int[]{android.R.id.text1},
                0);
        matchesDrawerAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                StringBuilder matchString = new StringBuilder();
                String compLevel = cursor.getString(cursor.getColumnIndex("comp_level"));
                int setNumber = cursor.getInt(cursor.getColumnIndex("set_number"));
                if ("qm".equals(compLevel))
                    matchString.append("Qual ");
                else if ("qf".equals(compLevel)) {
                    matchString.append("Q/F: ");
                    matchString.append(setNumber);
                } else if ("sf".equals(compLevel)) {
                    matchString.append("S/F: ");
                    matchString.append(setNumber);
                } else if ("f".equals(compLevel)) {
                    matchString.append("Final: ");
                    matchString.append(setNumber);
                }
                matchString.append(" Match: ").append(cursor.getInt(cursor.getColumnIndex("match_number")));

                ((TextView) view).setText(matchString.toString());
                view.setTag(R.id.match_key, cursor.getString(cursor.getColumnIndex("key")));

                return true;
            }
        });

        spinnerRegional = (Spinner) mainView.findViewById(R.id.spinnerRegional);
        cursorAdapterRegionalNames = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_spinner_item,
                dbHelper.createEventNameCursor(),
                new String[]{"short_name"},
                new int[]{android.R.id.text1}, 0);
        cursorAdapterRegionalNames.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cursorAdapterRegionalNames.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                view.setTag(cursor.getString(cursor.getColumnIndex("key")));
                StringBuilder textBuilder = new StringBuilder(cursor.getString(cursor.getColumnIndex("start_date")).substring(0,10));
                textBuilder.append(" | ").append(cursor.getString(i));
                if(view instanceof TextView)
                    ((TextView) view).setText(textBuilder.toString());
                return true;
            }
        });
        spinnerRegional.setAdapter(cursorAdapterRegionalNames);
        spinnerRegional.setOnItemSelectedListener(this);

        spinnerTaskSelection = (Spinner) mainView.findViewById(R.id.spinnerTaskSelection);
        spinnerTaskSelection.setOnItemSelectedListener(this);

        mDrawerExpandableListView = (ExpandableListView) mainView.findViewById(R.id.expandableListViewDrawerContent);
        mDrawerExpandableListView.setAdapter(scoutingDrawerAdapter);
        mDrawerExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPos, int childPos, long id) {
                switch (spinnerTaskSelection.getSelectedItemPosition()) {
                    case 0:
                        if (mCallbacks != null && spinnerRegional != null) {
                            // spinner tag == Regional Key && view tag == team key
                            TextView tv = (TextView) view.findViewById(android.R.id.text1);
                            mCallbacks.onScoutingTeamSelected(
                                    (String) spinnerRegional.getSelectedView().getTag(),
                                    (String) tv.getTag(R.id.team_key),
                                    (String) tv.getTag(R.id.match_key));
                        }
                        break;
                }
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

        mDrawerListView = (ListView) mainView.findViewById(R.id.listViewDrawerContent);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                switch (spinnerTaskSelection.getSelectedItemPosition()){
                    case 1: // Matches
                        mCallbacks.onMatchSelected((String) tv.getTag(R.id.match_key));
                        break;
                    case 2: // Teams
                        mCallbacks.onTeamSelected(getSelectedEvent().getKey(), (String) tv.getTag(R.id.team_key));
                        break;
                }
                mDrawerLayout.closeDrawers();
            }
        });

        return mainView;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                updateDrawerData();

                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Event current = getSelectedEvent();
        switch (parent.getId()) {
            case R.id.spinnerRegional:
                if (current != null) {
                    updateDrawerData();
                    blueAlliance.loadMatches(current, new NetworkCallback() {
                        @Override
                        public void handleFinishDownload(boolean success) {
                            updateDrawerData();
                        }
                    });
                    blueAlliance.loadTeams(current, new NetworkCallback() {
                        @Override
                        public void handleFinishDownload(boolean success) {
                            updateDrawerData();
                        }
                    });
                }
                break;

            case R.id.spinnerTaskSelection:
                if(current != null){
                    if(spinnerTaskSelection.getSelectedItem().equals(getString(R.string.scouting))){
                        mDrawerExpandableListView.setVisibility(View.VISIBLE);
                        mDrawerListView.setVisibility(View.GONE);
                        mDrawerExpandableListView.setAdapter(scoutingDrawerAdapter);
                    }else if(spinnerTaskSelection.getSelectedItem().equals(getString(R.string.teams))){
                        mDrawerExpandableListView.setVisibility(View.GONE);
                        mDrawerListView.setVisibility(View.VISIBLE);
                        mDrawerListView.setAdapter(teamsDrawerAdapter);
                    }else if(spinnerTaskSelection.getSelectedItem().equals(getString(R.string.matches))){
                        mDrawerExpandableListView.setVisibility(View.GONE);
                        mDrawerListView.setVisibility(View.VISIBLE);
                        mDrawerListView.setAdapter(matchesDrawerAdapter);
                    }
                    updateDrawerData();
                }
                break;
        }
    }

    public void updateDrawerData(){
        if(getActivity() == null)
            return;
        NavigationDrawerFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cursorAdapterRegionalNames.changeCursor(dbHelper.createEventNameCursor());
                if(spinnerRegional != null && spinnerRegional.getSelectedView() != null){
                    Event currentEvent= dbHelper.getEvent((String) spinnerRegional.getSelectedView().getTag());
                    scoutingDrawerAdapter.changeCursor(dbHelper.createMatchCursor(currentEvent));
                    teamsDrawerAdapter.changeCursor(dbHelper.createTeamCursor(currentEvent));
                    matchesDrawerAdapter.changeCursor(dbHelper.createMatchCursor(currentEvent));
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public Event getSelectedEvent(){
        Event current = null;
        if(spinnerRegional != null && spinnerRegional.getSelectedView() != null)
            current= dbHelper.getEvent((String) spinnerRegional.getSelectedView().getTag());
        return current;
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {

        public void onScoutingTeamSelected(String eventId, String teamId, String matchId);

        public void onMatchSelected(String matchId);

        public void onTeamSelected(String eventId, String teamId);
    }
}
