package edu.temple.palette;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class PaletteFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public PaletteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_palette, container, false);

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
                if (convertView != null) {
                    if (((LinearLayout) convertView).getChildAt(0) != null) {
                        ((TextView) ((LinearLayout) convertView).getChildAt(0)).setText(colorNames[position]);
                        convertView.setBackgroundColor(colors[position] != 0 ? colors[position] : Color.WHITE);
                        return convertView;
                    }
                } else {
                    convertView = new LinearLayout(getActivity());
                }
                TextView text = new TextView(getActivity());
                text.setText(colorNames[position]);
                ((LinearLayout) convertView).addView(text);
                convertView.setBackgroundColor((int) getItem(position) != 0 ? colors[position] : Color.WHITE);
                return convertView;
            }
        };
        Spinner spinner = (Spinner) v.findViewById(R.id.colorPicker);
        spinner.setAdapter(colorPicker);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    return;
                mListener.setFragmentBackground((int) parent.getAdapter().getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void setFragmentBackground(int color);
    }

}
