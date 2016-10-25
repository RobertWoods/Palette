package edu.temple.palette;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class PaletteActivity extends Activity implements PaletteFragment.OnFragmentInteractionListener {

    boolean twoPanes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        twoPanes = findViewById(R.id.canvasLayout) != null;

        FragmentManager fm = this.getFragmentManager();

        if (savedInstanceState == null) {
            PaletteFragment paletteFragment = new PaletteFragment();
            paletteFragment.setArguments(getIntent().getExtras());

            fm.beginTransaction()
                    .replace(R.id.paletteLayout, paletteFragment)
                    .commit();
        }

        if (twoPanes) {
            CanvasFragment canvasFragment = new CanvasFragment();
            fm.beginTransaction()
                    .replace(R.id.canvasLayout, canvasFragment)
                    .commit();
        }

    }

    @Override
    public void setFragmentBackground(int color) {
        CanvasFragment cf = new CanvasFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(CanvasFragment.dataKey, color);
        cf.setArguments(bundle);

        int layout = twoPanes ? R.id.canvasLayout : R.id.paletteLayout;
        if (!twoPanes) {
            getFragmentManager().beginTransaction()
                    .add(layout, cf)
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .add(layout, cf)
                    .commit();

        }
    }
}
