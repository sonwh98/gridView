package com.datayumyum.pos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class GridViewActivity extends Activity {
    final static String TAG = "com.datayumyum.pos.GridViewActivity";
    private ItemRepository itemRepository;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        final String jsonCatalog = readJsonCatalog();
        itemRepository = new ItemRepository(jsonCatalog);
        shoppingCart = new ShoppingCart();

        configureCategoryViews();
        configureLineItemView();
    }

    private void configureCategoryViews() {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        List<View> itemButtonList = createButtonsFor("Entrees");
        gridview.setAdapter(new CategoryAdapter(itemButtonList));
    }

    private void configureLineItemView() {
        ListView listView = (ListView) findViewById(R.id.list);
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
    }

    private List<View> createButtonsFor(String category) {
        List<Item> itemList = itemRepository.groupItemByCategory(category);
        List<View> buttonList = new ArrayList<View>();
        for (Item item : itemList) {
            String name = (String) item.get("name");
            buttonList.add(createImageButton(name));
        }
        return buttonList;
    }

    private View createImageButton(final String label) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View itemButton = inflater.inflate(R.layout.item_button, null);
        ImageButton imageButton = (ImageButton) itemButton.findViewById(R.id.item_image_button);
        int i = new Random().nextInt(mThumbIds.length - 1);
        imageButton.setImageResource(mThumbIds[i]);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, label);
                shoppingCart.add(label);
            }
        });
        TextView itemLabel = (TextView) itemButton.findViewById(R.id.item_label);
        itemLabel.setText(label);
        return itemButton;
    }

    private String readJsonCatalog() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.catalog)));
        String json = "";
        try {
            String line;
            do {
                line = reader.readLine();
                if (line != null)
                    json += line + "\n";
            } while (line != null);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {

            }
        }
        return json.trim();
    }

    private class ShoppingCart extends BaseAdapter {
        ArrayList<HashMap> lineItems;

        ShoppingCart() {
            lineItems = new ArrayList<HashMap>();
        }

        @Override
        public int getCount() {
            return lineItems.size();
        }

        @Override
        public Object getItem(int position) {
            return lineItems.get(position);
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

            HashMap<String, String> map = lineItems.get(position);
            textViewMap.get("quantity").setText(map.get("quantity"));
            textViewMap.get("description").setText(map.get("description"));
            textViewMap.get("price").setText(map.get("price"));
            textViewMap.get("subTotal").setText(map.get("subTotal"));

            return convertView;
        }

        public void remove(int position) {
            lineItems.remove(position);
            notifyDataSetChanged();
        }

        public void add(String str) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("quantity", "1");
            item.put("description", str);
            item.put("price", str);
            item.put("subTotal", str);
            lineItems.add(item);
            notifyDataSetChanged();
        }

        public void add(Item item) {

        }
    }

    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}
