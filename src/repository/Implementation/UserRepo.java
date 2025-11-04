package repository.Implementation;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.UserQuery;
import exceptions.NotFoundException;
import factory.UserFactory;
import models.Role;
import models.Users;
import enums.Gender;
import repository.RepoInterface;

public class UserRepo implements RepoInterface<Users> {

    private static UserRepo instance;
    private final Map<Integer, Users> users = new ConcurrentHashMap<>();
    private final Set<Integer> unsyncedUserIds = ConcurrentHashMap.newKeySet();

    private UserRepo() {}

    public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    // ====================================================
    // Load all users from DB into memory
    // ====================================================
    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(UserQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Role role = RoleRepo.getInstance().getById(rs.getInt("role_id"));

                Users user = UserFactory.generateUser(
                        rs.getString("email"),
                        rs.getString("password"),
                        role
                );

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getString("gender"));
                user.setActive(rs.getBoolean("is_active"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                user.setRole(role);
                users.put(user.getUserId(), user);
            }

        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // ====================================================
    // Get all users from cache
    // ====================================================
    @Override
    public List<Users> getAll() {
        return new ArrayList<>(users.values());
    }

    // ====================================================
    // Get user by ID
    // ====================================================
    @Override
    public Users getById(int id) {
        Users user = users.get(id);
        if (user == null)
            throw new NotFoundException("User not found with ID: " + id);
        return user;
    }

    // ====================================================
    // Add new user
    // ====================================================
    @Override
    public void add(Users user) {
        users.put(user.getUserId(), user);
        unsyncedUserIds.add(user.getUserId());
    }

    // ====================================================
    // Update existing user
    // ====================================================
    @Override
    public void update(Users user) {
        users.put(user.getUserId(), user);
        unsyncedUserIds.add(user.getUserId());
    }

    // ====================================================
    // Delete user by ID
    // ====================================================
    @Override
    public void deleteById(int id) {
        users.remove(id);
        unsyncedUserIds.add(id);
    }

    // ====================================================
    // Sync unsynced users with database
    // ====================================================
    public void syncChanges() {
        if (unsyncedUserIds.isEmpty()) return;

        for (int id : unsyncedUserIds) {
            Users user = getById(id);
            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(UserQuery.INSERT.getQuery())) {

                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setInt(3, user.getRole().getRoleId());
                ps.setString(4, user.getFullName());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getAddress());
                ps.setString(7, user.getGender());
                ps.setBoolean(8, user.isActive());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing user: " + e.getMessage());
            }
        }
    }
}
