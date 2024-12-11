package com.deepak.event_service.service;

import com.deepak.event_service.model.Event;
import com.deepak.event_service.model.OrganizerEventCount;

import java.util.List;

public interface IEventService {

    String addEvent(String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, long organizerId);

    Event findById(long eventId);

    List<Event> findAll();

    String updateEvent(long eventId, String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, long organizerId);

    String deleteEvent(long eventId);

    List<Event> pagination(int pageNumber, int pageSize);

    public String viewEventNameAlongWithOrgName(long eventId);

    List<Event> sortByAsc();

    List<Event> sortByDesc();

    List<Event> viewEventByOrganizer(long organizerId);

    List<OrganizerEventCount> viewOrganizerEventCount();

    List<OrganizerEventCount> viewOrganzierEventCountOrderDesc();
}
