package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Egor on 25.10.2016.
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @Column
    private int code;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String productName;

    @Column
    private String category;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @Column(length = 65535)
    private byte[] image;



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", code=" + code +
                ", price='" + price + '\'' +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (code != product.code) return false;
        if (!productName.equals(product.productName)) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        return brand != null ? brand.equals(product.brand) : product.brand == null;

    }


}

