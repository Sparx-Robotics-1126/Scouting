package org.gosparx.scouting.aerialassist.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import org.gosparx.scouting.aerialassist.DatabaseHelper;

/**
 * Created by jbass on 3/1/14.
 */
public class MatchesDrawerAdapter extends BaseExpandableListAdapter {

    private DatabaseHelper dbHelper;

    public MatchesDrawerAdapter(DatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public int getGroupCount() {
        return dbHelper.getMatchCount(null);
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
