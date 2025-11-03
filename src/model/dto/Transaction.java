package src.model.dto;
import java.sql.Timestamp;
public class Transaction {
     private int transactionId;
    private int accountId;
    private String transactionType;
    private double amount;
    private String description;
    private Timestamp transactionDate;
    private double balanceAfter;
    private String toAccount;

   
    public Transaction()
    {
        
    }
    public Transaction(int transactionId, int accountId, String transactionType, double amount, String description,
            Timestamp transactionDate, double balanceAfter, String toAccount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.balanceAfter = balanceAfter;
        this.toAccount = toAccount;
    }
     public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
    public double getBalanceAfter() {
        return balanceAfter;
    }
    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }
    public String getToAccount() {
        return toAccount;
    }
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }
}
