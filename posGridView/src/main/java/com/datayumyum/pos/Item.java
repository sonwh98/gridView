package com.datayumyum.pos;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by sto on 3/15/14.
 */
public class Item extends HashMap {
    final static String TAG = "com.datayumyum.pos.Item";

    Item bind(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.names();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                String name = (String) jsonArray.get(i);
                Object value = jsonObject.get(name);
                put(name, value);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return this;
    }
}

