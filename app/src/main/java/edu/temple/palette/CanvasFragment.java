package edu.temple.palette;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CanvasFragment extends Fragment {

    public static String dataKey = "momma can you hear me now";

    public CanvasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_canvas, container, false);
        if (getArguments() != null && getArguments().getInt(dataKey, -1) != -1) {
            int color = getArguments().getInt(dataKey);
            v.setBackgroundColor(color);
        }
        return v;
    }

}
