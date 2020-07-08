package com.yj.base.home;

import android.view.View;
import com.yj.base.R;
import com.yj.baselibrary.base.BaseFragment;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 构造空的fragmnet
     */
    public HomeFragment() {

    }

    public static HomeFragment homeFragment;

    public static HomeFragment getInstance(){
        homeFragment = new HomeFragment();
        return homeFragment;
    }
    @Override
    protected int getLayoutResource(){
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter(){
    }
    @Override
    protected void initView(){
    }

    @Override
    public void onClick(View v) {

    }
}
