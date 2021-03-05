package ro.itschool.curs.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import ro.itschool.curs.entity.Event;
import ro.itschool.curs.service.EventService;

public class AppUtils {
	public static List<Event> findEventByDate() {
		EventService eventService = new EventService();
		Scanner scanner = new Scanner(System.in);
		String date;
		LocalDate localDate;
		while(true) {
			System.out.println("Please enter the date in the format 'YYYY-MM-DD' ");
			date = scanner.next();
			if(!isValid(date)) {
				 System.err.println("Wrong date format, please enter date again.");
				 continue;
			}
			break;
		}
		scanner.close();
		localDate = LocalDate.parse(date);
		return eventService.findEventByDate(localDate) ;
	}
	
	private static boolean isValid(String date) {
		try {
			LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
	

}
