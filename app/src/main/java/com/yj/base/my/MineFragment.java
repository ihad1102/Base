package com.yj.base.my;
import android.view.View;
import android.widget.LinearLayout;

import com.yj.base.R;
import com.yj.base.my.activity.MyinformationActivity;
import com.yj.baselibrary.base.BaseFragment;

/**
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener{

    private LinearLayout ly_persion_information,csl_cache,csl_version,csl_service,csl_aboutas,csl_message,csl_exit;
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
        ly_persion_information=rootView.findViewById(R.id.ly_persion_information);
        csl_cache=rootView.findViewById(R.id.csl_cache);
        csl_version=rootView.findViewById(R.id.csl_version);
        csl_service=rootView.findViewById(R.id.csl_service);
        csl_aboutas=rootView.findViewById(R.id.csl_aboutas);
        csl_message=rootView.findViewById(R.id.csl_message);
        csl_exit=rootView.findViewById(R.id.csl_exit);
        initEvent();
    }

    private void initEvent() {
        ly_persion_information.setOnClickListener(this);
        csl_cache.setOnClickListener(this);
        csl_version.setOnClickListener(this);
        csl_service.setOnClickListener(this);
        csl_aboutas.setOnClickListener(this);
        csl_message.setOnClickListener(this);
        csl_exit.setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ly_persion_information:
                startActivity(MyinformationActivity.class);
                 break;
             case R.id.csl_cache:

                 break;
             case R.id.csl_version:

                 break;
             case R.id.csl_service:

                 break;
             case R.id.csl_message:

                 break;
             case R.id.csl_aboutas:

                 break;
             case R.id.csl_exit:

                 break;
         }
    }
}
