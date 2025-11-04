package models;

import java.time.LocalDate;

public class Manager extends Users {

    private Branch branch;
    private LocalDate dateJoined;
    private String status;

    public Manager() {}

   
public Manager(String email, String password, Role role) {
    super(email, password, role);
}

    public Manager(String email, String password, Role role, Branch branch, LocalDate dateJoined, String status) {
        super(email, password, role);
        this.branch = branch;
        this.dateJoined = dateJoined;
        this.status = status;
    }

    // Getters & Setters
    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public LocalDate getDateJoined() { return dateJoined; }
    public void setDateJoined(LocalDate dateJoined) { this.dateJoined = dateJoined; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
