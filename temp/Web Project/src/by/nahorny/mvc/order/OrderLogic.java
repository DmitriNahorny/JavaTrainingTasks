package by.nahorny.mvc.order;

import by.nahorny.mvc.dao.OrderDAO;
import by.nahorny.mvc.dao.SongDAO;
import by.nahorny.mvc.dao.UserDAO;
import by.nahorny.mvc.entity.Order;
import by.nahorny.mvc.entity.Song;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.pool.ConnectionPool;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.Date;

/**
 * Created by Dmitri_Nahorny on 4/27/2017.
 */
public class OrderLogic {
    /*rollback order creation in case user update failure*/
    private ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();

    public User placeOrder(int userId, int songId) throws DAOException, UnavailableConnectionException {
        User userUpdate = null;
        Connection connection = connectionPoolInstance.getConnection();

        if (!this.checkOrderExist(userId, songId) && this.checkUserBalance(userId, songId)) {
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = new Order(new Date(System.currentTimeMillis()), userId, songId);
            if (orderDAO.create(order)) {
                UserDAO userDAO = new UserDAO(connection);
                User user = userDAO.findEntityById(userId);

                SongDAO songDAO = new SongDAO(connection);
                Song song = songDAO.findEntityById(songId);

                float userBalance = user.getCurrentBalance();
                float songPrice = song.getPrice();
                userBalance -= songPrice * (100 - user.getDiscount()) / 100;

                float intPart = (float)Math.floor(userBalance);
                BigDecimal fractionPart = new BigDecimal(userBalance - Math.floor(userBalance), new MathContext(2));
                userBalance = intPart + fractionPart.floatValue();
                user.setCurrentBalance(userBalance);

                userUpdate = userDAO.update(user);
                userUpdate.setPassword(null);
            }
        }

        connectionPoolInstance.closeConnection(connection);
        
        return userUpdate;
    }

    public boolean checkOrderExist(int userId, int songId) throws DAOException, UnavailableConnectionException {
        boolean isOrderExist = false;

        Connection connection = connectionPoolInstance.getConnection();
        OrderDAO orderDAO = new OrderDAO(connection);

        Order order = orderDAO.findOrderByUserSongId(userId, songId);

        if (order != null) {
            isOrderExist = true;
        }

        connectionPoolInstance.closeConnection(connection);

        return isOrderExist;
    }

    public boolean checkUserBalance(int userId, int songId)  throws DAOException, UnavailableConnectionException {
        boolean isBalanceSufficient = false;

        Connection connection = connectionPoolInstance.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.findEntityById(userId);

        SongDAO songDAO = new SongDAO(connection);
        Song song = songDAO.findEntityById(songId);

        if (user != null && song != null) {
            float songPrice = song.getPrice();
            float userBalance = user.getCurrentBalance();
            int userDiscount = user.getDiscount();
            if (songPrice * (100 - userDiscount) / 100 <= userBalance) {
                isBalanceSufficient = true;
            }
        }

        connectionPoolInstance.closeConnection(connection);

        return isBalanceSufficient;
    }
}
