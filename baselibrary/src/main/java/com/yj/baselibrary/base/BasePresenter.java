package com.yj.baselibrary.base;

import android.content.Context;

import com.yj.baselibrary.baserx.RxManager;

/**
 */

public abstract class BasePresenter <T,E>{

    public Context mContext;

    public E mModel;

    public T mView;

    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
