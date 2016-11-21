package com.kravchenko.wakeodessa.domains;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Egor on 18.10.2016.
 */
@Entity
@Table(name = "brand")
public class Brand implements Serializable {
    @Id
    @GeneratedValue
    private int brand_id;

    @Column
    private String info;

    @Column
    private String brandName;

    @Column(length = 65535)
    private byte[] image;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public Brand() {
    }

    public String getInfo() {
        return info;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int id) {
        this.brand_id = id;
    }

    @Override
    public String toString() {
        return brandName;
    }
}



