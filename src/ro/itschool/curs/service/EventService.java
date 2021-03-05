package ro.itschool.curs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import ro.itschool.curs.Dao.EventDao;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.enums.EventType;



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

	public Event  findEventById(int id) {
		eventDao.openCurrentSession();
		Event event = eventDao.findById(id);
		eventDao.closeCurrentSession();
		return event;
	}

	public List<Event> findEventByName(String name) {
		eventDao.openCurrentSession();
		List<Event> events = new ArrayList<Event>();
		try {
			events =	eventDao.findEventByName(name);
		} catch (Exception e) {
			System.out.println("ERROR HANDLING");
			e.printStackTrace();
		}finally {
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
			events =	eventDao.findEventByDate(date);
		} catch (Exception e) {
			System.out.println("ERROR HANDLING");
			e.printStackTrace();
		}finally {
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
		}finally {
			System.out.println("Finally");
		}
		eventDao.closeCurrentSession();
		return "The list is "+ lista;
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
