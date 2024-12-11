package com.deepak.event_service.repository;

import com.deepak.event_service.model.Event;

import java.util.List;

public interface IEventRepository {
    public boolean add(Event event);

    public Event findById(long id);

    public List<Event> findAll();

    public boolean update(long eventId, Event event);

    public boolean delete(long eventId);

    public int eventSize();

    public List<Event> pagination(int pageNumber, int pageSize);

    public List<Event> sortByDateAsc();

    public List<Event> sortByDateDesc();

}

