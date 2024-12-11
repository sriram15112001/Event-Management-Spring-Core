package com.deepak.event_service.service.impl;

import com.deepak.account_service.model.Account;
import com.deepak.account_service.repository.IAccountRepository;
import com.deepak.event_service.model.Event;
import com.deepak.event_service.model.OrganizerEventCount;
import com.deepak.event_service.repository.IEventRepository;
import com.deepak.event_service.service.IEventService;
import com.deepak.organizer_service.model.Organizer;
import com.deepak.organizer_service.repository.IOrganizerRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventServiceImpl implements IEventService {

    private final IEventRepository eventRepository;
    private final IOrganizerRepository organizerRepository;
    private final IAccountRepository accountRepository;

    public EventServiceImpl(IOrganizerRepository organizerRepository, IEventRepository eventRepository, IAccountRepository accountRepository){
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Adds a new event to the repository.
     * @param eventName
     * @param eventDescription
     * @param eventLocation
     * @param eventDate
     * @param eventTime
     * @param organizerId
     * @return A message indicating the success of the add operation, including the ID of the added event.
     */
    @Override
    public String addEvent(String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, long organizerId) {
        int eventId = eventRepository.eventSize() + 1;
        Event event = new Event(eventId, eventName, eventDescription, eventLocation, LocalDate.parse(eventDate), LocalTime.parse(eventTime), organizerId);
        eventRepository.add(event);
        return "Event " + eventId + " added successfully";
    }

    /**
     * Retrieves an event by its ID from the repository.
     * @param eventId
     * @return The {@link Event} object with the specified ID, or {@code null} if the event with the given ID does not exist in the repository.
     */
    @Override
    public Event findById(long eventId) {
        return eventRepository.findById(eventId);
    }

    /**
     * Retrieves a list of all events from the repository.
     * @return A list of {@link Event} objects representing all the events in the repository.
     *         If no events are found, an empty list is returned.
     */
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    /**
     * Updates the details of an event based on the provided event ID.
     * @param eventId
     * @param eventName
     * @param eventDescription
     * @param eventLocation
     * @param eventDate
     * @param eventTime
     * @param organizerId
     * @return A message indicating the success or failure of the update operation.
     *           Returns "Updated Event successfully" if the event was updated,
     *           or "Event id not found" if the event with the given ID was not found.
     */
    @Override
    public String updateEvent(long eventId, String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, long organizerId) {
        Event event = new Event(eventId, eventName, eventDescription, eventLocation, LocalDate.parse(eventDate), LocalTime.parse(eventTime), organizerId);
        boolean isUpdated = eventRepository.update(eventId, event);
        return isUpdated ? "Updated Event successfully" : "Event id not found";
    }

    /**
     * Deletes an event based on the provided event ID.
     * @param eventId
     * @return A message indicating the success or failure of the delete operation.
     *           Returns "Event Deleted successfully" if the event was deleted,
     *           or "Event id not found" if the event with the given ID was not found.
     */
    @Override
    public String deleteEvent(long eventId) {
        boolean isDeleted = eventRepository.delete(eventId);
        return isDeleted ? "Event Deleted successfully" : "Event id not found";
    }


    @Override
    public List<Event> pagination(int pageNumber, int pageSize) {
        return eventRepository.pagination(pageNumber, pageSize);
    }

    @Override
    public String viewEventNameAlongWithOrgName(long eventId) {
        Event event = eventRepository.findById(eventId);
        long organizerId = event.getOrganizerId();
        Organizer organizer = organizerRepository.findById(organizerId);
        Account account = accountRepository.findById(organizer.getAccountId());
        return "Organizer : " + account.getAccountName() + " Event : " + event.getEventName();
    }

    @Override
    public List<Event> sortByAsc() {
        return eventRepository.sortByDateAsc();
    }

    @Override
    public List<Event> sortByDesc() {
        return eventRepository.sortByDateDesc();
    }

    @Override
    public List<Event> viewEventByOrganizer(long organizerId) {
        List<Event> events = eventRepository.findAll();
        List<Event> orgEvents = new ArrayList<>();
        for(Event event: events){
            if(event.getOrganizerId() == organizerId){
                orgEvents.add(event);
            }
        }
        return orgEvents;
    }

    @Override
    public List<OrganizerEventCount> viewOrganizerEventCount() {
        List<Event> events = eventRepository.findAll();
        List<Organizer> organizers = organizerRepository.findAll();
        List<OrganizerEventCount> organizerEventCounts = new ArrayList<>();
        for(Organizer org: organizers){
            int count = 0;
            for(Event event: events){
                if(event.getOrganizerId() == org.getAccountId()){
                    count++;
                }
            }
            Account account = accountRepository.findById(org.getAccountId());
            OrganizerEventCount organizerEventCount = new OrganizerEventCount();
            organizerEventCount.setOrganizerId(org.getOrganizerId());
            organizerEventCount.setOrganizerName(account.getAccountName());
            organizerEventCount.setEventCount(count);
            organizerEventCounts.add(organizerEventCount);
        }
        return organizerEventCounts;
    }

    @Override
    public List<OrganizerEventCount> viewOrganzierEventCountOrderDesc() {
        List<OrganizerEventCount> organizerEventCounts = this.viewOrganizerEventCount();
        for(int i = 0; i < organizerEventCounts.size() - 1; i++){
            for(int j = i + 1; j < organizerEventCounts.size(); j++){
                if(organizerEventCounts.get(i).getEventCount() < organizerEventCounts.get(j).getEventCount()){
                    OrganizerEventCount temp = organizerEventCounts.get(i);
                    organizerEventCounts.set(i, organizerEventCounts.get(j));
                    organizerEventCounts.set(j, temp);
                }
            }
        }
        return organizerEventCounts;
    }


}
