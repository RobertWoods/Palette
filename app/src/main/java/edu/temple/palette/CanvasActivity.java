package edu.temple.palette;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by rober_000 on 9/22/2016.
 */
public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        findViewById(R.id.canvasLayout).setBackgroundColor(getIntent().getIntExtra("COLOR", Color.WHITE));
    }
}
