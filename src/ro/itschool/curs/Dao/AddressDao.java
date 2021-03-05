package ro.itschool.curs.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import lombok.extern.java.Log;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.util.HibernateUtils;

@Log
public class AddressDao implements EntityDao<Address, Integer> {
	
	private Session session;

	private Transaction transaction;


	public AddressDao() {
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
	public void persist(Address entity) {
		log.info("Am apelat metoda persist");
		session.save(entity);
		
	}

	@Override
	public void update(Address entity) {
		log.info("Am apelat metoda update");
		session.update(entity);
		
	}

	@Override
	public Address findById(Integer id) {
		log.info("Am apelat metoda find");
		return session.get(Address.class, id);
		}

	public List<Address> findAddressByName(String name) throws Exception {
		log.info("Am apelat metoda find address by name");
		@SuppressWarnings("unchecked")
		List<Address> adresses = session.createQuery("from Address b where b.name like CONCAT('%',:name,'%')")
				.setParameter("name", name).list();
		log.info("Numele dupa care cautam adresa este: " + name);
		log.info("Avem urmatoarele adrese: " + adresses);
		if (adresses.isEmpty())
			throw new Exception("Nu exista adrese cu numele: " + name);
		return adresses;
	}
	
	@Override
	public void delete(Address entity) {
		log.info("Am apelat metoda delete");
		session.delete(entity);		
	}

	@Override
	public List<Address> findAll() {
		log.info("Am apelat metoda findAll");
		return session.createQuery("from Address").list();
	}

	@Override
	public void deleteAll() {
		log.info("Am apelat metoda deleteAll");
		session.createQuery("delete from Address").executeUpdate();
	}

}
