package com.ghargharbazaar.easykonnect.model;

import java.util.ArrayList;

public class TaggedProductsModel {

    String idpackage_prod_list, package_item_qty, idbrand, idproduct_master, idcategory, idsub_category, idsub_sub_category, prod_name, description, barcode, hsn, sgst, cgst, is_veg, status, quantity, idinventory, selling_price, mrp, discount, idproduct_batch;

    public TaggedProductsModel(String idpackage_prod_list, String package_item_qty, String idbrand, String idproduct_master, String idcategory, String idsub_category, String idsub_sub_category, String prod_name, String description, String barcode, String hsn, String sgst, String cgst, String is_veg, String status, String quantity, String idinventory, String selling_price, String mrp, String discount, String idproduct_batch) {
        this.idpackage_prod_list = idpackage_prod_list;
        this.package_item_qty = package_item_qty;
        this.idbrand = idbrand;
        this.idproduct_master = idproduct_master;
        this.idcategory = idcategory;
        this.idsub_category = idsub_category;
        this.idsub_sub_category = idsub_sub_category;
        this.prod_name = prod_name;
        this.description = description;
        this.barcode = barcode;
        this.hsn = hsn;
        this.sgst = sgst;
        this.cgst = cgst;
        this.is_veg = is_veg;
        this.status = status;
        this.quantity = quantity;
        this.idinventory = idinventory;
        this.selling_price = selling_price;
        this.mrp = mrp;
        this.discount = discount;
        this.idproduct_batch = idproduct_batch;
    }

    public String getIdpackage_prod_list() {
        return idpackage_prod_list;
    }

    public void setIdpackage_prod_list(String idpackage_prod_list) {
        this.idpackage_prod_list = idpackage_prod_list;
    }

    public String getPackage_item_qty() {
        return package_item_qty;
    }

    public void setPackage_item_qty(String package_item_qty) {
        this.package_item_qty = package_item_qty;
    }

    public String getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(String idbrand) {
        this.idbrand = idbrand;
    }

    public String getIdproduct_master() {
        return idproduct_master;
    }

    public void setIdproduct_master(String idproduct_master) {
        this.idproduct_master = idproduct_master;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getIdsub_category() {
        return idsub_category;
    }

    public void setIdsub_category(String idsub_category) {
        this.idsub_category = idsub_category;
    }

    public String getIdsub_sub_category() {
        return idsub_sub_category;
    }

    public void setIdsub_sub_category(String idsub_sub_category) {
        this.idsub_sub_category = idsub_sub_category;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getIs_veg() {
        return is_veg;
    }

    public void setIs_veg(String is_veg) {
        this.is_veg = is_veg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIdinventory() {
        return idinventory;
    }

    public void setIdinventory(String idinventory) {
        this.idinventory = idinventory;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIdproduct_batch() {
        return idproduct_batch;
    }

    public void setIdproduct_batch(String idproduct_batch) {
        this.idproduct_batch = idproduct_batch;
    }
}
