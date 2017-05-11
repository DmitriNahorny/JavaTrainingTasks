package by.nahorny.mvc.entity;

import java.util.Date;

/**
 * Created by Dmitri_Nahorny on 4/27/2017.
 */
public class Order extends BusinessEntity {
    private int orderId;
    private Date orderDate;
    private int userId;
    private int songId;

    public Order(int orderId, Date orderDate, int userId, int songId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.songId = songId;
    }

    public Order(Date orderDate, int userId, int songId) {
        this.orderDate = orderDate;
        this.userId = userId;
        this.songId = songId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
