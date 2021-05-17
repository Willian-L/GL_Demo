package com.example.gd_lg.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gd_lg.R;
import com.example.gd_lg.wx_fragment.FindFragment;
import com.example.gd_lg.wx_fragment.IpaFragment;
import com.example.gd_lg.wx_fragment.MyFragment;
import com.example.gd_lg.wx_fragment.NewsFragment;

public class WeiXinActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_news, btn_ipa, btn_find, btn_my;
    FrameLayout fm_content;
    TextView txt_title;
    Toolbar toolbar;

    private NewsFragment fg_news;
    private IpaFragment fg_ipa;
    private FindFragment fg_find;
    private MyFragment fg_my;
    private FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);

        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        init();

        this.fragmentManager = getSupportFragmentManager();

        btn_news.performClick();

        setToolbar();
    }

    private void init(){
        btn_news = findViewById(R.id.btn_wx_news);
        btn_ipa = findViewById(R.id.btn_wx_ipa);
        btn_find = findViewById(R.id.btn_wx_find);
        btn_my = findViewById(R.id.btn_wx_my);
        fm_content = findViewById(R.id.fm_wx_content);
        txt_title = findViewById(R.id.tool_title);
        toolbar = findViewById(R.id.wx_toolBar);

        btn_news.setOnClickListener(this);
        btn_ipa.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_my.setOnClickListener(this);
    }

    private void setSelected(){
        btn_news.setBackgroundResource(R.mipmap.wx_news_false);
        btn_ipa.setBackgroundResource(R.mipmap.wx_ipa_false);
        btn_find.setBackgroundResource(R.mipmap.wx_find_false);
        btn_my.setBackgroundResource(R.mipmap.wx_my_false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg_news != null)fragmentTransaction.hide(fg_news);
        if(fg_ipa != null)fragmentTransaction.hide(fg_ipa);
        if(fg_find != null)fragmentTransaction.hide(fg_find);
        if(fg_my != null)fragmentTransaction.hide(fg_my);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.btn_wx_news:
                txt_title.setText("微信");
                toolbar.setVisibility(View.VISIBLE);
                setSelected();
                btn_news.setBackgroundResource(R.mipmap.wx_news_true);
                if (fg_news == null){
                    fg_news = new NewsFragment();
                    fTransaction.add(R.id.fm_wx_content, fg_news);
                }else {
                    fTransaction.show(fg_news);
                }
                break;
            case R.id.btn_wx_ipa:
                txt_title.setText("通讯录");
                toolbar.setVisibility(View.VISIBLE);
                setSelected();
                btn_ipa.setBackgroundResource(R.mipmap.wx_ipa_true);
                if (fg_ipa == null){
                    fg_ipa = new IpaFragment();
                    fTransaction.add(R.id.fm_wx_content, fg_ipa);
                }else {
                    fTransaction.show(fg_ipa);
                }
                break;
            case R.id.btn_wx_find:
                txt_title.setText("发现");
                toolbar.setVisibility(View.VISIBLE);
                setSelected();
                btn_find.setBackgroundResource(R.mipmap.wx_find_true);
                if (fg_find == null){
                    fg_find = new FindFragment();
                    fTransaction.add(R.id.fm_wx_content, fg_find);
                }else {
                    fTransaction.show(fg_find);
                }
                break;
            case R.id.btn_wx_my:
                txt_title.setText("我");
                toolbar.setVisibility(View.GONE);
                setSelected();
                btn_my.setBackgroundResource(R.mipmap.wx_my_true);
                if (fg_my == null){
                    fg_my = new MyFragment();
                    fTransaction.add(R.id.fm_wx_content, fg_my);
                }else {
                    fTransaction.show(fg_my);
                }
                break;
        }
        fTransaction.commit();
    }

    private void setToolbar(){
        setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        Toast.makeText(WeiXinActivity.this, "Click search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_add_group_chat:
                        Toast.makeText(WeiXinActivity.this, "Click add groud chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_add_friend:
                        Toast.makeText(WeiXinActivity.this, "Click add friend", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_scan:
                        Toast.makeText(WeiXinActivity.this, "Click scan", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_payment:
                        Toast.makeText(WeiXinActivity.this, "Click payment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_help:
                        Toast.makeText(WeiXinActivity.this, "Click help", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wei_xin_plus, menu);
        return true;
    }

    //    @SuppressLint("RestrictedApi")
//    private void showPopupMenu(final Context context, View view){
//        PopupMenu popupMenu = new PopupMenu(context, view);
//        popupMenu.inflate(R.menu.menu_wei_xin_plus);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_add_group_chat:
//                        Toast.makeText(getApplicationContext(), "添加群聊", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_add_friend:
//                        Toast.makeText(getApplicationContext(), "添加好友", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_scan:
//                        Toast.makeText(getApplicationContext(), "扫一扫", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_payment:
//                        Toast.makeText(getApplicationContext(), "收付款", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_help:
//                        Toast.makeText(getApplicationContext(), "帮助与反馈", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//                return false;
//            }
//        });
//        try {
//            Field field = popupMenu.getClass().getDeclaredField("mPopup");
//            field.setAccessible(true);
//            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
//            mHelper.setForceShowIcon(true);
//        } catch (IllegalAccessException | NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        popupMenu.show();
//    }
}