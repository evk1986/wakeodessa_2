package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Egor on 25.10.2016.
 */
@Entity
@Table(name = "storage")
public class Storage implements Serializable {


    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column
    private int count;

    public Storage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "product=" + product.getProductName() +
                ", count=" + count +
                '}';
    }
}
