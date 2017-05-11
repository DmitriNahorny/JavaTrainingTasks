package by.nahorny.mvc.model;

import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.order.OrderLogic;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitri_Nahorny on 4/29/2017.
 */
public class OrderCommand implements ActionCommand {

    /*use 'listen' button instead of 'buy' for purchased songs*/
    /*implement 'buy album' button*/

    private final static Logger LOGGER = LogManager.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String userParam = request.getParameter("usr");
        String songParam = request.getParameter("song");
        try {
            int userId = Integer.parseInt(userParam);
            int songId = Integer.parseInt(songParam);
            OrderLogic orderLogic = new OrderLogic();

            if (!orderLogic.checkOrderExist(userId, songId)) {
                if (orderLogic.checkUserBalance(userId, songId)) {
                    User updatedUser = orderLogic.placeOrder(userId, songId);
                    HttpSession currentSession = request.getSession();
                    currentSession.setAttribute("user", updatedUser);
                    request.setAttribute("orderConfirmation", MessageManager.getProperty("message.orderconfirm"));
                    page = ConfigurationManager.getProperty("path.page.main");
                } else {
                    request.setAttribute("orderConfirmation", MessageManager.getProperty("message.insufficientbalance"));
                    page = ConfigurationManager.getProperty("path.page.main");
                }
            } else {
                request.setAttribute("orderConfirmation", MessageManager.getProperty("message.duplicateorder"));
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } catch (NumberFormatException e) {
            page = ConfigurationManager.getProperty("path.page.controller");
            request.setAttribute("command", "library");
            request.setAttribute("listLevel", "artist");
        } catch (DAOException | UnavailableConnectionException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            request.setAttribute("techIssueMessage", MessageManager.getProperty("message.techissue"));
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
