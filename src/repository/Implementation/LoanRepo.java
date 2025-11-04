package repository.Implementation;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.LoanQuery;
import exceptions.NotFoundException;
import models.Customer;
import models.Loan;
import models.LoanType;
import models.Users;
import repository.RepoInterface;

public class LoanRepo implements RepoInterface<Loan> {

    private static LoanRepo instance;
    private final Map<Integer, Loan> loans = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedLoanIds = ConcurrentHashMap.newKeySet();

    private LoanRepo() {}

    public static LoanRepo getInstance() {
        if (instance == null) {
            instance = new LoanRepo();
        }
        return instance;
    }


    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(LoanQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = CustomerRepo.getInstance().getById(rs.getInt("customer_id"));
                LoanType loanType = LoanTypeRepo.getInstance().getById(rs.getInt("loan_type_id"));
                Users approvedBy = UserRepo.getInstance().getById(rs.getInt("approved_by"));

                Loan l = new Loan();
                l.setLoanId(rs.getInt("id"));
                l.setCustomer(customer);
                l.setLoanType(loanType);
                l.setPrincipalAmount(rs.getDouble("principal_amount"));
                l.setInterestRate(rs.getDouble("interest_rate"));
                l.setEmiAmount(rs.getDouble("emi_amount"));
                l.setTenureMonths(rs.getInt("tenure_months"));
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                if (start != null) l.setStartDate(start.toLocalDate());
                if (end != null) l.setEndDate(end.toLocalDate());
                l.setStatus(rs.getString("status"));
                l.setApprovedBy(approvedBy);

                loans.put(l.getLoanId(), l);
            }

        } catch (SQLException e) {
            System.out.println("Error loading loans: " + e.getMessage());
        }
    }

    // ✅ Return all loaded loans
    @Override
    public List<Loan> getAll() {
        return new ArrayList<>(loans.values());
    }

    // ✅ Get loan by ID
    @Override
    public Loan getById(int id) {
        Loan l = loans.get(id);
        if (l == null) throw new NotFoundException("Loan not found");
        return l;
    }

    // ✅ Add loan in memory and mark unsynced
    @Override
    public void add(Loan l) {
        loans.put(l.getLoanId(), l);
        unSyncedLoanIds.add(l.getLoanId());
    }

    // ✅ Update existing loan and mark unsynced
    @Override
    public void update(Loan l) {
        loans.put(l.getLoanId(), l);
        unSyncedLoanIds.add(l.getLoanId());
    }

    // ✅ Delete loan from memory and mark unsynced
    @Override
    public void deleteById(int id) {
        loans.remove(id);
        unSyncedLoanIds.add(id);
    }

    // ✅ Sync unsynced changes to DB
    public void syncChanges() {
        for (int id : unSyncedLoanIds) {
            Loan l = getById(id);

            try (Connection conn = DBConnection.getInstance().getConnection()) {

                // Try updating first; if not found → insert
                try (PreparedStatement check = conn.prepareStatement(LoanQuery.GETBYID.getQuery())) {
                    check.setInt(1, id);
                    try (ResultSet rs = check.executeQuery()) {

                        if (rs.next()) {
                            try (PreparedStatement ps = conn.prepareStatement(LoanQuery.UPDATE.getQuery())) {
                                ps.setInt(1, l.getCustomer().getUser().getUserId());
                                ps.setInt(2, l.getLoanType().getLoanTypeId());
                                ps.setDouble(3, l.getPrincipalAmount());
                                ps.setDouble(4, l.getInterestRate());
                                ps.setDouble(5, l.getEmiAmount());
                                ps.setInt(6, l.getTenureMonths());
                                ps.setDate(7, Date.valueOf(l.getStartDate()));
                                ps.setDate(8, Date.valueOf(l.getEndDate()));
                                ps.setString(9, l.getStatus());
                                ps.setInt(10, l.getApprovedBy().getUserId());
                                ps.setInt(11, l.getLoanId());
                                ps.executeUpdate();
                            }
                        } else {
                            try (PreparedStatement ps = conn.prepareStatement(LoanQuery.INSERT.getQuery())) {
                                ps.setInt(1, l.getCustomer().getUser().getUserId());
                                ps.setInt(2, l.getLoanType().getLoanTypeId());
                                ps.setDouble(3, l.getPrincipalAmount());
                                ps.setDouble(4, l.getInterestRate());
                                ps.setDouble(5, l.getEmiAmount());
                                ps.setInt(6, l.getTenureMonths());
                                ps.setDate(7, Date.valueOf(l.getStartDate()));
                                ps.setDate(8, Date.valueOf(l.getEndDate()));
                                ps.setString(9, l.getStatus());
                                ps.setInt(10, l.getApprovedBy().getUserId());
                                ps.executeUpdate();
                            }
                        }
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error syncing loan: " + e.getMessage());
            }
        }
        unSyncedLoanIds.clear();
    }
}
