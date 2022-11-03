package com.example.proyectosw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
    public static final String USUARIO = "app.user";
    public static final String CONTRASEÑA = "app.password";

    private Properties properties;

    private static AppProperties instance;

    private AppProperties() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("swapi.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getProp(String propkey) {
        return getInstance().properties.getProperty(propkey);
    }

    public static AppProperties getInstance() {
        if (instance == null) {
            instance = new AppProperties();
        }
        return instance;
    }
}
