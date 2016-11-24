package com.nuoman.westernele.filter;

import java.util.List;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:55
 */
public class FilterItemDataModel {

    private String filterText;
    private String filterKey;
    private String isMultiSelect;//0 单选 1 多选
    private List<SelectItemModel> filterItems;

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public String getIsMultiSelect() {
        return isMultiSelect;
    }

    public void setIsMultiSelect(String isMultiSelect) {
        this.isMultiSelect = isMultiSelect;
    }

    public List<SelectItemModel> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<SelectItemModel> filterItems) {
        this.filterItems = filterItems;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }
}
