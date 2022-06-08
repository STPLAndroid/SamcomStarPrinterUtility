package com.stpl.starmicronicsprinterlibrary.interfaces;


import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo;

import java.util.List;

public interface PrinterListCallBack {
    void onSuccessSearchResult(List<SearchResultInfo> result);
    void onFailSearchResult(String message);
}