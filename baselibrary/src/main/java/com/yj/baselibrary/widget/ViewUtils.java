package com.yj.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yj.baselibrary.R;
import com.yj.baselibrary.utils.ConvertUtils;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ViewUtils {
    /**
     * 设置组件高度
     *
     * @param view
     * @param hieght
     * @param widtht
     */
    public static void SetViewSize(View view, int hieght, int widtht) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = hieght;
        linearParams.width = widtht;
        view.setLayoutParams(linearParams);
    }

    public static <T> T find(Activity activity, int resID) {
        T view = (T) activity.findViewById(resID);
        return view;
    }

    public static <T> T find(View view, int resID) {
        T v = (T) view.findViewById(resID);
        return v;
    }

    public static <T> T getLayout(Context context, int resID) {
        T layout = (T) LayoutInflater.from(context).inflate(resID, null);
        return layout;
    }

    public static void setOnClickListener(View view, int resID,
                                          View.OnClickListener linstener) {
        if (view != null && resID != 0) {
            View tmpView = view.findViewById(resID);
            tmpView.setOnClickListener(linstener);
        }
    }

    public static void setOnClickListener(Activity activity, int resID,
                                          View.OnClickListener linstener) {
        if (activity != null && resID != 0) {
            View tmpView = activity.findViewById(resID);
            tmpView.setOnClickListener(linstener);
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener linstener) {
        if (view != null) {
            view.setOnClickListener(linstener);
        }
    }

    public static void setVisibility(int visibility, View view) {
        if (view != null) {
            if (view.getVisibility() != visibility) {
                view.setVisibility(visibility);
            }
        }
    }

    public static void setVisibility(int visibility, View view, int res) {
        if (view != null && res != 0) {
            View layout = find(view, res);
            if (layout != null) {
                if (layout.getVisibility() != visibility) {
                    layout.setVisibility(visibility);
                }
            }
        }
    }

    public static void setVisibility(int visibility, Activity activity, int res) {
        if (activity != null && res != 0) {
            View layout = find(activity, res);
            if (layout != null) {
                if (layout.getVisibility() != visibility) {
                    layout.setVisibility(visibility);
                }
            }
        }
    }

    public static void setVisibility(int visibility, View... views) {
        if (views != null) {
            for (int i = 0, len = views.length; i < len; i++) {
                View v = views[i];
                if (v != null && v.getVisibility() != visibility) {
                    v.setVisibility(visibility);
                }
            }
        }
    }

    public static void setVisibility(int[] visibilitys, View... views) {
        if (views != null) {
            for (int i = 0, len = views.length; i < len; i++) {
                View v = views[i];
                if (v != null && v.getVisibility() != visibilitys[i]) {
                    v.setVisibility(visibilitys[i]);
                }
            }
        }
    }

    public static void setTextData(TextView tv, Object data) {
        if (tv != null) {
            String value = String.valueOf(data != null ? data : "");
            if (!tv.getText().equals(value)) {
                tv.setText(String.valueOf(data != null ? data : ""));
            }
        }
    }

    public static void setTextHtmlData(TextView tv, String data) {
        if (tv != null) {
            tv.setText(Html.fromHtml(data != null ? data : ""));
        }
    }

    public static void setTextHtmlData(TextView tv, int res, Object data) {
        if (tv != null) {
            tv.setText(Html.fromHtml(tv.getResources().getString(res,
                    data != null ? data : "")));
        }
    }

    public static void setTextData(TextView tv, int res) {
        if (tv != null && res != 0) {
            setTextData(tv, tv.getResources().getString(res));
        }
    }

    public static void setViewBackGround(View view, int res) {
        if (view != null && res != 0) {
            view.setBackgroundResource(res);
        }
    }

    @SuppressWarnings("deprecation")
    public static void setViewBackGround(View view, Drawable drawable) {
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setIntTextData(TextView tv, int data) {
        setTextData(tv, String.valueOf(data));
    }

    public static void setTextData(TextView tv, int res, Object data) {
        if (tv != null && res != 0) {
            setTextData(tv,
                    tv.getResources().getString(res, data != null ? data : ""));
        }
    }

    public static String getText(Context context, int res, Object obj) {
        return context.getResources().getString(res, obj != null ? obj : "");
    }

    public static ViewGroup createAnimLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    public static void setViewHeightOrWidth(View view, int height, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(width, height);
        } else {
            params.height = height;
            params.width = width;
        }
        view.setLayoutParams(params);
    }

    public static void setImageResource(ImageView iv, int res) {
        try {
            if (iv != null && res != 0) {
                iv.setImageResource(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImageDrawable(ImageView iv, Drawable drawable){
        try {
            if (iv != null && drawable != null){
                iv.setImageDrawable(drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void addListViewFooterView(Context context,
                                             ListView listView, int heightValue){
        View view = new View(context);
        view.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ConvertUtils.dp2px(context,
                heightValue)));
        if (listView != null) {
            listView.addFooterView(view, null, false);
        }
    }

    @SuppressWarnings("deprecation")
    public static void addListViewHeadView(Context context, ListView listView,
                                           int heightValue) {
        View view = new View(context);
        view.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ConvertUtils.dp2px(context,
                heightValue)));
        if (listView != null) {
            listView.addHeaderView(view, null, false);
        }
    }

    public static int getStateHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    public static void setBackgroundColorRes(View view, int resColor) {
        if (view != null) {
            view.setBackgroundColor(view.getResources().getColor(resColor));
        }
    }

    public static void setTVDrawablesLeft(TextView tv, Drawable left) {
        if (tv != null && left != null) {
            left.setBounds(0, 0, left.getMinimumWidth(),
                    left.getMinimumHeight());
            tv.setCompoundDrawables(left, null, null, null);
        }

    }

    public static void setTVDrawablesRight(TextView tv, Drawable right) {
        if (tv != null && right != null) {
            right.setBounds(0, 0, right.getMinimumWidth(),
                    right.getMinimumHeight());
            tv.setCompoundDrawables(null, null, right, null);
        }

    }

    public static void setTVDrawablesTop(TextView tv, Drawable top) {
        if (tv != null && top != null) {
            top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
            tv.setCompoundDrawables(null, top, null, null);
        }
    }

    public static void setTVDrawablesBottom(TextView tv, Drawable bottom) {
        if (tv != null && bottom != null) {
            bottom.setBounds(0, 0, bottom.getMinimumWidth(),
                    bottom.getMinimumHeight());
            tv.setCompoundDrawables(null, null, null, bottom);
        }
    }

    public static LinearLayout AddView(LinearLayout parntview, View view) {
        if (parntview != null && view != null) {

            parntview.removeAllViews();
            parntview.addView(view);
            return parntview;
        } else return null;
    }

    /**
     *
     * @param textView  组件
     * @param flage  1上2下3左4右
     * @param id   图片
     * @param context  上下文
     */
    public static void drawImg(TextView textView, int flage, int id, Context context) {
        Drawable img_on, img_off;
        Resources res = context.getResources();
        img_off = res.getDrawable(id);
        //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
        if (flage == 1) {
            textView.setCompoundDrawables(null, img_off, null, null); //设置左图标
        } else if (flage == 2) {
            textView.setCompoundDrawables(null, null, null, img_off); //设置左图标
        } else if (flage == 3) {
            textView.setCompoundDrawables(img_off, null, null, null); //设置左图标
        } else if (flage == 4) {
            textView.setCompoundDrawables(null, null, img_off, null); //设置左图标
        }
    }

    /**
     * 设置时间选择器上下两条线得颜色
     * @param context
     * @param datePicker
     */
    public static void setDatePickerDividerColor(Context context, DatePicker datePicker){

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.colorAccent)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
