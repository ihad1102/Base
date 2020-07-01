package com.yj.baselibrary.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

/**
 *高版本自定义时间选择器
 */
public class MyDatePickerDialog extends DatePickerDialog{
    public MyDatePickerDialog(Context context, int theme,
                              OnDateSetListener callBack, int year, int monthOfYear,
                              int dayOfMonth) {
        super(context, theme, callBack, year, monthOfYear, dayOfMonth);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
        if (mSpinners != null) {
            NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
            NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
            NumberPicker mDaySpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
            mSpinners.removeAllViews();
            //如果要隐藏年，月，日中的某一项取消其addView就好了
            if (mYearSpinner != null) {
                mSpinners.addView(mYearSpinner);
            }
            if (mMonthSpinner!= null) {
                mSpinners.addView(mMonthSpinner);
            }
//            if (mDaySpinner != null) {
//                mSpinners.addView(mDaySpinner);
//            }
        }
    }
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
//        setTitle(year+"年"+(month+1)+"月");
    }
}