package com.deepak.account_service.repository;

import com.deepak.account_service.model.Account;

import java.util.List;

public interface IAccountRepository {
    public long add(Account account);

    public Account findById(long id);

    public List<Account> findAll();

    public boolean checkIfExist(long id);

    public boolean update(long id, Account account);

    public boolean deleteById(long id);

    public long size();
}