package com.yj.base.information;

import android.view.View;
import android.widget.TextView;

import com.yj.base.R;
import com.yj.baselibrary.base.BaseFragment;

/**
 * 咨询
 */
public class InformationFragment extends BaseFragment{

    private TextView tb_tv_title;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_information;
    }

    @Override
    public void initPresenter() {
    }

    /**
     * 构造空的fragmnet
     */
    public InformationFragment() {

    }

    public static InformationFragment mineFragment;

    public static InformationFragment getInstance() {
        mineFragment = new InformationFragment();
        return mineFragment;
    }
    @Override
    protected void initView(){
        tb_tv_title=rootView.findViewById(R.id.tb_tv_title);
        tb_tv_title.setText("资讯");
    }
}
