package ro.itschool.curs.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import lombok.extern.java.Log;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.util.HibernateUtils;

@Log
public class EventDao implements EntityDao<Event, Integer> {
	

		private Session session;

		private Transaction transaction;

		public EventDao() {
		}

		public Session openCurrentSession() {
			session = HibernateUtils.getSessionFactory().openSession();
			return session;
		}

		public Session openCurrentSessionwithTransaction() {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			return session;
		}

		public void closeCurrentSession() {
			session.close();
		}

		public void closeCurrentSessionwithTransaction() {
			transaction.commit();
			session.close();
		}

		@Override
		public void persist(Event entity) {
			log.info("Am apelat metoda persist");
			session.save(entity);
		}

		@Override
		public void update(Event entity) {
			log.info("Am apelat metoda update");
			session.update(entity);

		}

		@Override
		public Event findById(Integer id) {
			log.info("Am apelat metoda find");
			return session.get(Event.class, id);

		}

		public List<Event> findEventByName(String name) {
			log.info("Am apelat metoda find by name");
			List<Event> events = session.createQuery("from Event").list();
			List<Event> filteredEvent = new ArrayList<>();
			for (Event event : events) {
				if (event.getName().contains(name))
					filteredEvent.add(event);
			}
			return filteredEvent;
		}
		
//		@SuppressWarnings("unchecked")// name of organizer found in event-mapping class
//		public List<Event> findEventsOrganizedBy(String name) throws Exception {
//			log.info("method findEventsOrganizedBy is called");
//			List<Event> events = session.createQuery("from Event b where b.name like CONCAT('%',:name,'%')")
//					.setParameter("name", name).list();
//			log.info("OrganizedBy name is: " + name);
//			log.info("events organizedBy " +name+ " are: " + events);
//			if (events.isEmpty())
//				throw new Exception("there are no events OrganizedBy: " + name);
//			return events;
//		}
		
		public List<Event> findEventByDate(LocalDate date) {
			log.info("Am apelat metoda find by date");
			List<Event> events = session.createQuery("from Event").list();
			List<Event> eventsByDate = new ArrayList<>();
			for (Event event : events) {
				if (event.getLocalDate().equals(date))
					eventsByDate.add(event);
			}
			return eventsByDate;
		}
		
		public List<Event> findEventsByType(EventType eventType) throws Exception {
			log.info("Am apelat metoda find events by type");
			@SuppressWarnings("unchecked")
			List<Event> events = session.createQuery("from Event b where b.eventType like CONCAT('%',:name,'%')")
					.setParameter("name", eventType).list();
			log.info("Event type is " + eventType);
			log.info("These are the events : " + events);
			if (events.isEmpty())
				throw new Exception("There are no events of type: " + eventType);
			return events;
		}
		
	
		

		@Override
		public void delete(Event entity) {
			log.info("Am apelat metoda delete");
			session.delete(entity);

		}

		@Override
		public List<Event> findAll() {
			log.info("Am apelat metoda findAll");
			return session.createQuery("from event").list();
		}

		@Override
		public void deleteAll() {
			log.info("Am apelat metoda deleteAll");
			session.createQuery("delete from event").executeUpdate();

		}

	}


