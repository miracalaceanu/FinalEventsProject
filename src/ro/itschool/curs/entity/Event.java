package ro.itschool.curs.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OrderBy;

import lombok.Data;
import lombok.NoArgsConstructor;

import ro.itschool.curs.enums.EventType;
import ro.itschool.curs.enums.TicketType;

@Entity 
@Table(name = "Event") 
		
@Data

@NoArgsConstructor

public class Event extends Identification {
	@Enumerated(EnumType.STRING) //  converts an "enum" to its ordinal or String value.
	@Column
	private EventType eventType;

	@Column
	private LocalDate localDate;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TicketType ticketType;

	@Column
	private double ticketPrice;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // single adress
	private Address address;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "organizedBy_event", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = {
			@JoinColumn(name = "organizedBy_id") })
	private Set<OrganizedBy> organizer = new HashSet<>();
	
	public Event(int id, String name,  EventType eventType, LocalDate localDate, Set<OrganizedBy> organizer,
			Address address, TicketType ticketType, double ticketPrice) {
		super();
		this.eventType = eventType;
		this.localDate = localDate;
		this.organizer 	 = organizer;
		this.address = address;
		this.ticketType = ticketType;
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		String finalString;
		finalString= "\nEVENT " +super.toString()+ ", eventType=" + eventType
				+ ", localDate=" + localDate + ", ticketType=" + ticketType + ", ticketPrice=" + ticketPrice;
		if (Hibernate.isInitialized(this.organizer) && this.organizer != null)
			finalString += this.organizer.toString();
		if (Hibernate.isInitialized(this.address) && this.address != null)
			finalString += this.address.toString();
	
		return finalString;
			
	}

	
	

}
