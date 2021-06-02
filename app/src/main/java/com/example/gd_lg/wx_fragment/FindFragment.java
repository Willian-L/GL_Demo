package com.example.gd_lg.wx_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gd_lg.R;
import com.example.gd_lg.activities.FirstActivity;
import com.example.gd_lg.activities.GetExtraActivity;
import com.example.gd_lg.activities.LinWeiLiActivity;
import com.example.gd_lg.activities.LinearLayoutActivity;
import com.example.gd_lg.activities.SQLiteActivity;
import com.example.gd_lg.activities.WeiXinActivity;

import org.w3c.dom.Text;


public class FindFragment extends Fragment{

    EditText edt_extra;
    ConstraintLayout btn_put_extra, btn_intent, btn_linear, btn_sqlite;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        init(view);

        btn_put_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GetExtraActivity.class);
                intent.putExtra("name", edt_extra.getText().toString().trim());
                startActivity(intent);
            }
        });

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LinWeiLiActivity.class);
                startActivity(intent);
            }
        });

        btn_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        btn_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SQLiteActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void init(View view){
        edt_extra = view.findViewById(R.id.find_edt_putExtra);
        btn_put_extra = view.findViewById(R.id.find_btn_put_extra);
        btn_intent = view.findViewById(R.id.find_btn_intent);
        btn_linear = view.findViewById(R.id.find_btn_linear);
        btn_sqlite = view.findViewById(R.id.find_btn_sqlite);
    }
}