package com.deepak.organizer_service.repository.impl;

import com.deepak.organizer_service.model.Organizer;
import com.deepak.organizer_service.repository.IOrganizerRepository;
import com.deepak.sharables.Utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizerRepositoryImpl implements IOrganizerRepository {

    private final Utility<Organizer> utility;

    public OrganizerRepositoryImpl(Utility<Organizer> utility){
        this.utility = utility;
        String query = "CREATE TABLE IF NOT EXISTS organizers(\n" +
                "    organizer_id BIGINT PRIMARY KEY,\n" +
                "    organizer_contact VARCHAR(15),\n" +
                "    fk_account_id BIGINT NOT NULL,\n" +
                "    FOREIGN KEY(fk_account_id) REFERENCES accounts(account_id) ON DELETE CASCADE\n" +
                ");";

        this.utility.execute(query);
    }

    @Override
    public boolean add(Organizer organizer) {
        String query = "INSERT INTO organizers VALUES (?, ?, ?);";
        this.utility.executeUpdate(query, organizer.getOrganizerId(), organizer.getContactInfo(), organizer.getAccountId());
        return true;
    }

    @Override
    public Organizer findById(long organizerId) {
        String query = "SELECT * FROM organizers WHERE organizer_id = ?";
        return this.utility.findOne(query, resultSet -> {
            try {
                long orgId = resultSet.getLong(1);
                String orgContact = resultSet.getString(2);
                long accountId = resultSet.getLong(3);
                return new Organizer(orgId, orgContact, accountId);
            } catch (SQLException e){
                return new Organizer();
            }
        }, organizerId);
    }

    @Override
    public List<Organizer> findAll() {
        String query = "SELECT * FROM organizers";
        return this.utility.findAll(query, resultSet -> {
            List<Organizer> organizers = new ArrayList<>();
            try{
                while(resultSet.next()){
                    long orgId = resultSet.getLong(1);
                    String orgContact = resultSet.getString(2);
                    long accountId = resultSet.getLong(3);
                    organizers.add(new Organizer(orgId, orgContact, accountId));
                }
                return organizers;
            } catch (SQLException e){
                return organizers;
            }
        });
    }

    @Override
    public boolean update(long organizerId, Organizer organizer) {
        String query = "UPDATE organizers SET organizer_id = ?, organizer_contact = ?, fk_account_id = ? WHERE organizer_id = ?";
        int result = this.utility.executeUpdate(query, organizer.getOrganizerId(), organizer.getContactInfo(), organizer.getAccountId(), organizerId);
        return result == 1;
    }

    @Override
    public boolean deleteById(long organizerId) {
        String query = "DELETE FROM organizers WHERE organizer_id = ?";
        int result = this.utility.executeUpdate(query, organizerId);
        return result == 1;
    }

    @Override
    public boolean deleteAll() {
        String query = "DELETE FROM organizers";
        int result = this.utility.executeUpdate(query);
        return result > 0;
    }

    @Override
    public int getSize() {
        String query = "SELECT COUNT(*) FROM organizers";
        Organizer org = this.utility.findOne(query, resultSet -> {
            try{
                int count = resultSet.getInt(1);
                Organizer organizer = new Organizer();
                organizer.setOrganizerId(Long.valueOf(count));
                return organizer;
            } catch (SQLException e){
                return new Organizer();
            }

        });
        return (int) org.getOrganizerId();
    }

    @Override
    public boolean checkExists(long organizerId) {
        String query = "SELECT * FROM organizers WHERE organizer_id = ?";
        Organizer organizer = this.utility.findOne(query, resultSet -> {
            try {
                long orgId = resultSet.getLong(1);
                String orgContact = resultSet.getString(2);
                long accountId = resultSet.getLong(3);
                return new Organizer(orgId, orgContact, accountId);
            } catch (SQLException e){
                return new Organizer();
            }
        }, organizerId);
        if(organizer == null){
            return false;
        }
        return organizer.getOrganizerId() == organizerId;
    }
}
