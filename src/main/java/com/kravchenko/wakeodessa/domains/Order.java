package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Egor on 25.10.2016.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column
    private Date date;

    @OneToMany(mappedBy = "orderId")
    private List<OrderContent> orderProducts;

    @ManyToOne
    @JoinColumn(name = "login")
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", orderProducts=" + orderProducts +
                ", user=" + user +
                ", mobile=" + mobile +
                ", adress='" + adress + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Column
    private String mobile;

    @Column
    private String adress;

    private String token;

    public List<OrderContent> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderContent> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public Order() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }
*/
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
