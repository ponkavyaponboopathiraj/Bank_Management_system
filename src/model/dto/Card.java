package src.model.dto;
import java.sql.Date;
public class Card {
        private int cardId;
    private int accountId;
    private String cardNumber;
    private String cardType;
    private String cvv;
    private Date expiryDate;
    private Date issueDate;
    private String status;
    public Card()
    {
        
    }
    public Card(int cardId, int accountId, String cardNumber, String cardType, String cvv, Date expiryDate,
            Date issueDate, String status) {
        this.cardId = cardId;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.issueDate = issueDate;
        this.status = status;
    }
     public int getCardId() {
        return cardId;
    }
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
