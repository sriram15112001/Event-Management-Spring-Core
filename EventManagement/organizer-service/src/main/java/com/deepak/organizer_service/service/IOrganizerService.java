package com.deepak.organizer_service.service;

import com.deepak.organizer_service.model.Organizer;

import java.util.List;

public interface IOrganizerService {
    public String addOrganzier(String name, String password, String contactInfo);

    public Organizer findOrganizerById(long id);

    public List<Organizer> findAllOrganizers();

    public String updateOrganizer(long organizerId, String name, String password, String contactInfo);

    public String deleteOrganizer(long id);

    public String deleteAllOrganizers();
}

