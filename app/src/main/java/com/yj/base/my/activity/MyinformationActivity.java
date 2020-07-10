package com.yj.base.my.activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yj.base.R;
import com.yj.baselibrary.base.BaseActivity;

/**
 * 个人信息
 */
public class MyinformationActivity extends BaseActivity implements  View.OnClickListener {

    private TextView  tb_tv_title;
    private ImageView iv_left;
    @Override
    public int getLayoutId() {
        return R.layout.activity_myinformation;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        tb_tv_title=findViewById(R.id.tb_tv_title);
        iv_left=findViewById(R.id.iv_left);
        tb_tv_title.setText("个人信息");
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
