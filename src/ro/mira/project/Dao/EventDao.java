package ro.mira.project.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import lombok.extern.java.Log;
import ro.mira.project.entity.Address;
import ro.mira.project.entity.Event;
import ro.mira.project.entity.OrganizedBy;
import ro.mira.project.entity.Event;
import ro.mira.project.enums.EventType;
import ro.mira.project.util.HibernateUtils;

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
		
		public Set<Event> listEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Event.class);
			criteria.add(Restrictions.between("localDate", startDate,endDate));
			Set listEventsBetweenDates = (Set) criteria.list();
			return listEventsBetweenDates;
		}
		
		public List<Event> findEventsByType(EventType eventType)  {
			log.info("Am apelat metoda find events by type");
			List<Event> events = session.createQuery("from Event b where b.eventType like CONCAT('%',:name,'%')")
					.setParameter("name", eventType.toString()).list();
			log.info("Event type is " + eventType);
			log.info("These are the events : " + events);
			if (events.isEmpty()) {
			System.out.println(("There are no events of type: " + eventType));}
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