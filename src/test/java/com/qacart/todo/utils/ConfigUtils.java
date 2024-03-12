package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
    private static ConfigUtils configUtils;
    private Properties properties;

    private ConfigUtils() {
        properties = new Properties();
        properties = readProp();
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }

        return configUtils;
    }

    private Properties readProp() {
        FileInputStream fileInputStream = null;
        String env = System.getProperty("env", "PRODUCTION");

        try {
            switch (env) {
                case "PRODUCTION" -> {
                    fileInputStream = new FileInputStream("src/test/resources/env/production.properties");
                }
                case "LOCAL" -> {
                    fileInputStream = new FileInputStream("src/test/resources/env/local.properties");
                }
                default -> {
                    throw new RuntimeException("Environment isn't supported" + env);
                }
            }
        } catch (IOException e) {
        }

        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public String getBaseUrl() {
        return properties.getProperty("URL");
    }
}