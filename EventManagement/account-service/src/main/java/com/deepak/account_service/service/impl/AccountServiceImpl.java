package com.deepak.account_service.service.impl;


import com.deepak.account_service.model.Account;
import com.deepak.account_service.model.AccountType;
import com.deepak.account_service.repository.IAccountRepository;
import com.deepak.account_service.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {

    IAccountRepository accountRepository;

    public AccountServiceImpl(IAccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public long addAccount(String name, String password, AccountType accountType) {
        long accountId = this.accountRepository.size() + 1;
        Account account = new Account(accountId, name, password, accountType);
        return this.accountRepository.add(account);
    }

    @Override
    public Account findByAccountId(long accountId) {
        return this.accountRepository.findById(accountId);
    }

    @Override
    public List<Account> findAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public boolean updateAccount(long id, String name, String password, AccountType accountType) {
        boolean ifExists = this.accountRepository.checkIfExist(id);
        if(ifExists){
            Account acc = new Account(id, name, password, accountType);
            return this.accountRepository.update(id, acc);
        }
        return false;
    }

    @Override
    public boolean deleteAccountById(long id) {
        return this.accountRepository.deleteById(id);
    }
}
