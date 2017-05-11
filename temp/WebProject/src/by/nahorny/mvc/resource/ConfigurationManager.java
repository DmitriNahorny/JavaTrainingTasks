package by.nahorny.mvc.resource;

import java.util.ResourceBundle;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");
    // class for get properties from config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}