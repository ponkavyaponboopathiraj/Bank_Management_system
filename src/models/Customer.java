package models;

import java.time.LocalDate;

public class Customer extends Users {
    private Users user;
    private String aadhaarNumber;
    private String panNumber;
    private LocalDate dateOfBirth;
    private Branch branch;
    private String status;

    public Customer() {}
public Customer(String email, String password, Role role) {
    super(email, password, role);
}

    public Customer(String email, String password, Role role,String aadhaarNumber, String panNumber, LocalDate dateOfBirth,
                    Branch branch, String status) {
                          super(email, password, role);
      
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
        this.dateOfBirth = dateOfBirth;
        this.branch = branch;
        this.status = status;
    }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
