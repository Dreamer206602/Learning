package com.mx.learning.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mx.learning.R;
import com.mx.learning.ui.activity.view.CountTimerActivity;
import com.mx.learning.ui.activity.view.CustomeViewLearnActivity;
import com.mx.learning.ui.activity.view.LoadingViewActivity;
import com.mx.learning.ui.adapter.CustomViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomViewActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private CustomViewAdapter mAdapter;
    private List<String>mStrings=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();

    }

    private void initData() {
        mStrings.add("自定义View初始化");
        mStrings.add("启动页动画");
        mStrings.add("绚丽钟表1");
        mStrings.add("绚丽钟表2");
        mStrings.add("绚丽钟表3");

        mAdapter=new CustomViewAdapter(mStrings);
        mAdapter.openLoadAnimation();

        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(CustomViewActivity.this, CustomeViewLearnActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(CustomViewActivity.this, CountTimerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(CustomViewActivity.this, LoadingViewActivity.class));

                        break;
                }
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

    }
}
