package edu.temple.palette;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PaletteActivity extends AppCompatActivity {

    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        BaseAdapter colorPicker = new BaseAdapter() {
            //0 is unelectable when app is first run so we use it as a prompt so the user can select red immediately
            int[] colors = new int[]{0, Color.RED, Color.WHITE, Color.YELLOW, Color.GREEN, Color.MAGENTA};
            String[] colorNames = getResources().getStringArray(R.array.spinner_color_names);
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
                if(convertView != null){
                    if(((LinearLayout) convertView).getChildAt(0)!=null) {
                        ((TextView) ((LinearLayout) convertView).getChildAt(0)).setText(colorNames[position]);
                        convertView.setBackgroundColor(colors[position] != 0 ? colors[position] : Color.WHITE );
                        return convertView;
                    }
                } else {
                    convertView = new LinearLayout(PaletteActivity.this);
                }
                TextView text = new TextView(PaletteActivity.this);
                text.setText(colorNames[position]);
                ((LinearLayout) convertView).addView(text);
                convertView.setBackgroundColor((int) getItem(position) != 0 ? colors[position] : Color.WHITE  );
                return convertView;
            }
        };
        Spinner spinner = (Spinner) findViewById(R.id.colorPicker);
        spinner.setAdapter(colorPicker);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0)
                        return;
                    Intent intent = new Intent(PaletteActivity.this, CanvasActivity.class);
                    intent.putExtra("COLOR", (Integer) parent.getAdapter().getItem(position));
                    startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
