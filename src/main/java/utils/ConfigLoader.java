package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
        }
    }

    public static String getProperty(String key) {
        String value = System.getProperty(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        return properties.getProperty(key);
    }
}