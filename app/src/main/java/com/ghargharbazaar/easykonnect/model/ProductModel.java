package com.ghargharbazaar.easykonnect.model;


import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable {
    private String idbrand, brand, idproduct_master, idcategory, category, idsub_category, scategory, idsub_sub_category, sscategory, prod_name, description, barcode, hsn, sgst, cgst, igst, status, quantity, idinventory, selling_price, purchase_price, mrp, product, copartner, land, discount, instant_discount_percent, listing_type, origListType, sellingPriceForInstantDisc;
    ArrayList<BatchModel> batches;
    ArrayList<ProductMembershipModel> productMembershipModels;
    BatchModel selected_batch;
    String instant;
    int sel;

    public ProductModel(String idbrand, String brand, String idproduct_master, String idcategory, String category, String idsub_category, String scategory, String idsub_sub_category, String sscategory, String prod_name, String description, String barcode, String hsn, String sgst, String cgst, String igst, String status, String quantity, String idinventory, String selling_price, String purchase_price, String mrp, String product, String copartner, String land, String discount, String instant_discount_percent, String listing_type, String origListType, String sellingPriceForInstantDisc, ArrayList<BatchModel> batches, ArrayList<ProductMembershipModel> productMembershipModels, BatchModel selected_batch, String instant, int sel) {
        this.idbrand = idbrand;
        this.brand = brand;
        this.idproduct_master = idproduct_master;
        this.idcategory = idcategory;
        this.category = category;
        this.idsub_category = idsub_category;
        this.scategory = scategory;
        this.idsub_sub_category = idsub_sub_category;
        this.sscategory = sscategory;
        this.prod_name = prod_name;
        this.description = description;
        this.barcode = barcode;
        this.hsn = hsn;
        this.sgst = sgst;
        this.cgst = cgst;
        this.igst = igst;
        this.status = status;
        this.quantity = quantity;
        this.idinventory = idinventory;
        this.selling_price = selling_price;
        this.purchase_price = purchase_price;
        this.mrp = mrp;
        this.product = product;
        this.copartner = copartner;
        this.land = land;
        this.discount = discount;
        this.instant_discount_percent = instant_discount_percent;
        this.listing_type = listing_type;
        this.origListType = origListType;
        this.sellingPriceForInstantDisc = sellingPriceForInstantDisc;
        this.batches = batches;
        this.productMembershipModels = productMembershipModels;
        this.selected_batch = selected_batch;
        this.instant = instant;
        this.sel = sel;
    }

    public String getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(String idbrand) {
        this.idbrand = idbrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdsub_category() {
        return idsub_category;
    }

    public void setIdsub_category(String idsub_category) {
        this.idsub_category = idsub_category;
    }

    public String getScategory() {
        return scategory;
    }

    public void setScategory(String scategory) {
        this.scategory = scategory;
    }

    public String getIdsub_sub_category() {
        return idsub_sub_category;
    }

    public void setIdsub_sub_category(String idsub_sub_category) {
        this.idsub_sub_category = idsub_sub_category;
    }

    public String getSscategory() {
        return sscategory;
    }

    public void setSscategory(String sscategory) {
        this.sscategory = sscategory;
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

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
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

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
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

    public String getInstant_discount_percent() {
        return instant_discount_percent;
    }

    public void setInstant_discount_percent(String instant_discount_percent) {
        this.instant_discount_percent = instant_discount_percent;
    }

    public String getListing_type() {
        return listing_type;
    }

    public void setListing_type(String listing_type) {
        this.listing_type = listing_type;
    }

    public String getOrigListType() {
        return origListType;
    }

    public void setOrigListType(String origListType) {
        this.origListType = origListType;
    }

    public String getSellingPriceForInstantDisc() {
        return sellingPriceForInstantDisc;
    }

    public void setSellingPriceForInstantDisc(String sellingPriceForInstantDisc) {
        this.sellingPriceForInstantDisc = sellingPriceForInstantDisc;
    }

    public ArrayList<BatchModel> getBatches() {
        return batches;
    }

    public void setBatches(ArrayList<BatchModel> batches) {
        this.batches = batches;
    }

    public ArrayList<ProductMembershipModel> getProductMembershipModels() {
        return productMembershipModels;
    }

    public void setProductMembershipModels(ArrayList<ProductMembershipModel> productMembershipModels) {
        this.productMembershipModels = productMembershipModels;
    }

    public BatchModel getSelected_batch() {
        return selected_batch;
    }

    public void setSelected_batch(BatchModel selected_batch) {
        this.selected_batch = selected_batch;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

    public int getSel() {
        return sel;
    }

    public void setSel(int sel) {
        this.sel = sel;
    }
}
