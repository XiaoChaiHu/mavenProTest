package com.test.wartest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class WarUtil {

    public static void main(String[] args) throws Exception {
        String fileName="D:\\ideaPro\\warTest\\dubbo.war";
        uncompress(fileName);
    }

    public static void uncompress(String inFileName) throws Exception{
        if(inFileName == null){
            return;
        }
        String inFileName2 = inFileName.replace("\\", "/");
        int index = inFileName2.lastIndexOf(".");
        String outputDir = inFileName2.substring(0,index);
        File outputFile = new File(outputDir);
        if(!outputFile.exists()){
            outputFile.mkdirs();
        }
        outputFile = null;

        File file = new File(inFileName2);
        ZipFile zipFile = new ZipFile(file);
        Enumeration<?> en = zipFile.entries();
        while(en.hasMoreElements()){
            ZipEntry zipEntry = (ZipEntry) en.nextElement();
            if(zipEntry.isDirectory()){
                File file2 = new File(outputDir + File.separator + zipEntry.getName());
                if(!file2.exists()){
                    file2.mkdirs();
                }
                continue;
            }else{
                String name = zipEntry.getName();
                index = name.lastIndexOf("/");
                if(index >= 0){
                    name = name.substring(0,index);
                    File file2 = new File(outputDir + File.separator + name);
                    if(!file2.exists()){
                        file2.mkdirs();
                    }
                }
            }
            InputStream in = zipFile.getInputStream(zipEntry);
            OutputStream os = new FileOutputStream(outputDir + File.separator + zipEntry.getName());
            copyTo(in, os, zipEntry.getSize());
            in.close();
            os.close();
        }
        zipFile.close();
    }

    private static void copyTo(InputStream is,OutputStream os,long size) throws Exception{
        if(size <= 0){
            return;
        }
        int readSize = -1;
        int hasRead = 0;
        byte[] data = new byte[1024];
        while(hasRead < size){
            readSize = is.read(data);
            os.write(data, 0,readSize);
            os.flush();
            hasRead += readSize;
        }
    }
}
