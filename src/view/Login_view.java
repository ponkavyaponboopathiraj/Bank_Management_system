package src.view;
import src.Data_transfer_object.LoginRequest;
import src.model.dto.Customer;
import src.model.dto.Employee;
import src.controller.LoginController;
import java.util.Scanner;
public class Login_view{
     Scanner sc = new Scanner(System.in);
         private static final LoginController LOGIN_CONTROLLER = LoginController.getLoginController();

    public void welcome_screen(){
       
        System.out.println("Welcome to Zoho Bank");
        System.out.println("--------------------");
        while(true){
            System.out.println("1. Login as Admin\n 2. Login as Employee \n3. Login as Customer\n 4.Exit");
            int choice = src.service.Validator.getValidatedChoice(1 , 3);
              switch (choice) {
                case 1:
                    handleLogin("ADMIN");
                    break;
                case 2:
                    handleLogin("EMPLOYEE");
                    break;
                 case 3:
                    handleLogin("CUSTOMER");
                    break;
                case 4:
                    System.out.println("Thank you....");
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
    public void handleLogin(String role){
                    System.out.println("Enter the User name: ");
                    String userName=sc.next();

                     System.out.println("Enter the password : ");
                    String passWord=sc.next();

                                LoginRequest loginRequest = new LoginRequest(userName , passWord , role);
                          Employee employee = LOGIN_CONTROLLER.login(loginRequest);

    }

        }
