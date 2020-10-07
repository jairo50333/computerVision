package sfedu.jairo.co;


import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.log4j.Logger;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.*;
import static sfedu.jairo.co.Constants.*;
import static sfedu.jairo.co.ImageProc.showImage;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);
    private final static int[] kernelArray = {3, 5, 7, 9, 13, 15};
    //private final static OpenCvApi openCvApi = new OpenCvApiImp();
    private static ConfigurationUtil conf = new ConfigurationUtil((System.getProperty(Constants.CONFIGURATION_PATH)));

    private final static String dirPathorg = conf.readConfig("pathDesImages");

    private final static String dirPath = conf.readConfig("pathDesImages");
    private final static String destDirPath = conf.readConfig("pathDesImages");
    private  final static String sourcePath=conf.readConfig("pathToScrImages");


    public static void main(String[] args) throws Exception {

        //BasicConfigurator.configure();
        OperatingSystemType.loadOpencv();

        // FiltersImg.morfologyTest();
        // CONFIGURATION_PATH
        // change_image_chanel(1);
       // filter_image();



        if (args.length > 0) {
            switch (Check_task.getIntegerValue(args[0])) {
                case (1):
                    logger.info("open cv has been  configured successfully");
                    break;
                case (2):
                    change_image_chanel(1);
                    logger.info("open cv has been  configured successfully");
                    break;
                case (3):
                    task_tree();
                    logger.info("open cv has been  configured successfully");
                    break;
                case (4):
                    taskFour();
                    break;
            }
        } else {
            logger.info("you don't select any one task");

        }
    }

    /*2 lab*/
    private static void change_image_chanel(int numch) {

        Mat img = ImageProc.uploadImage();
        img = ImageProc.channelToZero(img, 1);
        showImage(img);
        Imgcodecs.imwrite(dirPath + IMAGE_NAME, img);
        logger.info("the file was saved into = " + dirPath + IMAGE_NAME);

    }


    private static void filter_image() {
    Mat img = ImageProc.uploadImage();
    Size kernel = new Size(7, 7);
    ImageProc.showImage(img);
    blur(img,img,kernel);
    Imgproc.GaussianBlur(img,img,kernel,0);
//    medianBlur(img,img,3);
//    bilateralFilter(img,img,3,80,80,Core.BORDER_DEFAULT);
    ImageProc.showImage(img);
}
    private static void task_tree(){

        Mat img = ImageProc.uploadImage();
        for(int kernel:kernelArray) {
            FiltersImg.applyFilters(img, kernel);
        }
    }

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


    private static void taskFour() {
        Filters_4.testFillFlood();
    }

        private static Mat getMatImage() {
            //String dirPath = Config(PATH_TO_IMAGES);
            Mat matImage = imread(sourcePath + IMAGE_NAME);
            if (matImage.empty()) {
                logger.error("Error: Image " + IMAGE_NAME + " not found in path " + dirPath);
                throw new RuntimeException("Error: Image " + IMAGE_NAME + " not found in path " + dirPath);
            }
            return matImage;
        }
/*
        blur(Mat src, Mat dst, Size ksize)
        GaussianBlur(Mat src, Mat dst, Size ksize, double sigmaX [, double sigmaY], int
        borderType)
        medianBlur(Mat src, Mat dst, int ksize)
        bilateralFilter(Mat src, Mat dst, int d, double sigmaColor, double sigmaSpace, [int
        borderType])
*/






}






