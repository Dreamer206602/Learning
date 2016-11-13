package com.mx.learning.ui.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mx.learning.R;
import com.mx.learning.widget.counterDown.CounterDownView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 倒计时界面
 */
public class CountTimerActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.countDownView)
    CounterDownView mCounterDownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mCounterDownView.setOnClickListener(this);
        mCounterDownView.setCountDownListener(new CounterDownView.CountDownListener() {
            @Override
            public void onStartCountDown() {
                Toast.makeText(getApplicationContext(),"开始计时",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFinishCountDown() {
                Toast.makeText(getApplicationContext(),"计时结束",Toast.LENGTH_SHORT).show();


            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.countDownView:
                mCounterDownView.start();
                break;
        }
    }
}
