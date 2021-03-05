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
		OrganizedByDao organizedByDao=new OrganizedByDao();
		organizedByDao.openCurrentSession();
		System.out.println(organizedByDao.listEventsByOrganizer("Muzeul de Arta"));
			organizedByDao.closeCurrentSession();
		
		

//		System.out.println(AppUtils.findEventByDate());


		
		
//		EventService eventService =  new EventService();
//		Event event = new Event();
//		event= eventService.findEventById(6);
//		event.setLocalDate(LocalDate.parse("2021-03-11"));
//		eventService.updateEvent(event);
//		System.out.println(eventService.findEventsByType(EventType.FAIR));
//		System.out.println(eventService.findEventByName("Purcell's King Arthur"));
//		eventService.createEvent();
	

//		OrganizedByService organizedByService= new OrganizedByService();
//		System.out.println(organizedByService.findOrganizerByName("Opera"));
//		OrganizedBy organizedBy= new OrganizedBy();
//		organizedBy.setFounded(2021);
//		organizedBy.setName("LALA land");
//		organizedByService.saveOrganizedBy(organizedBy);
		

	}

	

}
