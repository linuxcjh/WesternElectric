package com.nuoman.westernele.filter;

import java.util.List;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:55
 */
public class TransFilterItemDataModel {

    private String filterKey;
    private List<SelectItemModel> filterValues;

    public List<SelectItemModel> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(List<SelectItemModel> filterValues) {
        this.filterValues = filterValues;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }
}
