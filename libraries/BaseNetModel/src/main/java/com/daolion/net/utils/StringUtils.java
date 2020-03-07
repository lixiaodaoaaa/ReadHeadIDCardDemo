package com.daolion.net.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
    public static boolean isBlank(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String getUUId() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String changeStr(String str) {
        if (isBlank(str)) {
            return "--";
        }
        return str;
    }
    // 应用默认金额数字都保留到小数点后两位，要截取出来
    // 长字符串的转化中文表示处理，规则为：超过一百万，如有必要标示为小数点后一位，超过一亿，

    /**
     * @param str 示例 101000.00 1000000 10000 11111 1000
     * @return 10.1万
     */
    public static String rawIntStr2IntStr(String str) {
        if (str.contains(".00")) {
            str = str.substring(0, str.indexOf("."));
        }
        if (str.length() > 4 && str.length() < 9) {
            // 超过万不超过亿
            char c = str.charAt(str.length() - 4);
            if (Integer.valueOf(c + "") > 0) {
                str = str.substring(0, str.length() - 4) + "." + c + "万";
            } else {
                str = str.substring(0, str.length() - 4) + "万";
            }
        } else if (str.length() > 8) {
            // 超过亿
            boolean flag = false;
            StringBuffer buffer = new StringBuffer();
            for (int i = 4; i < 9; i++) {
                char c = str.charAt(str.length() - i);
                if (flag) {
                    buffer.insert(0, c);
                } else if (Integer.valueOf(c + "") > 0) {
                    flag = true;
                    buffer.append(c);
                }
            }
            // 开始拼装
            if (buffer.toString().length() != 0) {
                str = str.substring(0, str.length() - 8) + "." + buffer.toString() + "亿";
            } else {
                str = str.substring(0, str.length() - 8) + "亿";
            }
        }
        return str;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][345678]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    public static String getSafeMobileNumber(final String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 11) {
            return new StringBuilder().append(phoneNumber.substring(0, 3)).append("****").append(phoneNumber.substring(7, 11)).toString();
        }
        return phoneNumber;
    }

    public static String getSafeRealName(final String realName) {
        if (realName != null && realName.length() >= 2) {
            return new StringBuilder().append(realName.substring(0, 1)).append("**").toString();
        }
        return realName;
    }

    //private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    /*
      (?=.*\d)		    #   must contains one digit from 0-9
	  (?=.*[a-z])		#   must contains one lowercase characters
	  (?=.*[A-Z])		#   must contains one uppercase characters
	  (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
	              .		#     match anything with previous condition checking
	             6,20}	#        length at least 6 characters and maximum of 20
	)*/

    //(?!.*\\s)         #  must contains no-empty
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z])(?!.*\\s).{6,16})";

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean isValidPassword(final String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEquals(String actual, String expected) {
        if (actual != expected) {
            if (actual == null) {
                if (expected != null) {
                    return false;
                }
            } else if (!actual.equals(expected)) {
                return false;
            }
        }

        return true;
    }

    public static String nullStrToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            char c = str.charAt(0);
            return Character.isLetter(c) && !Character.isUpperCase(c) ? (new StringBuilder(str.length())).append(Character.toUpperCase(c)).append(str.substring(1)).toString() : str;
        }
    }

    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", var2);
            }
        } else {
            return str;
        }
    }

    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                return defultReturn;
            }
        } else {
            return str;
        }
    }

    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        } else {
            String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
            Pattern hrefPattern = Pattern.compile(hrefReg, 2);
            Matcher hrefMatcher = hrefPattern.matcher(href);
            return hrefMatcher.matches() ? hrefMatcher.group(1) : href;
        }
    }

    public static String htmlEscapeCharsToString(String source) {
        return isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        } else {
            char[] source = s.toCharArray();

            for (int i = 0; i < source.length; ++i) {
                if (source[i] == 12288) {
                    source[i] = 32;
                } else if (source[i] >= '！' && source[i] <= '～') {
                    source[i] -= 'ﻠ';
                } else {
                    source[i] = source[i];
                }
            }

            return new String(source);
        }
    }

    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        } else {
            char[] source = s.toCharArray();

            for (int i = 0; i < source.length; ++i) {
                if (source[i] == 32) {
                    source[i] = 12288;
                } else if (source[i] >= 33 && source[i] <= 126) {
                    source[i] += 'ﻠ';
                } else {
                    source[i] = source[i];
                }
            }

            return new String(source);
        }
    }

    public static boolean isNumber(String num) {
        for (int i = 0; i < num.length(); ++i) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static boolean checkFirstCharLower(String content) {
        if (isEmpty(content)) {
            return false;
        } else {
            String check = "[a-z](.)*";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(content);
            return matcher.matches();
        }
    }

    public static boolean checkEmailUserName(String email) {
        if (Build.VERSION.SDK_INT >= 8) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            String check = "^([a-z0-9A-Z-_]+[-|_|\\.]?)+[a-z0-9A-Z_-]@([a-z0-9A-Z_+]+(-[a-z0-9A-Z_+]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            return matcher.matches();
        }
    }

    public static boolean checkStringIsForbid(String string) {
        String check = "(Abuse|contact|help|info|jobs|owner|sales|staff|sales|support|www)?+(@sohu.com)";
        Pattern regex = Pattern.compile(check, 2);
        Matcher matcher = regex.matcher(string);
        return matcher.matches();
    }

    public static boolean checkStringIsContains(String string) {
        String check = "admin|master";
        Pattern regex = Pattern.compile(check, 2);
        Matcher matcher = regex.matcher(string);
        return matcher.find();
    }

    public static boolean checkPassportIsValid(String str) {
        return checkEmailUserName(str) && (!checkStringIsContains(str) && !checkStringIsForbid(str));
    }

    public static boolean checkPasswordIsValid(String string) {
        if (isEmpty(string)) {
            return false;
        } else {
            String check = "[0-9a-zA-Z~!@#$%^&*()\\-+_={}\\[\\];:\'\",.<>?/]*";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(string);
            return matcher.matches();
        }
    }

    public static int stringToInt(String str) {
        if (isEmpty(str)) {
            return 0;
        } else {
            int ret = 0;

            try {
                ret = Integer.parseInt(str);
            } catch (Exception var3) {
            }

            return ret;
        }
    }

    public static long stringToLong(String str) {
        if (isEmpty(str)) {
            return 0L;
        } else {
            long dest = 0L;

            try {
                dest = Long.parseLong(str);
            } catch (Exception var4) {
            }

            return dest;
        }
    }

    public static float stringToFloat(String str) {
        if (isEmpty(str)) {
            return 0.0F;
        } else {
            float dest = 0.0F;

            try {
                dest = Float.parseFloat(str);
            } catch (Exception var3) {
            }

            return dest;
        }
    }

    /*public static int compareVersion(String sourceVersion, String desVersion) {
        String[] source = sourceVersion.split(".");
        String[] des = desVersion.split(".");
        String tempSource = "";

        for (int tempDes = 0; tempDes < source.length; ++tempDes) {
            String i = "";
            if (source[tempDes].length() >= 8) {
                i = source[tempDes].substring(0, 8);
            } else {
                i = source[tempDes];

                for (int str = source[tempDes].length(); str < 8; ++str) {
                    i = "0" + i;
                }
            }

            tempSource = tempSource + i;
        }

        String var9 = "";

        for (int var10 = 0; var10 < des.length; ++var10) {
            String var11 = "";
            if (des[var10].length() >= 8) {
                var11 = des[var10].substring(0, 8);
            } else {
                var11 = des[var10];

                for (int j = des[var10].length(); j < 8; ++j) {
                    var11 = "0" + var11;
                }
            }
            var9 = var9 + var11;
        }
        return tempSource.compareTo(var9);
    }*/

    /**
     * 格式化卡号
     * 手机号3,4,4； 其他卡号 4,4,4...
     *
     * @param originalCardNumber
     * @return
     */
    public static String formatCardNumber(String originalCardNumber) {
        if (TextUtils.isEmpty(originalCardNumber)) {
            return "";
        }
        StringBuilder resultStringBuilder = new StringBuilder();
        originalCardNumber = originalCardNumber.trim();
        if (originalCardNumber.contains(" ")) {
            return originalCardNumber;
        }
        if (isMobileNO(originalCardNumber)) {
            resultStringBuilder
                    .append(originalCardNumber.substring(0, 3)).append(" ")
                    .append(originalCardNumber.substring(3, 7)).append(" ")
                    .append(originalCardNumber.substring(7, 11));
        } else {
            for (int i = 0; i < originalCardNumber.length(); i++) {
                resultStringBuilder.append(originalCardNumber.charAt(i));
                if ((i + 1) % 4 == 0) {
                    resultStringBuilder.append(" ");
                }
            }
        }
        return resultStringBuilder.toString().trim();
    }

    public static Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException{
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key =idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value =idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }


    public static Map<String, List<String>> splitQueryToLowerKey(URL url) throws UnsupportedEncodingException{
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key =idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8").toLowerCase() : pair.toLowerCase();
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value =idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }

}
