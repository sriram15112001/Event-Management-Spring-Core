package com.deepak.organizer_service.service.impl;


import com.deepak.account_service.model.AccountType;
import com.deepak.account_service.service.IAccountService;
import com.deepak.organizer_service.model.Organizer;
import com.deepak.organizer_service.repository.IOrganizerRepository;
import com.deepak.organizer_service.service.IOrganizerService;

import java.util.List;

public class OrganizerServiceImpl implements IOrganizerService {

    private IOrganizerRepository organizerRepository;
    private IAccountService accountService;

    public OrganizerServiceImpl(IOrganizerRepository repository, IAccountService accountService){
        this.organizerRepository = repository;
        this.accountService = accountService;
    }

    @Override
    public String addOrganzier(String name, String password, String contactInfo) {
        long accountId = this.accountService.addAccount(name, password, AccountType.ORGANIZER);
        int organizerId = organizerRepository.getSize() + 1;
        Organizer organizer = new Organizer(organizerId, contactInfo, accountId);
        organizerRepository.add(organizer);
        return name + " with id : " + organizerId +  " has been added successfully as an organizer";
    }

    @Override
    public Organizer findOrganizerById(long id) {
        return organizerRepository.findById(id);
    }

    @Override
    public List<Organizer> findAllOrganizers() {
        return organizerRepository.findAll();
    }

    @Override
    public String updateOrganizer(long organizerId, String name, String password, String contactInfo){
        Organizer organizer = organizerRepository.findById(organizerId);
        boolean accountUpdate = accountService.updateAccount(organizer.getAccountId(), name, password, AccountType.ORGANIZER);
        if(accountUpdate){
            organizerRepository.update(organizerId, new Organizer(organizerId, contactInfo, organizer.getAccountId()));
            return "update organizer success";
        }
        return "update failed";
    }

    @Override
    public String deleteOrganizer(long id) {
        boolean orgExists = organizerRepository.checkExists(id);
        Organizer organizer = organizerRepository.findById(id);
        if(orgExists){
            organizerRepository.deleteById(id);
            accountService.deleteAccountById(organizer.getAccountId());
            return "organizer " + id + " deleted successfully";
        } else {
            return "Organizer doesn't exist";
        }
    }

    @Override
    public String deleteAllOrganizers() {
        organizerRepository.deleteAll();
        return "All Organizers are deleted";
    }
}

