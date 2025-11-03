package src.model.dto;

public class LoanType {
    private int loanTypeId;
    private String loanName;
    private double interestRate;
    private double maxAmount;
    private int tenureMonths;

    public LoanType()
    {

    }

    public LoanType(int loanTypeId, String loanName, double interestRate, double maxAmount, int tenureMonths) {
        this.loanTypeId = loanTypeId;
        this.loanName = loanName;
        this.interestRate = interestRate;
        this.maxAmount = maxAmount;
        this.tenureMonths = tenureMonths;
    }
       public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    
}
