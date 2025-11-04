package enums;

public enum LoanTypeQuery {

    LOADALL("SELECT loan_type_id, loan_name, interest_rate, max_amount, max_tenure_months FROM loan_type"),
    GETBYID("SELECT loan_type_id, loan_name, interest_rate, max_amount, max_tenure_months FROM loan_type WHERE loan_type_id = ?"),
    INSERT("INSERT INTO loan_type (loan_name, interest_rate, max_amount, max_tenure_months) VALUES (?, ?, ?, ?)"),
    UPDATE("UPDATE loan_type SET loan_name = ?, interest_rate = ?, max_amount = ?, max_tenure_months = ? WHERE loan_type_id = ?"),
    DELETE("DELETE FROM loan_type WHERE loan_type_id = ?");

    private final String query;

    LoanTypeQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
