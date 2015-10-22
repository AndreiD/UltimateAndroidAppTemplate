package com.andrei.template.models;


import java.util.HashMap;
import java.util.Map;

public class WhatsMyIpPOJO {

    private String YourFuckingIPAddress;
    private String YourFuckingLocation;
    private String YourFuckingHostname;
    private String YourFuckingISP;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The YourFuckingIPAddress
     */
    public String getYourFuckingIPAddress() {
        return YourFuckingIPAddress;
    }

    /**
     * @param YourFuckingIPAddress The YourFuckingIPAddress
     */
    public void setYourFuckingIPAddress(String YourFuckingIPAddress) {
        this.YourFuckingIPAddress = YourFuckingIPAddress;
    }

    /**
     * @return The YourFuckingLocation
     */
    public String getYourFuckingLocation() {
        return YourFuckingLocation;
    }

    /**
     * @param YourFuckingLocation The YourFuckingLocation
     */
    public void setYourFuckingLocation(String YourFuckingLocation) {
        this.YourFuckingLocation = YourFuckingLocation;
    }

    /**
     * @return The YourFuckingHostname
     */
    public String getYourFuckingHostname() {
        return YourFuckingHostname;
    }

    /**
     * @param YourFuckingHostname The YourFuckingHostname
     */
    public void setYourFuckingHostname(String YourFuckingHostname) {
        this.YourFuckingHostname = YourFuckingHostname;
    }

    /**
     * @return The YourFuckingISP
     */
    public String getYourFuckingISP() {
        return YourFuckingISP;
    }

    /**
     * @param YourFuckingISP The YourFuckingISP
     */
    public void setYourFuckingISP(String YourFuckingISP) {
        this.YourFuckingISP = YourFuckingISP;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @Override public String toString() {
        return this.YourFuckingIPAddress + " " + this.YourFuckingLocation + " " + this.YourFuckingHostname + " " + this.YourFuckingISP;
    }
}