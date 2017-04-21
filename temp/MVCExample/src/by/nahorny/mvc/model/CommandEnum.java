package by.nahorny.mvc.model;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    LOCALE {
        {
            this.command = new SetLocaleCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    LIBRARY {
        {
            this.command = new LibraryCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
