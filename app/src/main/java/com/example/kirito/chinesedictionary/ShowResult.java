package com.example.kirito.chinesedictionary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirito.chinesedictionary.entity.Item;
import com.example.kirito.chinesedictionary.support.LoadData;

/**
 * Created by kirito on 2016.10.11.
 */

public class ShowResult extends AppCompatActivity {
    private String word;
    //private String url = "http://v.juhe.cn/xhzd/query?key=354226a191afcb1bda7b621063210c1a&word=";

    private TextView zi,py,bushou,bihua,jijie,xj;
    private static final String TAG = "ShowResult";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showresult);

        initView();
        LoadData load = null;
            load = new LoadData();
        load.setListener(new LoadData.Listener() {
            @Override
            public void setItem(Item item) {
                if (item != null){
                    zi.setText(item.getZi());
                    bihua.setText(item.getBihua());
                    bushou.setText(item.getBushou());
                    py.setText(item.getPy());
                    jijie.setText("简解："+dealString(item.getJijie()));
                    xj.setText("详解："+dealString(item.getXiangjie()));
                    /*jijie.setText("简解："+item.getJijie());
                    xj.setText("详解："+item.getXiangjie());*/
                }else {
                    Toast.makeText(getApplicationContext(),"查询失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
            word = getIntent().getStringExtra("word");
            load.execute(word);
    }

    private String dealString(String str){
        char []arr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] == '"' && arr[i+1] == ',' && arr[i+2] == '"'){
                sb.append(arr[i]).append(arr[i+1]).append("\r\n").append(arr[i+2]);
                i += 2;
            }else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    private void  initView(){
        zi = (TextView) findViewById(R.id.zi_show);
        bihua = (TextView) findViewById(R.id.bihua_show);
        bushou = (TextView) findViewById(R.id.bushou_show);
        py = (TextView) findViewById(R.id.pinyin_show);
        xj = (TextView) findViewById(R.id.xiangjie_show);
        jijie = (TextView) findViewById(R.id.jijie_show);
    }
}
