package com.savepictosdcard.file;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/21.
 */

public class FileService {

    private Context context;

    public FileService(Context context){
        this.context = context;
    }

    public boolean saveFileToSdcard(String fileName,byte[] data){
        boolean flag= false;
        String state = Environment.getExternalStorageState();
        FileOutputStream outputStream = null;
       // System.out.println("state=="+state);
        //Toast.makeText(context,"state="+state,Toast.LENGTH_LONG).show();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            try {
                File root = Environment.getExternalStorageDirectory();
                System.out.println("root="+root.getAbsolutePath());
               // Toast.makeText(context,"path="+root.getAbsolutePath(),Toast.LENGTH_LONG).show();
                File file = new File(root, fileName);
                file.createNewFile();
                if(file.exists()) {
                    outputStream = new FileOutputStream(file,true);
                    outputStream.write(data, 0, data.length);
                    String s = new String(data);
                    Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(outputStream!=null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return flag;
    }

    public String readContextFromSdcard(String fileName) {
        String result = null;
        String state = Environment.getExternalStorageState();
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            try {
                File root = Environment.getExternalStorageDirectory();
                File file = new File(root, fileName);
                if (file.exists()) {
                    int len = 0;
                    byte[] data = new byte[1024];
                    inputStream = new FileInputStream(file);
                    while ((len = inputStream.read(data)) != -1) {
                        outputStream.write(data, 0, len);
                    }
                }
                result = new String(outputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
