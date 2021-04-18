package com.example.gd_lg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gd_lg.R;

public class FirstActivity extends Activity {

    EditText edt_putExtra;
    Button btn_intent, btn_putExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, LinWeiLiActivity.class);
                startActivity(intent);
            }
        });

        btn_putExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_putExtra.getText().toString().trim();
                Log.i("putExtra", name);
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.setClass(FirstActivity.this, GetExtraActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        btn_intent = findViewById(R.id.btn_intent);
        btn_putExtra = findViewById(R.id.btn_putExtra);
        edt_putExtra = findViewById(R.id.edt_putExtra);
    }
}