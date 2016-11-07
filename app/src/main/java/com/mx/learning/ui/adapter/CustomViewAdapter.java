package com.mx.learning.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mx.learning.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class CustomViewAdapter extends BaseQuickAdapter<String> {
    public CustomViewAdapter(List<String> data) {
        super(R.layout.item_custom_view,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s) {

        holder.setText(R.id.tv_title,s);


    }
}
