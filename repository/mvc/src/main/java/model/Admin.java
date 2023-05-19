package model;



public class Admin {
    private String AdminID;
    private String AdminEmail;
    private String AdminPassword;
    

    public Admin(String AdminID, String AdminEmail, String AdminPassword) {
        this.AdminID = AdminID;
        this.AdminEmail = AdminEmail;
        this.AdminPassword = AdminPassword;
        
    }
    
    
    public String getAdminID() {
        return AdminID;
    }
    
    public String getAdminEmail(){
        return AdminEmail;
    }
    
    public String getAdminPassword(){
        return AdminPassword;
    }
    public void  setAdminEmail(String email){
        this.AdminEmail = email;
    }
    public void  setAdminPassword(String password){
        this.AdminPassword = password;
    }

}
