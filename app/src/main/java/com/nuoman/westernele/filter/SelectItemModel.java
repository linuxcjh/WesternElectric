package com.nuoman.westernele.filter;

public class SelectItemModel {
    private String itemText;
    private String itemValue;
    private String order;

    public SelectItemModel(String itemValue, String itemText) {
        this.itemValue = itemValue;
        this.itemText = itemText;
    }
    public SelectItemModel() {
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}