package com.deepak.account_service.service;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.model.AccountType;

import java.util.List;

public interface IAccountService {
    public long addAccount(String name, String password, AccountType accountType);

    public Account findByAccountId(long accountId);

    public List<Account> findAllAccounts();

    public boolean updateAccount(long id, String name, String password, AccountType accountType);

    public boolean deleteAccountById(long id);
}
