package src.model.dto;

import java.sql.Date;
public class Employee {
     private int employeeId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private Integer branchId;
    private Integer roleId;
    private Date dateJoined;
    private String status;

    public Employee()
    {
        
    }
  
    public Employee(int employeeId, String fullName, String email, String password, String phone, Integer branchId,
            Integer roleId, Date dateJoined, String status) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.branchId = branchId;
        this.roleId = roleId;
        this.dateJoined = dateJoined;
        this.status = status;
    }
      public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getBranchId() {
        return branchId;
    }
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Date getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
