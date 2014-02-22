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
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TableRow tr = new TableRow(getApplicationContext());
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                for (int i = 0; i < 4; i++) {
                    TextView textView = new TextView(getApplicationContext());
                    textView.setText("foobar" + i);
                    textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    textView.setPadding(5, 5, 5, 5);
                    tr.addView(textView);
                }
                tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

}
