package com.cmpe277.project.zeusrealty.model;

import java.util.List;

public class PropertyListingAPIResponseContainer {
    List<PropertyListingAPIResponse> data;
    Meta meta;

    public List<PropertyListingAPIResponse> getData() {
        return data;
    }

    public void setData(List<PropertyListingAPIResponse> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public PropertyListingAPIResponseContainer(List<PropertyListingAPIResponse> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }
}
class Meta{
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Meta(int page) {
        this.page = page;
    }
}
