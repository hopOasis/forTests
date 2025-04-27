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
            } else {
                System.err.println("Warning: config.properties file not found in classpath");
            }
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
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