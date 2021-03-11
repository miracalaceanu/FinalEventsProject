package ro.itschool.curs.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;

import lombok.extern.java.Log;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.enums.TicketType;
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
			log.info("Persist method is called");
			session.save(entity);
		}

		@Override
		public void update(Event entity) {
			log.info("Update method is called");
			session.update(entity);

		}

		@Override
		public Event findById(Integer id) {
			log.info("Find method is called");
			return session.get(Event.class, id);

		}
/**
 * finds events that contain the "name" String passed as argument
 * @param name
 * @return list of events that contain "name"
 */
		public List<Event> findEventByName(String name) {
			log.info("Find by name method is called");
			List<Event> events = session.createQuery("from Event").list();
			List<Event> filteredEvent = new ArrayList<>();
			for (Event event : events) {
				if (event.getName().contains(name))
					filteredEvent.add(event);
			}
			return filteredEvent;
		}
		
		/**
		 * finds events that take place at a certain date
		 * @param date
		 * @return 	list of events 
		 */
			public List<Event> findEventByDate(LocalDate date) {
			log.info("Find by date method is called");
			List<Event> events = session.createQuery("from Event").list();
			List<Event> eventsByDate = new ArrayList<>();
			for (Event event : events) {
				if (event.getLocalDate().equals(date))
					eventsByDate.add(event);
			}
			if(eventsByDate.isEmpty()) {
				System.err.println("There are no events on:" +date );
			}
			return eventsByDate;
		}
		//events between dates
		public List<Event> listEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Event.class);
			criteria.add(Restrictions.between("localDate", startDate,endDate));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// getting duplicates because of EAGER fetch
			List listEventsBetweenDates =  criteria.list();
			return listEventsBetweenDates;
		}
		
		public List<Event> findEventsByType(EventType eventType)  {
			log.info("Find events by type method is called");
			List<Event> events = session.createQuery("from Event b where b.eventType like CONCAT('%',:name,'%')")
					.setParameter("name", eventType.toString()).list();
			log.info("Event type is " + eventType);
			log.info("These are the events : " + events);
			if (events.isEmpty()) {
			System.out.println(("There are no events of type: " + eventType));}
			return events;
		}
		
		public List<Event> findEventsByTicketType(TicketType ticketType)  {
			log.info("findEventsByTicketType method is called");
			List<Event> events = session.createQuery("from Event b where b.ticketType = :name")
					.setParameter("name", ticketType).list();
			log.info ("Events with " + ticketType+ " tickets are: ");
			if (events.isEmpty()) {
			System.err.println(("There are no events of type: " + ticketType));}
			return events;
		}
		
		public List<Event> sortAscEventsByTicketPrice() {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Event.class, "Event");
			criteria.addOrder(org.hibernate.criterion.Order.asc("ticketPrice"));
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// getting duplicates because of EAGER fetch
			List listEventsBetweenDates =  criteria.list();
			return listEventsBetweenDates;
		}
		
		

		@Override
		public void delete(Event entity) {
			log.info("Delete method is called");
			session.delete(entity);

		}

		@Override
		public List<Event> findAll() {
			log.info("FindAll method is called");
			return session.createQuery("from event").list();
		}

		@Override
		public void deleteAll() {
			log.info("DeleteAll method is called");
			session.createQuery("delete from event").executeUpdate();

		}

	}


