package com.deepak.spring_event_management.Executable;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.model.AccountType;
import com.deepak.account_service.service.IAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final IAccountService accountService;

    public Runner(IAccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        long accountId = accountService.addAccount("Deepak", "Deepak", AccountType.ORGANIZER);
        Account account = accountService.findByAccountId(accountId);
        System.out.println(account);


    }
}
