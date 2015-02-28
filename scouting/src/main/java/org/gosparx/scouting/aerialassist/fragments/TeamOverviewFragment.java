package org.gosparx.scouting.aerialassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.R;
import org.gosparx.scouting.aerialassist.dto.Scouting;
import org.gosparx.scouting.aerialassist.dto.Team;
import org.gosparx.scouting.aerialassist.networking.NetworkCallback;
import org.gosparx.scouting.aerialassist.networking.SparxScouting;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jbass on 3/8/14.
 */
public class TeamOverviewFragment extends Fragment {

    public static final String ARG_EVENT_KEY = "event_key";
    public static final String ARG_TEAM_KEY = "team_key";

    private TextView textViewTeamNickname;
    private ImageView imageViewRobot;
    private TextView textViewOverallRecord;

    private TextView textViewAutoRobotInZone;
    private TextView textViewAutoCreatedYellowStack;
    private TextView textViewAutoNumYellowToAutoZone;
    private TextView textViewAutoNumRCMovedToAutoZone;
    private TextView textViewAutoNumRCFromStep;

    private TextView textViewTeleNumTotesStacked;
    private TextView textViewTeleRCStacked;
    private TextView textViewTeleLitter;

    private TextView textViewGenNumOfTotesFromHP;
    private TextView textViewGenNumOfTotesFromLandfill;
    private TextView textViewGenNumOfFouls;
    private TextView textViewGenNumOfTippedStacks;
    private TextView textViewGenDeadRobot;

    private EditText editTextGenPenaltyComments;
    private EditText editTextGenGeneralComments;

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retVal = inflater.inflate(R.layout.fragment_team_overview, container, false);

        textViewTeamNickname = (TextView) retVal.findViewById(R.id.textViewTeamNickname);
        imageViewRobot = (ImageView) retVal.findViewById(R.id.imageViewRobot);
        textViewOverallRecord = (TextView) retVal.findViewById(R.id.textViewOverallRecord);

        textViewAutoRobotInZone = (TextView) retVal.findViewById(R.id.textViewAutoRobotInZone);
        textViewAutoCreatedYellowStack = (TextView) retVal.findViewById(R.id.textViewAutoYellowStack);
        textViewAutoNumYellowToAutoZone = (TextView) retVal.findViewById(R.id.textViewAutoYellowToAutoZone);
        textViewAutoNumRCMovedToAutoZone = (TextView) retVal.findViewById(R.id.textViewAutoRCMovedToAutoZone);
        textViewAutoNumRCFromStep = (TextView) retVal.findViewById(R.id.textViewAutoRCFromStep);

        textViewTeleNumTotesStacked = (TextView) retVal.findViewById(R.id.textViewTeleTotesStacked);
        textViewTeleRCStacked = (TextView) retVal.findViewById(R.id.textViewTeleRCStacked);
        textViewTeleLitter = (TextView) retVal.findViewById(R.id.textViewTeleLitterOnStack);

        textViewGenNumOfTotesFromHP = (TextView) retVal.findViewById(R.id.textViewGenTotesFromHP);
        textViewGenNumOfTotesFromLandfill = (TextView) retVal.findViewById(R.id.textViewGenTotesFromLandfill);
        textViewGenNumOfFouls = (TextView) retVal.findViewById(R.id.textViewGenFouls);
        textViewGenNumOfTippedStacks = (TextView) retVal.findViewById(R.id.textViewGenStacksTipped);
        textViewGenDeadRobot = (TextView) retVal.findViewById(R.id.textViewGenDeadRobot);

