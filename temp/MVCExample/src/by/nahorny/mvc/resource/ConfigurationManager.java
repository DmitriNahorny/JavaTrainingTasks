package by.nahorny.mvc.resource;

import java.util.ResourceBundle;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");
    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}