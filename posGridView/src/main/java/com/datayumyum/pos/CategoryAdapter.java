package com.datayumyum.pos;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonnyto on 2/17/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private static final String TAG = "com.datayumyum.pos.CategoryAdapter";
    private List<ItemButton> itemButtonList;

    public CategoryAdapter(Context gridViewActivity) {
        mContext = gridViewActivity;
        itemButtonList = new ArrayList<ItemButton>();
        String json = getStringReader();

        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jSonItems = (JSONArray) jObject.get("Entrees");
            for (int i = 0; i < jSonItems.length(); i++) {
                JSONObject o = (JSONObject) jSonItems.get(i);
                String name = o.getString("name");
                Double price = o.getDouble("price");
                Log.w(TAG, name + ":" + price);
                ItemButton itemButton = new ItemButton(mContext, name);
                itemButton.setLayoutParams(new GridView.LayoutParams(85, 85));
                itemButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                itemButton.setPadding(8, 8, 8, 8);
                itemButton.setImageResource(mThumbIds[i]);
                itemButtonList.add(itemButton);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return itemButtonList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.w(TAG, itemButtonList.size() + " position=" + position);
        ImageView imageView = itemButtonList.get(position);
        return imageView;
    }

    private String getStringReader() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.catalog)));
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
