package com.yj.baselibrary.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DisplayUtils {

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //不是第一个的格子都设一个上边和底部的间距  这些间隔大小可以自行修改
            int pos = parent.getChildLayoutPosition(view);  //当前条目的position
            int itemCount = state.getItemCount() - 1;           //最后一条的postion

            if (pos == itemCount) {   //最后一条
                outRect.bottom = 0;
                outRect.top = 1;
            } else {
                if (pos % 2 == 1) {  //下面一行
                    outRect.bottom = 1;
                    outRect.top = 1;
                } else { //上面一行
                    if (pos == 0) {
                        outRect.bottom = 1;
                        outRect.top =2;
                    } else {
                        outRect.top = 1;
                        outRect.bottom = 1;
                    }
                }

            }
        }}
}
