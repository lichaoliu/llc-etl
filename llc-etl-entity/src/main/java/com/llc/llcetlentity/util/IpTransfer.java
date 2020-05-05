package com.llc.llcetlentity.util;

import com.google.common.net.InetAddresses;
import com.llc.llcetlentity.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: liulichao
 * 日期 2020/3/7 17:09
 * @description: ipv4 ipv6 范围查询
 */
public class IpTransfer {
    /**
     * 255.255.255.255
     */
    public static final BigInteger MAX_IPV4_NUMBER = BigInteger.valueOf(4294967295L);

    private static final long PART1 = 0xff000000;
    private static final long PART2 = 0xff0000;
    private static final long PART3 = 0xff00;
    private static final long PART4 = 0xff;

    private static final String REGEX_POINT = "\\.";
    private static final String COVER_STR = "000000000000000000000000000000000000000";
    private static final Integer FFFF = Integer.parseInt("ffff", 16);

    private static final String STR_999 = "999";
    private static final String STR_555 = "555";
    private static final String STR_FFFF = "ffff";

    /**
     * 39位十进制转ip
     *
     * @param ip39Decimal
     * @return
     */
    public static String decimalToIp(String ip39Decimal) {
        if (StringUtils.isEmpty(ip39Decimal)) {
            return null;
        }
        BigInteger b = new BigInteger(ip39Decimal);
        if (b.compareTo(MAX_IPV4_NUMBER) > 0) {
            return numberToIpv6(b.subtract(MAX_IPV4_NUMBER));
        } else {
            return numbertoipv4(b.longValue());
        }
    }

