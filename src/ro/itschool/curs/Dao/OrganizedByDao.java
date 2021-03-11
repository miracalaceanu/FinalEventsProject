package ro.itschool.curs.Dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.type.Type;

import lombok.extern.java.Log;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.util.HibernateUtils;

@Log
public class OrganizedByDao implements EntityDao<OrganizedBy, Integer> {
	private Session session;

	private Transaction transaction;

	public OrganizedByDao() {
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
	public void persist(OrganizedBy entity) {
		log.info("Persist method is called");
		session.save(entity);
	}

	@Override
	public void update(OrganizedBy entity) {
		log.info("Update method is called");
		session.update(entity);
	}

	@Override
	public OrganizedBy findById(Integer id) {
		log.info("Find method is called");
		return session.get(OrganizedBy.class, id);
	}

//	CRITERIA
	public List<OrganizedBy> listOrganizerById(int id) {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(OrganizedBy.class);
		criteria.add(Restrictions.eq("id", id));
		List organizer = criteria.list();
		return organizer;
	}

// enhanced for- nested loop
	public List<Event> listEventsByOrganizer(String name) {
		List<Event> events = session.createQuery("from Event").list();
		List<Event> filteredEvent = new ArrayList<>();
		for (Event event : events) {
			for (OrganizedBy organizedBy : event.getOrganizer())
				if (organizedBy.getName().contains(name))
					filteredEvent.add(event);
		}
		if (filteredEvent.isEmpty()) {
			System.err.println("There are no events organized by : " + name);
		}
		return filteredEvent;
	}

	// suficient sa folosim o parte din nume
	public List<OrganizedBy> findOrganizerByName(String name) throws Exception {
		log.info("findOrganizedbyByName method is called");
		List<OrganizedBy> organizer = session.createQuery("from OrganizedBy b where b.name like CONCAT('%',:name,'%')")
				.setParameter("name", name).list();
		log.info("Name of the organizer is: " + name);
		log.info("These are the organizers: " + organizer);
		if (organizer.isEmpty())
			throw new Exception("There are no organizers called: " + name);
		return organizer;
	}

	@Override
	public void delete(OrganizedBy entity) {
		log.info("Delete method is called");
		session.delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizedBy> findAll() {
		log.info("FindAll method is called");
		return session.createQuery("from OrganizedBy").list();
	}

	public List<OrganizedBy> filterOrganizersByFounded(int founded) {
		List<OrganizedBy> organizers = session.createQuery("from OrganizedBy").list();
		List<OrganizedBy> filteredOrganizers = new ArrayList<>();
		for (OrganizedBy organizedBy : organizers) {
			if (organizedBy.getFounded() == founded)
				filteredOrganizers.add(organizedBy);
		}
		if (filteredOrganizers.isEmpty())
			System.out.println("filteredOrganizers is empty");
		
		return filteredOrganizers;
	}

	@Override
	public void deleteAll() {
		log.info("DeleteAll method is called");
		session.createQuery("delete from OrganizedBy").executeUpdate();

	}

}