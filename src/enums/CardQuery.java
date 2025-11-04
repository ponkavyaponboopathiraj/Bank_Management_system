package enums;

public enum CardQuery {

    LOADALL("SELECT c.card_id, c.account_id, c.card_number, c.card_type, c.cvv, " +
            "c.expiry_date, c.issue_date, c.status " +
            "FROM card c JOIN account a ON c.account_id = a.account_id"),

    GETBYID("SELECT c.card_id, c.account_id, c.card_number, c.card_type, c.cvv, " +
            "c.expiry_date, c.issue_date, c.status " +
            "FROM card c JOIN account a ON c.account_id = a.account_id WHERE c.card_id = ?"),

    INSERT("INSERT INTO card (account_id, card_number, card_type, cvv, expiry_date, issue_date, status) " +
           "VALUES (?, ?, ?, ?, ?, ?, ?)"),

    UPDATE("UPDATE card SET account_id = ?, card_number = ?, card_type = ?, cvv = ?, " +
           "expiry_date = ?, issue_date = ?, status = ? WHERE card_id = ?"),

    DELETE("DELETE FROM card WHERE card_id = ?");

    private final String query;

    CardQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
