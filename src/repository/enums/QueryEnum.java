package src.repository.enums;

public enum QueryEnum {
INSERT_ACCOUNT("INSERT INTO account (customer_id, balance, account_type_id, status) VALUES (?, ?, ?, ?)"),
UPDATE_ACCOUNT("UPDATE account SET balance = ?, account_type_id = ?, status = ? WHERE account_id = ?"),
SELECT_ACCOUNT_BY_ID("SELECT * FROM account WHERE account_id = ?"),
SELECT_ALL_ACCOUNTS("SELECT * FROM account"),
DELETE_ACCOUNT("DELETE FROM account WHERE account_id = ?");


    private final String query;

    QueryEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
