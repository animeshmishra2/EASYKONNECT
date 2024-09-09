package com.ghargharbazaar.easykonnect.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CounterModel implements Serializable {
    String idcounter, idstore_warehouse, name, live_status, created_at, updated_at, created_by, updated_by, status;
    LastLoginModel lastLoginModels;

    public CounterModel(String idcounter, String idstore_warehouse, String name, String live_status, String created_at, String updated_at, String created_by, String updated_by, String status, LastLoginModel lastLoginModels) {
        this.idcounter = idcounter;
        this.idstore_warehouse = idstore_warehouse;
        this.name = name;
        this.live_status = live_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.status = status;
        this.lastLoginModels = lastLoginModels;
    }

    public String getIdcounter() {
        return idcounter;
    }

    public void setIdcounter(String idcounter) {
        this.idcounter = idcounter;
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

    public String getLive_status() {
        return live_status;
    }

    public void setLive_status(String live_status) {
        this.live_status = live_status;
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

    public LastLoginModel getLastLoginModels() {
        return lastLoginModels;
    }

    public void setLastLoginModels(LastLoginModel lastLoginModels) {
        this.lastLoginModels = lastLoginModels;
    }
}
