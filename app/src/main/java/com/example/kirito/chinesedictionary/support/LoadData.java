package com.example.kirito.chinesedictionary.support;

import android.os.AsyncTask;

import com.example.kirito.chinesedictionary.entity.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirito on 2016.10.06.
 */

public class LoadData extends AsyncTask<String,Void,String> {
    private Listener listener;
    private String word_url = "http://v.juhe.cn/xhzd/query";
    private String py_url = "http://v.juhe.cn/xhzd/querypy";

    private int flag;

    public LoadData(int flag) {
        this.flag = flag;
    }

    public LoadData() {
    }

    @Override
    protected String doInBackground(String... params) {
        Map par = new HashMap();
        par.put("word",params[0]);
        par.put("key","354226a191afcb1bda7b621063210c1a");
        if (flag == 1){
            return Http.getData(py_url,par,"GET");
        }else {
            return Http.getData(word_url,par,"GET");
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Item item = JsonHelper.parseJsonToItem(s);
        if (listener != null && item != null){
            listener.setItem(item);
        }
    }

    public interface Listener{
        void setItem(Item item);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
}
