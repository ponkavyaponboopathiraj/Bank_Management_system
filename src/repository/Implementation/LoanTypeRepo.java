package repository.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.LoanTypeQuery;
import exceptions.NotFoundException;
import models.LoanType;
import repository.RepoInterface;

public class LoanTypeRepo implements RepoInterface<LoanType> {

    private static LoanTypeRepo instance;
    private final Map<Integer, LoanType> loanTypes = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedLoanTypeIds = ConcurrentHashMap.newKeySet();

    private LoanTypeRepo() {}

    public static LoanTypeRepo getInstance() {
        if (instance == null) {
            instance = new LoanTypeRepo();
        }
        return instance;
    }

    // ✅ Load all Loan Types from DB
    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(LoanTypeQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LoanType lt = new LoanType();
                lt.setLoanTypeId(rs.getInt("loan_type_id"));
                lt.setLoanName(rs.getString("loan_name"));
                lt.setInterestRate(rs.getDouble("interest_rate"));
                lt.setMaxAmount(rs.getDouble("max_amount"));
                lt.setMaxTenureMonths(rs.getInt("max_tenure_months"));

                loanTypes.put(lt.getLoanTypeId(), lt);
            }

        } catch (SQLException e) {
            System.out.println("Error loading Loan Types: " + e.getMessage());
        }
    }

    // ✅ Get all loan types
    @Override
    public List<LoanType> getAll() {
        return new ArrayList<>(loanTypes.values());
    }

    // ✅ Get by ID
    @Override
    public LoanType getById(int id) {
        LoanType lt = loanTypes.get(id);
        if (lt == null) throw new NotFoundException("Loan Type not found with ID: " + id);
        return lt;
    }

    // ✅ Add new LoanType
    @Override
    public void add(LoanType lt) {
        int id = loanTypes.size() + 1;
        lt.setLoanTypeId(id);
        addOrUpdate(lt);
    }

    // ✅ Update LoanType
    @Override
    public void update(LoanType lt) {
        addOrUpdate(lt);
    }

    private void addOrUpdate(LoanType lt) {
        loanTypes.put(lt.getLoanTypeId(), lt);
        unSyncedLoanTypeIds.add(lt.getLoanTypeId());
    }

    // ✅ Delete LoanType
    @Override
    public void deleteById(int id) {
        loanTypes.remove(id);
        unSyncedLoanTypeIds.add(id);
    }

    // ✅ Sync in-memory changes to DB
    @Override
    public void syncChanges() {
        for (int id : unSyncedLoanTypeIds) {
            LoanType lt = getById(id);

            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(LoanTypeQuery.INSERT.getQuery())) {

                ps.setString(1, lt.getLoanName());
                ps.setDouble(2, lt.getInterestRate());
                ps.setDouble(3, lt.getMaxAmount());
                ps.setInt(4, lt.getMaxTenureMonths());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing Loan Type: " + e.getMessage());
            }
        }
        unSyncedLoanTypeIds.clear();
    }
}
