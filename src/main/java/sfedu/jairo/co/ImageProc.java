package sfedu.jairo.co;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Properties;


import static org.opencv.imgcodecs.Imgcodecs.imread;
import static sfedu.jairo.co.Constants.*;

public class ImageProc{

    private static ConfigurationUtil conf= new ConfigurationUtil((System.getProperty(Constants.CONFIGURATION_PATH)));

    private final static String dirPath = conf.readConfig(PATH_FROM_IMAGES);

    public static void processing_image_by_channel(String path, Integer channel){
        Mat img = uploadImage();
        img = channelToZero(img, channel);
        showImage(img);
        Imgcodecs.imwrite(path, img);
    }
    public static Mat uploadImage(){
        Mat srcImage = imread(dirPath + IMAGE_NAME);
        return srcImage;
}

    public static Mat channelToZero(Mat matImage, int numChn) {
        //logger.info("image " + matImage.toString() + ", channel " + numChn);
        Mat clone = matImage.clone();
        for (int i = 0; i < matImage.cols(); i++) {
            for (int j = 0; j < matImage.rows(); j++) {
                    if(i==j){ double[] data = matImage.get(j, i);
        data[numChn] = 0;  clone.put(j, i, data); }
            }
        }
        return clone;
    }


    public static void showImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b);
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        ImageIcon icon=new ImageIcon(image);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth(null)+50, image.getHeight(null)+50);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void showImage(Mat m, String name){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b);
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        ImageIcon icon=new ImageIcon(image);
        JFrame frame=new JFrame(name);
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth(null)+50, image.getHeight(null)+50);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}






