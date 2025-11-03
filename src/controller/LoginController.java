package src.controller;

import src.Data_transfer_object.LoginRequest;
import src.model.dto.Employee;
import src.model.dto.Customer;
public class LoginController {
      private static LoginController loginController = null;
   // private static final LoginService LOGIN_SERVICE = LoginService.getInstance();

    private LoginController(){

    }

    public static LoginController getLoginController() {
        if (loginController == null){
            loginController = new LoginController();
        }
        return loginController;
    }

    
}


