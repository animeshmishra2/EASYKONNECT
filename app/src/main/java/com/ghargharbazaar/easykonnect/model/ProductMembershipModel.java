package com.ghargharbazaar.easykonnect.model;

import java.io.Serializable;

public class ProductMembershipModel implements Serializable {
    String name, price, instant_discount, type;

    public ProductMembershipModel(String name, String price, String instant_discount, String type) {
        this.name = name;
        this.price = price;
        this.instant_discount = instant_discount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInstant_discount() {
        return instant_discount;
    }

    public void setInstant_discount(String instant_discount) {
        this.instant_discount = instant_discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
