package com.deepak.event_service.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {
    private long eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private long organizerId;
    private List<Long> users;

    public Event() {
    }

    public Event(long eventId, String eventName, String eventDescription, String eventLocation, LocalDate eventDate, LocalTime eventTime, long organizerId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.organizerId = organizerId;
        this.users = new ArrayList<>();
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }

    public void addUser(long userId){
        this.users.add(userId);
    }

    public void removeUser(long userId){
        this.users.remove(userId);
    }

    public List<Long> getUsers(){
        return this.users;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", organizerId=" + organizerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && organizerId == event.organizerId && Objects.equals(eventName, event.eventName) && Objects.equals(eventDescription, event.eventDescription) && Objects.equals(eventLocation, event.eventLocation) && Objects.equals(eventDate, event.eventDate) && Objects.equals(eventTime, event.eventTime) && Objects.equals(users, event.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventDescription, eventLocation, eventDate, eventTime, organizerId, users);
    }
}

