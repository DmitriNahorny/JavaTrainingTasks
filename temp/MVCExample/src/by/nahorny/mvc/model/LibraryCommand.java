package by.nahorny.mvc.model;

import by.nahorny.mvc.contenthandler.ArtistList;
import by.nahorny.mvc.contenthandler.LibraryList;
import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/19/2017.
 */
public class LibraryCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String listLevel = request.getParameter("listLevel");

        try {
            LevelList levelListEnum = LevelList.valueOf(listLevel.toUpperCase());
            List<ContentEntityHandler> artistList = levelListEnum.getCurrentList().getEntityList();
            request.setAttribute("artistList", artistList);
            page = levelListEnum.getListPage();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", listLevel + MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.main");
        }  catch (DAOException | UnavailableConnectionException e) {
            /*attribute to set in main page*/
            request.setAttribute("wrongAction", MessageManager.getProperty("message.techissue"));
            page = ConfigurationManager.getProperty("path.page.main");
        }

        return page;
    }
    private enum LevelList {
        ARTIST {
            {
                this.contentList = new ArtistList();
                this.listPage = ConfigurationManager.getProperty("path.page.artistlist");
            }
        };
        LibraryList contentList;
        String listPage;

        public LibraryList getCurrentList() {
            return contentList;
        }
        public String getListPage() {
            return listPage;
        }
    }
}