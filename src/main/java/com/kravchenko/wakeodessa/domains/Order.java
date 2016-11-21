package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Egor on 25.10.2016.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private int orderId;

    @Column
    private Date date;

    /*@OneToMany(mappedBy = "order")
    private List<Product> orderProducts;*/

    @ManyToOne
    @JoinColumn(name = "login")
    private User user;


    private String token;

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
