package org.gosparx.scouting.aerialassist.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.view.View;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

import org.gosparx.scouting.aerialassist.R;

import org.gosparx.scouting.aerialassist.DatabaseHelper;

import org.gosparx.scouting.aerialassist.dto.Team;

/**
 * Created by jbass on 3/3/14.
 */
public class ScoutingDrawerAdapter extends SimpleCursorTreeAdapter {

    DatabaseHelper dbHelper;

    public ScoutingDrawerAdapter(Context c, DatabaseHelper dbHelper){
        super(c, null,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"key"},
                new int[]{android.R.id.text1},
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"team_number", "team_nick"},
                new int[]{android.R.id.text1, android.R.id.text2});
        this.dbHelper = dbHelper;
        setViewBinder(new SimpleCursorTreeAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                view.setTag(R.id.match_key,cursor.getString(cursor.getColumnIndex("match_key")));
                view.setTag(R.id.team_key, cursor.getString(cursor.getColumnIndex("team_key")));
                return false;
            }
        });
    }

    @Override
    protected Cursor getChildrenCursor(Cursor cursor) {
        MatrixCursor c = new MatrixCursor(new String[]{"_id", "match_key", "team_key", "team_number", "team_nick"}, 6);
        String[] teamList = new String[]{
                cursor.getString(cursor.getColumnIndex("blue_one")),
                cursor.getString(cursor.getColumnIndex("blue_two")),
                cursor.getString(cursor.getColumnIndex("blue_three")),
                cursor.getString(cursor.getColumnIndex("red_one")),
                cursor.getString(cursor.getColumnIndex("red_two")),
                cursor.getString(cursor.getColumnIndex("red_three"))
        };

        for(int i=0; i<teamList.length; i++){
            Team team = dbHelper.getTeam(teamList[i]);
            c.addRow(new String[]{
                    Integer.toString(i),
                    cursor.getString(cursor.getColumnIndex("key")),
                    teamList[i],
                    Integer.toString(team.getTeamNumber()),
                    team.getNickname()
            });
        }
        return c;
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean isExpanded) {
        StringBuilder matchString = new StringBuilder();
        String compLevel = cursor.getString(cursor.getColumnIndex("comp_level"));
        int setNumber = cursor.getInt(cursor.getColumnIndex("set_number"));
        if("qm".equals(compLevel))
            matchString.append("Qual ");
        else if("qf".equals(compLevel)){
            matchString.append("Q/F: ");
            matchString.append(setNumber);
        }else if("sf".equals(compLevel)){
            matchString.append("S/F: ");
            matchString.append(setNumber);
        }else if("f".equals(compLevel)){
            matchString.append("Final: ");
            matchString.append(setNumber);
        }
        matchString.append(" Match: ").append(cursor.getInt(cursor.getColumnIndex("match_number")));

        ((TextView) view).setText(matchString.toString());
    }
}
