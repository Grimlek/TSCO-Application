package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ErrorMessageModel {
    private final Properties settings;


    public ErrorMessageModel() {
        this.settings = new Properties();
    }


    public void loadProperties() {
        FileInputStream inputStream;

        if (settings != null) {
            try {
                inputStream = new FileInputStream("config.properties");

                settings.load(inputStream);

                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getErrorMessage(String key) {
        return settings.getProperty(key);
    }


    public boolean isDisplayable(String key) {
        if (settings.getProperty(key).equals("false"))
            return false;
        else
            return true;
    }


    public void saveProperties(String key, String value) {
        settings.put(key, value);

        storeProperties();
    }


    public void storeProperties() {
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream("config.properties");

            settings.store(outputStream, null);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
