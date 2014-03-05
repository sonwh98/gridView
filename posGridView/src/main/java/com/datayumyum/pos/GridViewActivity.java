package com.datayumyum.pos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class GridViewActivity extends Activity {
    final static String TAG = "com.datayumyum.pos.GridViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_view);
        //////
        String[] items = new String[1];
        for (int i = 0; i < items.length; i++) {
            items[i] = "Item " + (i + 1);
        }
        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<String>(Arrays.asList(items)));


        final ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Quantity", "2");
        map.put("Description", "Vietnamese Hoagie");
        map.put("Price", "$5.00");
        map.put("SubTotal", "$10.00");
        mylist.add(map);
        final SimpleAdapter adapter = new SimpleAdapter(this, mylist, R.layout.row,
                new String[]{"Quantity", "Description", "Price", "SubTotal"}, new int[]{R.id.QUANTITY_CELL, R.id.DESCRIPTION_CELL, R.id.PRICE_CELL, R.id.SUB_TOTAL_CELL});
        listView.setAdapter(mAdapter);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(listView, new SwipeDismissListViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            mAdapter.remove(mAdapter.getItem(position));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
        ///////


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                mAdapter.add("foobar" + position);
            }
        });
    }

    private class ShoppingCart extends BaseAdapter {
        ArrayList<HashMap> list;

        ShoppingCart() {
            list = new ArrayList<HashMap>();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            Map<String, TextView> textViewMap;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row, null);
                TextView tv1 = (TextView) convertView.findViewById(R.id.QUANTITY_CELL);
                TextView tv2 = (TextView) convertView.findViewById(R.id.DESCRIPTION_CELL);
                TextView tv3 = (TextView) convertView.findViewById(R.id.PRICE_CELL);
                TextView tv4 = (TextView) convertView.findViewById(R.id.SUB_TOTAL_CELL);
                textViewMap = new HashMap<String, TextView>();
                textViewMap.put("quantity", tv1);
                textViewMap.put("description", tv2);
                textViewMap.put("price", tv3);
                textViewMap.put("subTotal", tv4);
                convertView.setTag(textViewMap);
            } else {
                textViewMap = (Map) convertView.getTag();
            }

            HashMap<String, String> map = list.get(position);
            textViewMap.get("quantity").setText(map.get("quantity"));
            textViewMap.get("description").setText(map.get("description"));
            textViewMap.get("price").setText(map.get("price"));
            textViewMap.get("subTotal").setText(map.get("subTotal"));

            return convertView;
        }
    }
}
