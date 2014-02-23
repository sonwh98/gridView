package com.datayumyum.pos;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class GridViewActivity extends Activity {
    final static String TAG = "com.datayumyum.pos.GridViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_view);

        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                final TableRow tr = new TableRow(getApplicationContext());
                tr.setOnTouchListener(new SwipeListener(getApplicationContext()) {
                    @Override
                    public void onSwipeRight() {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
                        animation.setDuration(2000);
                        animation.setFillAfter(true);
                        tr.startAnimation(animation);
                        tableLayout.removeView(tr);
                        Toast.makeText(GridViewActivity.this, "right", Toast.LENGTH_SHORT).show();
                    }
                });


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
