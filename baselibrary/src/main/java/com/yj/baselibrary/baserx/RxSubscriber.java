package com.yj.baselibrary.baserx;

import android.content.Context;

import com.yj.baselibrary.R;
import com.yj.baselibrary.base.BaseApplication;
import com.yj.baselibrary.utils.NetWorkUtils;
import com.yj.baselibrary.utils.ToastUitl;
import com.yj.baselibrary.widget.DialogUtile;

import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by yangjie on 17/6/20.
 */

public abstract class  RxSubscriber<T> extends Subscriber<T>{

private Context mContext;

private String msg;

public RxSubscriber(Context context, String msg){
        this.mContext = context;
        this.msg = msg; }
public RxSubscriber(Context context) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading));
        }

@Override
public void onCompleted() {
        }
@Override
public void onStart() {
        super.onStart();
        }


@Override
public void onNext(T t) {
        _onNext(t);
        }

@Override
public void onError(Throwable e) {
        e.printStackTrace();
        //网络断开
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())){
         _onError(BaseApplication.getAppContext().getString(R.string.no_net));
          ToastUitl.show(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器异常
        else if (e instanceof ServerException){
         _onError(e.getMessage());
        ToastUitl.show("服务器异常,请稍后再试");
        }
        else if(e instanceof retrofit2.adapter.rxjava.HttpException){
          _onError("500");
         ToastUitl.show("服务器异常,请稍后再试");
        }
        //服务器请求超时
        else if(e instanceof SocketTimeoutException){
         _onError(e.getMessage());
        ToastUitl.show("服务请求超时,请稍后再试");
        }
        //其它
        else{
           _onError(BaseApplication.getAppContext().getString(R.string.net_error));
        }
        DialogUtile.closeDialog();
        }

protected abstract void _onNext(T t);

protected abstract void _onError(String message);
}
