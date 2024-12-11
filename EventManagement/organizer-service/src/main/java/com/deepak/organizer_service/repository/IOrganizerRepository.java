package com.deepak.organizer_service.repository;

import com.deepak.organizer_service.model.Organizer;

import java.util.List;

public interface IOrganizerRepository {
    public boolean add(Organizer organizer);

    public Organizer findById(long organizerId);

    public List<Organizer> findAll();

    public boolean update(long organizerId, Organizer organizer);

    public boolean deleteById(long organizerId);

    public boolean deleteAll();

    public int getSize();

    public boolean checkExists(long organizerId);
}
