package sfedu.jairo.co;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Locale;

public class OperatingSystemType {
    static final Logger logger = Logger.getLogger(OperatingSystemType.class);


    private OperatingSystemType(){
    }

    public static Constants.OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            return Constants.OSType.MACOS;
        } else if (OS.contains("win")) {
            return Constants.OSType.WINDOWS;
        } else if (OS.contains("nux")) {
            return Constants.OSType.LINUX;
        }
        else {
            return Constants.OSType.OTHER;
        }
    }

    public  static void loadOpencv() throws Exception {
        ConfigurationUtil conf = new ConfigurationUtil(System.getProperty(Constants.CONFIGURATION_PATH));
        BasicConfigurator.configure();
        switch (getOperatingSystemType()) {
            case LINUX:
                System.load(conf.readConfig(Constants.PATH_TO_NATIVE_LIB_LINUX));
                logger.info("your OS is Linux");
                break;
            case WINDOWS:
                System.load(conf.readConfig(Constants.PATH_TO_NATIVE_LIB_WIN));
                logger.info("your OS is Windows");
                break;
            case MACOS:
                throw new Exception("Mac OS does not support!!!!!!!!");
            case OTHER:
                throw new Exception("Current OS does not support!!!!!");
            default:
                throw new Exception("Your OS does not support!!!");
        }
    }




}
