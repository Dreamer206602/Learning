package com.mx.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_custom_view)
    TextView mTvCustom;
    @Bind(R.id.tv_animation)
    TextView mTvAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
