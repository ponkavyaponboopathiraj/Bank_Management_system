package enums;

public enum AccountTypeQuery {

    LOADALL("SELECT account_type_id AS id, type_name, interest_rate, min_balance, description FROM account_type"),

    GETBYID("SELECT account_type_id AS id, type_name, interest_rate, min_balance, description FROM account_type WHERE account_type_id = ?"),

    INSERT("INSERT INTO account_type (type_name, interest_rate, min_balance, description) VALUES (?, ?, ?, ?)"),

    UPDATE("UPDATE account_type SET type_name = ?, interest_rate = ?, min_balance = ?, description = ? WHERE account_type_id = ?"),

    DELETE("DELETE FROM account_type WHERE account_type_id = ?");

    private final String query;

    AccountTypeQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
