package com.savepictosdcard.img;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/21.
 */

public class ImageCache {

    private static Context context;

    public ImageCache(Context context){
        this.context = context;
    }

    public static String saveImageCache(String fileName,byte[] data){
        String path =null;
        boolean flag= false;
        String state = Environment.getExternalStorageState();
        FileOutputStream outputStream = null;
        if(Environment.MEDIA_MOUNTED.equals(state)){
            try {
                File root = Environment.getExternalStorageDirectory();
                File file = new File(root, fileName);
                file.createNewFile();
                if(file.exists()) {
                    outputStream = new FileOutputStream(file,true);
                    outputStream.write(data, 0, data.length);
                    Toast.makeText(context,data.length,Toast.LENGTH_SHORT).show();
                }
                path = file.getAbsolutePath();
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
        return path;
    }

}
