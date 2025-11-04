package models;

import java.time.LocalDate;

public class Employee extends Users{

    private Users user;         
    private Branch branch;     
    private String jobTitle;
    private LocalDate dateJoined;
    private String status;

    public Employee() {}
public Employee(String email,String password, Role role){
    super(email,password,role);
}
    public Employee(Users user, Branch branch, String jobTitle, LocalDate dateJoined, String status) {
        this.user = user;
        this.branch = branch;
        this.jobTitle = jobTitle;
        this.dateJoined = dateJoined;
        this.status = status;
    }

    // ✅ Getter and Setter for User
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    // ✅ Getter and Setter for Branch
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    // ✅ Job Title
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ✅ Date Joined
    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    // ✅ Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ✅ ToString
    @Override
    public String toString() {
        return "Employee{" +
                "user=" + (user != null ? user.getUserId() : "null") +
                ", branch=" + (branch != null ? branch.getBranchName() : "null") +
                ", jobTitle='" + jobTitle + '\'' +
                ", dateJoined=" + dateJoined +
                ", status='" + status + '\'' +
                '}';
    }
}
