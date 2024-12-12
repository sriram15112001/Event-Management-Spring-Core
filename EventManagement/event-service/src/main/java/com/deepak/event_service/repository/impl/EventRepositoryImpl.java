package com.deepak.event_service.repository.impl;


import com.deepak.event_service.model.Event;
import com.deepak.event_service.repository.IEventRepository;
import com.deepak.sharables.Utility;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements IEventRepository {
    private final Utility<Event> utility;

    public EventRepositoryImpl(Utility<Event> utility){
        this.utility = utility;
        String query = "CREATE TABLE IF NOT EXISTS events(\n" +
                "    event_id BIGINT PRIMARY KEY,\n" +
                "    event_name VARCHAR(30) NOT NULL,\n" +
                "    event_description VARCHAR(50),\n" +
                "    event_location VARCHAR(50) NOT NULL,\n" +
                "    event_date DATE NOT NULL,\n" +
                "    event_time TIME NOT NULL,\n" +
                "    fk_organizer_id BIGINT NOT NULL,\n" +
                "    FOREIGN KEY(fk_organizer_id) REFERENCES organizers(organizer_id) ON DELETE CASCADE\n" +
                ");";
        this.utility.execute(query);

    }
    @Override
    public boolean add(Event event) {
        String query = "INSERT INTO events values(?,?,?,?,?,?,?);";
        int result = this.utility.executeUpdate(query, event.getEventId(), event.getEventName(), event.getEventDescription(), event.getEventLocation(), event.getEventDate(), event.getEventTime(), event.getOrganizerId());
        return result == 1;
    }

    @Override
    public Event findById(long id) {
        String query = "SELECT * from events WHERE event_id = ?";
        return this.utility.findOne(query, resultSet -> {
            try {
                long event_id = resultSet.getLong(1);
                String event_name = resultSet.getString(2);
                String event_description = resultSet.getString(3);
                String event_location = resultSet.getString(4);
                LocalDate event_date = resultSet.getDate(5).toLocalDate();
                LocalTime event_time = resultSet.getTime(6).toLocalTime();
                long organizer_id = resultSet.getLong(7);
                return new Event(event_id, event_name, event_description, event_location, event_date, event_time, organizer_id);
            } catch (SQLException e){
                return new Event();
            }
        }, id);
    }

    @Override
    public List<Event> findAll() {
        String query = "SELECT * FROM events";
        return this.utility.findAll(query, resultSet -> {
            List<Event> events = new ArrayList<>();
            try {
                while(resultSet.next()){
                    long event_id = resultSet.getLong(1);
                    String event_name = resultSet.getString(2);
                    String event_description = resultSet.getString(3);
                    String event_location = resultSet.getString(4);
                    LocalDate event_date = resultSet.getDate(5).toLocalDate();
                    LocalTime event_time = resultSet.getTime(6).toLocalTime();
                    long organizer_id = resultSet.getLong(7);
                    events.add(new Event(event_id, event_name, event_description, event_location, event_date, event_time, organizer_id));
                }
                return events;
            } catch (SQLException e){
                return events;
            }
        });
    }

    @Override
    public boolean update(long eventId, Event event) {
        String query = "UPDATE events SET event_id = ?, event_name = ?, event_description = ?, event_location = ?, event_date = ?, event_time = ?, fk_organizer_id = ? WHERE event_id = ?;";
        int result = this.utility.executeUpdate(query, event.getEventId(), event.getEventName(), event.getEventDescription(), event.getEventLocation(), String.valueOf(event.getEventDate()), String.valueOf(event.getEventTime()), event.getOrganizerId(), eventId);
        return result == 1;
    }

    @Override
    public boolean delete(long eventId) {
        String query = "DELETE FROM events WHERE event_id = ?";
        int result = this.utility.executeUpdate(query, eventId);
        return result == 1;
    }

    @Override
    public int eventSize() {
        String query = "SELECT COUNT(*) FROM events;";
        Event eve = this.utility.findOne(query, resultSet -> {
            try {
                int size = resultSet.getInt(1);
                Event event = new Event();
                event.setEventId(size);
                return event;
            } catch (SQLException e){
                return new Event();
            }
        });
        return (int) eve.getEventId();
    }

    @Override
    public List<Event> pagination(int pageNumber, int pageSize) {
        String query = "SELECT * FROM events ORDER BY event_id LIMIT ? OFFSET ?";
        List<Event> events = new ArrayList<>();
        return this.utility.findAll(query, resultSet -> {
            try{
                while(resultSet.next()){
                    long event_id = resultSet.getLong(1);
                    String event_name = resultSet.getString(2);
                    String event_description = resultSet.getString(3);
                    String event_location = resultSet.getString(4);
                    LocalDate event_date = resultSet.getDate(5).toLocalDate();
                    LocalTime event_time = resultSet.getTime(6).toLocalTime();
                    long organizer_id = resultSet.getLong(7);
                    events.add(new Event(event_id, event_name, event_description, event_location, event_date, event_time, organizer_id));
                }
                return events;
            } catch (SQLException e){
                return events;
            }
        }, pageNumber, pageSize);
    }

    @Override
    public List<Event> sortByDateAsc() {
        String query = "SELECT * FROM events ORDER BY event_date;";
        List<Event> events = new ArrayList<>();
        return this.utility.findAll(query, resultSet -> {
            try{
                while(resultSet.next()){
                    long event_id = resultSet.getLong(1);
                    String event_name = resultSet.getString(2);
                    String event_description = resultSet.getString(3);
                    String event_location = resultSet.getString(4);
                    LocalDate event_date = resultSet.getDate(5).toLocalDate();
                    LocalTime event_time = resultSet.getTime(6).toLocalTime();
                    long organizer_id = resultSet.getLong(7);
                    events.add(new Event(event_id, event_name, event_description, event_location, event_date, event_time, organizer_id));
                }
                return events;
            } catch (SQLException e){
                return events;
            }
        });
    }

    @Override
    public List<Event> sortByDateDesc() {
        String query = "SELECT * FROM events ORDER BY event_date DESC;";
        List<Event> events = new ArrayList<>();
        return this.utility.findAll(query, resultSet -> {
            try{
                while(resultSet.next()){
                    long event_id = resultSet.getLong(1);
                    String event_name = resultSet.getString(2);
                    String event_description = resultSet.getString(3);
                    String event_location = resultSet.getString(4);
                    LocalDate event_date = resultSet.getDate(5).toLocalDate();
                    LocalTime event_time = resultSet.getTime(6).toLocalTime();
                    long organizer_id = resultSet.getLong(7);
                    events.add(new Event(event_id, event_name, event_description, event_location, event_date, event_time, organizer_id));
                }
                return events;
            } catch (SQLException e){
                return events;
            }
        });
    }
}

