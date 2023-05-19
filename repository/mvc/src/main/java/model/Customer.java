package model;




public class Customer {
    private String customerId;
    private String firstName; 
    private String lastName; 
    private String DOB; 
    private String email; 
    private String phoneNum;
    private String street;
    private String suburb;
    private String state;
    private String postCode;
    private String userName;
    private String password;
    
    //Customer constructor has changed significantly to match the DB therefore the Assignment 1 pages will no longer work 
    // really you just have to remove gender and Fav Color. 
    public Customer(String customerId, String firstName, String lastName, String DOB, String email, String phoneNum, String street, String suburb,String state, String postCode, String userName, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.email = email;
        this.phoneNum = phoneNum;
        this.street = street;
        this.state = state;
        this.suburb = suburb;
        this.postCode = postCode;
        this.userName = userName;
        this.password = password;
    }
  
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    //Fname
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Lname
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //DOB
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    //Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    //phone Num
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    //Sesame street
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    //burb
    public String getSuburb() {
        return suburb;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    
    //state
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
    //PostCode
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    //username
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
