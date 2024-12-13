package com.deepak.spring_event_management.Executable;

import com.deepak.account_service.service.IAccountService;
import com.deepak.event_service.model.Event;
import com.deepak.event_service.model.OrganizerEventCount;
import com.deepak.event_service.service.IEventService;
import com.deepak.organizer_service.model.Organizer;
import com.deepak.organizer_service.service.IOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final IAccountService accountService;
    private final IOrganizerService organizerService;
    private final IEventService eventService;
    private final Scanner scanner;

    @Autowired
    public Runner(IAccountService accountService, IOrganizerService organizerService, IEventService eventService, Scanner scanner){
        this.accountService = accountService;
        this.organizerService = organizerService;
        this.eventService = eventService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Event Management system");
        while(true){
            System.out.println("1. Organizer\n2. Events\n3. Exit");
            int option = scanner.nextInt();
            if(option == 1){
                boolean orgOptionRunning = true;
                while(orgOptionRunning){
                    System.out.println("1. Create Organizer\n2. Find Organizer by id\n3. Find All Organizers\n4. Update Organizer\n5. Delete Organizer\n6. Exit");
                    int organizerOption = scanner.nextInt();
                    switch(organizerOption){
                        case 1:
                            System.out.println("Enter the organizer name : (one word)");
                            String orgName = scanner.next();
                            System.out.println("Enter the password : (one word)");
                            String password = scanner.next();
                            System.out.println("Enter the contact info : (one word)");
                            String contactInfo = scanner.next();
                            System.out.println(organizerService.addOrganzier(orgName, password, contactInfo));
                            break;
                        case 2:
                            System.out.println("Enter the organizer id:");
                            long orgId = scanner.nextLong();
                            Organizer organizer = organizerService.findOrganizerById(orgId);
                            System.out.println(organizer);
                            break;
                        case 3:
                            List<Organizer> organizers = organizerService.findAllOrganizers();
                            for(Organizer org: organizers){
                                System.out.println(org);
                            }
                            break;
                        case 4:
                            System.out.println("Enter the organizer id : ");
                            long organizerId = scanner.nextLong();
                            System.out.println("Enter the organizer name : ");
                            String organizerName = scanner.next();
                            System.out.println("Enter the organizer password : ");
                            String pass = scanner.next();
                            System.out.println("Enter the organizer contact-info : ");
                            String contInfo = scanner.next();
                            System.out.println(organizerService.updateOrganizer(organizerId, organizerName, pass, contInfo));
                            break;
                        case 5:
                            System.out.println("Enter the organizer id : ");
                            long id = scanner.nextLong();
                            System.out.println(organizerService.deleteOrganizer(id));
                            break;
                        case 6:
                            orgOptionRunning = false;
                    }
                }
            }
            else if (option == 2){
                boolean eventRunning = true;
                while(eventRunning){
                    System.out.println("1. Create Event\n2. Find event by Id\n3. Find All Events\n4. Update Event\n5. Delete Event\n6. Pagination\n7. Event with organizer Name\n8. Sort event by date ascending\n9. Sort event by date descending\n10. View Organizer's event\n11. View number of events of organizer\n12. View number of events of organizer descending\n13. Exit");
                    int userInput = scanner.nextInt();
                    if(userInput == 13){
                        eventRunning = false;
                    }
                    else if(userInput < 1 || userInput > 12){
                        System.out.println("Enter the correct option");
                    } else {
                        switch (userInput){
                            case 1:
                                System.out.println("Enter the event name : (one word)");
                                String name = scanner.next();
                                System.out.println("Enter the event description : (one word)");
                                String desc = scanner.next();
                                System.out.println("Enter thh event location : (one word)");
                                String location = scanner.next();
                                System.out.println("Enter the event date : (YYYY-MM-DD)");
                                String date = scanner.next();
                                System.out.println("Enter the event timing : (HH:MM)");
                                String time = scanner.next();
                                System.out.println("Enter the organizer Id : ");
                                long organizerId = scanner.nextLong();
                                System.out.println(eventService.addEvent(name, desc, location, date, time, organizerId));
                                break;
                            case 2:
                                System.out.println("Enter the event id : ");
                                long eventId = scanner.nextLong();
                                System.out.println(eventService.findById(eventId));
                                break;
                            case 3:
                                List<Event> events = eventService.findAll();
                                if(events.isEmpty()){
                                    System.out.println("Currently no events created");
                                } else {
                                    for(Event event: events){
                                        System.out.println(event);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Enter the even id to update : ");
                                long ueventId = scanner.nextLong();
                                System.out.println("Enter the event name : (one word)");
                                String uname = scanner.next();
                                System.out.println("Enter the event description : (one word)");
                                String udesc = scanner.next();
                                System.out.println("Enter thh event location : (one word)");
                                String ulocation = scanner.next();
                                System.out.println("Enter the event date : (YYYY-MM-DD)");
                                String udate = scanner.next();
                                System.out.println("Enter the event timing : (HH:MM)");
                                String utime = scanner.next();
                                System.out.println("Enter the organizer Id : ");
                                long uorganizerId = scanner.nextLong();
                                System.out.println(eventService.updateEvent(ueventId, uname, udesc, ulocation, udate, utime, uorganizerId));
                                break;
                            case 5:
                                System.out.println("Enter the event to delete");
                                long eveId = scanner.nextLong();
                                System.out.println(eventService.deleteEvent(eveId));
                                break;
                            case 6:
                                System.out.println("Enter the page number : ");
                                int pageNumber = scanner.nextInt();
                                System.out.println("Enter the page size : ");
                                int pageSize = scanner.nextInt();
                                List<Event> evts = eventService.pagination(pageNumber, pageSize);
                                for(Event evt: evts){
                                    System.out.println(evt);
                                }
                                break;
                            case 7:
                                System.out.println("Enter the event id : ");
                                long eventid = scanner.nextLong();
                                System.out.println(eventService.viewEventNameAlongWithOrgName(eventid));
                                break;
                            case 8:
                                List<Event> eventsAsc = eventService.sortByAsc();
                                for(Event evt: eventsAsc){
                                    System.out.println(evt);
                                }
                                break;
                            case 9:
                                List<Event> eventsDesc = eventService.sortByDesc();
                                for(Event evt: eventsDesc){
                                    System.out.println(evt);
                                }
                                break;
                            case 10:
                                System.out.println("Enter the organizer Id : ");
                                long orId = scanner.nextLong();
                                List<Event> eventList = eventService.viewEventByOrganizer(orId);
                                for(Event e: eventList){
                                    System.out.println(e);
                                }
                                break;
                            case 11:
                                List<OrganizerEventCount> organizerEventCounts = eventService.viewOrganizerEventCount();
                                for(OrganizerEventCount org: organizerEventCounts){
                                    System.out.println(org);
                                }
                                break;
                            case 12:
                                List<OrganizerEventCount> organizerEventCountsDesc = eventService.viewOrganzierEventCountOrderDesc();
                                for(OrganizerEventCount org: organizerEventCountsDesc){
                                    System.out.println(org);
                                }
                                break;
                        }
                    }
                }
            } else {
                break;
            }
        }
    }
}

