package com.deepak.spring_event_management.config;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.repository.IAccountRepository;
import com.deepak.account_service.repository.impl.AccountRepositoryImpl;
import com.deepak.account_service.service.IAccountService;
import com.deepak.account_service.service.impl.AccountServiceImpl;
import com.deepak.event_service.model.Event;
import com.deepak.event_service.repository.IEventRepository;
import com.deepak.event_service.repository.impl.EventRepositoryImpl;
import com.deepak.event_service.service.IEventService;
import com.deepak.event_service.service.impl.EventServiceImpl;
import com.deepak.organizer_service.model.Organizer;
import com.deepak.organizer_service.repository.IOrganizerRepository;
import com.deepak.organizer_service.repository.impl.OrganizerRepositoryImpl;
import com.deepak.organizer_service.service.IOrganizerService;
import com.deepak.organizer_service.service.impl.OrganizerServiceImpl;
import com.deepak.sharables.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class EventManagementConfig {
    private final String JDBC_URL;
    private final String JDBC_USERNAME;
    private final String JDBC_PASSWORD;
    private final String DRIVER;

    public EventManagementConfig(
            @Value("${deepak.url}") String JDBC_URL,
            @Value("${deepak.username}") String JDBC_USERNAME,
            @Value("${deepak.password}") String JDBC_PASSWORD,
            @Value("${deepak.driver}") String DRIVER) {

        this.JDBC_URL = JDBC_URL;
        this.JDBC_PASSWORD = JDBC_PASSWORD;
        this.JDBC_USERNAME = JDBC_USERNAME;
        this.DRIVER = DRIVER;
    }

    @Bean
    public Utility<Account> accountUtility(){
        return new Utility<>(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DRIVER);
    }

    @Bean
    public IAccountRepository accountRepository(Utility<Account> accountUtility){
        return new AccountRepositoryImpl(accountUtility);
    }

    @Bean
    public IAccountService accountService(IAccountRepository accountRepository){
        return new AccountServiceImpl(accountRepository);
    }

    @Bean
    public Utility<Organizer> organizerUtility(){
        return new Utility<>(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DRIVER);
    }

    @Bean
    public IOrganizerRepository organizerRepository(Utility<Organizer> organizerUtility){
        return new OrganizerRepositoryImpl(organizerUtility);
    }

    @Bean
    public IOrganizerService organizerService(IOrganizerRepository organizerRepository, IAccountService accountService){
        return new OrganizerServiceImpl(organizerRepository, accountService);
    }

    @Bean
    public Utility<Event> eventUtility(){
        return new Utility<Event>(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DRIVER);
    }

    @Bean
    public IEventRepository eventRepository(Utility<Event> eventUtility){
        return new EventRepositoryImpl(eventUtility);
    }

    @Bean
    public IEventService eventService(IOrganizerRepository organizerRepository, IEventRepository eventRepository, IAccountRepository accountRepository){
        return new EventServiceImpl(organizerRepository, eventRepository, accountRepository);
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}
