package models;

import java.math.BigDecimal;

public class AccountType {
    private int accountTypeId;
    private String typeName;
 private BigDecimal minBalance;
    private BigDecimal interestRate;
    private String description;

    public AccountType() {}

    public AccountType(int accountTypeId, String typeName, BigDecimal interestRate, BigDecimal minBalance, String description) {
        this.accountTypeId = accountTypeId;
        this.typeName = typeName;
        this.interestRate = interestRate;
        this.minBalance = minBalance;
        this.description = description;
    }

    public int getAccountTypeId() { return accountTypeId; }
    public void setAccountTypeId(int accountTypeId) { this.accountTypeId = accountTypeId; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

 public BigDecimal getMinBalance() { return minBalance; }
    public void setMinBalance(BigDecimal minBalance) { this.minBalance = minBalance; }

    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
