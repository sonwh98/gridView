package com.datayumyum.pos;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonnyto on 2/17/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private static final String TAG = "com.datayumyum.pos.CategoryAdapter";
    private List<ItemButton> itemButtonList;
    ItemRepository itemRepository;

    public CategoryAdapter(Context gridViewActivity, ItemRepository itemRepository) {
        mContext = gridViewActivity;
        this.itemRepository = itemRepository;

        itemButtonList = new ArrayList<ItemButton>();
        List<Item> jSonItems = itemRepository.groupItemByCategory("Entrees");
        for (int i = 0; i < jSonItems.size(); i++) {
            Item o = jSonItems.get(i);
            String name = (String) o.get("name");
            Double price = (Double) o.get("price");
            Log.w(TAG, name + ":" + price);
            ItemButton itemButton = new ItemButton(mContext, name);
            itemButton.setLayoutParams(new GridView.LayoutParams(85, 85));
            itemButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemButton.setPadding(8, 8, 8, 8);
            itemButton.setImageResource(mThumbIds[i]);
            itemButtonList.add(itemButton);
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
