package com.yj.baselibrary.baserx;

/**
 * 服务器请求异常
 */

public class ServerException extends Exception {

    public ServerException(String msg){
        super(msg);
    }
}
