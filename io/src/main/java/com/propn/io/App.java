package com.propn.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App {
    private static final String FILENAME="E:\\电影\\[高清电影]阿甘正传.1994.美国.中文字幕.1280x720.rmvb";
    
    public static void main(String[] args) throws IOException {
        long l1 = readByBufferedInputStream();
        long l2 = readByInputStream();
        System.out.println("通过BufferedInputStream读取用时："+l1+";通过InputStream读取用时："+l2);
    }

    public static long readByInputStream() throws IOException {
        InputStream in=new FileInputStream(FILENAME);
        byte[] b=new byte[8192];
        int l=0;
        long start=System.currentTimeMillis();
        while(in.read(b,0,8192)!=-1){
        }
        long end=System.currentTimeMillis();
        return end-start;
    }

    public static long readByBufferedInputStream() throws IOException {
        BufferedInputStream in=new BufferedInputStream(new FileInputStream(FILENAME));
        byte[] b=new byte[8192];
        int l=0;
        long start=System.currentTimeMillis();
        while(in.read(b,0,8192)!=-1){
        }
        long end=System.currentTimeMillis();
        return end-start;
    }
}