    /**
     * ipv4 ipv6获取39位十进制数
     *
     * @param ip
     * @return ip转换十进制数
     */
    public static String ipTo39Decimal(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return null;
        }
        if (isIpv4LiteralAddress(ip)) {
            return ipv4To39Decimal(ip);
        }
        if (isIpv6LiteralAddress(ip)) {
            return ipv6To39Decimal(ip);
        }
        return null;
    }

    /**
     * 获取半截ip 39位十进制数范围
     *
     * @param halfIp
     * @return 半截ip 39位十进制数范围
     */
    public static List<Range> getIpRange39Decimal(String halfIp) {
        List<Range> rangeList = new ArrayList<>();
        if (!isHalfIp(halfIp)) {
            return rangeList;
        }
        boolean isIpv4 = halfIp.contains(CommonConstant.DOT);
        boolean isIpv6 = halfIp.contains(CommonConstant.COLON);
        if (isIpv4) {
            rangeList.add(getIpv4Range39Decimal(halfIp));
        }
        if (isIpv6) {
            rangeList.add(getIpv6Range39Decimal(halfIp));
        }
        //不包含 . 和 :
        if (!isIpv4 && !isIpv6) {
            boolean isHex = isHex(halfIp);
            boolean isNumeric = isNumeric(halfIp);
            if (isNumeric) {
                Integer halfIpInteger = Integer.parseInt(halfIp);
                //0-255
                if (halfIpInteger >= CommonConstant.NUM_0 && halfIpInteger < CommonConstant.NUM_256) {
                    rangeList.add(getIpv4Range39Decimal(halfIp));
                    rangeList.add(getIpv6Range39Decimal(halfIp));
                }
                //256-999
                if (halfIpInteger > CommonConstant.NUM_255 && halfIpInteger < CommonConstant.NUM_1000) {
                    rangeList.add(getIpv6Range39Decimal(halfIp));
                }
            }
            //字母且为十六进制
            if (isHex && !isNumeric) {
                Integer halfIpInteger = Integer.parseInt(halfIp, CommonConstant.NUM_16);
                if (halfIpInteger >= CommonConstant.NUM_0 && halfIpInteger <= FFFF) {
                    rangeList.add(getIpv6Range39Decimal(halfIp));
                }
            }
        }
        //既包含 . 也包含 :
        return rangeList;
    }

    /**
     * 获取半截ip 39位十进制数范围
     *
     * @param halfIp
     * @return ip 39位十进制数范围包装类
     */
    public static DecimalRage getDecimalRage(String halfIp) {
        List<Range> rangeList = getIpRange39Decimal(halfIp);
        DecimalRage decimalRage = new DecimalRage();
        if (CollectionUtils.isEmpty(rangeList)) {
            decimalRage.setRangeIpv4(false);
            decimalRage.setRangeIpv6(false);
        } else {
            if (rangeList.size() == 1) {
                Range range = rangeList.get(0);
                decimalRage.setRangeIpv4(true);
                decimalRage.setRangeIpv6(false);
                decimalRage.setStartIpv4(range.getStart());
                decimalRage.setEndIpv4(range.getEnd());
            } else {
                Range ipv4Range = rangeList.get(0);
                Range ipv6Range = rangeList.get(1);
                decimalRage.setRangeIpv4(true);
                decimalRage.setRangeIpv6(true);
                decimalRage.setStartIpv4(ipv4Range.getStart());
                decimalRage.setEndIpv4(ipv4Range.getEnd());
                decimalRage.setStartIpv6(ipv6Range.getStart());
                decimalRage.setEndIpv6(ipv6Range.getEnd());
            }
        }
        return decimalRage;
    }

    /**
     * 获取ipv4 39位十进制范围
     *
     * @param halfIp
     * @return ipv4 39位十进制范围
     */
    private static Range getIpv4Range39Decimal(String halfIp) {
        return new Range(ipv4To39Decimal(getLowIpv4Addr(halfIp)), ipv4To39Decimal(getHighIpv4Addr(halfIp)));
    }

    /**
     * 获取ipv6 39位十进制范围
     *
     * @param halfIp
     * @return ipv6 39位十进制范围
     */
    private static Range getIpv6Range39Decimal(String halfIp) {
        return new Range(ipv6To39Decimal(getLowIpv6Addr(halfIp)), ipv6To39Decimal(getHighIpv6Addr(halfIp)));
    }


    /**
     * 将IP地址长整型数值转化为IPv4字符串
     */
    public static String numbertoipv4(long ip) {
        String ipStr = String.valueOf((ip & PART1) >> CommonConstant.NUM_24);
        ipStr += CommonConstant.DOT + ((ip & PART2) >> CommonConstant.NUM_16);
        ipStr += CommonConstant.DOT + ((ip & PART3) >> CommonConstant.NUM_8);
        ipStr += CommonConstant.DOT + (ip & PART4);
        return ipStr;
    }

    /**
     * 将IPv4字符串转化为对应的长整型整数
     */
    public static BigInteger ipv4ToLong(String ipv4) {
        if (isIpv4LiteralAddress(ipv4)) {
            byte[] bytes = InetAddresses.forString(ipv4).getAddress();
            return new BigInteger(CommonConstant.NUM_1, bytes);
        } else {
            return null;
        }
    }

    /**
     * 根据str拼接起止ipv4起始地址
     *
     * @param halfIp ipv4 left
     * @param endStr 结尾字符串
     * @return
     */
    private static String getFullIpv4Addr(String halfIp, String endStr) {
        if (StringUtils.isEmpty(halfIp) || StringUtils.isEmpty(endStr)) {
            return null;
        }
        if (halfIp.startsWith(CommonConstant.DOT) || halfIp.endsWith(CommonConstant.DOT.concat(CommonConstant.DOT))) {
            return null;
        }
        if (halfIp.endsWith(CommonConstant.DOT)) {
            halfIp = halfIp.substring(0, halfIp.length() - 1);
        }
        String[] ipArray = halfIp.split(REGEX_POINT);
        if (ipArray.length > CommonConstant.NUM_4) {
            return null;
        }
        for (String str : ipArray) {
            if (!isNumeric(str)) {
                return null;
            }
            Integer num = Integer.parseInt(str);
            if (num < 0 || num > CommonConstant.NUM_255) {
                return null;
            }
        }
        StringBuilder sb = new StringBuilder(halfIp);
        for (int i = 0; i < CommonConstant.NUM_4 - ipArray.length; i++) {
            sb.append(CommonConstant.DOT).append(endStr);
        }
        return sb.toString();
    }

    private static boolean isNumeric(CharSequence cs) {
        if (StringUtils.isEmpty(cs)) {
            return false;
        } else {
            int sz = cs.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 获取ipv4最小地址
     *
     * @param halfIp 左起ipv4地址
     * @return ipv4最小地址
     */
    private static String getLowIpv4Addr(String halfIp) {
        return getFullIpv4Addr(halfIp, "0");
    }

    /**
     * 获取ipv4最大地址
     *
     * @param halfIp 左起ipv4地址
     * @return ipv4最大地址
     */
    private static String getHighIpv4Addr(String halfIp) {
        return getFullIpv4Addr(halfIp, "255");
    }

    private static String getHighHalfIpv4(String halfIp) {
        if (!isHalfIp(halfIp) || halfIp.contains(CommonConstant.COLON)) {
            return null;
        }
        if (halfIp.endsWith(CommonConstant.DOT)) {
            halfIp = halfIp.substring(0, halfIp.length() - 1);
        }
        String tail, head;
        Integer index = halfIp.lastIndexOf(CommonConstant.DOT);
        if (index > 0) {
            tail = halfIp.substring(index + 1);
            head = halfIp.substring(0, index + 1);
        } else {
            head = "";
            tail = halfIp;
        }
        Integer tailNum = Integer.parseInt(tail);
        String tailStr = tailNum.toString();
        if (tailStr.startsWith("1")) {
            tailStr = tailStr.length() < STR_999.length() ? tailStr + STR_999.substring(tailStr.length()) : tailStr;
            return head + tailStr;
        }
        if (tailStr.startsWith("2")) {
            tailStr = tailStr.length() < STR_555.length() ? tailStr + STR_555.substring(tailStr.length()) : tailStr;
            return head + tailStr;
        }
        if (tailStr.length() == CommonConstant.NUM_2) {
            return head + tailStr;
        } else {
            return head + tailStr.concat("9");
        }
    }

    /**
     * 获取 ipv4 39位十进制数
     *
     * @param ipv4
     * @return
     */
    private static String ipv4To39Decimal(String ipv4) {
        if (StringUtils.isEmpty(ipv4)) {
            return null;
        }
        String longStr = ipv4ToLong(ipv4).toString();
        return longStr.length() < CommonConstant.NUM_39 ? COVER_STR.substring(longStr.length()) + longStr : longStr;
    }

    public static BigInteger ipv6ToNumber(String ipv6) {
        if (isIpv6LiteralAddress(ipv6)) {
            byte[] bytes = InetAddresses.forString(ipv6).getAddress();
            return new BigInteger(CommonConstant.NUM_1, bytes);
        } else {
            return null;
        }
    }

    public static BigInteger ipv6ToNumberAddmaxIpv4Number(String addr) {
        return ipv6ToNumber(addr).add(MAX_IPV4_NUMBER);
    }

    public static String numberToIpv6(BigInteger ipNumber) {
        String ipString = "";
        BigInteger a = new BigInteger("FFFF", CommonConstant.NUM_16);
        for (int i = 0; i < CommonConstant.NUM_8; i++) {
            ipString = ipNumber.and(a).toString(CommonConstant.NUM_16) + CommonConstant.COLON + ipString;

            ipNumber = ipNumber.shiftRight(CommonConstant.NUM_16);
        }
        return ipString.substring(0, ipString.length() - 1);
    }

    /**
     * 根据str拼接起止ipv6起始地址
     *
     * @param halfIp ipv6 left
     * @param endStr 结尾字符串
     * @return
     */
    private static String getFullIpv6Addr(String halfIp, String endStr) {
        if (StringUtils.isEmpty(halfIp) || StringUtils.isEmpty(endStr)) {
            return null;
        }
        if (halfIp.startsWith(CommonConstant.COLON) || halfIp.endsWith(CommonConstant.COLON.concat(CommonConstant.COLON))) {
            return null;
        }
        if (halfIp.endsWith(CommonConstant.COLON)) {
            halfIp = halfIp.substring(0, halfIp.length() - 1);
        }
        String[] ipArray = halfIp.split(CommonConstant.COLON);
        if (ipArray.length > CommonConstant.NUM_8) {
            return null;
        }
        for (String str : ipArray) {
            if (!isHex(str)) {
                return null;
            }
            Integer num = Integer.parseInt(str, CommonConstant.NUM_16);
            if (num < 0 || num > FFFF) {
                return null;
            }
        }
        StringBuilder sb = new StringBuilder(halfIp);
        for (int i = 0; i < CommonConstant.NUM_8 - ipArray.length; i++) {
            sb.append(CommonConstant.COLON).append(endStr);
        }
        return sb.toString();
    }

    /**
     * 获取ipv6最小地址
     *
     * @param halfIp 左起ipv6地址
     * @return ipv6最小地址
     */
    private static String getLowIpv6Addr(String halfIp) {
        return getFullIpv6Addr(halfIp, "0");
    }

    /**
     * 获取ipv6最大地址
     *
     * @param halfIp 左起ipv6地址
     * @return ipv6最大地址
     */
    private static String getHighIpv6Addr(String halfIp) {
        return getFullIpv6Addr(halfIp, "ffff");
    }

    private static String getHighHalfIpv6(String halfIp) {
        if (!isHalfIp(halfIp) || halfIp.contains(CommonConstant.DOT)) {
            return null;
        }
        if (halfIp.endsWith(CommonConstant.COLON)) {
            halfIp = halfIp.substring(0, halfIp.length() - 1);
        }
        String tail, head;
        Integer index = halfIp.lastIndexOf(CommonConstant.COLON);
        if (index > 0) {
            tail = halfIp.substring(index + 1);
            head = halfIp.substring(0, index + 1);
        } else {
            head = "";
            tail = halfIp;
        }

        tail = tail.length() < STR_FFFF.length() ? tail + STR_FFFF.substring(tail.length()) : tail;
        return head + tail;
    }

    /**
     * 判断字符串是否符合十六进制
     *
     * @param str
     * @return
     */
    private static boolean isHex(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        final int one = 47;
        final int nine = 58;
        final int smallA = 64;
        final int smallF = 71;
        final int bigA = 96;
        final int bigF = 103;

        for (int i = 0; i < str.length(); i++) {
            int asciiNum = (int) str.charAt(i);
            //判断ascii 值是否在 0-9 a-f A-F
            boolean notInRang = !((asciiNum > one && asciiNum < nine) || (asciiNum > smallA && asciiNum < smallF) || (asciiNum > bigA && asciiNum < bigF));
            if (notInRang) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取 ipv6 39位十进制数
     *
     * @param ipv6
     * @return
     */
    private static String ipv6To39Decimal(String ipv6) {
        if (StringUtils.isEmpty(ipv6)) {
            return null;
        }
        String ipv6Decimal = ipv6ToNumberAddmaxIpv4Number(ipv6).toString();
        return ipv6Decimal.length() < CommonConstant.NUM_39 ? COVER_STR.substring(ipv6Decimal.length()) + ipv6Decimal : ipv6Decimal;
    }

    /**
     * 是否是ipv4
     *
     * @param ip
     * @return 是 true 不是 false
     */
    public static boolean isIpv4LiteralAddress(String ip) {
        if (InetAddresses.isInetAddress(ip)) {
            BigInteger b = new BigInteger(CommonConstant.NUM_1, InetAddresses.forString(ip).getAddress());
            return b.compareTo(MAX_IPV4_NUMBER) > 0 ? false : true;
        } else {
            return false;
        }
    }

    /**
     * 是否是ipv6
     *
     * @param ip
     * @return 是 true 不是 false
     */
    public static boolean isIpv6LiteralAddress(String ip) {
        if (InetAddresses.isInetAddress(ip)) {
            BigInteger b = new BigInteger(CommonConstant.NUM_1, InetAddresses.forString(ip).getAddress());
            return b.compareTo(MAX_IPV4_NUMBER) > 0 ? true : false;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否符合半截ip地址  ipv4 或 ipv6
     *
     * @param halfIp
     * @return true false
     */
    public static boolean isHalfIp(String halfIp) {
        boolean flag = true;
        if (StringUtils.isEmpty(halfIp)) {
            return false;
        }
        boolean isIpv4 = halfIp.contains(CommonConstant.DOT);
        boolean isIpv6 = halfIp.contains(CommonConstant.COLON);
        if (halfIp.contains("..") || halfIp.contains("::")) {
            flag = false;
        }
        if (halfIp.startsWith(CommonConstant.DOT) || halfIp.startsWith(CommonConstant.COLON)) {
            flag = false;
        }
        if (isIpv4 && isIpv6) {
            flag = false;
        }
        if (isIpv4) {
            String[] ipArray = halfIp.split(REGEX_POINT);
            if (ipArray.length > CommonConstant.NUM_4) {
                flag = false;
            } else {
                for (String str : ipArray) {
                    if (!isNumeric(str)) {
                        flag = false;
                    } else {
                        Integer num = Integer.parseInt(str);
                        if (num < 0 || num > CommonConstant.NUM_255) {
                            flag = false;
                        }
                    }
                }
            }
        }
        if (isIpv6) {
            String[] ipArray = halfIp.split(CommonConstant.COLON);
            if (ipArray.length > CommonConstant.NUM_8) {
                flag = false;
            } else {
                for (String str : ipArray) {
                    if (!isHex(str)) {
                        flag = false;
                    } else {
                        Integer num = Integer.parseInt(str, CommonConstant.NUM_16);
                        if (num < 0 || num > FFFF) {
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * ip范围
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Range {
        private String start;
        private String end;
    }

    /**
     * ip范围
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecimalRage {
        /**
         * 是否包含ipv4范围
         */
        private boolean rangeIpv4;
        /**
         * 是否包含ipv6范围
         */
        private boolean rangeIpv6;

        private String startIpv4;
        private String endIpv4;
        private String startIpv6;
        private String endIpv6;
    }
}
