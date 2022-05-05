package com.stpl.starmicronicsprinterlibrary.searchPrinter;

import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_ALL;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_BLUETOOTH;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_ETHERNET;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_USB;

import android.app.Activity;
import android.os.AsyncTask;

import com.stpl.starmicronicsprinterlibrary.Utils.Utils;
import com.stpl.starmicronicsprinterlibrary.interfaces.PrinterListCallBack;
import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo;
import com.starmicronics.stario.PortInfo;
import com.starmicronics.stario.StarIOPort;
import com.starmicronics.stario.StarIOPortException;

import java.util.ArrayList;
import java.util.List;

public class SearchPrinterUtils {
    int count = 0;
    Activity activity;
    PrinterListCallBack printerTypeCallBack;
    private List<SearchResultInfo> searchResultArray = new ArrayList<>();

    public SearchPrinterUtils(Activity activity, PrinterListCallBack printerTypeCallBack) {
        this.activity = activity;
        this.printerTypeCallBack = printerTypeCallBack;
    }

    public void startSearchToLan() {
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_ETHERNET);
    }

    public void startSearchToBluetooth() {
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_BLUETOOTH);
    }

    public void startSearchToUsb() {
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_USB);
    }


    public void startSearchAll() {
        count = 0;
        searchResultArray.clear();
        String[] str = new String[]{IF_TYPE_ETHERNET, IF_TYPE_BLUETOOTH, IF_TYPE_USB};
        for (String s : str) {
            SearchTaskAll searchTask = new SearchTaskAll();
            searchTask.execute(s);
        }
    }

    private class SearchTask extends AsyncTask<String, Void, Void> {
        private List<PortInfo> mPortList;

        SearchTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.pDialog(activity);
        }

        @Override
        protected Void doInBackground(String... interfaceType) {
            try {
                mPortList = StarIOPort.searchPrinter(interfaceType[0], activity);
            } catch (StarIOPortException e) {
                mPortList = new ArrayList<>();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void doNotUse) {
            Utils.pDialogDismiss();
            for (PortInfo info : mPortList) {
                addItem(info);
            }
            if (searchResultArray.isEmpty()) {
                printerTypeCallBack.onFailureResult("no item found");
            } else {
                printerTypeCallBack.onSuccessSearchResult(searchResultArray);
            }
        }

    }

    private class SearchTaskAll extends AsyncTask<String, Void, Void> {
        private List<PortInfo> mPortList;

        SearchTaskAll() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.pDialog(activity);
        }

        @Override
        protected Void doInBackground(String... interfaceType) {
            try {
                mPortList = StarIOPort.searchPrinter(interfaceType[0], activity);
            } catch (StarIOPortException e) {
                mPortList = new ArrayList<>();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void doNotUse) {
            count++;
            Utils.pDialogDismiss();
            for (PortInfo info : mPortList) {
                addItem(info);
            }
            if (count == 3) {
                if (searchResultArray.isEmpty()) {
                    printerTypeCallBack.onFailureResult("no item found");
                } else {
                    printerTypeCallBack.onSuccessSearchResult(searchResultArray);
                }
            }
        }
    }

    private void addItem(PortInfo info) {
        try {
            String modelName;
            String portName;
            String macAddress;

            if (info.getPortName().startsWith(IF_TYPE_BLUETOOTH)) {
                modelName = info.getPortName().substring(IF_TYPE_BLUETOOTH.length());
                portName = IF_TYPE_BLUETOOTH + info.getMacAddress();
                macAddress = info.getMacAddress();
            } else {
                modelName = info.getModelName();
                portName = info.getPortName();
                macAddress = info.getMacAddress();
            }

            SearchResultInfo searchResultInfo = new SearchResultInfo();
            searchResultInfo.setModelName(modelName);
            searchResultInfo.setPortNumber(portName);
            searchResultInfo.setMacAddress(macAddress);
            searchResultArray.add(searchResultInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void searchPrinter(String type) {
        if (type.equalsIgnoreCase(IF_TYPE_ETHERNET)) {
            startSearchToLan();
        } else if (type.equalsIgnoreCase(IF_TYPE_BLUETOOTH)) {
            startSearchToBluetooth();
        }
        else if (type.equalsIgnoreCase(IF_TYPE_USB)) {
            startSearchToUsb();
        }
        else if (type.equalsIgnoreCase(IF_TYPE_ALL)) {
            startSearchAll();
        }
    }
}

