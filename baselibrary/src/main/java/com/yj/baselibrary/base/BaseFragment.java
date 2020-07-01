package com.yj.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yj.baselibrary.R;
import com.yj.baselibrary.UserInfoBean;
import com.yj.baselibrary.baserx.RxManager;
import com.yj.baselibrary.utils.SharedPreferencesUtils;
import com.yj.baselibrary.utils.TUtil;

/**
 * 基类fragment
 */
public abstract  class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements View.OnTouchListener  {
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
        rootView = inflater.inflate(getLayoutResource(), container, false);
        mRxManager=new RxManager();
        mPresenter = TUtil.getT(this, 0);
        mModel= TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this.getActivity();
        }
        initPresenter();
        initView();
        return rootView;
    }
    //获取布局文件
    protected abstract int getLayoutResource();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    protected abstract void initView();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    protected void setSessionId(String sessionId){
        SharedPreferencesUtils.setStringDate("SESSIONID",sessionId);
    }
    protected void setSP(int userId,int settled,String userName,String nickName,boolean isFirst){
        SharedPreferencesUtils.setIntDate("USERID",userId);
        SharedPreferencesUtils.setIntDate("SETTLED",settled);
        SharedPreferencesUtils.setStringDate("USERNAME",userName);
        SharedPreferencesUtils.setStringDate("NICKRNAME",nickName);
        SharedPreferencesUtils.setBooleanDate("ISLOGIN",isFirst);
    }
    protected UserInfoBean getSP(){
        UserInfoBean entity = new UserInfoBean();
        entity.setSettled(SharedPreferencesUtils.getIntDate("SETTLED"));
        entity.setFirst(SharedPreferencesUtils.getBooleanDate("ISLOGIN"));
        entity.setUsername(SharedPreferencesUtils.getStringDate("USERNAME"));
        entity.setNickname(SharedPreferencesUtils.getStringDate("NICKRNAME"));
        entity.setSessionId(SharedPreferencesUtils.getStringDate("SESSIONID"));
        entity.setUserId(SharedPreferencesUtils.getIntDate("USERID"));
        return entity;
    }
    protected void clearData(){
        SharedPreferencesUtils.clearUser();
        SharedPreferencesUtils.setBooleanDate("isOpen",true);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
           mPresenter.onDestroy();
           mRxManager.clear();
    }

    /**
     * 防止点击穿透
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // 拦截触摸事件，防止泄露下去
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

}
