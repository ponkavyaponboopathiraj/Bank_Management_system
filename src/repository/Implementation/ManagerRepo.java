package repository.Implementation;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.ManagerQuery;
import exceptions.NotFoundException;
import models.Branch;
import models.Manager;
import models.Users;
import repository.RepoInterface;

public class ManagerRepo implements RepoInterface<Manager> {

    private static ManagerRepo instance;
    private final Map<Integer, Manager> managers = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedManagerIds = ConcurrentHashMap.newKeySet();

    private ManagerRepo() {}

    public static ManagerRepo getInstance() {
        if (instance == null) {
            instance = new ManagerRepo();
        }
        return instance;
    }

    // -------------------- LOAD ALL MANAGERS --------------------
    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(ManagerQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users user = UserRepo.getInstance().getById(rs.getInt("user_id"));
                Branch branch = BranchRepo.getInstance().getById(rs.getInt("branch_id"));

                Manager manager = new Manager();
                manager.setUser(user);
                manager.setBranch(branch);
                if (rs.getDate("date_joined") != null)
                    manager.setDateJoined(rs.getDate("date_joined").toLocalDate());
                manager.setStatus(rs.getString("status"));

                managers.put(user.getUserId(), manager);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error loading managers: " + e.getMessage());
        }
    }

    // -------------------- GET ALL MANAGERS --------------------
    @Override
    public List<Manager> getAll() {
        return new ArrayList<>(managers.values());
    }

    // -------------------- GET MANAGER BY ID --------------------
    @Override
    public Manager getById(int id) {
        Manager manager = managers.get(id);
        if (manager == null)
            throw new NotFoundException("❌ Manager not found with ID: " + id);
        return manager;
    }

    // -------------------- ADD MANAGER --------------------
    @Override
    public void add(Manager manager) {
        if (manager.getUser() == null || manager.getUser().getUserId() == 0) {
            throw new IllegalArgumentException("❌ Manager must have a valid User before adding!");
        }
        managers.put(manager.getUser().getUserId(), manager);
        unSyncedManagerIds.add(manager.getUser().getUserId());
    }

    // -------------------- UPDATE MANAGER --------------------
    @Override
    public void update(Manager manager) {
        if (manager.getUser() == null || manager.getUser().getUserId() == 0) {
            throw new IllegalArgumentException("❌ Manager must have a valid User before updating!");
        }
        managers.put(manager.getUser().getUserId(), manager);
        unSyncedManagerIds.add(manager.getUser().getUserId());
    }

    // -------------------- DELETE MANAGER --------------------
    @Override
    public void deleteById(int id) {
        managers.remove(id);
        unSyncedManagerIds.add(id);
    }

    // -------------------- SYNC CHANGES TO DATABASE --------------------
    @Override
    public void syncChanges() {
        for (int id : unSyncedManagerIds) {
            Manager manager = getById(id);
            if (manager == null) continue;

            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(ManagerQuery.INSERT.getQuery())) {

                ps.setInt(1, manager.getUser().getUserId());
                ps.setInt(2, manager.getBranch().getBranchId());
                ps.setDate(3, manager.getDateJoined() != null ? Date.valueOf(manager.getDateJoined()) : null);
                ps.setString(4, manager.getStatus());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("❌ Error syncing manager: " + e.getMessage());
            }
        }
    }
}
