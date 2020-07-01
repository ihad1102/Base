package com.yj.baselibrary;

import java.io.Serializable;

/**
 * Created by yangjie on 17/8/2.
 */

public class UserInfoBean implements Serializable {
    private int userId;
    private int settled;
    private String sessionId;
    private String nickname;
    private String username;
    private boolean isFirst; //第一次登录

    public int getSettled() {
        return settled;
    }

    public void setSettled(int settled) {
        this.settled = settled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

}
