package sfedu.jairo.co;

import org.apache.log4j.Logger;

public final class Check_task {

    private final static Logger logger = Logger.getLogger(Check_task.class);

    public static int getIntegerValue(String stringValue){
        try {
            return Integer.parseInt(stringValue);

        }catch (NumberFormatException e){
            logger.error("The argument is not a number" + e);
            return 0;
        }
    }

}
