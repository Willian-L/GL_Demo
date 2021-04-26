package com.example.gd_lg.wx_fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gd_lg.R;
import com.example.gd_lg.activities.WeiXinActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    public NewsFragment( ) {
        // Required empty public constructor
    }

    private List<WxList> mydata;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mydata = new ArrayList<WxList>();
        WxList data1 = new WxList();
        data1.hg = R.mipmap.hp_boy;
        data1.name = "林炜力";
        data1.content = "2013202604032";
        mydata.add(data1);
        WxList data2 = new WxList();
        data2.hg = R.mipmap.hp_girl;
        data2.name = "专插本计科4班";
        data2.content = "广东理工学院信息系";
        mydata.add(data2);
        WxList data3 = new WxList();
        data3.hg = R.mipmap.hp_boy;
        data3.name = "微信";
        data3.content = "微信是腾讯公司推出的一款供即时通讯服务的免费应用程序";
        mydata.add(data3);
        WxList data4 = new WxList();
        data4.hg = R.mipmap.hp_girl;
        data4.name = "Tencent";
        data4.content = "腾讯于1998年11月成立,是一家以互联网为基础的平台公司,通过技术丰富互联网用户的生活,助力企业数字化升级。我们的使命是“用户为本 科技向善”。";
        mydata.add(data4);
        WxList data5 = new WxList();
        data5.hg = R.mipmap.hp_girl;
        data5.name = "QQ";
        data5.content = "腾讯QQ,8亿人在用的即时通讯软件,你不仅可以在各类通讯终端上通过QQ聊天交友,还能进行免费的视频、语音通话,或者随时随地收发重要文件。";
        mydata.add(data5);
        MylistAdapter mylistAdapter = new MylistAdapter();
        ListView newList = view.findViewById(R.id.lv_content);
        newList.setAdapter(mylistAdapter);
        newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "点击了第" + (position+1) + "项", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class MylistAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mydata.size();
        }

        @Override
        public Object getItem(int position) {
            return mydata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weixin_list, null);
            ImageView hg = itemView.findViewById(R.id.img_wx_list_hg);
            hg.setImageResource(mydata.get(position).hg);
            TextView name = itemView.findViewById(R.id.txt_wx_list_name);
            name.setText(mydata.get(position).name);
            TextView content = itemView.findViewById(R.id.txt_wx_list_content);
            content.setText(mydata.get(position).content);
            return itemView;
        }
    }
}