package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private static final Properties properties;

    static {
        try {
            properties = TestConfig.loadProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/testconfig.properties");
        props.load(fis);
        return props;
    }

    public static String getAppiumServerIp() {
        return properties.getProperty("appiumServerIp");
    }

    public static int getAppiumServerPort() {
        return Integer.parseInt(properties.getProperty("appiumServerPort"));
    }

    public static String getAndroidAppName() {
        return properties.getProperty("androidAppName");
    }

    public static String getIosAppName() {
        return properties.getProperty("iosAppName");
    }
}
