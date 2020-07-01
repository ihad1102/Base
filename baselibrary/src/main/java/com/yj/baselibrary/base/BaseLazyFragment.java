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
public abstract  class BaseLazyFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements View.OnTouchListener  {
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected final String TAG = "BaseLazyFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
        rootView = inflater.inflate(getLayoutResource(), container, false);
        isInit = true;
        mRxManager=new RxManager();
        mPresenter = TUtil.getT(this, 0);
        mModel= TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this.getActivity();
        }
        initPresenter();
        initView();
        isCanLoadData();
        return rootView;
    }
    //获取布局文件
    protected abstract int getLayoutResource();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    protected abstract void initView();

    /**
     * 获取设置的布局
     *
     * @return
     */
    protected View getContentView() {
        return rootView;
    }

    /**
     * 找出对应的控件
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewById(int id) {

        return (T) getContentView().findViewById(id);
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
     */
    protected void stopLoad() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.onDestroy();
            mRxManager.clear();
            isInit = false;
            isLoad = false;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }
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
    protected void setSP(int userId,String userName,String nickName,boolean isFirst){
        SharedPreferencesUtils.setIntDate("USERID",userId);
        SharedPreferencesUtils.setStringDate("USERNAME",userName);
        SharedPreferencesUtils.setStringDate("NICKRNAME",nickName);
        SharedPreferencesUtils.setBooleanDate("ISLOGIN",isFirst);
    }
    protected UserInfoBean getSP(){
        UserInfoBean entity = new UserInfoBean();
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
