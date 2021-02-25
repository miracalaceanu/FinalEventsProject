package ro.itschool.curs;

import java.time.LocalDate;

import ro.itschool.curs.Dao.AddressDao;
import ro.itschool.curs.Dao.OrganizedByDao;
import ro.itschool.curs.entity.Address;
import ro.itschool.curs.entity.Event;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.enums.TicketType;
import ro.itschool.curs.service.AddressService;
import ro.itschool.curs.service.EventService;
import ro.itschool.curs.service.OrganizedByService;

public class App {

	public static void main(String[] args) {
//		System.out.println("hello world!");
		EventService eventService =  new EventService();
		System.out.println(eventService.findEventByName("Luna Amara- Live Concert"));
		
//		OrganizedByService organizedBySrervice= new OrganizedByService();
//		System.out.println(organizedBySrervice.findAllOrganizedBy());
//		System.out.println(organizedBySrervice.findOrganizedByById(5));
//		organizedBySrervice.findOrganizedByById(5).getName();
//		OrganizedByDao organizedByDao= new OrganizedByDao();
//		organizedByDao.findAll();
		
		
//		Address address=new Address(); 
//		address.setName("opera");
//		address.setNumber(3);
//		address.setPhoneNumber("123456");
//		address.setStreetName("no name");
//		address.setWebsite("www.website.com");
//		
//		HostedBy hostedby=new HostedBy();
//		hostedby.setName("opera");
//		
//		
//		Event event = new Event();
//		event.setAddress(address);
//		event.setEventType(EventType.LIVE_MUSIC_CONCERT);
//		event.setHostedBy(hostedby);
//		event.setLocalDate(LocalDate.now());
//		event.setName("party");
//		event.setOrganizedBy("tate");
//		event.setTicketPrice(25.0);
//		event.setTicketType(TicketType.BUY_TICKET_ON_THE_SPOT);
//		
//		System.out.println(event);
		
//		EventService eventService =  new EventService();
//		eventService.saveEvent(event);
		
		//works why use dao?
//		AddressDao addressDao= new AddressDao();
//		AddressService addressService= new AddressService(addressDao); 
//		System.out.println(addressService.findAllAddresses());
//		
//		HostedByService hostedByService= new HostedByService();
//		System.out.println(hostedByService.findHostedByById(5));
//		System.out.println(hostedByService.findAllHostedBy());
		//works

	}

}
