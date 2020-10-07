package sfedu.jairo.co;

import java.awt.*;
import java.util.Map;
import org.apache.log4j.Logger;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.sql.Types.NULL;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static sfedu.jairo.co.Constants.*;
import static sfedu.jairo.co.ImageProc.showImage;


public class Filters_4 {

    private final static Logger logger = Logger.getLogger(Filters_4.class);
    private static ConfigurationUtil conf = new ConfigurationUtil((System.getProperty(Constants.CONFIGURATION_PATH)));
    private final static String dirPathorg = conf.readConfig("pathDesImages");
    private final static String dirPath = conf.readConfig("pathDesImages");
    private final static String destDirPath = conf.readConfig("pathDesImages");
    private  final static String sourcePath=conf.readConfig("pathToScrImages");

    public static void testFillFlood() {
        String srcImgName = IMAGE_NAME;
        int initVal =50;
        Mat srcImage = Imgcodecs.imread(dirPath + srcImgName);
        Point seedPoint = new Point(0,0);
        Scalar newVal = new Scalar(0,255,0);
        Scalar loDiff = new Scalar(initVal,initVal,initVal);
        Scalar upDiff = new Scalar(initVal,initVal,initVal);
        Mat mask = new Mat();
        showImage(srcImage, "SRC");
        Imgproc.floodFill(srcImage, mask, seedPoint, newVal,new Rect(), loDiff, upDiff,
                Imgproc.FLOODFILL_FIXED_RANGE);
        showImage(srcImage, "FILL");
    }
    public static void piramid(){
        String srcImgName =IMAGE_NAME;
        Mat srcImage = Imgcodecs.imread(dirPath + srcImgName);;
        Mat mask = new Mat();
        showImage(srcImage, "SRC");
        Imgproc.pyrDown(srcImage, mask);
        showImage(mask, "pyrDown native");
        Imgproc.pyrUp(mask, mask);
        showImage(mask, "pyrUp");
        Core.subtract(srcImage, mask, mask);
        showImage(mask, "Core");
    }




    private Integer generateValueIfNull(Integer integerValue) {
        return integerValue != null ? integerValue : new Random().nextInt(256);
    }


    private boolean validateColor(Integer initVal, Integer red, Integer green, Integer blue) {
        return red > 255 || red < 0 ||
                green > 255 || green < 0 ||
                blue > 255 || blue < 0 ||
                initVal > 255 || initVal < 0;
    }



    public static void change_size(){
        String srcImgName = IMAGE_NAME;
        Mat srcImage = Imgcodecs.imread(dirPath + srcImgName);
        Mat mask = new Mat();
        showImage(srcImage, "SRC");
        Imgproc.pyrDown(srcImage, mask);
        showImage(mask, "pyrDown native");
        Imgproc.pyrUp(mask, mask);
        showImage(mask, "pyrUp");
        Core.subtract(srcImage, mask, mask);
        showImage(mask, "Core");
    }

    public static Mat raisePyramid(Mat matImage, int iterations) {
        Mat clone = new Mat();

        for (int i = 0; i < iterations; i++) {
            Imgproc.pyrUp(matImage, clone);
        }

        return clone;
    }

    public static Mat reducePyramid(Mat matImage, int iterations) {
        Mat clone = new Mat();

        for (int i = 0; i < iterations; i++) {
            Imgproc.pyrDown(matImage, clone);
        }

        return clone;

    }




}
