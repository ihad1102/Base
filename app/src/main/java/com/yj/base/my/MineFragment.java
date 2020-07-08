package com.yj.base.my;
import android.view.View;
import com.yj.base.R;
import com.yj.baselibrary.base.BaseFragment;

/**
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener{
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
    public MineFragment() {

    }

    public static MineFragment mineFragment;
    public static MineFragment getInstance() {
        mineFragment = new MineFragment();
        return mineFragment;
    }
    @Override
    protected void initView(){

    }

    @Override
    public void onClick(View v) {

    }
}
