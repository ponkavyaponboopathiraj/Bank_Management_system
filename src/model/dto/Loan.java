package src.model.dto;
import java.sql.Date;
public class Loan {
       private int loanId;
    private int customerId;
    private int loanTypeId;
    private double principalAmount;
    private double interestRate;
    private double emiAmount;
    private int tenureMonths;
    private Date startDate;
    private Date endDate;
    private String status;
    private Integer approvedBy;
   
     

     public Loan()
     {
        
     }

    public Loan(int loanId, int customerId, int loanTypeId, double principalAmount, double interestRate,
            double emiAmount, int tenureMonths, Date startDate, Date endDate, String status, Integer approvedBy) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.loanTypeId = loanTypeId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.emiAmount = emiAmount;
        this.tenureMonths = tenureMonths;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.approvedBy = approvedBy;
    }
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }
    
}
