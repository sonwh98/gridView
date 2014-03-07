package com.datayumyum.pos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import org.mozilla.javascript.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GridViewActivity extends Activity {
    final static String TAG = "com.datayumyum.pos.GridViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        Scriptable scope = rhino.initStandardObjects();

        String source = "{items:[{name:'foo', price:2}, {name:'bar',price:3}]}";
        // Note the forth argument is 1, which means the JavaScript source has
        // been compressed to only one line using something like YUI
        NativeArray items = (NativeArray) rhino.evaluateString(scope, source, "ScriptAPI", 1, null);
        NativeObject item0 = (NativeObject) items.get(0, null);
        assert "foo".equals(item0.get("name", null));
        assert item0.get("price", null).equals(2.0);

        NativeObject item1 = (NativeObject) items.get(1, null);
        assert item1.get("name", null).equals("bar");
        assert item0.get("price", null).equals(3.0);

        setContentView(R.layout.activity_grid_view);
        final ListView listView = (ListView) findViewById(R.id.list);
        final ShoppingCart shoppingCart = new ShoppingCart();

        listView.setAdapter(shoppingCart);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(listView, new SwipeDismissListViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            shoppingCart.remove(position);
                        }
                    }
                });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
        ///////


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                shoppingCart.add("foobar" + position);
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

        public void remove(int position) {
            list.remove(position);
            notifyDataSetChanged();
        }

        public void add(String str) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("quantity", "1");
            item.put("description", str);
            item.put("price", str);
            item.put("subTotal", str);
            list.add(item);
            notifyDataSetChanged();
        }
    }
}
