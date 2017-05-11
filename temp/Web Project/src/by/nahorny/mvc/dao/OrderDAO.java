package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Order;
import by.nahorny.mvc.exception.DAOException;

import java.sql.*;
import java.sql.Date;
import java.util.List;


/**
 * Created by Dmitri_Nahorny on 4/27/2017.
 */
public class OrderDAO extends AbstractDAO <Integer, Order> {

    private final String SQL_CREATE_ORDER = "INSERT INTO user_library (order_date, user_id, song_id) VALUES (?, ?, ?)";
    private final String SQL_FIND_ORDER_BY_USER_SONG = "SELECT order_id, order_date FROM user_library WHERE user_id = ? AND song_id = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll() throws DAOException {
        return null;
    }

    @Override
    public Order findEntityById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Order entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Order entity) throws DAOException {
        PreparedStatement createStatement = null;
        Order orderToAdd = entity;
        Boolean isOrderAdded = false;

        try {
            createStatement = this.connection.prepareStatement(SQL_CREATE_ORDER);
            createStatement.setDate(1, new Date(orderToAdd.getOrderDate().getTime()));
            createStatement.setInt(2, orderToAdd.getUserId());
            createStatement.setInt(3, orderToAdd.getSongId());
            if(createStatement.executeUpdate() > 0) {
                isOrderAdded = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(createStatement);
        }

        return isOrderAdded;
    }

    @Override
    public Order update(Order entity) throws DAOException {
        return null;
    }

    public Order findOrderByUserSongId(int userId, int songId) throws DAOException {
        PreparedStatement findStatement = null;
        Order findOrder = null;
        try {
            findStatement = this.connection.prepareStatement(SQL_FIND_ORDER_BY_USER_SONG);
            findStatement.setInt(1, userId);
            findStatement.setInt(2, songId);
            ResultSet ordersResultSet = findStatement.executeQuery();
            if (ordersResultSet.next()) {
                int orderId = ordersResultSet.getInt("order_id");
                Date orderDate = ordersResultSet.getDate("order_date");
                findOrder = new Order(orderId, orderDate, userId, songId);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }
        return findOrder;
    }
}
