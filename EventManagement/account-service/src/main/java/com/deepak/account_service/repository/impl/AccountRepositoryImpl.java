package com.deepak.account_service.repository.impl;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.model.AccountType;
import com.deepak.account_service.repository.IAccountRepository;
import com.deepak.sharables.Utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    Utility<Account> utility;

    public AccountRepositoryImpl(Utility<Account> utility){
        this.utility = utility;
        String query = "CREATE TABLE IF NOT EXISTS accounts(\n" +
                "    account_id BIGINT PRIMARY KEY,\n" +
                "    account_name VARCHAR(30) NOT NULL,\n" +
                "    account_password VARCHAR(30) NOT NULL,\n" +
                "    account_role VARCHAR(10) NOT NULL\n" +
                ");";
        this.utility.execute(query);
    }


    @Override
    public long add(Account account) {
        String query = "INSERT INTO accounts values (?,?,?,?);";
        this.utility.execute(query, preparedStatement -> {
            try {
                preparedStatement.setLong(1, account.getAccountId());
                preparedStatement.setString(2, account.getAccountName());
                preparedStatement.setString(3, account.getAccountPassword());
                preparedStatement.setString(4, String.valueOf(account.getAccountType()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        return account.getAccountId();

    }

    @Override
    public Account findById(long id) {
        String query = "SELECT * FROM accounts WHERE account_id = ?;";
        return this.utility.findOne(query, resultSet -> {
            try {
                long account_id = resultSet.getLong(1);
                String account_name = resultSet.getString(2);
                String account_password = resultSet.getString(3);
                Enum<AccountType> account_type = AccountType.valueOf(resultSet.getString(4));
                return new Account(account_id, account_name, account_password, account_type);
            } catch (SQLException e) {
                return null;
            }
        }, id);
    }

    @Override
    public List<Account> findAll() {
        String query = "SELECT * FROM accounts;";
        return this.utility.findAll(query, resultSet -> {
            try {
                List<Account> accountList = new ArrayList<>();
                while(resultSet.next()){
                    long account_id = resultSet.getLong(1);
                    String account_name = resultSet.getString(2);
                    String account_password = resultSet.getString(3);
                    Enum<AccountType> account_type = AccountType.valueOf(resultSet.getString(4));
                    accountList.add(new Account(account_id, account_name, account_password, account_type));
                }
                return accountList;
            } catch (SQLException e){
                return null;
            }
        });
    }

    @Override
    public boolean checkIfExist(long id) {
        String query = "SELECT * FROM accounts WHERE account_id = ?;";
        Account val = this.utility.findOne(query, resultSet -> {
            try {
                long account_id = resultSet.getLong(1);
                String account_name = resultSet.getString(2);
                String account_description = resultSet.getString(3);
                Enum<AccountType> accountTypeEnum = AccountType.valueOf(resultSet.getString(4));
                return new Account(account_id, account_name, account_description, accountTypeEnum);
            } catch (SQLException e){
                return new Account();
            }
        }, id);
        if(val == null){
            return false;
        }
        return val.getAccountId() == id;
    }

    @Override
    public boolean update(long id, Account account) {
        String query = "UPDATE accounts SET account_id = ?, account_name = ?, account_password = ?, account_role = ? WHERE account_id = ?";
        int returnVal = this.utility.executeUpdate(query, account.getAccountId(), account.getAccountName(), account.getAccountPassword(), String.valueOf(account.getAccountType()), id);
        return returnVal > 0;
    }

    @Override
    public boolean deleteById(long id) {
        String query = "DELETE FROM accounts WHERE account_id = ?;";
        int returnVal = this.utility.executeUpdate(query, id);
        return returnVal == 1;
    }

    @Override
    public long size() {
        String query = "SELECT COUNT(*) FROM accounts;";
        Account account = this.utility.findOne(query, resultSet -> {
            try {
                int size = resultSet.getInt(1);
                Account acc = new Account();
                acc.setAccountId(size);
                return acc;
            } catch (SQLException e){
                return new Account();
            }
        });
        return account.getAccountId();
    }
}
