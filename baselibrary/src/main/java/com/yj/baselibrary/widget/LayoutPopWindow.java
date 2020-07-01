package com.yj.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 首页自定义泡泡窗口(根据控件的位置进行窗口显示)
 */
public class LayoutPopWindow extends PopupWindow {

    private SparseArray<View> mViews;
    private int layoutId;
    private ViewGroup mConvertView;

    public LayoutPopWindow(Activity context, int layoutId, View layout){
       this.layoutId=layoutId;
       this.mViews=new SparseArray<View>();
        LayoutInflater mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         mConvertView = (ViewGroup) mLayoutInflater.inflate(
                 layoutId, null, true);
        this.setContentView(mConvertView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mConvertView.setTag(this);
        // 设置动画效果
//        this.setAnimationStyle(R.style.popwin_slide_style);
        //设置如下四条信息，当点击其他区域使其隐藏，要在show之前配置
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new BitmapDrawable());
        // 显示窗口
        this.showAsDropDown(layout);// 设置layout在PopupWindow中显示的位置
        // 设置动画效果
//        popHead.setAnimationStyle(R.style.AnimBottom);
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        mConvertView.setFocusable(true);
//        mConvertView.setFocusableInTouchMode(true);
//        mConvertView.setOnKeyListener(new View.OnKeyListener(){
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK){
//                    if (this != null) {
//                        LayoutPopWindow.this.dismiss();
//                    }
//                }
//                return false;
//            }
//        });


    }
    //关闭窗口
    public void dismissPopupWindow(){
        LayoutPopWindow.this.dismiss();
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     * @param viewId
     * @param text
     * @return
     */
    public LayoutPopWindow setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }
    /**
     * 为ImageView设置图片
     */
    public LayoutPopWindow setImageRes(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * View的事件监听
     * */
    public void setOnClickListener(int viewId,View.OnClickListener li){
        getView(viewId).setOnClickListener(li);
    }
    /**
     * ListView设置数据
     * */
    public LayoutPopWindow setListAdapter(int viewId, BaseAdapter adapter){
        ListView listView=getView(viewId);
        listView.setAdapter(adapter);
        return this;
    }
    /**
     * ListView的事件监听
     * */
    public void setItemListener(int viewId, AdapterView.OnItemClickListener li){
        ListView listView=getView(viewId);
        listView.setOnItemClickListener(li);
    }
    //判断是否关闭窗口
    private void isClosePop(){
        if(this.isShowing()){
            this.dismiss();
        }
    }
}
