package com.sampatparas.starprinterutility.model;

import java.io.Serializable;

/**
 * Created by kajol on 09-Oct-18.
 */

public class SearchResultInfo implements Serializable {

    private String modelName;
    private String portNumber;
    private String macAddress;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}