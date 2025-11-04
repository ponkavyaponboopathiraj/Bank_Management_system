package models;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private Customer customer;
    private LoanType loanType;
    private double principalAmount;
    private double interestRate;
    private double emiAmount;
    private int tenureMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Users approvedBy;

    public Loan(){
        
    }
    public Loan(int loanId, Customer customer, LoanType loanType, double principalAmount, double interestRate,
            double emiAmount, int tenureMonths, LocalDate startDate, LocalDate endDate, String status,
            Users approvedBy) {
        this.loanId = loanId;
        this.customer = customer;
        this.loanType = loanType;
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
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LoanType getLoanType() {
        return loanType;
    }
    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
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
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Users getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(Users approvedBy) {
        this.approvedBy = approvedBy;
    }

    // Getters, Setters, Constructors
}
