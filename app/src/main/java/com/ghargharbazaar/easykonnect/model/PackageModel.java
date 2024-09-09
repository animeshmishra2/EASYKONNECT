package com.ghargharbazaar.easykonnect.model;

import java.util.ArrayList;

public class PackageModel {
    String idpackage, idpackage_master, idstore_warehouse, name, applicable_on, frequency, base_trigger_amount, additional_tag_amount, bypass_make_gen, valid_from, valid_till, created_at, updated_at, created_by, updated_by, status;

    ArrayList<TaggedProductsModel> taggedProductsModels;
    ArrayList<TriggeredProductModel> triggeredProductModels;


    public PackageModel(String idpackage, String idpackage_master, String idstore_warehouse, String name, String applicable_on, String frequency, String base_trigger_amount, String additional_tag_amount, String bypass_make_gen, String valid_from, String valid_till, String created_at, String updated_at, String created_by, String updated_by, String status, ArrayList<TaggedProductsModel> taggedProductsModels, ArrayList<TriggeredProductModel> triggeredProductModels) {
        this.idpackage = idpackage;
        this.idpackage_master = idpackage_master;
        this.idstore_warehouse = idstore_warehouse;
        this.name = name;
        this.applicable_on = applicable_on;
        this.frequency = frequency;
        this.base_trigger_amount = base_trigger_amount;
        this.additional_tag_amount = additional_tag_amount;
        this.bypass_make_gen = bypass_make_gen;
        this.valid_from = valid_from;
        this.valid_till = valid_till;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.status = status;
        this.taggedProductsModels = taggedProductsModels;
        this.triggeredProductModels = triggeredProductModels;
    }

    public String getIdpackage() {
        return idpackage;
    }

    public void setIdpackage(String idpackage) {
        this.idpackage = idpackage;
    }

    public String getIdpackage_master() {
        return idpackage_master;
    }

    public void setIdpackage_master(String idpackage_master) {
        this.idpackage_master = idpackage_master;
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

    public String getApplicable_on() {
        return applicable_on;
    }

    public void setApplicable_on(String applicable_on) {
        this.applicable_on = applicable_on;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBase_trigger_amount() {
        return base_trigger_amount;
    }

    public void setBase_trigger_amount(String base_trigger_amount) {
        this.base_trigger_amount = base_trigger_amount;
    }

    public String getAdditional_tag_amount() {
        return additional_tag_amount;
    }

    public void setAdditional_tag_amount(String additional_tag_amount) {
        this.additional_tag_amount = additional_tag_amount;
    }

    public String getBypass_make_gen() {
        return bypass_make_gen;
    }

    public void setBypass_make_gen(String bypass_make_gen) {
        this.bypass_make_gen = bypass_make_gen;
    }

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    public String getValid_till() {
        return valid_till;
    }

    public void setValid_till(String valid_till) {
        this.valid_till = valid_till;
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

    public ArrayList<TaggedProductsModel> getTaggedProductsModels() {
        return taggedProductsModels;
    }

    public void setTaggedProductsModels(ArrayList<TaggedProductsModel> taggedProductsModels) {
        this.taggedProductsModels = taggedProductsModels;
    }

    public ArrayList<TriggeredProductModel> getTriggeredProductModels() {
        return triggeredProductModels;
    }

    public void setTriggeredProductModels(ArrayList<TriggeredProductModel> triggeredProductModels) {
        this.triggeredProductModels = triggeredProductModels;
    }
}