        editTextGenPenaltyComments = (EditText) retVal.findViewById(R.id.editTextPenaltiesComments);
        editTextGenGeneralComments = (EditText) retVal.findViewById(R.id.editTextGeneralComments);
        return retVal;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getArguments() != null) {
            loadData();
            SparxScouting.getInstance(getActivity()).getScouting(dbHelper.getTeam(getArguments().getString(ARG_TEAM_KEY)), new NetworkCallback() {
                @Override
                public void handleFinishDownload(boolean success) {
                    if(!success)
                        return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() { loadData();}
                    });
                }
            });
        }
    }

    private void loadData(){
        Bundle args = getArguments();
        String eventKey = "";
        String teamKey = "";

        if(args != null){
            eventKey = args.getString(ARG_EVENT_KEY);
            teamKey = args.getString(ARG_TEAM_KEY);
        }

        Team team = dbHelper.getTeam(teamKey);;
        textViewTeamNickname.setText(team.getNickname());
        Ion.with(imageViewRobot)
                .placeholder(R.drawable.icon_launcher)
                .load("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2009/12/2/1259769084987/Pingpong-playing-robot--i-001.jpg");

        Map<String, List<Scouting>> scoutingData = createScoutingMap(dbHelper.getScouting(eventKey, teamKey));

        List<Double> robotInAutoZone = new ArrayList<Double>();
        List<Double> createdYellowStack = new ArrayList<Double>();
        List<Double> movedTotesToAutoZone = new ArrayList<Double>();
        List<Double> numRCMovedToAutoZone = new ArrayList<Double>();
        List<Double> numRCFromStep = new ArrayList<Double>();
        List<Double> numTotesStacked = new ArrayList<Double>();
        List<Double> numrcStacked = new ArrayList<Double>();
        List<Double> numLitterOnStacks = new ArrayList<Double>();
        List<Double> numTotesFronHP = new ArrayList<Double>();
        List<Double> numTotesFromLandfill = new ArrayList<Double>();
        List<Double> numFouls = new ArrayList<Double>();
        List<Double> numStacksTipped = new ArrayList<Double>();
        List<Double> deadRobot = new ArrayList<Double>();
        List<String> penaltiesComments = new ArrayList<String>();
        List<String> generalComments = new ArrayList<String>();

        final String allianceString = "alliance";
        final String middleString = "middle";
        final String opponentString = "opponent";

        for (List<Scouting> entry : scoutingData.values()) {
            List<Boolean> robotInAutoZoneMatch = new ArrayList<Boolean>();
            List<Boolean> createdYellowStackMatch = new ArrayList<Boolean>();
            List<Integer> movedTotesToAutoZoneMatch = new ArrayList<Integer>();
            List<Integer> numRCMovedToAutoZoneMatch = new ArrayList<Integer>();
            List<Integer> numRCFromStepMatch = new ArrayList<Integer>();

            List<Integer> numTotesStackedMatch = new ArrayList<Integer>();
            List<Integer> numRcStackedMatch = new ArrayList<Integer>();
            List<Integer> numLitterOnStacksMatch = new ArrayList<Integer>();
            List<Integer> numTotesFromHPMatch = new ArrayList<Integer>();
            List<Integer> numTotesFromLandfillMatch = new ArrayList<Integer>();
            List<Integer> numFoulsMatch = new ArrayList<Integer>();
            List<Integer> numStacksTippedMatch = new ArrayList<Integer>();
            List<Boolean> deadRobotMatch = new ArrayList<Boolean>();
            for(Scouting scouting : entry){
                if(scouting.getAuto().getRCTakenFromStep() != -1){

                }
                robotInAutoZoneMatch.add(scouting.getAuto().getRobotInAutoZone());
                createdYellowStackMatch.add(scouting.getAuto().getRobotCreateStack());
                if(scouting.getAuto().getTotesMovedToAutoZone() != -1)
                    movedTotesToAutoZoneMatch.add(scouting.getAuto().getTotesMovedToAutoZone());
                if(scouting.getAuto().getRcMovedToAutoZone() != -1)
                    numRCMovedToAutoZoneMatch.add(scouting.getAuto().getRcMovedToAutoZone());
                if(scouting.getAuto().getRCTakenFromStep() != -1)
                    numRCFromStepMatch.add(scouting.getAuto().getRCTakenFromStep());

                int totesStacked = 0;
                if(scouting.getTele().getTotesStacked1() != -1)
                    totesStacked += (scouting.getTele().getTotesStacked1());
                if(scouting.getTele().getTotesStacked2() != -1)
                    totesStacked+=(scouting.getTele().getTotesStacked2());
                if(scouting.getTele().getTotesStacked3() != -1)
                    totesStacked+=(scouting.getTele().getTotesStacked3());
                if(scouting.getTele().getTotesStacked4() != -1)
                    totesStacked+=(scouting.getTele().getTotesStacked4());
                numTotesStackedMatch.add(totesStacked);

                int rcCapped = 0;
                rcCapped+=(scouting.getTele().getRCStacked1()?1:0);
                rcCapped+=(scouting.getTele().getRCStacked2()?1:0);
                rcCapped+=(scouting.getTele().getRCStacked3()?1:0);
                rcCapped+=(scouting.getTele().getRCStacked4()?1:0);
                numRcStackedMatch.add(rcCapped);

                int litter = 0;
                litter+=(scouting.getTele().getLitter1()?1:0);
                litter+=(scouting.getTele().getLitter2()?1:0);
                litter+=(scouting.getTele().getLitter3()?1:0);
                litter+=(scouting.getTele().getLitter4()?1:0);
                if(scouting.getGeneral().getNumberOfTotesAcquiredFromHP() != -1)
                    numTotesFromHPMatch.add(scouting.getGeneral().getNumberOfTotesAcquiredFromHP());
                if(scouting.getGeneral().getNumberOfTotesFromLandfill() != -1)
                    numTotesFromLandfillMatch.add(scouting.getGeneral().getNumberOfTotesFromLandfill());
                if(scouting.getGeneral().getNumberOfFouls() != -1)
                    numFoulsMatch.add(scouting.getGeneral().getNumberOfFouls());
                if(scouting.getGeneral().getNumberOfStacksTipped() != -1)
                    numStacksTippedMatch.add(scouting.getGeneral().getNumberOfStacksTipped());
                deadRobotMatch.add(scouting.getGeneral().getIsDead());

                penaltiesComments.add(scouting.getGeneral().getCommentsOnPenalties());
                generalComments.add(scouting.getGeneral().getGeneralComments());
            }
            robotInAutoZone.add(averageBoolean(robotInAutoZoneMatch));
            createdYellowStack.add(averageBoolean(createdYellowStackMatch));
            movedTotesToAutoZone.add(average(movedTotesToAutoZoneMatch));
            numRCMovedToAutoZone.add(average(numRCMovedToAutoZoneMatch));
            numRCFromStep.add(average(numRCFromStepMatch));
            numTotesStacked.add(average(numTotesStackedMatch));
            numrcStacked.add(average(numRcStackedMatch));
            numLitterOnStacks.add(average(numLitterOnStacksMatch));
            numTotesFronHP.add(average(numTotesFromHPMatch));
            numTotesFromLandfill.add(average(numTotesFromLandfillMatch));
            numFouls.add(average(numFoulsMatch));
            numStacksTipped.add(average(numStacksTippedMatch));
            deadRobot.add(averageBoolean(deadRobotMatch));
        }


        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        double tempDouble = average(robotInAutoZone);
        int tempInt = (int) (tempDouble * robotInAutoZone.size());
        textViewAutoRobotInZone.setText("Robot in Zone: " + tempInt + " of " + robotInAutoZone.size() + " (" + (int)(tempDouble*100) + "%)");
        tempDouble = average(createdYellowStack);
        tempInt = (int) (tempDouble * createdYellowStack.size());
        textViewAutoCreatedYellowStack.setText("Created a Yellow Stack: " + tempInt + " of " + createdYellowStack.size() + " (" + (int)(tempDouble*100) + "%)");
        textViewAutoNumYellowToAutoZone.setText("Number of Yellow to Auto Zone " + df.format(average(createdYellowStack)));
        textViewAutoNumRCMovedToAutoZone.setText("Number of R/C moved to auto zone: " + df.format(average(numRCMovedToAutoZone)));
        textViewAutoNumRCFromStep.setText("Number of R/C taken for the step: " + df.format(average(numRCFromStep)));

        textViewTeleNumTotesStacked.setText("Number of Totes stacked (per match)" +df.format(average(numTotesStacked)));
        textViewTeleRCStacked.setText("Number of R/C stacked: " + df.format(average(numrcStacked)));
        textViewTeleLitter.setText("Number of litter of stacks: " + df.format(average(numLitterOnStacks)));

        textViewGenNumOfTotesFromHP.setText("Totes from HP: " + df.format(average(numTotesFronHP)));
        textViewGenNumOfTotesFromLandfill.setText("Totes from Landfill: " + df.format(average(numTotesFromLandfill)));
        textViewGenNumOfFouls.setText("Number of Fouls: " + df.format(average(numFouls)));
        textViewGenNumOfTippedStacks.setText("Number of Tipped Stacks: " + df.format(average(numStacksTipped)));
        tempDouble = average(deadRobot);
        tempInt = (int) (tempDouble * deadRobot.size());
        textViewGenDeadRobot.setText("Dead robot: " + tempInt + " of " + deadRobot.size() + " (" + (int)(tempDouble*100) + "%)");

//        StringBuilder sb = new StringBuilder();
//        for(String comment : penaltiesComments){
//            sb.append(comment).append("\n");
//        }
//        editTextGenPenaltyComments.setText(sb.toString());
//
//        sb = new StringBuilder();
//        for(String comment : generalComments){
//            sb.append(comment).append("\n");
//        }
//        editTextGenGeneralComments.setText(sb.toString());
    }

    /**
     * Creates a Map<Matches,Scouting[]>
     *
     * @param scoutingData
     * @return
     */
    private Map<String, List<Scouting>> createScoutingMap(List<Scouting> scoutingData) {
        Map<String, List<Scouting>> map = new HashMap<String, List<Scouting>>();

        for(Scouting scout : scoutingData){

            List<Scouting> matchList = map.get(scout.getMatchKey());
            if(matchList == null) {
                matchList = new ArrayList<Scouting>();
                map.put(scout.getMatchKey(), matchList);
            }

            matchList.add(scout);
        }

        return map;
    }

    private double average(List<? extends Number> list){
        double retVal = 0;
        for(Number dub : list)
            retVal += dub.doubleValue();
        return list.isEmpty() ? 0 : retVal / list.size();
    }

    private double averageBoolean(List<Boolean> list){
        double retVal = 0;
        for(Boolean bool : list){
            retVal += bool ? 1:0;
        }

        return list.isEmpty()? 0:retVal / list.size();
    }

}
