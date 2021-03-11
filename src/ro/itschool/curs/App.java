package ro.itschool.curs;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.itschool.curs.Dao.AddressDao;
import ro.itschool.curs.Dao.EventDao;
import ro.itschool.curs.Dao.OrganizedByDao;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.enums.TicketType;
import ro.itschool.curs.service.AddressService;
import ro.itschool.curs.service.EventService;
import ro.itschool.curs.service.OrganizedByService;
import ro.itschool.curs.util.HibernateUtils;

public class App {

	public static void main(String[] args) throws Exception {

		EventService eventService = new EventService();
		
//		eventService.findEventsByDate();
		
//		System.out.println(eventService.findEventByName(" Four Seasons"));
		eventService.createEvent();
		
		
//		System.out.println(eventService.findEventsByType(EventType.ART_OPENING) );
//		System.out.println(eventService.findEventsByTicketType(TicketType.BUY_TICKET_ON_THE_SPOT));

//		System.out.println(eventService.sortAscEventsByTicketPrice());
	
// System.out.println(eventService.listEventsBetweenDates(LocalDate.of(2021, 03, 01), LocalDate.of(2021, 03, 20)));
		
//		Event event= eventService.findEventById(6);
//		System.err.println("This is the event before date is modified: "+event);
//		event.setLocalDate(LocalDate.parse("2021-03-20"));
//		eventService.updateEvent(event);
//		System.out.println("This is the event after date is modified: "+event);
		
//		OrganizedByService organizedByService = new OrganizedByService();
//		System.out.println(organizedByService.listEventsByOrganizer("Vatican "));
//		OrganizedBy organizedBy= new OrganizedBy();
//		organizedBy.setFounded(2021);
//		organizedBy.setName("LALA land");
//		organizedByService.saveOrganizedBy(organizedBy);
		
//OrganizedByDao organizedDao= new OrganizedByDao();
//		organizedDao.openCurrentSessionwithTransaction();
//		System.out.println(organizedDao.filterOrganizersByFounded(2015));
////		List<OrganizedBy>organizers= organizedDao.findAll();
//		System.out.println(organizedDao.filterOrganizersByFounded(organizers, 2015));
//organizedDao.closeCurrentSessionwithTransaction();
		
//		AddressService addressService= new AddressService();
//	Address address =addressService.findAddressById(8);
//	address.setName("Opera");
//	addressService.updateAddress(address);
//	
//	Address address2 =addressService.findAddressById(7);
//	address2.setName("Street Opera");
//	addressService.updateAddress(address2);
//	System.out.println(addressService.findAddressById(8));
//	System.out.println(addressService.findAddressById(7));
//	
//	AddressDao addressDao= new AddressDao();
//	addressDao.openCurrentSession();
//	addressDao.listAddressByName("opera").toString();
//	addressDao.closeCurrentSession();



	}

}
