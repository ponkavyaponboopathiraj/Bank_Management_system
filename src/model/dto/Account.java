package src.model.dto;
import java.sql.Timestamp;
public class Account {
   private int accountId;
    private String accountNumber;
    private int customerId;
    private int branchId;
    private int accountTypeId;
    private double balance;
    private Timestamp createdAt;
    private String status;

    public Account() {
    
    }

    public Account(int accountId, String accountNumber, int customerId, int branchId, int accountTypeId, double balance,
            Timestamp createdAt, String status) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.branchId = branchId;
        this.accountTypeId = accountTypeId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
    }
     public int getAccountId() {
        return accountId;
    }


    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public int getBranchId() {
        return branchId;
    }


    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    public int getAccountTypeId() {
        return accountTypeId;
    }


    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    
}
