package by.nahorny.mvc.model;

import by.nahorny.mvc.contentmanager.AbstractContentManager;
import by.nahorny.mvc.contentmanager.SongManager;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Dmitri_Nahorny on 5/6/2017.
 */
public class ContentManagerCommand implements ActionCommand {

    /*implement handling of incorrect content and action*/
    /*implement messaging for successful/unsuccessful action*/

    private final static Logger LOGGER = LogManager.getLogger(ContentManagerCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        String contentTypeParam = request.getParameter("contentType");
        String actionTypeParam = request.getParameter("actionType");
        ContentManagerEnum contentManagerEnum = ContentManagerEnum.valueOf(contentTypeParam.toUpperCase());
        AbstractContentManager contentManager = contentManagerEnum.getContentManager();
        Map<String,String[]> requestParameters = request.getParameterMap();

        try {
            switch (actionTypeParam) {
                case "add":
                    contentManager.add(requestParameters);
                    break;
                case "edit":
                    contentManager.edit(requestParameters);
                    break;
                case "delete":
                    contentManager.delete(requestParameters);
                    break;
                default:
                    break;
            }
            /*page = ConfigurationManager.getProperty("path.page.controller");
            request.setAttribute("command", "library");
            request.setAttribute("listLevel", "artist");*/
            page = ConfigurationManager.getProperty("path.page.main");
        } catch (DAOException | UnavailableConnectionException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            request.setAttribute("techIssueMessage", MessageManager.getProperty("message.techissue"));
            page = ConfigurationManager.getProperty("path.page.main");
        }

        return page;
    }

    private enum ContentManagerEnum {
        SONG {
            {
                this.contentManager = new SongManager();
            }
        };
        AbstractContentManager contentManager;
        AbstractContentManager getContentManager() {
            return this.contentManager;
        }
    }
}
