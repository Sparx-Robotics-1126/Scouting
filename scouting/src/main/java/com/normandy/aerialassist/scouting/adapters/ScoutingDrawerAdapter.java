package com.normandy.aerialassist.scouting.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.SimpleCursorTreeAdapter;

import com.normandy.aerialassist.scouting.DatabaseHelper;
import com.normandy.aerialassist.scouting.dto.Event;

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
                new String[]{"key"},
                new int[]{android.R.id.text1});
        this.dbHelper = dbHelper;
    }

    public void setCurrentEvent(Event event){
        changeCursor(dbHelper.createMatchCursor(event));
    }

    @Override
    protected Cursor getChildrenCursor(Cursor cursor) {
        MatrixCursor c = new MatrixCursor(new String[]{"_id", "key"}, 6);
        c.addRow(new String[]{"0",cursor.getString(cursor.getColumnIndex("blue_one"))});
        c.addRow(new String[]{"1",cursor.getString(cursor.getColumnIndex("blue_two"))});
        c.addRow(new String[]{"2",cursor.getString(cursor.getColumnIndex("blue_three"))});
        c.addRow(new String[]{"3",cursor.getString(cursor.getColumnIndex("red_one"))});
        c.addRow(new String[]{"4",cursor.getString(cursor.getColumnIndex("red_two"))});
        c.addRow(new String[]{"5",cursor.getString(cursor.getColumnIndex("red_three"))});
        return c;
    }
}
