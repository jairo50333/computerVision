package sfedu.jairo.co;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;

import static sfedu.jairo.co.ImageProc.showImage;

public class MorphologicalOperations {

    public void morfologyTest() {
        /*try {
            String fileName = "numpl5.jpg";
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
        }*/
    }



}
