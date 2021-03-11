package ro.itschool.curs.Dao;

import java.util.List;

import javax.persistence.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
		log.info("Persist method is called");
		session.save(entity);
		
	}

	@Override
	public void update(Address entity) {
		log.info("Update method is called");
		session.update(entity);
		
	}

	@Override
	public Address findById(Integer id) {
		log.info("Find method is called");
		return session.get(Address.class, id);
		}

	public List<Address> findAddressByName(String name) throws Exception {
		log.info("Find address by name method is called");
		@SuppressWarnings("unchecked")
		List<Address> adresses = session.createQuery("from Address b where b.name like CONCAT('%',:name,'%')")
				.setParameter("name", name).list();
		log.info("Name of the address is: " + name);
		log.info("These are the addresses: " + adresses);
		if (adresses.isEmpty())
			throw new Exception("There are no adresses with the name: " + name);
		return adresses;
	}
//	CRITERIA
	public List<Address> listAddressById(int id){
		@SuppressWarnings("deprecation")
		Criteria criteria= session.createCriteria(Address.class);
		criteria.add(Restrictions.eq("id", id));
		List addresses= criteria.list();
		return addresses;
	}
	public List<Address> listAddressByName(String name){
		Criteria criteria= session.createCriteria(Address.class);
		criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());// gaseste organizedby care contine in nume "stringul dat"
		List address= criteria.list();
		System.out.println(address);
		return address;
	}
	
	@Override
	public void delete(Address entity) {
		log.info("Delete method is called");
		session.delete(entity);		
	}

	@Override
	public List<Address> findAll() {
		log.info("FindAll method is called");
		return session.createQuery("from Address").list();
	}

	@Override
	public void deleteAll() {
		log.info("DeleteAll method is called");
		session.createQuery("delete from Address").executeUpdate();
	}

}
