package enums;

public enum ManagerQuery {

    LOADALL("SELECT m.user_id AS id, m.branch_id, b.branch_name, m.date_joined, m.status " +
            "FROM manager m JOIN branch b ON m.branch_id = b.branch_id"),
    
    GETBYID("SELECT m.user_id AS id, m.branch_id, b.branch_name, m.date_joined, m.status " +
            "FROM manager m JOIN branch b ON m.branch_id = b.branch_id WHERE m.user_id = ?"),
    
    INSERT("INSERT INTO manager (user_id, branch_id, date_joined, status) VALUES (?, ?, ?, ?)"),
    
    UPDATE("UPDATE manager SET branch_id = ?, date_joined = ?, status = ? WHERE user_id = ?"),
    
    DELETE("DELETE FROM manager WHERE user_id = ?");

    private final String query;

    ManagerQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
