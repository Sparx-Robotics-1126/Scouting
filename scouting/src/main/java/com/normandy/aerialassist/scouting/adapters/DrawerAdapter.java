package com.normandy.aerialassist.scouting.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.normandy.aerialassist.scouting.DatabaseHelper;

/**
 * Created by jbass on 3/1/14.
 */
public class DrawerAdapter extends BaseExpandableListAdapter {

    private DatabaseHelper dbHelper;

    public DrawerAdapter(DatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public int getGroupCount() {
        return 0;
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
