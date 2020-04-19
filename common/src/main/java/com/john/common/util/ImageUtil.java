package com.john.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：<p>
 * Date：2020/2/10 16:42<p>
 * Updater：
 */
public class ImageUtil {

    /**
     * @param imagePath
     * @param percent   压缩到的  百分比
     * @param maxSize   最大的大小
     */
    public static File compressImage(String imagePath, int percent, int maxSize, String outPath) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        if (imagePath.endsWith(".jpeg") || imagePath.endsWith(".jpg")) {
            format = Bitmap.CompressFormat.JPEG;
        } else if (imagePath.endsWith(".png")) {
            format = Bitmap.CompressFormat.PNG;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        File file = compressImage(bitmap, format, percent, maxSize, outPath);
        bitmap.recycle();
        return file;
    }

    /**
     * @param bitmap
     * @param format
     * @param percent
     * @param maxSize
     * @param outPath
     * @return
     */
    public static File compressImage(Bitmap bitmap, Bitmap.CompressFormat format, int percent, int maxSize, String outPath) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, percent, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        int length = baos.toByteArray().length;
        while (length > maxSize) {  //循环判断如果压缩后图片是否大于maxSize,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(format, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            length = baos.toByteArray().length;
        }
        File file = new File(outPath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
