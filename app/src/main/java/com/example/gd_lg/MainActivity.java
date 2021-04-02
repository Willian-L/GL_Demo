package com.example.gd_lg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edt_putExtra;
    Button btn_intent, btn_putExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LinWeiLiActivity.class);
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
                intent.setClass(MainActivity.this, GetExtraActivity.class);
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

