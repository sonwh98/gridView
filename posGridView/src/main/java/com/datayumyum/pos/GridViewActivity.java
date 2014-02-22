package com.datayumyum.pos;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.datayumyum.pos.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GridViewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_view);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
