package com.ghargharbazaar.easykonnect.model;

public class GSTModel {
    String percentage, taxable, cgst, sgst, total;

    public GSTModel(String percentage, String taxable, String cgst, String sgst, String total) {
        this.percentage = percentage;
        this.taxable = taxable;
        this.cgst = cgst;
        this.sgst = sgst;
        this.total = total;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
