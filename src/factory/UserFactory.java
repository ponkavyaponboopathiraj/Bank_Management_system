package factory;

import exceptions.NotFoundException;
import models.Admin;
import models.Customer;
import models.Employee;
import models.Manager;
import models.Role;
import models.Users;

public class UserFactory {

   public static Users generateUser(String email, String password, Role role) {

    switch (role.getRoleName().toLowerCase()) 
    {
   

    
        case "admin":
            // No separate Admin class, just return a base Users instance
            return new Admin(email, password, role);

        case "manager":
            return new Manager(email, password, role);

        case "employee":
            return new Employee(email, password, role);

        case "customer":
            return new Customer(email, password, role);

        default:
            throw new NotFoundException("❌ Invalid role type: " + role.getRoleName());
    }
}

    // ✅ Create empty user objects based on roleName (for repo queries, etc.)
    public static Users generateUser(String roleName) {
        switch (roleName.toLowerCase()) {
            case "admin":
                return new Admin(); // base user

            case "manager":
                return new Manager();

            case "employee":
                return new Employee();

            case "customer":
                return new Customer();

            default:
                throw new NotFoundException("❌ Invalid role type: " + roleName);
        }
    }
}
