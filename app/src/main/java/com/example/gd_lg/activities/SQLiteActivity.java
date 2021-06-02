package com.example.gd_lg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gd_lg.R;
import com.example.gd_lg.SQLite.SQLiteDBAdapter;
import com.example.gd_lg.encapsulation.SQLiteInfo;

public class SQLiteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txt_result;
    private Button btn_open, btn_close, btn_insert, btn_delete, btn_query, btn_update;
    private EditText edt_non_stu_id, edt_non_stu_name, edt_que_stu_id, edt_ori_stu_id, edt_new_stu_id;
    private boolean dbState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        init();
        SQLiteInfo info = new SQLiteInfo();

        SQLiteDBAdapter dbAdapter = new SQLiteDBAdapter(getApplicationContext());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbAdapter.open()){
                    btn_open.setEnabled(false);
                    btn_close.setEnabled(true);
                    txt_result.setText("SQLite database is open");
                    dbState = true;
                }
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbAdapter.close()){
                    btn_open.setEnabled(true);
                    btn_close.setEnabled(false);
                    txt_result.setText("SQLite database is closed");
                    edt_new_stu_id.setText(null);
                    edt_non_stu_id.setText(null);
                    edt_non_stu_name.setText(null);
                    edt_ori_stu_id.setText(null);
                    edt_que_stu_id.setText(null);
                    btn_delete.setEnabled(false);
                    btn_insert.setEnabled(false);
                    btn_query.setEnabled(false);
                    btn_update.setEnabled(false);
                    dbState = false;
                }
            }
        });

        edt_non_stu_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (dbState == true && !TextUtils.isEmpty(edt_non_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_non_stu_name.getText().toString())){
                    btn_insert.setEnabled(true);
                } else {
                    btn_insert.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dbState == true && !TextUtils.isEmpty(edt_non_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_non_stu_name.getText().toString())){
                    btn_insert.setEnabled(true);
                } else {
                    btn_insert.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_non_stu_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (dbState == true && !TextUtils.isEmpty(edt_non_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_non_stu_name.getText().toString())){
                    btn_insert.setEnabled(true);
                } else {
                    btn_insert.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dbState == true && !TextUtils.isEmpty(edt_non_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_non_stu_name.getText().toString())){
                    btn_insert.setEnabled(true);
                } else {
                    btn_insert.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_que_stu_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (dbState == true && !TextUtils.isEmpty(edt_que_stu_id.getText().toString())){
                    btn_query.setEnabled(true);
                    btn_delete.setEnabled(true);
                } else {
                    btn_query.setEnabled(false);
                    btn_delete.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dbState == true && !TextUtils.isEmpty(edt_que_stu_id.getText().toString())){
                    btn_query.setEnabled(true);
                    btn_delete.setEnabled(true);
                } else {
                    btn_query.setEnabled(false);
                    btn_delete.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_ori_stu_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (dbState == true && !TextUtils.isEmpty(edt_ori_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_new_stu_id.getText().toString())){
                    btn_update.setEnabled(true);
                } else {
                    btn_update.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dbState == true && !TextUtils.isEmpty(edt_ori_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_new_stu_id.getText().toString())){
                    btn_update.setEnabled(true);
                } else {
                    btn_update.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_new_stu_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (dbState == true && !TextUtils.isEmpty(edt_ori_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_new_stu_id.getText().toString())){
                    btn_update.setEnabled(true);
                } else {
                    btn_update.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dbState == true && !TextUtils.isEmpty(edt_ori_stu_id.getText().toString()) && !TextUtils.isEmpty(edt_new_stu_id.getText().toString())){
                    btn_update.setEnabled(true);
                } else {
                    btn_update.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setNon_id(edt_non_stu_id.getText().toString().trim());
                info.setNon_name(edt_non_stu_name.getText().toString().trim());
                txt_result.setText(dbAdapter.insert(info.getNon_id(), info.getNon_name()));
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setQue_id(edt_que_stu_id.getText().toString().trim());
                txt_result.setText(dbAdapter.delete(info.getQue_id()));
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setQue_id(edt_que_stu_id.getText().toString().trim());
                txt_result.setText(dbAdapter.query(info.getQue_id()));
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setOri_id(edt_ori_stu_id.getText().toString().trim());
                info.setNew_id(edt_new_stu_id.getText().toString().trim());
                txt_result.setText(dbAdapter.update(info.getOri_id(), info.getNew_id()));
            }
        });
    }

    private void init(){
        toolbar = findViewById(R.id.sql_toolbar);
        txt_result = findViewById(R.id.sql_txt_result);
        btn_open = findViewById(R.id.sql_btn_open);
        btn_close = findViewById(R.id.sql_btn_close);
        btn_insert = findViewById(R.id.sql_btn_insert);
        btn_delete = findViewById(R.id.sql_btn_delete);
        btn_query = findViewById(R.id.sql_btn_query);
        btn_update = findViewById(R.id.sql_btn_update);
        edt_non_stu_id = findViewById(R.id.sql_edt_ins_stu_id);
        edt_non_stu_name = findViewById(R.id.sql_edt_ins_stu_name);
        edt_que_stu_id = findViewById(R.id.sql_edt_sel_stu_id);
        edt_ori_stu_id = findViewById(R.id.sql_edt_ori_id);
        edt_new_stu_id = findViewById(R.id.sql_edt_new_id);
    }
}