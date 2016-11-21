package com.kravchenko.wakeodessa.domains;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Егор on 03.11.2016.
 */
public class Brands implements Serializable
{

    private List<Brand> brands;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brands=" + brands +
                '}';
    }
}
