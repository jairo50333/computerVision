package sfedu.jairo.co;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author bjmailov
 */
public class ConfigurationUtil{

    private final static Logger logger = Logger.getLogger(ConfigurationUtil.class);
    private String defaultConfigPath;
    private final Properties configuration = new Properties();

    /**
     * Hides default constructor
     */
    private ConfigurationUtil(){
    }



    public  ConfigurationUtil(String configPath) throws IllegalArgumentException {
        if (configPath == null) {
            throw new IllegalArgumentException("configPath cannot be NULL");
        }
        this.defaultConfigPath = configPath;
    }

    public Properties getConfiguration() throws IOException {
        if (configuration.isEmpty()) {
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>defaultConfigPath</code>
     */
    private void loadConfiguration() {
        try  {
           // ConfigurationUtil conf = new ConfigurationUtil(System.getProperty(Constants.CONFIGURATION_PATH));
            FileInputStream fileStream = new FileInputStream(System.getProperty(Constants.CONFIGURATION_PATH));
            configuration.load(fileStream);
            logger.info("file loaded");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Gets configuration entry value
     *
     * @param key Entry key
     * @return Entry value by key
     */
    public String readConfig(String key) {
        try {
            return getConfiguration().getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}