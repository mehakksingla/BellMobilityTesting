package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static Logger logger= LogManager.getLogger(Config.class);
    private final String propertyFile = "framework.properties";
    private Properties properties;

    private static Config instance;

    private Config(){
        logger.info("Read file {} to load properties", propertyFile);
        Path path= Paths.get("src", "test", propertyFile);
        properties = new Properties();

    }

    public synchronized static Config getInstance(){
    logger.debug("Requested Instance for Logger");
        if (instance == null){
            instance = new Config();
        }
    return instance;
    }

    public String getProperty(String key){
        logger.debug("Requested property for the key", key);
        if (key == null || key.isEmpty()){
            throw new RuntimeException("Invalid or null was passed - key");
        }

        if (properties.getProperty(key) == null) {
            logger.warn("Property{} - is missing in the file, please check - Returning null");
        }
        logger.debug("Found property for key {}, value of property is {}", key, properties.getProperty(key));
        return properties.getProperty(key);

    }
}
