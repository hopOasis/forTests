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
                System.out.println("Successfully loaded config.properties");
            } else {
                System.err.println("ERROR: config.properties file not found!");
            }
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Available properties:");
        for (String key : properties.stringPropertyNames()) {
            if (key.contains("PASSWORD")) {
                System.out.println(key + "=*****");
            } else {
                System.out.println(key + "=" + properties.getProperty(key));
            }
        }
    }

    public static String getProperty(String key) {

        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }


        value = System.getenv(key);
        if (value != null) {
            return value;
        }

        return properties.getProperty(key);
    }
}
