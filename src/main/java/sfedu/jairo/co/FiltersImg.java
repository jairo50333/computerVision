package sfedu.jairo.co;

import org.apache.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.Arrays;
import static sfedu.jairo.co.ImageProc.showImage;


public class FiltersImg {
    private final static Logger logger = Logger.getLogger(FiltersImg.class);
    private static ConfigurationUtil conf = new ConfigurationUtil((System.getProperty(Constants.CONFIGURATION_PATH)));
    private final static String dirPath = conf.readConfig("pathToScrImages");
    private final static String destDirPath = conf.readConfig("pathDesImages");


    public static void applyFilters(Mat matImage, int kernelSize) {
        logger.debug("apply filters: kernelSize " + kernelSize);

        Mat dst = matImage.clone();
        Size kSize = new Size(kernelSize, kernelSize);


        Imgproc.blur(matImage, dst, kSize);
        Imgcodecs.imwrite(dirPath + "Blur_[" + kernelSize + "]_" + Constants.IMAGE_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.GaussianBlur(matImage, dst, kSize, 0);
        Imgcodecs.imwrite(dirPath + "GaussianBlur_[" + kernelSize + "]_" + Constants.IMAGE_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.medianBlur(matImage, dst, kernelSize);
        Imgcodecs.imwrite(dirPath + "EdianBlur_[" + kernelSize + "]_" + Constants.IMAGE_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.bilateralFilter(matImage, dst, 15, 80, 80);
        Imgcodecs.imwrite(dirPath + "BilateralFilter_[" + kernelSize + "]_" + Constants.IMAGE_NAME, dst);
        showImage(dst);

    }

    public static void morfologyTest() {
        try {
            String fileName = "1.jpg";
            String prfName = "mrf_";
            Mat src = Imgcodecs.imread(dirPath + fileName, Imgcodecs.IMREAD_COLOR);
            Mat dst = src.clone();
            Mat element_10 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(10, 10));
            Mat element_01 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 1));
            Mat element_05 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.erode(src, dst, element_10);
            Imgcodecs.imwrite(destDirPath + prfName + "erode_10_" + fileName, dst);
            showImage(dst);
            dst = src.clone();
            Imgproc.erode(src, dst, element_01);
            Imgcodecs.imwrite(destDirPath + prfName + "erode_01_" + fileName, dst);
            showImage(dst);
            dst = src.clone();
            Imgproc.erode(src, dst, element_05);
            Imgcodecs.imwrite(destDirPath + prfName + "erode_05_" + fileName, dst);
            showImage(dst);
            dst = src.clone();
            Imgproc.dilate(src, dst, element_10);
            Imgcodecs.imwrite(destDirPath + prfName + "dilate_10_" + fileName, dst);
            showImage(dst);
            dst = src.clone();
            Imgproc.dilate(src, dst, element_01);
            Imgcodecs.imwrite(destDirPath + prfName + "dilate_01_" + fileName, dst);
            showImage(dst);
            dst = src.clone();
            Imgproc.dilate(src, dst, element_05);
            Imgcodecs.imwrite(destDirPath + prfName + "dilate_05_" + fileName, dst);
            showImage(dst);
        } catch (Exception ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }




}
