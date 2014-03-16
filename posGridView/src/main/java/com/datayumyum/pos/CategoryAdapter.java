package com.datayumyum.pos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sonnyto on 2/17/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private static final String TAG = "com.datayumyum.pos.CategoryAdapter";
    private List<View> itemButtonList;
    ItemRepository itemRepository;
    Random random = new Random();

    public CategoryAdapter(Context gridViewActivity, ItemRepository itemRepository) {
        context = gridViewActivity;
        this.itemRepository = itemRepository;

        itemButtonList = new ArrayList<View>();
        List<Item> jSonItems = itemRepository.groupItemByCategory("Entrees");
        for (int i = 0; i < jSonItems.size(); i++) {
            Item o = jSonItems.get(i);
            String name = (String) o.get("name");
            View itemButton = createImageButton(name);
            itemButtonList.add(itemButton);
        }

    }

    View createImageButton(String label) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemButton = inflater.inflate(R.layout.item_button, null);
        ImageButton imageButton = (ImageButton) itemButton.findViewById(R.id.item_image_button);
        int i = random.nextInt(mThumbIds.length - 1);
        Log.w(TAG, "i=" + i);
        imageButton.setImageResource(mThumbIds[i]);
        TextView itemLabel = (TextView) itemButton.findViewById(R.id.item_label);
        itemLabel.setText(label);
        return itemButton;
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
        View imageView = itemButtonList.get(position);
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
