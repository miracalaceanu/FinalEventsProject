package ro.mira.project;

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

import ro.mira.project.Dao.AddressDao;
import ro.mira.project.Dao.EventDao;
import ro.mira.project.Dao.OrganizedByDao;
import ro.mira.project.entity.Address;
import ro.mira.project.entity.Event;
import ro.mira.project.entity.OrganizedBy;
import ro.mira.project.enums.EventType;
import ro.mira.project.enums.TicketType;
import ro.mira.project.service.AddressService;
import ro.mira.project.service.EventService;
import ro.mira.project.service.OrganizedByService;
import ro.mira.project.util.HibernateUtils;

public class App {

	public static void main(String[] args) throws Exception {
		
		EventService eventService = new EventService();
		
		// Main functionality - create new event interactively
		eventService.createEvent();

		// Example queries (commented out for demo)
		// eventService.findEventsByDate();
		// System.out.println(eventService.findEventsByType(EventType.LIVE_MUSIC_CONCERT));
		// System.out.println(eventService.findEventByName("Four Seasons"));
		// System.out.println(eventService.listEventsBetweenDates(LocalDate.of(2021, 03, 01), LocalDate.of(2021, 04, 30)));
		
		// OrganizedByService organizedByService = new OrganizedByService();
		// System.out.println(organizedByService.listEventsByOrganizer("Muzeul de Arta"));

		// Event update example
		// Event event = eventService.findEventById(6);
		// event.setLocalDate(LocalDate.parse("2021-03-20"));
		// eventService.updateEvent(event);
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



		// Graceful shutdown
		// System.out.println("\nApplication completed successfully.");
		// HibernateUtils.shutdown();
	}

}