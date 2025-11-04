package enums;

public enum TransactionQuery {
    LOADALL("SELECT * FROM transaction"),
    INSERT("INSERT INTO transaction (account_id, transaction_type, amount, description, transaction_date, balance_after, to_account) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    UPDATE("UPDATE transaction SET account_id=?, transaction_type=?, amount=?, description=?, transaction_date=?, balance_after=?, to_account=? WHERE transaction_id=?"),
    DELETE("DELETE FROM transaction WHERE transaction_id=?");

    private final String query;

    TransactionQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
