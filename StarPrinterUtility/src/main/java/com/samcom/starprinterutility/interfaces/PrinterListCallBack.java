package com.samcom.starprinterutility.interfaces;


import com.samcom.starprinterutility.model.SearchResultInfo;

import java.util.List;

public interface PrinterListCallBack {
    void onSuccessSearchResult(List<SearchResultInfo> result);
    void onFlailedResult(String message);
}
