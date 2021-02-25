package ro.itschool.curs.service;

import java.util.ArrayList;
import java.util.List;

import ro.itschool.curs.Dao.EventDao;
import ro.itschool.curs.entity.Event;



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
			System.out.println("AICI GESTIONAM EROAREA");
			e.printStackTrace();
		}finally {
			System.out.println("Suntem in finally");
		}
		eventDao.closeCurrentSession();	
		return events;
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
