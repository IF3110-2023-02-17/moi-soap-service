package moi.soap.maven.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
    private final Properties properties;
    public ConfigProperties(String path) {
        this.properties = new Properties();

        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream(path);
            this.properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProp(String key) {
        return this.properties.getProperty(key);
    }
}
