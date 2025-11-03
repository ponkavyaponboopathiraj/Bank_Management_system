package src.model.dto;

public class Branch {
     private int branchId;
    private String branchName;
    private String ifscCode;
    private String branchAddress;
    private String branchContact;
    private Integer managerId;
    
    public Branch()
    {

    }
    
    public Branch(int branchId, String branchName, String ifscCode, String branchAddress, String branchContact,
            Integer managerId) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.branchAddress = branchAddress;
        this.branchContact = branchContact;
        this.managerId = managerId;
    }
    public int getBranchId() {
        return branchId;
    }
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getIfscCode() {
        return ifscCode;
    }
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
    public String getBranchAddress() {
        return branchAddress;
    }
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
    public String getBranchContact() {
        return branchContact;
    }
    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
    }
    public Integer getManagerId() {
        return managerId;
    }
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    
}
