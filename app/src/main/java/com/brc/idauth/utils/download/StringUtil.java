package com.brc.idauth.utils.download;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;
import com.brc.idauth.base.BaseApplication;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String CpStrPara(int strID) {
        return BaseApplication.getInstance().getString(strID);
    }

    public static String CpStrStrPara(int strID, String param) {
        String mStrStr = BaseApplication.getInstance().getString(strID);
        return String.format(mStrStr, param);
    }

    public static String CpStrIntPara(int strID, int intParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, intParam);
    }

    public static String CpStrIntPara(String str, int intParam) {
        return String.format(str, intParam);
    }

    public static String CpStrFloatPara(int strID, float floatParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, floatParam);
    }

    public static String CpStrStrIntPara(int strID, String strParam,
                                         int intParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam, intParam);
    }

    public static String CpStrStrInt2Para(int strID, String strParam,
                                          int intParam, int intParam2) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam, intParam, intParam2);
    }

    public static String CpStrStrFloatPara(int strID, String strParam,
                                           float floatParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam, floatParam);
    }

    public static String CpStrIntStrPara(int strID, int intParam,
                                         String strParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, intParam, strParam);
    }

    public static String CpStrStrIntPara(int strID, int intParam,
                                         String strParam) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam, intParam);
    }

    public static String CpStrStr2Para(int strID, String strParam1,
                                       String strParam2) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam1, strParam2);
    }

    public static String CpStrStr3Para(int strID, String strParam1,
                                       String strParam2, String strParam3) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam1, strParam2, strParam3);
    }

    public static String CpStrStr4Para(int strID, String strParam1,
                                       String strParam2, String strParam3, String strParam4) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam1, strParam2, strParam3, strParam4);
    }

    public static String CpStrInt2Para(int strID, int intParam1, int intParam2) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, intParam1, intParam2);
    }

    public static String CpStrInt3Para(int strID, int intParam1,
                                       int intParam2, int intParam3) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, intParam1, intParam2, intParam3);
    }

    public static String CpStrInt4Para(int strID, int intParam1,
                                       int intParam2, int intParam3, int intParam4) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, intParam1, intParam2, intParam3,intParam4);
    }

    public static String CpStrStr2Int2Para(int strID, String strParam1,
                                           String strParam2, int intParam1, int intParam2) {
        String mIntStr = BaseApplication.getInstance().getString(strID);
        return String.format(mIntStr, strParam1, strParam2, intParam1,
                intParam2);
    }

    public static Spanned CpStrHtmlColor(String mStr) {
        return Html.fromHtml(mStr);
    }

    public static Spanned CpStrHtmlColor(int strId) {
        return Html.fromHtml(BaseApplication.getInstance().getResources().getString(strId));
    }

    public static String CpStrGet(int strID) {
        return BaseApplication.getInstance().getString(strID);
    }

    public static String CpStrPrice(int price) {
        return price + ".00";
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static boolean isMobileNum(String mobiles) {
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        }

        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(170))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    //
    public static boolean isEmailAddr(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static String getEditTextString(EditText view) {
        return view.getEditableText().toString().trim();
    }

    public static int string2int(String value) {
        return Integer.parseInt(value);
    }

    public static long string2long(String value) {
        return Long.parseLong(value);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

//    public static String getQiNiuUploadUrl(String bucket) {
////        String url = "http://%1$s.qiniudn.com/";
////        return String.format(url, bucket);
//
////        return "http://odfjmoc6u.bkt.clouddn.com/";
//        return "http://pic.lebaoedu.com";
//    }

    public static String encodeStr(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }
}
