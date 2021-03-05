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
import ro.itschool.curs.util.AppUtils;
import ro.itschool.curs.util.HibernateUtils;

public class App {

	public static void main(String[] args) throws Exception {

//		System.out.println(AppUtils.enterEventDate());

//		AddressService addressService= new AddressService(); 
//		System.out.println(addressService.findAddressByName("u"));
//		
		
		EventService eventService =  new EventService();
//		System.out.println(eventService.findEventsByType(EventType.FAIR));
		System.out.println(eventService.findEventByName("Purcell's King Arthur"));
		
		
//		OrganizedByService organizedBySrervice= new OrganizedByService();
//		System.out.println(organizedBySrervice.findOrganizerByName("Opera"));
		


	}

	

}
