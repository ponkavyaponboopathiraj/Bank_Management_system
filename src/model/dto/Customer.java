package src.model.dto;
import java.sql.Date;
public class Customer {
    private int customerId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String aadhaarNumber;
    private String panNumber;
    private String address;
    private Date dateOfBirth;
    private Integer branchId;
    private String status;
    
    public Customer()
    {
        
    }
    public Customer(int customerId, String fullName, String email, String password, String phone, String aadhaarNumber,
            String panNumber, String address, Date dateOfBirth, Integer branchId, String status) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.branchId = branchId;
        this.status = status;
    }
 public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
