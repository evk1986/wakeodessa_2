package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Egor on 30.11.2016.
 */
@Entity
@Table(name = "order_content")
public class OrderContent implements Serializable {
    public OrderContent() {
    }

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order orderId;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return product.getProductName();
       /* return "OrderContent{" +
                "id=" + id +
                ", orderId=" + orderId.getOrderId() +
                ", product=" + product.getProductName() +
                '}';*/
    }
}
