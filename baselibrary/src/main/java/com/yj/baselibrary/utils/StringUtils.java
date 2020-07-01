package com.yj.baselibrary.utils;


import androidx.annotation.NonNull;

import com.blankj.utilcode.util.RegexUtils;

import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;

public class StringUtils {

    /**
     * 2019年12月27日,更新支持162/165/167/172/190/191/192/193/196/197
     * 手机号码正则表达式,根据工信部发文整理，截止2019年底29批</BR>
     * 以下规则参考互联网<BR>
     * <B>传统运营商</B><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;电信: 133、149、153、173、177、180、181、189、199、191、190、162、193<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;联通: 130、131、132、145、155、156、166、175、176、185、186、167、196<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;移动: 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198、197、165,172<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;广电: 192<BR>
     *
     * <B>虚拟运营商</B><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;电信: 1700、1701、1702<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;移动: 1703、1705、1706<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;联通: 1704、1707、1708、1709、171<BR>
     *
     * <B>上网卡号段</B><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;联通: 145<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;移动: 147<BR>
     *
     * <B>卫星通讯:</B> 1349<BR>
     *
     */
    public static final String MOBILE_NO_REGEX = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[2|5|6|7])|(17[0,1,2,3,5,6,7,8])|(18[0-9])|(19[0|1|2|3|6|7|8|9]))\\d{8}$";
    /**
     * 是否是手机号
     * @param phone 待校验的手机号
     * @return
     */
    public static boolean isMobileNumber(@NonNull String phone){
        return RegexUtils.isMatch(MOBILE_NO_REGEX, phone);
    }

    /**
     * html代码
     * @param bodyHTML
     * @return
     */
    public static String getHtmlData(String bodyHTML){
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static String getDataHtml(String lognUrl){
        String data= "<html><head><style> .newIndexDetail{margin:.58rem 3%;overflow:auto;} img{height: auto; width: auto/9;width:100%;}"
                + "</style></head><body style='background:#ffffff;'><section class='newIndexDetail'>"
                + lognUrl + "</section></body></html>";
        return data;
    }

    public static String generateHtml(String imgUrl){
       StringBuffer stringBuffer=new StringBuffer("<p style=\"text-align: center;\">");
       stringBuffer.append("<img src=\"");
        stringBuffer.append(imgUrl);
        stringBuffer.append("\"/></p>");
        return  stringBuffer.toString();
    }
    public static String agemnetContent(){
        String content="<br\\>&#12288;&#12288;感谢您信任并使用好快洁！我们依据最新法律法规及监管政策要求，更新了《好快洁用户协议》《好快洁隐私政策》，特此向您推送本提示<br\\>" +
                "&#12288;&#12288;请您务必仔细阅读并透彻理解相关条款内容，再确认充分理解并同意后使用好快捷相关产品及服务。点击同意即代表您已阅读并同意《好快洁用户协议》《好快洁隐私政策》</font>，如果您不同意，将可能影响使用好快洁的产品和服务。<br\\>"+
                "&#12288;&#12288;我们将按法律法规要求，采取相应安全保护措施，尽力保护您的个人信息安全可控。";
        return content;
    }
    public static String servicesContent(){
        String content="<br\\>&#12288;&#12288;开通服务前，请先阅读服务须知，申请开通后平台将视为您已同意服务相关条款；如服务人员或使用者未按照服务须知中的" +
                "规范进行操作由此产生的后果，将根据服务须知中的条款承担相关责任。<br\\>" +
                "&#12288;&#12288;相关服务人员的等级介绍，请在服务等级中查看，或开后在我的服务中点击服务等级图标进行服务登记页面进行查看。";
        return content;
    }

    /**
     * 计算总金额
     * @param price
     * @param number
     * @return
     */
    public static String getMoney(String price,int number){
        Double priceDb=Double.valueOf(price);
        Double allMoney=priceDb*number;
        return doubyMoney(allMoney);
    }

    public static Double getDoubMoney(double db){
        return Double.valueOf(doubyMoney(db));
    }
    private static String doubyMoney(double db){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);//设置舍入模式
        return decimalFormat.format(db);
    }
    private static String stringDbMoney(String db){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);//设置舍入模式
        return decimalFormat.format(Double.valueOf(db));
    }
//    public static String getHidString(){
//        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//    }
    /**
     * 判断图片地址是否为本地地址
     */

    public static boolean isLocalUrl(String mImageUrl){
        if(mImageUrl.contains("file")||mImageUrl.contains("cache")||
            mImageUrl.contains("storage")||mImageUrl.contains("emulated")) {//本地图片
           return true;
        }else{
            return false;
        }
    }

    /**
     * 生成md5
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            //1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            //2 将消息变成byte数组
            byte[] input = message.getBytes();

            //3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            //4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }
    /**
     * 二进制转十六进制
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        //把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if(digital < 0) {
                digital += 256;
            }
            if(digital < 16){
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toLowerCase();
    }

    /**
     * 判断是否全为数字
     */
    public static boolean isInteger(String str) {
        return str.matches("^[-\\+]?[\\d]*$");
    }
    /**
     * 判断是否全为字母
     */
    public static boolean isAM(String str){
        return str.matches("[a-zA-Z]+");
    }
}
