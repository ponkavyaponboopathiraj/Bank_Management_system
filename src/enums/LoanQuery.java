package enums;

public enum LoanQuery {

    LOADALL("""
        SELECT l.loan_id AS id, 
               l.customer_id, 
               l.loan_type_id, 
               l.principal_amount, 
               l.interest_rate, 
               l.emi_amount, 
               l.tenure_months, 
               l.start_date, 
               l.end_date, 
               l.status, 
               l.approved_by
        FROM loan l
        """),

    GETBYID("""
        SELECT l.loan_id AS id, 
               l.customer_id, 
               l.loan_type_id, 
               l.principal_amount, 
               l.interest_rate, 
               l.emi_amount, 
               l.tenure_months, 
               l.start_date, 
               l.end_date, 
               l.status, 
               l.approved_by
        FROM loan l
        WHERE l.loan_id = ?
        """),

    INSERT("""
        INSERT INTO loan (
            customer_id, loan_type_id, principal_amount, interest_rate, 
            emi_amount, tenure_months, start_date, end_date, status, approved_by
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """),

    UPDATE("""
        UPDATE loan
        SET customer_id = ?, loan_type_id = ?, principal_amount = ?, 
            interest_rate = ?, emi_amount = ?, tenure_months = ?, 
            start_date = ?, end_date = ?, status = ?, approved_by = ?
        WHERE loan_id = ?
        """),

    DELETE("DELETE FROM loan WHERE loan_id = ?");

    private final String query;

    LoanQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
