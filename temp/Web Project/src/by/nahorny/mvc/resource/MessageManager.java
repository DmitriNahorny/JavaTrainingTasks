package by.nahorny.mvc.resource;

import java.util.ResourceBundle;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.messages");
    // class for get properties from messages.properties
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
