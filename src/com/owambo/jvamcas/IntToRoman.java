package com.owambo.jvamcas;

/**
 *Leetcode solution to converting Roman to Integer
 **/

import java.util.*;

public class IntToRoman {

    public String toRoman(int num) {
        int[] val = {1000, 500, 100, 50, 10, 5, 1};
        String[] keys = {"M", "D", "C", "L", "X", "V", "I"};

        int re = num;
        int len = val.length;
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int divisor = val[i];
            int quotient = re / divisor;
            StringBuilder builder = new StringBuilder();

            if (i < len - 1 && !String.valueOf(val[i + 1]).contains("5") && re / val[i + 1] == 9)
                continue;
            if (quotient == 9)
                builder.append(keys[i]).append(keys[i - 2]);

            else if (quotient == 4)
                builder.append(keys[i]).append(keys[i - 1]);

            else {
                for (int j = 0; j < quotient; j++)
                    builder.append(keys[i]);
            }
            roman.append(builder.toString());
            re %= divisor;
        }
        return roman.toString();
    }

    public int fromRoman(String roman) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("D", 500);
        map.put("C", 100);
        map.put("L", 50);
        map.put("X", 10);
        map.put("V", 5);
        map.put("I", 1);
        map.put("XC", 90);
        map.put("IX", 9);
        map.put("CM", 900);
        map.put("IV", 4);
        map.put("XL", 40);
        map.put("CD", 400);

        int val = 0;
        String[] keys = roman.split("");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            if (builder.length() == 0 || builder.substring(0, 1).equals(keys[i]))
                builder.append(keys[i]);
            else {
                String str = builder.toString();
                int str_len = str.length();
                if (str_len > 1) {
                    val += str_len * map.get(str.substring(0, 1));
                    --i;
                } else {
                    str = builder.append(keys[i]).toString();
                    int value = map.getOrDefault(str, 0);
                    val += value;
                    if (value == 0) {
                        val += map.get(builder.substring(0,1));
                        --i;
                    }
                }builder = new StringBuilder();
            }
        }
        return val + (builder.length() > 0 ? builder.length() * map.get(builder.substring(0,1)) : 0);
    }

    public static void main(String[] args) {
        int val = 3999;
        String str = new IntToRoman().toRoman(val);
        System.out.println("Int value is " + val);
        System.out.println("Roman is " + str);
        System.out.println("Back to int " + new IntToRoman().fromRoman(str));
    }
}
