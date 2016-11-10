package com.mx.learning.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mx.learning.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_custom_view)
    TextView mTvCustom;
    @Bind(R.id.tv_animation)
    TextView mTvAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initView();


    }

    private void initView() {
        mTvAnimation.setOnClickListener(this);
        mTvCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_custom_view:
                startActivity(new Intent(this,CustomViewActivity.class));


                break;
            case R.id.tv_animation:
                startActivity(new Intent(this,AnimationActivity.class));
                break;
        }
    }
}
