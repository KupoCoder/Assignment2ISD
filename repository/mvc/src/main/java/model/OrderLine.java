
package model;

import java.sql.Timestamp;

public class OrderLine {
    private String orderlineID;
    private String orderID;
    private String deviceID;
    private Timestamp orderlineDateAdded;
    private int orderlineQuantity;

    public String getOrderLineID() {
        return orderlineID;
    }

    public void setOrderLineID(String orderlineID) {
        this.orderlineID = orderlineID;
    }
    
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Timestamp getOrderlineDateAdded() {
        return orderlineDateAdded;
    }

    public void setOrderlineDateAdded(Timestamp orderlineDateAdded) {
        this.orderlineDateAdded = orderlineDateAdded;
    }

    public int getOrderlineQuantity() {
        return orderlineQuantity;
    }

    public void setOrderlineQuantity(int orderlineQuantity) {
        this.orderlineQuantity = orderlineQuantity;
    }
}
