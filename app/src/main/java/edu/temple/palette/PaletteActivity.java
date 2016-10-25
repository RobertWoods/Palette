package edu.temple.palette;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;

public class PaletteActivity extends Activity implements PaletteFragment.OnFragmentInteractionListener {

    private static String COLOR_KEY = "CARDBOARD BOX";

    boolean twoPanes = false;
    int color = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) color = savedInstanceState.getInt(COLOR_KEY, Color.WHITE);
        setContentView(R.layout.activity_palette);
        twoPanes = findViewById(R.id.canvasLayout) != null;

        FragmentManager fm = this.getFragmentManager();

        PaletteFragment paletteFragment = new PaletteFragment();
        paletteFragment.setArguments(getIntent().getExtras());

        fm.beginTransaction()
                .replace(R.id.paletteLayout, paletteFragment)
                .commit();

        if (twoPanes) {
            CanvasFragment canvasFragment = new CanvasFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(CanvasFragment.dataKey, color);
            canvasFragment.setArguments(bundle);
            fm.beginTransaction()
                    .replace(R.id.canvasLayout, canvasFragment)
                    .commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstancestate) {
        savedInstancestate.putInt(COLOR_KEY, color);
        super.onSaveInstanceState(savedInstancestate);
    }

    @Override
    public void setFragmentBackground(int color) {
        this.color = color;
        CanvasFragment cf = new CanvasFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CanvasFragment.dataKey, color);
        cf.setArguments(bundle);

        int layout = twoPanes ? R.id.canvasLayout : R.id.paletteLayout;
        if (!twoPanes) {
            getFragmentManager().beginTransaction()
                    .replace(layout, cf)
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(layout, cf)
                    .commit();

        }
    }
}
