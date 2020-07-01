package com.yj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreenshotUtils {


    private static int counter = 0;
    private static final String TAG = "StringUtil";

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyMoney(String str) {
        try {
            if (null == str || str.equals("") || str.equals("0")
                    || str.equals("0.00") || 0 == Double.parseDouble(str)) {
                return true;
            }

            if (Double.parseDouble(str) == 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // String five_before = hitlistsBean.getHit_five().substring(0, hitlistsBean.getHit_five().indexOf(":"));
    //String five_after = hitlistsBean.getHit_five().substring(hitlistsBean.getHit_five().indexOf(":") + 1, hitlistsBean.getHit_five().length());

    /**
     * 截取字符串的前半截
     *
     * @param str
     * @return
     */
    public static String subStringBefore(String str) {
        String beforeStr = str.substring(0, str.indexOf(":"));
        return beforeStr;
    }

    /**
     * 截取字符串的后半截
     *
     * @param str
     * @return
     */
    public static String subStringAfter(String str) {
        String afterStr = str.substring(str.indexOf(":") + 1, str.length());
        return afterStr;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_one(String str) {
        String StrArray[] = str.split(":");

        return StrArray;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_two(String str) {
        String[] StrArray = str.split(",");
        return StrArray;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_three(String str) {
        String[] StrArray = subStringArray_two(str);
        //for(){}
        return StrArray;
    }

    /**
     * 判断str1中包含str2的个数
     *
     * @param str1
     * @param str2
     * @return counter
     */
    public static int countStr(char str1, String str2) {

        int countss = 0;
        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) == str1) {
                countss++;
            }
        }

        return countss;
    }

    /**
     * 截取()中的字符串
     *
     * @param str2
     * @return counter
     */
    public static String countStrParentheses(String str2) {
        // TODO SGF ADD
        String str1 = "";
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher matcher = pattern.matcher(str2);
        while (matcher.find()) {
            str1 = matcher.group();
        }

        return str1;
    }

    /**
     * 1:0:1:0:1
     *
     * @param
     * @return
     */

    public static String[] getCount(String a, String b) {
        String[] arrayStr = {};
        arrayStr = a.split(b);
        return arrayStr;
    }

    public static boolean isZero(String m1) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal("0");
        return b1.compareTo(b2) == 0;
    }

    // public static boolean checkPhone(String phone) {
    // if (null == phone || !phone.matches("^1[3-8]+\\d{9}")) {
    // return false;
    // }
    // return true;
    // }
    //
    // public static boolean checkPassword(String password) {
    // if (null == password || !password.matches("[a-f A-F 0-9]{6-15}")) {
    // return false;
    // }
    // return true;
    // }

    /**
     * 将分转化为元
     *
     * @param str
     * @return fen->yuan 112000(分)-> 1120.00(元)
     */
    public static String formatMoney_FenToYuan(String str) {

        try {
//            GlobalLog.d(TAG, "formatMoney_FenToYuan : " + str);
            if (!isEmpty(str)) {
                if (str.equals("0")) {
                    return "0";
                } else if (str.endsWith("00")) {
//               GlobalLog.d(TAG, str.substring(0, str.length() - 2));
                    return new BigDecimal(str.substring(0, str.length() - 2))
                            .toString();
                } else {
                    return BigDecimal.valueOf(new BigDecimal(str).longValue(),
                            2).toString();
                }
            }

        } catch (Exception e) {
//            GlobalException.proxy.handle(e, null);
        }

        return "0.00";
    }

    /**
     * @param str
     * @return 5->5.00
     */
    public static String formatMoney_Yuan(String str) {

        if (!isEmpty(str)) {
            return str + ".00";
        }

        return "0.00";
    }

    /**
     * @param str
     * @return 5->500
     */
    public static String formatMoney_Fen(String str) {

        if (!isEmpty(str)) {

            double ret = Double.valueOf(str) * 100;
            return String.valueOf((int) ret);
//            return str + "00";
        }

        return "0";
    }

    /**
     * @param str
     * @return 5->500
     */
    public static String format_FenToYuan(String str) {
        //TODO SGF  ADD

        if (!isEmpty(str)) {

            double ret = Double.valueOf(str) / 100;
            return String.valueOf((int) ret);
//            return str + "00";
        }

        return "0";
    }

    /**
     * @param m1
     * @param m2
     * @return true m1 > m2
     */
    public static boolean greaterThanMoney(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1;
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static boolean greaterEqualMoney(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean greaterThanZero(String m1) {
        return greaterThan(m1, "0");
    }

    public static boolean greaterEqualZero(String m1) {
        return greaterEqual(m1, "0");
    }

    public static boolean greaterEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }

        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean greaterThan(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }

        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean lessEqualZero(String m1) {
        return lessEqual(m1, "0");
    }

    public static boolean lessThanZero(String m1) {
        return lessThan(m1, "0");
    }

    public static boolean lessEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == -1 || b1.compareTo(b2) == 0;
    }

    /**
     * 比较大小
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean lessThan(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == -1;
    }

    /**
     * @param m1
     * @param m2
     * @return true m1 >= m2
     */
    public static boolean compareMoneyEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean isMoneyZero(String m1) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal("0");
        return b1.compareTo(b2) == 0;
    }

    /**
     * 加法运算
     *
     * @param m1 "1.7"
     * @param m2 "7.85"
     * @return "1.7" + "7.85" => 9.55
     */
    public static String add(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.add(b2).toString();
    }

    public static String add(String m1, String m2, String m3) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        if (isEmpty(m3)) {
            m3 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        BigDecimal b3 = new BigDecimal(m3);
        return b1.add(b2).add(b3).toString();
    }

    /**
     * 减法运算
     *
     * @param m1 2.3
     * @param m2 1.1
     * @return 1.2
     */
    public static String subtract(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.subtract(b2).toString();
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static String divide(String m1, String m2) {
//        GlobalLog.d(TAG, "m1 : " + m1 + " m2 : " + m2);
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "1";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.divide(b2, 2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 乘法运算
     *
     * @param m1 参数1
     * @param m2 参数2
     * @return 两个数相乘
     */
    public static String multiply(String m1, String m2) {
//        GlobalLog.d(TAG, "m1 : " + m1 + " ; m2 : " + m2);
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.multiply(b2).toString();
    }

    public static String getProgress(int progress) {
        return (progress / 20) + "";
    }

    public static String getLimit(String total_unit) {
        double l = Double.valueOf(total_unit);
        return (int) Math.max(Math.round(l * 0.05), 1) + "";
    }

    private static BigDecimal one_hundred_million = new BigDecimal("100000000");
    private static BigDecimal ten_thousand = new BigDecimal("10000");
    private static BigDecimal hundred_thousand = new BigDecimal("100000");
    private static BigDecimal one_million = new BigDecimal("1000000");
    private static BigDecimal ten_million = new BigDecimal("10000000");

    public static String formatMoney_Chinese(String m) {

        BigDecimal b1 = new BigDecimal(m);
        BigDecimal b2 = b1.divide(one_hundred_million);

        if (b2.doubleValue() >= 10) {
            return b2.toString() + "亿";
        } else if (b2.doubleValue() >= 1) {
            return b1.divide(one_hundred_million).setScale(2,
                    RoundingMode.HALF_DOWN)
                    + "亿";
        } else {
            b2 = b1.divide(ten_million);
            if (b2.intValue() > 0) {
                System.out.println(b2);
                return b2.multiply(new BigDecimal(1000))
                        .setScale(1, RoundingMode.HALF_DOWN).toString()
                        + "万";

            } else {
                b2 = b1.divide(one_million);
                if (b2.intValue() > 0) {

                    return b2.multiply(new BigDecimal(100))
                            .setScale(0, RoundingMode.HALF_DOWN).toString()
                            + "万";
                } else {
                    b2 = b1.divide(hundred_thousand);
                    if (b2.intValue() > 0) {
                        return b2.multiply(new BigDecimal(10))
                                .setScale(0, RoundingMode.HALF_DOWN).toString()
                                + "万";
                    } else {
                        b2 = b1.divide(ten_thousand);
                        if (b2.intValue() > 0) {
                            return b2.setScale(2, RoundingMode.HALF_DOWN) + "万";
                        }
                        return m + "元";
                    }
                }
            }
        }

    }

    /**
     * 是否显示出票明细
     *
     * @param issueState
     * @return
     */
    public static boolean isCanShowTicket(String issueState) {

        try {

            if (issueState.startsWith("QC@")) {
                String str = issueState.substring(3);
                if (Integer.parseInt(str) > 1) {
                    return true;
                }
//                GlobalLog.d(TAG, str);
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean verifyPassword(String password) {

        if (isEmpty(password)) {
            return false;
        } else if (password.matches("^[0-9a-zA-Z]{6,15}$")) {
            return true;
        }

        return false;
    }

    /**
     * 手机号码验证
     */
    public static boolean verifyPhoneNumber(String phone) {
        if (isEmpty(phone)) {
            return false;
        } else if (!phone.startsWith("1")) {
            return false;
        } else if (phone.length() == 11) {
            return true;
        }
//        if (phone
//                .matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(170))\\d{8}$")) {
//            return true;
//        }

        return false;
    }

    // /** 数字验证 */
    // public static boolean verifyNumeric(String paramString) {
    // return Pattern.compile("[0-9]*").matcher(paramString).matches();
    // }

    /**
     * 用户名验证 "^[^0-9]\\w{3,16}$"
     */
    public static boolean verifyUsername(String username) {
        if (isEmpty(username)) {
            return false;
        } else if (username.matches("[\u4E00-\u9FA5a-zA-Z]{1}[\u4E00-\u9FA5a-zA-Z0-9]{2,15}")) {
            return true;
        }
        return false;
    }

    // /** 密码验证 */
    // public static boolean verifyPassword(String paramString) {
    // return Pattern.compile("^[A-Za-z0-9]{6,16}$")
    // .matcher(paramString)
    // .matches();
    // }

    public static boolean verifyEmail(String paramString) {
        return Pattern
                .compile("^\\s*([A-Za-z0-9_-]+(\\.\\w+)*@(\\w+\\.)+\\w+)\\s*$")
                .matcher(paramString).matches();
    }

    public static boolean verifyIDCard(String validateStr) {
        String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(validateStr).matches()) {
            return false;
        }
        return true;
    }


    /**
     * 校验密码有 6到18位 字母和数字 组成 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifyPasswordLen(String passStr) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";
        return passStr.matches(regex);
    }
    /**
     * 校验输入框小数点后面只能有2位数字 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifTwo(String passStr) {
        String regex = "^\\d{1,8}(\\.\\d{1,2})?$";
        return passStr.matches(regex);
    }

    /**
     * 校验输入框只能输入数字 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifNum(String passStr) {
        String regex = "^[0-9]*[1-9][0-9]*$";
        return passStr.matches(regex);
    }

    public static String getLast_2(String issue) {
        if (ScreenshotUtils.isEmpty(issue)) {
            return "";
        } else {
            return issue.substring(issue.length() - 2);
        }
    }

    public static String getLast_3(String issue) {
        if (ScreenshotUtils.isEmpty(issue)) {
            return "";
        } else {
            return issue.substring(issue.length() - 3);
        }
    }

    public static Map<String, String> parseData(String data) {

        Map<String, String> retMap = new HashMap<String, String>();

        String[] items = data.split("#");
        String[] item = null;
        for (String i : items) {
            item = i.split("=");
            if (item.length >= 2) {
                retMap.put(item[0], item[1]);
            }
        }

//        for (String key : retMap.keySet()) {
//            System.out.println(key + " : " + retMap.get(key));
//        }

        return retMap;
    }

    public static String setStrRed(String str) {
        return "<font color='red'>" + str + "</font>";
    }
    //白色
    public static String setStrWhite(String str) {
        return "<font color='#ffffff'>" + str + "</font>";
    }

    public static String setStrMainRed(String str) {
        return "<font color='#f33b3b'>" + str + "</font>";
    }
    //灰色
    public static String setStrGray(String str) {
        return "<font color='#888888'>" + str + "</font>";
    }

    public static String setStrOrenge(String str) {
        return "<font color='#ff9e14'>" + str + "</font>";
    }
    //竞彩篮球-绿色
    public static String setStrGreen(String str) {
        return "<font color='#4bc160'>" + str + "</font>";
    }

    public static String setStrBlack(String str) {
        return "<font color='#313131'>" + str + "</font>";
    }

    public static String setStrOrange(String str) {
        return "<font color='#FF3D3D'>" + str + "</font>";
    }

    public static String setStrRedBold(String str) {
        return "<b><font color='red'>" + str + "</font></>";
    }

    public static String setStrBold(String str) {
        return "<b>" + str + "</b>";
    }

    public static List<String> getNumList(int start, int end) {

        List<String> list = new ArrayList<String>();
        for (int i = start; i <= end; i++) {
            list.add(i + "");
        }
        return list;
    }

    public static String setWinCodeRed(String drawCode, String code) {

        StringBuffer retBuf = new StringBuffer("");

        String[] drawCodes = drawCode.split(",");

        List<String> codes = new ArrayList<String>();

        for (String c : code.split(",")) {
            codes.add(c);
        }

        for (String d : drawCodes) {

            if (codes.contains(d)) {
                retBuf.append(setStrRed(d));
                retBuf.append(" ");
            } else {
                retBuf.append(d);
                retBuf.append(" ");
            }
        }

        return retBuf.toString();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String getString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.trim();
    }


    /**
     * 判断字符串是否非空非null
     *
     * @param strParm 需要判断的字符串
     * @return 真假
     */
    public static boolean isNoBlankAndNoNull(String strParm) {
        return !((strParm == null) || (strParm.equals("")));
    }


    /**
     * 指定目录写入文件内容
     *
     * @param filePath 文件路径+文件名
     * @param
     * @throws IOException
     */
    public static void saveAsJPEG(Bitmap bitmap, String filePath)
            throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 是否有内存
     *
     * @return
     */
    public static boolean isMountedSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    public static Bitmap activityShot(Activity activity) {
        /*获取windows中最顶层的view*/
        View view = activity.getWindow().getDecorView();
        //允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        WindowManager windowManager = activity.getWindowManager();
        //获取屏幕宽和高
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        //去掉状态栏
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        //销毁缓存信息
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /**
     * 截取scrollview的屏幕
     * @param scrollView
     * @return
     */
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取listView实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
//            scrollView.getChildAt(i).setBackgroundResource(R.drawable.white_drawable);
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/sdcard/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
    /**
     *  截图listview
     * **/
    public static Bitmap getbBitmap(ListView listView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取listView实际高度
        for (int i = 0; i < listView.getChildCount(); i++) {
            h += listView.getChildAt(i).getHeight();
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        listView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/sdcard/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
    /**
     * 截取RelativeLayout
     **/
    public static Bitmap getRelativeLayoutBitmap(RelativeLayout relativeLayout) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            h += relativeLayout.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        return bitmap;
    }
    /**
     * 截取LinearLayout
     **/
    public static Bitmap getLinearLayoutBitmap(LinearLayout linearLayout) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            h += linearLayout.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(linearLayout.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);
        return bitmap;
    }
    /**
     * 截取除了导航栏之外的整个屏幕
     */
    public static Bitmap screenShotWholeScreen(Activity activity) {
        View dView = activity.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        return bitmap;
    }
    /**
     * 获取cache路径
     *
     * @param context
     * @return
     */
    public static String getDiskCachePath(Context context,String imageFile) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath()+imageFile;
        } else {
            return context.getCacheDir().getPath()+imageFile;
        }
    }


    /**
     * 调用系统截图功能获取屏幕截图
     *
     * @return
     */
    public static Bitmap getScreenShot(Activity activity,View view) {
        view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
     }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片路径
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "haokuaijie");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //当前时间来命名图片
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        ToastUitl.show("图片保存到本地成功");
    }
}
