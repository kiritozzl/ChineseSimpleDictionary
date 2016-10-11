package com.example.kirito.chinesedictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et.getText().toString();
                if (!word.equals("")) {
                    Intent intent = new Intent(MainActivity.this, ShowResult.class);
                    intent.putExtra("isWord", true);
                    intent.putExtra("word", word);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"查询的字不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
