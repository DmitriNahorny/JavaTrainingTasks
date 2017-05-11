package by.nahorny.mvc.model;

import by.nahorny.mvc.contenthandler.AlbumList;
import by.nahorny.mvc.contenthandler.ArtistList;
import by.nahorny.mvc.contenthandler.LibraryList;
import by.nahorny.mvc.contenthandler.SongList;
import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/19/2017.
 */
public class LibraryCommand implements ActionCommand {

    /*implement handling of incorrect level*/

    static private final Logger LOGGER = LogManager.getLogger(LibraryCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String listLevel = request.getParameter("listLevel");
        String parameterId = request.getParameter("id");

        try {
            LevelList levelListEnum = LevelList.valueOf(listLevel.toUpperCase());
            List<ContentEntityHandler> contentEntityList = levelListEnum.getListInstance().getEntityList(parameterId);
            request.setAttribute("contentEntityList", contentEntityList);
            page = levelListEnum.getListPage();
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            request.setAttribute("techIssueMessage", listLevel + MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.main");
        }  catch (DAOException | UnavailableConnectionException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            request.setAttribute("techIssueMessage", MessageManager.getProperty("message.techissue"));
            page = ConfigurationManager.getProperty("path.page.main");
        }

        return page;
    }
    private enum LevelList {
        ARTIST {
            {
                this.contentListInstance = new ArtistList();
                this.listPage = ConfigurationManager.getProperty("path.page.artistlist");
            }
        },
        ALBUM {
            {
                this.contentListInstance = new AlbumList();
                this.listPage = ConfigurationManager.getProperty("path.page.albumlist");
            }
        },
        SONG {
            {
                this.contentListInstance = new SongList();
                this.listPage = ConfigurationManager.getProperty("path.page.songlist");
            }
        };
        LibraryList contentListInstance;
        String listPage;

        public LibraryList getListInstance() {
            return contentListInstance;
        }
        public String getListPage() {
            return listPage;
        }
    }
}