package com.example.gd_lg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gd_lg.R;
import com.example.gd_lg.SQLite.DBAdapter;
import com.example.gd_lg.encapsulation.LinearUserInfo;

import java.util.Iterator;
import java.util.List;

public class LinearLayoutActivity extends AppCompatActivity {

    EditText edt_account, edt_password;
    RadioGroup rag_sex;
    CheckBox cbx_remember, cbx_autoLogin;
    Button btn_register, btn_login;
    TextView txt_print;

    private String sex;
    private String remember;
    private String autoLogin;

    List<LinearUserInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        init();

        setListener();

        sex = "男";
        remember = "否";
        autoLogin = "否";

        cbx_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    remember = "是";
                } else {
                    remember = "否";
                }
            }
        });

        cbx_autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    autoLogin = "是";
                } else {
                    autoLogin = "否";
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearUserInfo userInfo = new LinearUserInfo();
                userInfo.setAccount(edt_account.getText().toString().trim());
                userInfo.setPassword(edt_password.getText().toString().trim());
                userInfo.setSex(sex);
                userInfo.setRemember(remember);
                userInfo.setAutoLogin(autoLogin);
//                Log.i("info", userInfo.getSex() + userInfo.getRemember() + userInfo.getAutoLogin());
                DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                dbAdapter.open();
                if (dbAdapter.insert_linear(userInfo.getAccount(), userInfo.getPassword(), userInfo.getSex(), userInfo.getRemember(), userInfo.getAutoLogin())) {
                    txt_print.setText("注册成功");
                } else {
                    txt_print.setText("注册失败");
                }
                dbAdapter.close();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearUserInfo userInfo = new LinearUserInfo();
                userInfo.setAccount(edt_account.getText().toString().trim());
                userInfo.setPassword(edt_password.getText().toString().trim());
                DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                dbAdapter.open();
                if (dbAdapter.login_linear(userInfo.getAccount(), userInfo.getPassword())) {
                    list = dbAdapter.select(userInfo.getAccount(), userInfo.getPassword());
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        LinearUserInfo linearUserInfo = (LinearUserInfo) iterator.next();
                        txt_print.setText("");
                        txt_print.append(linearUserInfo.toString());
                        txt_print.append("\n");
                    }
                } else {
                    txt_print.setText("登录失败");
                }
                dbAdapter.close();
            }
        });
    }

    private void setListener() {
        rag_sex.setOnCheckedChangeListener(mylistener);
    }

    RadioGroup.OnCheckedChangeListener mylistener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setText();
        }
    };

    private void setText() {
        RadioButton radioButton = findViewById(rag_sex.getCheckedRadioButtonId());
        sex = radioButton.getText().toString();
    }

    private void init() {
        edt_account = findViewById(R.id.linear_edt_account);
        edt_password = findViewById(R.id.linear_edt_password);
        rag_sex = findViewById(R.id.linear_rag_sex);
        cbx_remember = findViewById(R.id.linear_cbx_remember);
        cbx_autoLogin = findViewById(R.id.linear_cbx_autoLogin);
        btn_login = findViewById(R.id.linear_btn_login);
        btn_register = findViewById(R.id.linear_btn_register);
        txt_print = findViewById(R.id.linear_txt_print);
    }
}