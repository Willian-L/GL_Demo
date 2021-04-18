package com.example.gd_lg.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gd_lg.permissionsUtils.PermissionsUtils;
import com.example.gd_lg.R;

public class LinWeiLiActivity extends Activity {

    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lin_wei_li);

        init();

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("2013202604032");
            }
        });
    }

    private void init(){
        btn_call = findViewById(R.id.btn_call);
    }

    //拨号
    public void call(String telPhone) {
        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
        if(PermissionsUtils.getInstance().checkPermissions(LinWeiLiActivity.this, permissions, permissionsResult)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telPhone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
//            权限通过
        }

        @Override
        public void forbitPermissons() {
            Toast.makeText(LinWeiLiActivity.this, "权限不通过!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 权限请求
     */
//    public static final int REQUEST_CALL_PERMISSION = 10001;
//    public boolean checkReadPermission(String string_permission, int request_code){
//        boolean flag = false;
//        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {
//            flag = true;
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
//        }
//        return flag;
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case REQUEST_CALL_PERMISSION:
//                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "该功能需要拨号权限，请重试", Toast.LENGTH_SHORT).show();
//                } else {
//                    call("tel:10086");
//                }
//                break;
//        }
//    }
}