package com.yj.baselibrary.listener;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * 去掉下划线
 */
public abstract class NoLineColorSpan  extends ClickableSpan {
    @Override
    public void onClick(@NonNull View widget) {

    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
