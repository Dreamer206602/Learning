package com.mx.learning.ui.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.mx.learning.R;
import com.mx.learning.widget.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoadingViewActivity extends AppCompatActivity {

    @Bind(R.id.progressBar)
    SeekBar mProgressBar;
    @Bind(R.id.loadView)
    LoadingView mLoadingView;
    @Bind(R.id.btn_click)
    Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

//        mProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mLoadingView.setProgress(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoadingView.startAnimation(1,100,5000);

            }
        });

    }
}
