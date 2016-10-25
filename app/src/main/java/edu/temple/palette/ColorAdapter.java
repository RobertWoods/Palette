package edu.temple.palette;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rober_000 on 10/25/2016.
 */

public class ColorAdapter extends BaseAdapter {

    int[] colors = new int[]{0, Color.RED, Color.WHITE, Color.YELLOW, Color.GREEN, Color.MAGENTA};
    String[] colorNames;
    Context c;

    public ColorAdapter(Context c) {
        this.c = c;
        colorNames = c.getResources().getStringArray(R.array.spinner_color_names);
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            if (((LinearLayout) convertView).getChildAt(0) != null) {
                ((TextView) ((LinearLayout) convertView).getChildAt(0)).setText(colorNames[position]);
                convertView.setBackgroundColor(colors[position] != 0 ? colors[position] : Color.WHITE);
                return convertView;
            }
        } else {
            convertView = new LinearLayout(c);
        }
        TextView text = new TextView(c);
        text.setText(colorNames[position]);
        ((LinearLayout) convertView).addView(text);
        convertView.setBackgroundColor((int) getItem(position) != 0 ? colors[position] : Color.WHITE);
        return convertView;
    }
}
