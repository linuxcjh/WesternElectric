package com.nuoman.westernele.filter;

import java.util.List;

public interface OnFilterSelectedListener {
        void obtainDoubleSelectedResult(List<TransFilterItemDataModel> resultList);
        void obtainSingleFilterSelectedResult(SelectItemModel resultModel);
    }