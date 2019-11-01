package com.llc.etl.admin.test;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-29 16:40
 */
public class Test {
    public static void main(String[] argus){
        String tokenTmp = DigestUtils.md5DigestAsHex(String.valueOf("aa").getBytes());
        System.out.println(tokenTmp);
        String mm = new BigInteger(1, tokenTmp.getBytes()).toString(16);
        System.out.println(mm);

        System.out.println(Character.MIN_RADIX);
        System.out.println(Character.MAX_RADIX);
    }
}
