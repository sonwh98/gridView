package com.datayumyum.pos;

import android.widget.*;
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
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TableRow tr = new TableRow(getApplicationContext());
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView textView = new TextView(getApplicationContext());
                textView.setText("foobar");
                textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(textView);
                tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                Toast.makeText(GridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
