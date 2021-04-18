package com.example.gd_lg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gd_lg.R;

public class GetExtraActivity extends Activity {

    TextView txt_getExtra;
    Button btn_singleTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_extra);

        Log.d("onCreate", "GetExtraActivity onCreate()");

        init();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
//        Log.i("getExtra", name);
        try {
            txt_getExtra.setText(name);
        } catch (Exception e){
            Log.e("getExtra", "null");
        }
    }

    private void init(){
        txt_getExtra = findViewById(R.id.txt_getExtra);
        btn_singleTop = findViewById(R.id.btn_singleTop);
    }

    public void singleTop(View view) {
        Intent intent = new Intent(this, GetExtraActivity.class);
        startActivity(intent);
        Log.d("singleTop", "启动自身，singleTop Mode不会再次调用onCreate()");
    }
}