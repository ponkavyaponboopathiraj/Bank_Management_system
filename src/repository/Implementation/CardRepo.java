package repository.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.CardQuery;
import exceptions.NotFoundException;
import models.Account;
import models.Card;
import repository.RepoInterface;

public class CardRepo implements RepoInterface<Card> {

    private static CardRepo instance;

    private final Map<Integer, Card> cards = new ConcurrentHashMap<>();
    private final Set<Integer> unUpdatedCardIds = new ConcurrentHashMap<Integer, Boolean>().newKeySet();

    private CardRepo() {}

    public static CardRepo getInstance() {
        if (instance == null) {
            instance = new CardRepo();
        }
        return instance;
    }

    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(CardQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = AccountRepo.getInstance().getById(rs.getInt("account_id"));

                Card card = new Card();
                card.setCardId(rs.getInt("card_id"));
                card.setAccount(account);
                card.setCardNumber(rs.getString("card_number"));
                card.setCardType(rs.getString("card_type"));
                card.setCvv(rs.getString("cvv"));
                card.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
                card.setIssueDate(rs.getDate("issue_date").toLocalDate());
                card.setStatus(rs.getString("status"));

                cards.put(card.getCardId(), card);
            }

        } catch (SQLException e) {
            System.out.println("Error loading cards: " + e.getMessage());
        }
    }

    @Override
    public List<Card> getAll() {
        return new ArrayList<>(cards.values());
    }

    @Override
    public Card getById(int id) {
        Card card = cards.get(id);
        if (card == null) throw new NotFoundException("Card not found with ID: " + id);
        return card;
    }

    @Override
    public void add(Card card) {
        int id = cards.size() + 1;
        card.setCardId(id);
        addOrUpdate(card);
    }

    @Override
    public void update(Card card) {
        addOrUpdate(card);
    }

    private void addOrUpdate(Card card) {
        cards.put(card.getCardId(), card);
        unUpdatedCardIds.add(card.getCardId());
    }

    @Override
    public void deleteById(int id) {
        cards.remove(id);
        unUpdatedCardIds.add(id);
    }

    @Override
    public void syncChanges() {
        for (int id : unUpdatedCardIds) {
            Card card = getById(id);
            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(CardQuery.INSERT.getQuery())) {

                ps.setInt(1, card.getAccount().getAccountId());
                ps.setString(2, card.getCardNumber());
                ps.setString(3, card.getCardType());
                ps.setString(4, card.getCvv());
                ps.setDate(5, java.sql.Date.valueOf(card.getExpiryDate()));
                ps.setDate(6, java.sql.Date.valueOf(card.getIssueDate()));
                ps.setString(7, card.getStatus());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing card: " + e.getMessage());
            }
        }
    }
}
