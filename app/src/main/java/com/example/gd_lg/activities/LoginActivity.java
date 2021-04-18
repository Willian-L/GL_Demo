package com.example.gd_lg.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gd_lg.R;
import com.example.gd_lg.SQLite.DBAdapter;
import com.example.gd_lg.encapsulation.UserInfo;

public class LoginActivity extends AppCompatActivity {

    EditText edt_account, edt_password;
    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        init();

        edt_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edt_password.getText().toString())) {
                    btn_login.setEnabled(true);
                    btn_login.setBackgroundResource(R.drawable.btn_true);
                } else {
                    btn_login.setEnabled(false);
                    btn_login.setBackgroundResource(R.drawable.btn_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edt_account.getText().toString())) {
                    btn_login.setEnabled(true);
                    btn_login.setBackgroundResource(R.drawable.btn_true);
                } else {
                    btn_login.setEnabled(false);
                    btn_login.setBackgroundResource(R.drawable.btn_false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(edt_account.getText().toString().trim());
                userInfo.setPassword(edt_password.getText().toString().trim());

                DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                dbAdapter.open();
                if (dbAdapter.insert(userInfo.getAccount(), userInfo.getPassword())){
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                }
                dbAdapter.close();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(edt_account.getText().toString().trim());
                userInfo.setPassword(edt_password.getText().toString().trim());

                DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                dbAdapter.open();

                if (dbAdapter.login(userInfo.getAccount(), userInfo.getPassword())){
                    Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_LONG).show();
                }
                dbAdapter.close();
            }
        });
    }

    private void init(){
        edt_account = findViewById(R.id.edt_login_account);
        edt_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login_login);
        btn_register = findViewById(R.id.btn_login_register);
    }
}