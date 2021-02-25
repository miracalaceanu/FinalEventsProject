package ro.itschool.curs.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import lombok.extern.java.Log;
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
		log.info("Am apelat metoda persist");
		session.save(entity);
	}

	@Override
	public void update(OrganizedBy entity) {
		log.info("Am apelat metoda update");
		session.update(entity);
	}

	@Override
	public OrganizedBy findById(Integer id) {
		log.info("Am apelat metoda find");
		return session.get(OrganizedBy.class, id);
	}

	@Override
	public void delete(OrganizedBy entity) {
		log.info("Am apelat metoda delete");
		session.delete(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizedBy> findAll() {
		log.info("Am apelat metoda findAll");
		return session.createQuery("from OrganizedBy").list();
	}

	@Override
	public void deleteAll() {
		log.info("Am apelat metoda deleteAll");
		session.createQuery("delete from OrganizedBy").executeUpdate();

		
	}

	
}