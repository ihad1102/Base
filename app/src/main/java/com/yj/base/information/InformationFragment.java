package com.yj.base.information;

import android.view.View;

import com.yj.base.R;
import com.yj.baselibrary.base.BaseFragment;

/**
 * 咨询
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener{
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {
    }

    /**
     * 构造空的fragmnet
     */
    public InformationFragment() {

    }

//    public static InformationFragment mineFragment;
//
//    public static InformationFragment getInstance() {
//        mineFragment = new InformationFragment();
//        return mineFragment;
//    }
    @Override
    protected void initView(){

    }

    @Override
    public void onClick(View v) {

    }
}
