package com.example.nazanin_sarrafzadeh.faze4;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by nazanin-sarrafzadeh on 7/3/2018.
 */
public class HelpWindow extends BaseAdapter {
    private ArrayList<Button> Buttons = null;
    public HelpWindow(ArrayList<Button> b)
    {
        Buttons = b;
    }
    @Override
    public int getCount() {
        return Buttons.size();
    }
    @Override
    public Object getItem(int position) {
        return (Object) Buttons.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = Buttons.get(position);
        } else {
            button = (Button) convertView;
        }
        return button;
    }
}
