package com.example.gd_lg.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gd_lg.R;
import com.example.gd_lg.wx_fragment.FindFragment;
import com.example.gd_lg.wx_fragment.IpaFragment;
import com.example.gd_lg.wx_fragment.MyFragment;
import com.example.gd_lg.wx_fragment.NewsFragment;


public class WeiXinActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_search, btn_add, btn_news, btn_ipa, btn_find, btn_my;
    FrameLayout fm_content;

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

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick Search", Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick Add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        btn_search = findViewById(R.id.btn_wx_search);
        btn_add = findViewById(R.id.btn_wx_add);
        btn_news = findViewById(R.id.btn_wx_news);
        btn_ipa = findViewById(R.id.btn_wx_ipa);
        btn_find = findViewById(R.id.btn_wx_find);
        btn_my = findViewById(R.id.btn_wx_my);
        fm_content = findViewById(R.id.fm_wx_content);

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
}