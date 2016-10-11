package com.example.kirito.chinesedictionary.support;

import android.util.Log;

import com.example.kirito.chinesedictionary.entity.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kirito on 2016.10.06.
 */

public class JsonHelper {
    private static final String TAG = "JsonHelper";

    public static Item parseJsonToItem(String json){
        Item item = new Item();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject result = object.getJSONObject("result");
            if (result != null){
                item.setZi(result.getString("zi"));
                item.setBihua(result.getString("bihua"));
                item.setPy(result.getString("pinyin"));
                item.setBushou(result.getString("bushou"));

                JSONArray jijie = result.getJSONArray("jijie");
                //Log.e(TAG, "parseJsonToItem: obj---"+jijie.toString() );
                item.setJijie(jijie.toString());
                item.setXiangjie(result.getJSONArray("xiangjie").toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
