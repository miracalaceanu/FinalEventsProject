package ro.itschool.curs.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ro.itschool.curs.Dao.AddressDao;
import ro.itschool.curs.Dao.EventDao;
import ro.itschool.curs.Dao.OrganizedByDao;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.enums.TicketType;



public class EventService {
	private EventDao eventDao;

	public EventService() {
		super();
		this.eventDao = new EventDao();
	}

	public void saveEvent(Event event) {
		eventDao.openCurrentSessionwithTransaction();
		eventDao.persist(event);
		eventDao.closeCurrentSessionwithTransaction();
	}

	public void updateEvent(Event event) {
		eventDao.openCurrentSessionwithTransaction();
		eventDao.update(event);
		eventDao.closeCurrentSessionwithTransaction();
	}

	public Event findEventById(int id) {
		eventDao.openCurrentSession();
		Event event = eventDao.findById(id);
		eventDao.closeCurrentSession();
		return event;
	}

	public List<Event> findEventByName(String name) {
		eventDao.openCurrentSession();
		List<Event> events = new ArrayList<Event>();
		try {
			events = eventDao.findEventByName(name);
		} catch (Exception e) {
			System.out.println("ERROR HANDLING");
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return events;
	}

	public List<Event> findEventByDate(LocalDate date) {
		eventDao.openCurrentSession();
		List<Event> events = new ArrayList<Event>();
		try {
			events = eventDao.findEventByDate(date);
		} catch (Exception e) {
			System.out.println("ERROR HANDLING");
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		System.out.println(events);
		return events;
	}
	
//	find events by date, fix date with hyphen if necessary, re-enter date if necessary
	public  List<Event> findEventsByDate() {
		EventService eventService = new EventService();
		Scanner scanner = new Scanner(System.in);
		String date=null;
		LocalDate localDate;
		while(true) {
			System.out.println("Please enter the date in the format 'YYYY-MM-DD' ");
			String scannerDate = scanner.next();
			if(isValid(scannerDate)) {
				date=scannerDate;
						}
			if(scannerDate.length()<10) {
				date= scannerDate.substring(0, 4)+"-"+scannerDate.substring(4, 6)+"-"+scannerDate.substring(6);
				localDate = LocalDate.parse(date);
				}
			if(scannerDate.length()>=10&&(!isValid(scannerDate))) {
				 System.err.println("Wrong date format, please enter date again.");
				 continue;
			}
			break;
		}
		
		scanner.close();
		localDate = LocalDate.parse(date);
		System.err.println("these are the events");
		return eventService.findEventByDate(localDate) ;
	}
	
	private static boolean isValid(String date) {
		try {
			LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	/* Method to CREATE a new EVENT in the database */
	public Event createEvent() {
		eventDao.openCurrentSessionwithTransaction();
		Event event = new Event();
		Address address = new Address();
		OrganizedBy organizedBy = new OrganizedBy();
		Set<OrganizedBy> organizer = new HashSet<>();
		Scanner scanner = new Scanner(System.in);
		System.err.println("ADD NEW EVENT");
		System.out.println("Please enter event name: ");
		String eventName = scanner.nextLine();
		event.setName(eventName);
		System.out.println("Enter event type: ");
		String et = scanner.nextLine();
		event.setEventType(EventType.valueOf(et.toUpperCase()));
		System.out.println("Please enter the date in the format YYYY-MM-DD ");
		String localDate = scanner.nextLine();
		event.setLocalDate(LocalDate.parse(localDate));
		System.out.println("Enter ticket type: ");
		String tt = scanner.nextLine();
		event.setTicketType(TicketType.valueOf(tt.toUpperCase()));
		System.out.println("Enter ticket price: ");
		double ticketPrice = scanner.nextDouble();
		event.setTicketPrice(ticketPrice);

		System.out.println("Enter addressID (look up address IDs on the phpMyAdmin page): ");
		int addressID = scanner.nextInt();
		AddressDao addressDao= new AddressDao();
		addressDao.openCurrentSession();
		address= addressDao.findById(addressID);
		addressDao.closeCurrentSession();
		event.setAddress(address);
		
		String more="yes";
		while(more.charAt(0) == 'y' || more.charAt(0) =='Y') {
		System.out.println("Enter organizerID (look up address IDs on the phpMyAdmin page): ");
		int organizerID = scanner.nextInt();
		OrganizedByDao organizedByDao= new OrganizedByDao();
		organizedByDao.openCurrentSession();
		organizedBy= organizedByDao.findById(organizerID);
		organizer.add(organizedBy);
		organizedByDao.closeCurrentSession();
		event.setOrganizer(organizer);
		System.out.println("Do you want to enter more organizers? (yes/no): ");
		more = scanner.next();
		}
		eventDao.persist(event);

		eventDao.closeCurrentSessionwithTransaction();
		scanner.close();
		System.err.print("This is the event you created:");
		System.out.println(event);
		return event;

	}
	//events between dates
	public List<Event> listEventsBetweenDates(LocalDate startDate, LocalDate endDate){
		eventDao.openCurrentSession();
		List<Event> lista=null;
		try {
			lista = eventDao.listEventsBetweenDates(startDate,endDate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return lista;
	}
	
	public List<Event> findEventsByType(EventType eventType) {
		eventDao.openCurrentSession();
		List<Event> lista=null;
		try {
			lista = eventDao.findEventsByType(eventType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return lista;
	}
	public List<Event>findEventsByTicketType(TicketType ticketType) {
		eventDao.openCurrentSession();
		List<Event> lista=null;
		try {
			lista = eventDao.findEventsByTicketType(ticketType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return lista;
	}
	public List<Event>sortAscEventsByTicketPrice() {
		eventDao.openCurrentSession();
		List<Event> lista=null;
		try {
			lista = eventDao.sortAscEventsByTicketPrice();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return lista;
	}

	public void deleteEvent(Event event) {
		eventDao.openCurrentSessionwithTransaction();
		eventDao.delete(event);
		eventDao.closeCurrentSessionwithTransaction();
	}

	public List<Event> findAllEvents() {
		eventDao.openCurrentSession();
		List<Event> lista = eventDao.findAll();
		eventDao.closeCurrentSession();
		return lista;
	}

	public void deleteAllevents() {
		eventDao.openCurrentSessionwithTransaction();
		eventDao.deleteAll();
		eventDao.closeCurrentSessionwithTransaction();
	}

}
