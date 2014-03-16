package com.datayumyum.pos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sto on 3/15/14.
 */
public class ItemRepository {
    static final String TAG = "com.datayumyum.pos.ItemRepository";

    HashMap<String, List<Item>> itemHashMap;

    ItemRepository(String json) {
        try {
            itemHashMap = new HashMap<String, List<Item>>();
            JSONObject catalog = new JSONObject(json);
            JSONArray categories = catalog.names();
            for (int i = 0; i < categories.length(); i++) {
                String category = (String) categories.get(i);
                ArrayList<Item> itemList = new ArrayList<Item>();
                JSONArray jsonItems = (JSONArray) catalog.get(category);
                for (int j = 0; j < jsonItems.length(); j++) {
                    JSONObject o = (JSONObject) jsonItems.get(j);
                    itemList.add(new Item().bind(o));
                }
                itemHashMap.put(category, itemList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    List<Item> groupItemByCategory(String category) {
        return itemHashMap.get(category);
    }


}
