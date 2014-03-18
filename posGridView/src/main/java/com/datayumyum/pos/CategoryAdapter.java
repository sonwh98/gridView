package com.datayumyum.pos;


import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * Created by sonnyto on 2/17/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private static final String TAG = "com.datayumyum.pos.CategoryAdapter";
    private List<View> itemButtonList;

    public CategoryAdapter(List<View> itemButtonList) {
        this.itemButtonList = itemButtonList;
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
}
