package models;

import java.time.LocalDateTime;

public class Account {
    private int accountId;
    private String accountNumber;
    private Customer customer;
    private Branch branch;
    private AccountType accountType;
    private double balance;
    private LocalDateTime createdAt;
    private String status;

    public Account() {}

    public Account(int accountId, String accountNumber, Customer customer, Branch branch,
                   AccountType accountType, double balance, LocalDateTime createdAt, String status) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.branch = branch;
        this.accountType = accountType;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
    }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
