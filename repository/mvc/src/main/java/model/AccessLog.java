package model;

import java.sql.Timestamp;


public class AccessLog {
    private String logID;
    private String customerID;
    private String staffID;
    private Timestamp logLogin;
    private Timestamp logLogout;

    
    public AccessLog(String logID, String customerID,String staffID,   Timestamp logLogin,  Timestamp logLogout){
        this.logID = logID; 
        this.customerID = customerID; 
        this.staffID = staffID; 
        this.logLogin = logLogin;
        this.logLogout = logLogout; 
              
    }
    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Timestamp getLogLogin() {
        return logLogin;
    }

    public void setLogLogin(Timestamp logLogin) {
        this.logLogin = logLogin;
    }

    public Timestamp getLogLogout() {
        return logLogout;
    }

    public void setLogLogout(Timestamp logLogout) {
        this.logLogout = logLogout;
    }
}