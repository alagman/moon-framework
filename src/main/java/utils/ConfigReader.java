package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    Properties properties;
    Logger logger = LoggerFactory.getLogger(new Object(){}.getClass().getEnclosingClass().getSimpleName());
    public ConfigReader() {
        try {
            File source = new File("./config");
            FileInputStream inputStream = new FileInputStream(source);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("IOException error!",e);

        }
    }

    public String getURL() {
        String path = properties.getProperty("url");
        return path;
    }

    public String getBrowser() {
        String path = properties.getProperty("browser");
        return path;
    }

    public String getTestBed() {
        String path = properties.getProperty("testbed");
        return path;
    }

    public String getGridIP() {
        String path = properties.getProperty("grid_ip");
        return path;
    }
}
