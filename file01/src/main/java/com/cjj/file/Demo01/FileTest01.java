package com.cjj.file.Demo01;

import java.io.File;
import java.io.IOException;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2020/7/21
 * Time:22:41
 */
public class FileTest01 {
    public static void main(String[] args) throws IOException {
        //digui(1);
        int sum = sum(5);
        System.out.println(sum);
    }

    //展示列表
    public static void list(){
        File file = new File("F:\\BaiduNetdiskDownload");
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1 );
        }
    }

    /**
     * 递归
     */
    public static void digui(int i){
        System.out.println(i);
        i++;
        digui(i);
    }

    public static int sum(int n){
        if(n == 1){
            return 1;
        }
        return n + sum(n-1);
    }
}
