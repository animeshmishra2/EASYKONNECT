package com.ghargharbazaar.easykonnect.model;

import java.io.Serializable;

public class BatchModel implements Serializable {
    String idproduct_batch, idproduct_master, idstore_warehouse, name, purchase_price, selling_price, mrp, product, copartner, land, discount, quantity, expiry, created_at, updated_at, created_by, updated_by, status, instant;


    public BatchModel(String idproduct_batch, String idproduct_master, String idstore_warehouse, String name, String purchase_price, String selling_price, String mrp, String product, String copartner, String land, String discount, String quantity, String expiry, String created_at, String updated_at, String created_by, String updated_by, String status, String instant) {
        this.idproduct_batch = idproduct_batch;
        this.idproduct_master = idproduct_master;
        this.idstore_warehouse = idstore_warehouse;
        this.name = name;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
        this.mrp = mrp;
        this.product = product;
        this.copartner = copartner;
        this.land = land;
        this.discount = discount;
        this.quantity = quantity;
        this.expiry = expiry;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.status = status;
        this.instant = instant;
    }

    public String getIdproduct_batch() {
        return idproduct_batch;
    }

    public void setIdproduct_batch(String idproduct_batch) {
        this.idproduct_batch = idproduct_batch;
    }

    public String getIdproduct_master() {
        return idproduct_master;
    }

    public void setIdproduct_master(String idproduct_master) {
        this.idproduct_master = idproduct_master;
    }

    public String getIdstore_warehouse() {
        return idstore_warehouse;
    }

    public void setIdstore_warehouse(String idstore_warehouse) {
        this.idstore_warehouse = idstore_warehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCopartner() {
        return copartner;
    }

    public void setCopartner(String copartner) {
        this.copartner = copartner;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }
}
