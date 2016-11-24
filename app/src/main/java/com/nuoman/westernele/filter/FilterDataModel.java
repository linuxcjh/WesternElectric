package com.nuoman.westernele.filter;

import java.util.List;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:55
 */
public class FilterDataModel {

    private List<FilterItemDataModel> filter;
    private List<SelectItemModel> sorter;

    public List<FilterItemDataModel> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterItemDataModel> filter) {
        this.filter = filter;
    }

    public List<SelectItemModel> getSorter() {
        return sorter;
    }

    public void setSorter(List<SelectItemModel> sorter) {
        this.sorter = sorter;
    }
}
