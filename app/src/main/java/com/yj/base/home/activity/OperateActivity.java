package com.yj.base.home.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yj.base.R;
import com.yj.baselibrary.base.BaseActivity;

/**
 * 招商运营
 */
public class OperateActivity extends BaseActivity  implements  View.OnClickListener{
    private TextView tb_tv_title;
    private ImageView iv_left;
    @Override
    public int getLayoutId() {
        return R.layout.activity_operate;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tb_tv_title=findViewById(R.id.tb_tv_title);
        iv_left=findViewById(R.id.iv_left);
        tb_tv_title.setText("招商运营");
        iv_left.setVisibility(View.VISIBLE);
        initEvent();

    }

    private void initEvent() {
        iv_left.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finishActivity();
                break;
        }
    }
}