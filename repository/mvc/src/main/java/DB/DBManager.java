/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Admin;
import model.Customer;
import model.Staff;
import model.AccessLog;
import model.Device;


public class DBManager {

    private final Statement st;

    private final Connection connect;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();

        connect = conn;
    }
    
   
    public Customer findCustomer(String EmailOrUsername, String password) throws SQLException {

        String query1= "select * from CUSTOMER_T where CUSTOMEREMAIL= ? and  CUSTOMERPASSWORD= ?";
        String query2= "select * from CUSTOMER_T where CUSTOMERUSERNAME=? and CUSTOMERPASSWORD= ?";
       
        try (PreparedStatement statement = connect.prepareStatement(query1)) {
            statement.setString(1, EmailOrUsername);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String customerEmail = rs.getString(5);  //Do not change the relative position of Customer columns
                String customerPassWord = rs.getString(12); //if you do we need to change this
                if (customerEmail.equals(EmailOrUsername) && customerPassWord.equals(password)) {
                    return new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                }
            }
        } try (PreparedStatement statement = connect.prepareStatement(query2)) {
            statement.setString(1, EmailOrUsername);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String customerUsername = rs.getString(11); 
                String customerPassWord = rs.getString(12); 
                if (customerUsername.equals(EmailOrUsername) && customerPassWord.equals(password)) {
                    return new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                }
            }
        }
        return null;
    }
    
    public ArrayList<Customer> searchCustomer(String Input) throws SQLException{
        String[] querys = {
            "SELECT * FROM CUSTOMER_T WHERE LOWER(CUSTOMERFIRSTNAME) LIKE ?",
            "SELECT * FROM CUSTOMER_T WHERE LOWER(CUSTOMERLASTNAME) LIKE ?",
            "SELECT * FROM CUSTOMER_T WHERE LOWER(CUSTOMERUSERNAME) LIKE ?",
            "SELECT * FROM CUSTOMER_T WHERE LOWER(CUSTOMERID) LIKE ?"

        };
        
        ArrayList<Customer> SearchResult = new ArrayList<>();
        
        if(Input != null){
            for(String query : querys){
                try(PreparedStatement statement = connect.prepareStatement(query)){
                    statement.setString(1, "%" + Input.toLowerCase() + "%");
                    ResultSet rs = statement.executeQuery();
                    while(rs.next()){
                        SearchResult.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
                    }
                }
            }
        } else {
            return fetchCustomers();
        }
        
        return SearchResult;
    }
    
    
    public Customer findCustomerID(String ID) throws SQLException{
        String query = "SELECT * FROM CUSTOMER_T WHERE CUSTOMERID = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, ID );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(ID)){
                    return new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                }
            }
        }
        return null;
    }

    public void addCustomer(String firstName, String lastName, String DOB, String email, String phoneNum, String street, String suburb, String state, String postCode, String userName, String passWord) throws SQLException {

        String customerID = nextCustomerID();
        //Secure databse entry statement 
        String query = "INSERT INTO ISDUSER.CUSTOMER_T VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, customerID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, DOB);
            statement.setString(5, email);
            statement.setString(6, phoneNum);
            statement.setString(7, street);
            statement.setString(8, suburb);
            statement.setString(9, state);
            statement.setString(10, postCode);
            statement.setString(11, userName);
            statement.setString(12, passWord);
            statement.executeUpdate();
        }
    }
    
    private String nextCustomerID() throws SQLException {
        ResultSet maxIdRs = st.executeQuery("SELECT MAX(CUSTOMERID) FROM CUSTOMER_T");
        int IDLEN = 15;
        BigInteger freshID;
        String LOGID = null;

        if (maxIdRs.next()) {
            freshID = new BigInteger(maxIdRs.getString(1));
            freshID = freshID.add(BigInteger.ONE);
            LOGID = freshID.toString();
        } else {
            LOGID = "1";
        }
        // Add a bunch of zeros
        while (LOGID.length() < IDLEN) {
            LOGID += "0";
        }
        
        return LOGID;
    }
    
    public void updateCustomer(String customerId, String firstName, String lastName, String DOB, String customerEmail, String phoneNum, String street, String suburb, String postCode, String userName, String customerPassWord) throws SQLException {

        String query = "UPDATE CUSTOMER_T SET CUSTOMERFIRSTNAME=?, CUSTOMERLASTNAME=?, CUSTOMERDOB=?, CUSTOMEREMAIL=?, CUSTOMERPHONENUMBER=?, CUSTOMERSTREET=?, CUSTOMERSUBURB=?, CUSTOMERPOSTCODE=?, CUSTOMERUSERNAME=?, CUSTOMERPASSWORD=? WHERE CUSTOMERID = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, DOB);
            statement.setString(4, customerEmail);
            statement.setString(5, phoneNum);
            statement.setString(6, street);
            statement.setString(7, suburb);
            statement.setString(8, postCode);
            statement.setString(9, userName);
            statement.setString(10, customerPassWord);
            statement.setString(11, customerId);
            statement.executeUpdate();
        }
        //backup statement bellow 
        //st.executeUpdate("UPDATE ISDUSER.CUSTOMER_T SET CUSTOMERFIRSTNAME='"+ firstName+"',CUSTOMERLASTNAME='"+lastName+"',CUSTOMERDOB='"+DOB+ "',CUSTOMEREMAIL='"+customerEmail+"',CUSTOMERPHONENUMBER='"+phoneNum+"',CUSTOMERSTREET='"+street+"',CUSTOMERSUBURB='"+suburb+"',CUSTOMERPOSTCODE='"+postCode+"',CUSTOMERUSERNAME='"+userName+"',CUSTOMERPASSWORD='"+customerPassWord+"' WHERE CUSTOMERID = '"+customerId+ "'");
    }

    public void deleteCustomer(String customerId) throws SQLException {
        String query = "DELETE FROM ISDUSER.CUSTOMER_T WHERE CUSTOMERID=?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, customerId);
            statement.executeUpdate();
        }
        //backup statement 
        //st.executeUpdate("'DELETE FROM ISDUSER.CUSTOMER_T WHERE CUSTOMERID='" + customerId + "'");
    }

    public ArrayList<Customer> fetchCustomers() throws SQLException {
        String qurey = "SELECT * FROM CUSTOMER_T";
        ResultSet rs = st.executeQuery(qurey);

        ArrayList<Customer> allCustomers = new ArrayList<>();

        while (rs.next()) {
            String customerId = rs.getString(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String DOB = rs.getString(4);
            String email = rs.getString(5);
            String phoneNum = rs.getString(6);
            String street = rs.getString(7);
            String suburb = rs.getString(8);
            String state = rs.getString(9);
            String postCode = rs.getString(10);
            String userName = rs.getString(11);
            String password = rs.getString(12);

            allCustomers.add(new Customer(customerId, firstName, lastName, DOB, email, phoneNum, street, suburb, state, postCode, userName, password));

        }
        if (!allCustomers.isEmpty()) {
            return allCustomers;
        } else {
            return null;
        }
    }

   
    public Staff findStaff(String EmailOrUsername, String password) throws SQLException {
        String query1 = "SELECT * FROM ISDUSER.STAFF_T WHERE STAFFEMAIL =? and STAFFPASSWORD =?";
        String query2 = "SELECT * FROM ISDUSER.STAFF_T WHERE STAFFUSERNAME =? and STAFFPASSWORD =?";
        try (PreparedStatement statement = connect.prepareStatement(query1)) {
            statement.setString(1, EmailOrUsername);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                //where4 and 6 refer to email and password in staff_t
                
                if (rs.getString(4).equals(EmailOrUsername) && rs.getString(7).equals(password)) { 
                    return new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
                }
            }
        }
        try (PreparedStatement statement = connect.prepareStatement(query2)) {
            statement.setString(1, EmailOrUsername);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String username = rs.getString(6);
                String passowrd = rs.getString(7);
                //where5 and 6 refer to username and password in staff_t
                if (username.equals(EmailOrUsername) && passowrd.equals(password)) { 
                    return new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }
            }
        }
        return null;
    }
    
    public ArrayList<Staff> searchStaff(String Input) throws SQLException{
        String[] querys = {
            "SELECT * FROM STAFF_T WHERE LOWER(STAFFFIRSTNAME) LIKE ?",
            "SELECT * FROM STAFF_T WHERE LOWER(STAFFLASTNAME) LIKE ?",
            "SELECT * FROM STAFF_T WHERE LOWER(STAFFUSERNAME) LIKE ?",
            "SELECT * FROM STAFF_T WHERE LOWER(STAFFID) LIKE ?"

        };
        
        ArrayList<Staff> SearchResult = new ArrayList<>();
        
        if(Input != null){
            for(String query : querys){
                try(PreparedStatement statement = connect.prepareStatement(query)){
                    statement.setString(1, "%" + Input.toLowerCase() + "%");
                    ResultSet rs = statement.executeQuery();
                    while(rs.next()){
                        SearchResult.add(new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                    }
                }
            }
        } else {
            return fetchStaff();
        }
        
        return SearchResult;
    }
    
    public Staff findStaffID(String ID) throws SQLException{
        String query = "SELECT * FROM ISDUSER.STAFF_T WHERE STAFFID =?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, ID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(ID)){
                    return new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }
            }
        }
        return null;
    }
    
    public void addStaff(String staffFirstName, String staffLastName, String staffEmail, String staffPosition,String staffUserName, String staffPassword) throws SQLException{
        
        ResultSet maxIdRs = st.executeQuery("SELECT MAX(STAFFID) FROM STAFF_T");
        int IDLEN = 15;
        BigInteger freshID;
        String staffID = null;

        if (maxIdRs.next()) {
            freshID = new BigInteger(maxIdRs.getString(1));
            freshID = freshID.add(BigInteger.ONE);
            staffID = freshID.toString();
        } else {
            staffID = "1";
        }
        while (staffID.length() < IDLEN) {
            staffID += "0";
        }
        
        String qurey = "INSERT INTO STAFF_T VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement statement = connect.prepareStatement(qurey)){
            statement.setString(1, staffID);
            statement.setString(2, staffFirstName);
            statement.setString(3, staffLastName);
            statement.setString(4, staffEmail);
            statement.setString(5, staffPosition);
            statement.setString(6, staffUserName);
            statement.setString(7, staffPassword);
            statement.executeUpdate();
        }
    }

    //untested
    public void updateStaff(String staffID, String staffFirstName, String staffLastName, String staffEmail, String staffPosition,String staffUserName, String staffPassword ) throws SQLException {
        
        String qurey = "UPDATE STAFF_T SET STAFFFIRSTNAME=?, STAFFLASTNAME=?, STAFFEMAIL=?,STAFFPOSITION=?, STAFFUSERNAME=?, STAFFPASSWORD=? WHERE STAFFID =?";
        
        try(PreparedStatement statement = connect.prepareStatement(qurey)){
            statement.setString(1, staffFirstName);
            statement.setString(2, staffLastName);
            statement.setString(3, staffEmail);
            statement.setString(4, staffPosition);
            statement.setString(5, staffUserName);
            statement.setString(6, staffPassword);
            statement.setString(7, staffID);
            statement.executeUpdate();
            System.out.println("Updated");
        }
    }
       
    public void deleteStaff(String staffID) throws SQLException{
        String qurey = "DELETE FROM ISDUSER.STAFF_T WHERE STAFFID=?";
        try(PreparedStatement statement = connect.prepareStatement(qurey)){
            statement.setString(1, staffID);
            statement.executeUpdate();
        }
    }
    
    public ArrayList<Staff> fetchStaff() throws SQLException {
        String query = "SELECT * FROM ISDUSER.STAFF_T";
        ResultSet rs = st.executeQuery(query);

        ArrayList<Staff> allStaff = new ArrayList<>();

        while (rs.next()) {
            allStaff.add(new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        if (!allStaff.isEmpty()) {
            return allStaff;
        } else {
            return null;
        }
    }
    
    
    
    /////// Find Admin ////////
        public Admin findAdmin(String Email, String password) throws SQLException {
        String query1 = "SELECT * FROM ADMIN_T WHERE ADMINEMAIL =? and ADMINPASSWORD =?";
        
        try (PreparedStatement statement = connect.prepareStatement(query1)) {
            statement.setString(1, Email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                //where4 and 6 refer to email and password in staff_t
                if (rs.getString(2).equals(Email) && rs.getString(3).equals(password)) { 
                    return new Admin(rs.getString(1), rs.getString(2), rs.getString(3));
                }
            }
        }

        return null;
    }
        
    public void updateAdmin(String AdminID, String AdminEmail, String AdminPassword) throws SQLException {
        
        String qurey = "UPDATE ISDUSER.ADMIN_T SET  ADMINEMAIL=?, ADMINPASSWORD=? WHERE ADMINID =?";
        
        try(PreparedStatement statement = connect.prepareStatement(qurey)){
            statement.setString(1, AdminEmail);
            statement.setString(2, AdminPassword);
            
            statement.setString(3, AdminID); // there was a bug here where 3 was 7 for some ungodly reason
            statement.executeUpdate();
        }
    }
  
    public ArrayList<AccessLog> findAccessLogs(String ANY, String year, String month) throws SQLException {
        
        ArrayList<AccessLog> result = new ArrayList<>();

        String[] queries = {
            "SELECT * FROM ACCESSLOG_T WHERE STAFFID = ? AND (YEAR(LOGLOGIN) = ? OR ? IS NULL) AND (MONTH(LOGLOGIN) = ? OR ? IS NULL)",
            "SELECT * FROM ACCESSLOG_T WHERE CUSTOMERID = ? AND (YEAR(LOGLOGIN) = ? OR ? IS NULL) AND (MONTH(LOGLOGIN) = ? OR ? IS NULL) ",
            
           
        };

        int yearValue;
        if (year == null || year.isEmpty()) {
            yearValue = 0; 
        } else {
            yearValue = Integer.parseInt(year);
            if (yearValue < 1900 || yearValue > 2023) {
                yearValue = 2023; 
            }
        }
        
        int monthValue;
        if (month == null || month.isEmpty()) {
            monthValue = 0; 
        } else {
            monthValue = Integer.parseInt(month);
            if (monthValue < 1 || monthValue > 12) {
                monthValue = 1; 
            }
        }

        for (String query : queries) {
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, ANY);
                if (year == null || year.isEmpty()){
                    statement.setNull(2, java.sql.Types.INTEGER);
                    statement.setNull(3, java.sql.Types.INTEGER);
                }else{
                    statement.setInt(2, yearValue);
                    statement.setInt(3, yearValue);
                 }
                 if (month == null || month.isEmpty()) {
                    statement.setNull(4, java.sql.Types.INTEGER);
                    statement.setNull(5, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(4, monthValue);
                    statement.setInt(5, monthValue);
                } 
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    result.add(new AccessLog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5)));
                }
               
                  
            }
        }
        return result;
    }
    
    public String addAccessLog(String anyID) throws SQLException{
        Staff checkStaff = findStaffID(anyID);
        Customer checkCust = findCustomerID(anyID);
        
        //get max ID
        String LOGID = nextLogId();
        if(checkStaff != null){
            String query = "INSERT INTO ACCESSLOG_T VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, LOGID);
                statement.setString(2, null);
                statement.setString(3, checkStaff.getStaffID());
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                statement.executeUpdate();
                return LOGID;
            }
        } else if(checkCust != null){
            String query = "INSERT INTO ACCESSLOG_T VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, LOGID);
                statement.setString(2, checkCust.getCustomerId());
                statement.setString(3, null);
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                statement.executeUpdate();
                return LOGID;
            }
        }
            return null;
        
    }
    
    private String nextLogId() throws SQLException {
        ResultSet maxIdRs = st.executeQuery("SELECT MAX(LOGID) FROM ACCESSLOG_T");
        int IDLEN = 15;
        BigInteger freshID;
        String LOGID = null;

        if (maxIdRs.next()) {
            freshID = new BigInteger(maxIdRs.getString(1));
            freshID = freshID.add(BigInteger.ONE);
            LOGID = freshID.toString();
        } else {
            LOGID = "1";
        }
        // Add a bunch of zeros
        while (LOGID.length() < IDLEN) {
            LOGID += "0";
        }
        
        return LOGID;
    }
    
    public void updateAccessLog(String logID) throws SQLException {
        String query = "UPDATE ISDUSER.ACCESSLOG_T SET LOGLOGOUT = ? WHERE LOGID = ?";
        
        try(PreparedStatement statement = connect.prepareStatement(query)){
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setString(2, logID);
            statement.executeUpdate();
        }
    }
    
    public void deleteAccessLog(String logID) throws SQLException{
         String qurey = "DELETE FROM ISDUSER.ACCESSLOG_T WHERE LOGID=?";
        try(PreparedStatement statement = connect.prepareStatement(qurey)){
            statement.setString(1, logID);
            statement.executeUpdate();
        }
    }
    
    public ArrayList<AccessLog> fetchAccessLogs() throws SQLException {
        ArrayList<AccessLog> logs = new ArrayList<>();
        
        String query = "SELECT * FROM ACCESSLOG_T";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            logs.add(new AccessLog(rs.getString(1), rs.getString(2), rs.getString(3),rs.getTimestamp(4), rs.getTimestamp(5)));
        }
        return logs;
    }
   
  
    
    public Device findDevice(String deviceID) throws SQLException {
        String query = "SELECT * FROM DEVICE_T WHERE DEVICEID =?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, deviceID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(deviceID)){
                    return new Device(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getString(10), rs.getInt(11), rs.getString(12));
                }
            }
        }
        return null;
    }
    
    public ArrayList<Device> findDevices(String Input) throws SQLException{
        String[] querys = {
            "SELECT * FROM DEVICE_T WHERE LOWER(DEVICENAME) LIKE ?",
            "SELECT * FROM DEVICE_T WHERE LOWER(DEVICETYPE) LIKE ?",
            "SELECT * FROM DEVICE_T WHERE LOWER(DEVICEBRAND) LIKE ?"
        };
        
        ArrayList<Device> SearchResult = new ArrayList<>();
        
        if(Input != null){
            for(String query : querys){
                try(PreparedStatement statement = connect.prepareStatement(query)){
                    statement.setString(1, "%" + Input.toLowerCase() + "%");
                    ResultSet rs = statement.executeQuery();
                    while(rs.next()){
                        SearchResult.add(new Device(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getString(10), rs.getInt(11), rs.getString(12)));
                    }
                }
            }
        } else {
            return fetchDevices();
        }
        
        return SearchResult;
    }
    
    public ArrayList<Device> fetchDevices() throws SQLException{
        ArrayList<Device> allDevices = new ArrayList<>();
        
        ResultSet rs = st.executeQuery("SELECT * FROM DEVICE_T");
        
        while(rs.next()){
            allDevices.add(new Device(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getString(10), rs.getInt(11), rs.getString(12)));
        }
        
        return allDevices;
    }
    
    public void updateAll(String deviceID, String deviceName, String deviceDescription, String deviceBrand, String deviceSupplier, String deviceSpecifications, String deviceWRPolicy, float deviceStandardPrice, float deviceCurrentPrice, String deviceType, int deviceSOH, String deviceImage) throws SQLException{
      String query =  "UPDATE DEVICET SET deviceName = ? deviceDescription = ? deviceBrand  = ? deviceSupplier  = ? deviceSpecifications = ? deviceWRPolicy  = ? deviceStandardPrice  = ? deviceCurrentPrice  = ? deviceType = ? deviceSOH  = ? deviceImage = ? WHERE deviceID = ?";
        try(PreparedStatement statement = connect.prepareStatement(query)){
            statement.setString(1, deviceID);
            statement.setString(2, deviceName);
            statement.setString(3, deviceDescription);
            statement.setString(4, deviceBrand);
            statement.setString(5, deviceSupplier);
            statement.setString(6, deviceSpecifications);
            statement.setString(7, deviceWRPolicy);
            statement.setFloat(8, deviceStandardPrice);
            statement.setFloat(9, deviceCurrentPrice);
            statement.setString(10, deviceType);
            statement.setInt(11, deviceSOH);
            statement.setString(12, deviceImage);
            statement.executeUpdate();
        }
    }
    
    public void updatePrice(String deviceID, float newPrice) throws SQLException{
        String query = "UPDATE DEVICE_T SET DEVICECURRENTPRICE = ? WHERE DEVICEID = ?";
                
        try(PreparedStatement statement = connect.prepareStatement(query)){
            statement.setString(1, deviceID);
            statement.setFloat(2, newPrice);
            statement.executeUpdate();
        }
    }
    
    public void updateQuantity(String deviceID, int quant)throws SQLException{
        String query = "UPDATE DEVICE_T SET DEVICESOH = ? WHERE DEVICEID = ?";
                
        try(PreparedStatement statement = connect.prepareStatement(query)){
            statement.setString(1, deviceID);
            statement.setFloat(2, quant);
            statement.executeUpdate();
        }
    }
    public void deleteDevice(String deviceID) throws SQLException{
        String query = "DELETE FROM DEVICE_T WHERE DEVICEID = ?";
         
        try(PreparedStatement statement = connect.prepareStatement(query)){
            statement.setString(1, deviceID);
            statement.executeUpdate();
        }
    }
    
}
