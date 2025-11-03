package src.Data_transfer_object;

public class LoginRequest{
       private String username;
    private String password;
     private String rolename;

    public LoginRequest(String username, String password, String rolename) {
        this.username = username;
        this.password = password;
        this.rolename = rolename;
    }
 

    public String getUsername() {
        return username;
    }


     public String getPassword() {
         return password;
     }


     public String getRolename() {
         return rolename;
     }


}
