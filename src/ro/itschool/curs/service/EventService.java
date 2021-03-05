package ro.itschool.curs.service;

import java.time.LocalDate;
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
//	public List<Event> findEventsOrganizedBy(String name) {
//		eventDao.openCurrentSession();
//		List<Event> events = new ArrayList<Event>();
//		try {
//			events =	eventDao.findEventsOrganizedBy(name);
//		} catch (Exception e) {
//			System.out.println("ERROR HANDLING");
//			e.printStackTrace();
//		}finally {
//			System.out.println("Finally");
//		}
//		eventDao.closeCurrentSession();	
//		return events;
//	}

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
		return events;
	}

	public String findEventsByType(EventType eventType) {
		eventDao.openCurrentSession();
		List<Event> lista = null;
		try {
			lista = eventDao.findEventsByType(eventType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return "The list is " + lista;
	}

	/* Method to CREATE a new EVENT in the database */
	public Event createEvent() {
		eventDao.openCurrentSessionwithTransaction();
		Event event = new Event();
		Address address = new Address();
		Set<OrganizedBy> organizer = new HashSet<>();
		OrganizedBy organizedBy = new OrganizedBy();
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

		event.setAddress(address);
		System.out.println("Enter institution name : ");
		String addressName = scanner.next();
		address.setName(addressName);
		System.out.println("Enter street name : ");
		String addressStreetName = scanner.next();
		address.setStreetName(addressStreetName);
		System.out.println("Enter number : ");
		int addressNumber = scanner.nextInt();
		address.setNumber(addressNumber);
		System.out.println("Enter website : ");
		String addressWebsite = scanner.next();
		address.setWebsite(addressWebsite);
		System.out.println("Enter phone number : ");
		String addressPhoneNumber = scanner.next();
		address.setPhoneNumber(addressPhoneNumber);

		event.setOrganizer(organizer);
		System.out.println("Enter organizer name: ");
		String organizerName = scanner.next();
		organizedBy.setName(organizerName);

		System.out.println("Organizer fonded in year: ");
		int organizerFounded = scanner.nextInt();
		organizedBy.setFounded(organizerFounded);
		organizer.add(organizedBy);

		eventDao.persist(event);
	
		eventDao.closeCurrentSessionwithTransaction();
		scanner.close();
		System.err.print("This is the event you created:" );
		System.out.println(event);
		return event;

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
