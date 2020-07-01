package com.yj.baselibrary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileUtils {
    /**

     * 读取文件的大小

     */

   private static long getFileSize(File f){
     long l=0l;
     if (f.exists()){
         FileInputStream mFIS = null;
         try {
             mFIS = new FileInputStream(f);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         try {
             l=mFIS.available();
         } catch (IOException e) {
             e.printStackTrace();
         }
     } else {
         try {
             f.createNewFile();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
   return l;

}



/**

 * 将文件大小转换成字节

 */

  public static String formatFileSize(File f){
      long fSize=getFileSize(f);
    DecimalFormat df = new DecimalFormat("#.00");

     String fileSizeString = "";

    if(fSize<1024){

     fileSizeString = df.format((double) fSize) + "B";

      } else if ( fSize >104875 ){

    fileSizeString = df.format((double) fSize/1024) + "K";

    } else if ( fSize >1073741824){
        fileSizeString = df.format((double) fSize/104875) + "M";
     } else {

    fileSizeString = df.format((double) fSize/1073741824) + "G";
    }
    return fileSizeString;

    }
}
