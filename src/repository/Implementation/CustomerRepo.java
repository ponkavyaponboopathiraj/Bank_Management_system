package repository.Implementation;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.CustomerQuery;
import exceptions.NotFoundException;
import models.Branch;
import models.Customer;
import models.Users;
import repository.RepoInterface;

public class CustomerRepo implements RepoInterface<Customer> {

    private static CustomerRepo instance;
    private final Map<Integer, Customer> customers = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedCustomerIds = ConcurrentHashMap.newKeySet();

    private CustomerRepo() {}

    public static CustomerRepo getInstance() {
        if (instance == null) {
            instance = new CustomerRepo();
        }
        return instance;
    }

    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(CustomerQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users user = UserRepo.getInstance().getById(rs.getInt("user_id"));
                Branch branch = BranchRepo.getInstance().getById(rs.getInt("branch_id"));

                Customer c = new Customer();
                c.setUser(user);
                c.setAadhaarNumber(rs.getString("aadhaar_number"));
                c.setPanNumber(rs.getString("pan_number"));
                if (rs.getDate("date_of_birth") != null)
                    c.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                c.setBranch(branch);
                c.setStatus(rs.getString("status"));

                customers.put(user.getUserId(), c);
            }

        } catch (SQLException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getById(int id) {
        Customer c = customers.get(id);
        if (c == null) throw new NotFoundException("Customer not found");
        return c;
    }

    @Override
    public void add(Customer c) {
        customers.put(c.getUser().getUserId(), c);
        unSyncedCustomerIds.add(c.getUser().getUserId());
    }

    @Override
    public void update(Customer c) {
        customers.put(c.getUser().getUserId(), c);
        unSyncedCustomerIds.add(c.getUser().getUserId());
    }

    @Override
    public void deleteById(int id) {
        customers.remove(id);
        unSyncedCustomerIds.add(id);
    }

    public void syncChanges() {
        for (int id : unSyncedCustomerIds) {
            Customer c = getById(id);
            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(CustomerQuery.INSERT.getQuery())) {

                ps.setInt(1, c.getUser().getUserId());
                ps.setString(2, c.getAadhaarNumber());
                ps.setString(3, c.getPanNumber());
                ps.setDate(4, Date.valueOf(c.getDateOfBirth()));
                ps.setInt(5, c.getBranch().getBranchId());
                ps.setString(6, c.getStatus());
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing customer: " + e.getMessage());
            }
        }
    }
}
