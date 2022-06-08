package com.sampatparas.starprinterutility.interfaces;


import com.sampatparas.starprinterutility.model.SearchResultInfo;

import java.util.List;

public interface PrinterListCallBack {
    void onSuccessSearchResult(List<SearchResultInfo> result);
    void onFlailedResult(String message);
}
