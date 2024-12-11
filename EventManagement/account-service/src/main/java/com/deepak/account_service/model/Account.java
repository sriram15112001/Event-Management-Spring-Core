package com.deepak.account_service.model;

import java.util.Objects;

public class Account {
    private long accountId;
    private String accountName;
    private String accountPassword;
    private Enum<AccountType> accountType;

    public Account(){
    }

    public Account(long accountId, String accountName, String accountPassword, Enum<AccountType> accountType){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountType = accountType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Enum<AccountType> getAccountType() {
        return accountType;
    }

    public void setAccountType(Enum<AccountType> accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("accountId=").append(accountId);
        sb.append(", accountName='").append(accountName).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && Objects.equals(accountName, account.accountName) && Objects.equals(accountPassword, account.accountPassword) && Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountName, accountPassword, accountType);
    }
}
