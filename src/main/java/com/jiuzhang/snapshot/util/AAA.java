package com.jiuzhang.snapshot.util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * @Description TODO
 * @Date 2021/3/4 10:02
 * @Author FU
 */
public class AAA {
    public int a = 1;
    public int b = 2;
    public void c(){
        System.out.println("父类方法！");
    };

    public static void main(String[] args) {
        AAA x = new BBB();
        x.c();
        System.out.println(x.b);
        System.out.println(x.a);
    }
}
class BBB extends AAA {
    public int d = 3;
    public int b = 30;
    public void c(){
        System.out.println("子类方法！");
    };

    public static void main(String[] args) {
        int k =1, n =11;
        int cnt = 0;
        char key = (char)(48 + k);
        String str;
        while (n >= 0) {
            str = "" + n;
            char[] cs = str.toCharArray();
//            System.out.println(Arrays.toString(cs));
            for (int i = 0; i < cs.length; i++) {
                // cnt = cs[i] == k ? cnt+1 : cnt;
                System.out.println(cs[i] == key);

                if (cs[i] == k) {
                    cnt++;
                }
            }
            n--;
        }
    }
}

