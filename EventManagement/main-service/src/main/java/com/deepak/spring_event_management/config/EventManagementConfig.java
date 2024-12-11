package com.deepak.spring_event_management.config;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.repository.IAccountRepository;
import com.deepak.account_service.repository.impl.AccountRepositoryImpl;
import com.deepak.account_service.service.IAccountService;
import com.deepak.account_service.service.impl.AccountServiceImpl;
import com.deepak.sharables.Utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventManagementConfig {
    final String JDBC_URL = "jdbc:postgresql://localhost:5445/event_management";
    final String JDBC_USERNAME = "deepak";
    final String JDBC_PASSWORD = "sriram";
    final String POSTGRES_DRIVER = "org.postgresql.Driver";

    @Bean
    public Utility<Account> accountUtility(){
        return new Utility<>(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, POSTGRES_DRIVER);
    }

    @Bean
    public IAccountRepository accountRepository(Utility<Account> accountUtility){
        return new AccountRepositoryImpl(accountUtility);
    }

    @Bean
    public IAccountService accountService(IAccountRepository accountRepository){
        return new AccountServiceImpl(accountRepository);
    }

}
