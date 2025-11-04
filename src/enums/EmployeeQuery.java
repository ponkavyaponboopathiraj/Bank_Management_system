package enums;

public enum EmployeeQuery {

    LOADALL("SELECT e.user_id AS id, e.branch_id, b.branch_name, e.job_title, e.date_joined, e.status " +
            "FROM employee e JOIN branch b ON e.branch_id = b.branch_id"),
    
    GETBYID("SELECT e.user_id AS id, e.branch_id, b.branch_name, e.job_title, e.date_joined, e.status " +
            "FROM employee e JOIN branch b ON e.branch_id = b.branch_id WHERE e.user_id = ?"),
    
    INSERT("INSERT INTO employee (user_id, branch_id, job_title, date_joined, status) VALUES (?, ?, ?, ?, ?)"),
    
    UPDATE("UPDATE employee SET branch_id = ?, job_title = ?, date_joined = ?, status = ? WHERE user_id = ?"),
    
    DELETE("DELETE FROM employee WHERE user_id = ?");

    private final String query;

    EmployeeQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
