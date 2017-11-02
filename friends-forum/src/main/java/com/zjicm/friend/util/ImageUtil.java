package com.zjicm.friend.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.stream.DoubleStream;

public class ImageUtil {
    //设定图片大小
        /*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
    public static void zoomImage(File file,String path,int w,int h) throws Exception {

        double wr=0,hr=0;
        File srcFile = file;
        File destFile = new File(path);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板

        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,path.substring(path.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void zoomImage(File file,String path,double zoomProportionW , double zoomProportionH)throws Exception {

        int w=0,h=0;
        File srcFile = file;
        File destFile = new File(path);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        w=(int)zoomProportionW*bufImg.getWidth();     //获取缩放比例
        h=(int)zoomProportionH*bufImg.getHeight();

        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(zoomProportionW, zoomProportionH), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,path.substring(path.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void zoomImage(File file,String path)throws Exception {
        int w = 330;
        int h = 500;
        double wr=0,hr=0;
        File srcFile = file;
        File destFile = new File(path);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板

        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();
        double r =Math.max(wr,hr);
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(r, r), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,path.substring(path.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
