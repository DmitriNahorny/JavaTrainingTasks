package by.nahorny.mvc.authorization;

import by.nahorny.mvc.resource.MessageManager;

import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 5/5/2017.
 */
public class FieldValidator {

    private static final String LOGIN_REG_EXP = "(\\w{3,15})";
    private static final String PASSWORD_REG_EXP = "(.{4,})";
    private static final String EMAIL_REG_EXP = "(\\w{6,})@(\\w+\\.)([a-z]{2,4})";

    public static boolean checkFormFields(String login, String password, String passwordConfirmation, String email, StringBuilder errorMessage) {

        boolean checkResult = false;

        if (login != null && password != null && email != null) {

            if (password.equals(passwordConfirmation)) {

                if (Pattern.matches(LOGIN_REG_EXP, login)) {

                    if (Pattern.matches(PASSWORD_REG_EXP, password)) {

                        if (Pattern.matches(EMAIL_REG_EXP, email)) {

                            checkResult = true;

                        } else {
                            errorMessage.append(MessageManager.getProperty("message.incorrectemail"));
                        }
                    } else {
                        errorMessage.append(MessageManager.getProperty("message.incorrectpassword"));
                    }
                } else {
                    errorMessage.append(MessageManager.getProperty("message.incorrectlogin"));
                }
            } else {
                errorMessage.append(MessageManager.getProperty("message.differentpassword"));
            }
        } else {
            errorMessage.append(MessageManager.getProperty("message.emptyfields"));
        }

        return checkResult;
    }
}
