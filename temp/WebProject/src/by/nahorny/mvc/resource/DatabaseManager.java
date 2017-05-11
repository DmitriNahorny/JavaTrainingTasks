package by.nahorny.mvc.resource;

import java.util.ResourceBundle;

/**
 * Created by Dmitri_Nahorny on 3/31/2017.
 */
public class DatabaseManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.database");
    private DatabaseManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
