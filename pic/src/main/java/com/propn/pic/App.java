package com.propn.pic;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 try{  
    		 
             FileImageCreator creator = new FileImageCreator(new SimpleDrawer());  
             creator.setWidth(30); //图片宽度  
             creator.setHeight(30); //图片高度  
             creator.setLineNum(0); //干扰线条数  
             
             creator.setFontName("黑体");  
               
             //文字旋转  
             creator.setRadian(0); //旋转弧度  
             creator.setRotateX(creator.getWidth()/5);  
             creator.setRotateY(creator.getHeight()*5/10);  
             
             for(int i=0;i<10;i++) {
            	 //黑体
            	 creator.setFontName("黑体");
            	 for(int fontSize=10;fontSize<20;fontSize++) {
            		 creator.setFontSize(fontSize); //字体大小 
            		 creator.setFileName("pic/黑体"+fontSize+"_"+i+".jpeg");
                     creator.generateImage(" "+i);  
            	 }
            	//宋体
            	 creator.setFontName("宋体");
            	 for(int fontSize=10;fontSize<20;fontSize++) {
            		 creator.setFontSize(fontSize); //字体大小 
            		 creator.setFileName("pic/宋体"+fontSize+"_"+i+".jpeg");
                     creator.generateImage(" "+i);  
            	 }
            	//雅黑
            	 creator.setFontName("微软雅黑");
            	 for(int fontSize=10;fontSize<20;fontSize++) {
            		 creator.setFontSize(fontSize); //字体大小 
            		 creator.setFileName("pic/雅黑"+fontSize+"_"+i+".jpeg");
                     creator.generateImage(" "+i);  
            	 }
             }
               
             System.out.println("ok");  
               
         }catch(IOException ex){  
             ex.printStackTrace();  
         }  
     }  
}
