package repository.Implementation;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.EmployeeQuery;
import exceptions.NotFoundException;
import models.Branch;
import models.Employee;
import models.Users;
import repository.RepoInterface;

public class EmployeeRepo implements RepoInterface<Employee> {

    private static EmployeeRepo instance;

    private final Map<Integer, Employee> employees = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedEmployeeIds = ConcurrentHashMap.newKeySet();

    private EmployeeRepo() {}

    public static EmployeeRepo getInstance() {
        if (instance == null) {
            instance = new EmployeeRepo();
        }
        return instance;
    }

    // -------------------- LOAD ALL EMPLOYEES --------------------
    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(EmployeeQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users user = UserRepo.getInstance().getById(rs.getInt("user_id"));
                Branch branch = BranchRepo.getInstance().getById(rs.getInt("branch_id"));

                Employee emp = new Employee();
                emp.setUser(user);
                emp.setBranch(branch);
                emp.setJobTitle(rs.getString("job_title"));
                if (rs.getDate("date_joined") != null)
                    emp.setDateJoined(rs.getDate("date_joined").toLocalDate());
                emp.setStatus(rs.getString("status"));

                employees.put(user.getUserId(), emp);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error loading employees: " + e.getMessage());
        }
    }

    // -------------------- GET ALL EMPLOYEES --------------------
    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    // -------------------- GET EMPLOYEE BY ID --------------------
    @Override
    public Employee getById(int id) {
        Employee emp = employees.get(id);
        if (emp == null)
            throw new NotFoundException("❌ Employee not found with ID: " + id);
        return emp;
    }

    // -------------------- ADD EMPLOYEE --------------------
    @Override
    public void add(Employee emp) {
        if (emp.getUser() == null || emp.getUser().getUserId() == 0) {
            throw new IllegalArgumentException("❌ Employee must have a valid User reference before adding!");
        }
        employees.put(emp.getUser().getUserId(), emp);
        unSyncedEmployeeIds.add(emp.getUser().getUserId());
    }

    // -------------------- UPDATE EMPLOYEE --------------------
    @Override
    public void update(Employee emp) {
        if (emp.getUser() == null || emp.getUser().getUserId() == 0) {
            throw new IllegalArgumentException("❌ Employee must have a valid User reference before updating!");
        }
        employees.put(emp.getUser().getUserId(), emp);
        unSyncedEmployeeIds.add(emp.getUser().getUserId());
    }

    // -------------------- DELETE EMPLOYEE --------------------
    @Override
    public void deleteById(int id) {
        employees.remove(id);
        unSyncedEmployeeIds.add(id);
    }

    // -------------------- SYNC CHANGES WITH DATABASE --------------------
    @Override
    public void syncChanges() {
        for (int id : unSyncedEmployeeIds) {
            Employee emp = employees.get(id);
            if (emp == null) continue;

            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(EmployeeQuery.INSERT.getQuery())) {

                ps.setInt(1, emp.getUser().getUserId());
                ps.setInt(2, emp.getBranch().getBranchId());
                ps.setString(3, emp.getJobTitle());
                ps.setDate(4, emp.getDateJoined() != null ? Date.valueOf(emp.getDateJoined()) : null);
                ps.setString(5, emp.getStatus());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("❌ Error syncing Employee: " + e.getMessage());
            }
        }
    }
}
