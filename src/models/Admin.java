package models;

public class Admin extends Users{
    public Admin(){}

    public Admin( String email , String password , Role role){
        
        super( email, password, role);
    }
}
