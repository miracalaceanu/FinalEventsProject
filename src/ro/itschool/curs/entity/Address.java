package ro.itschool.curs.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
private String streetName;
	
	@Column
private int number;
	
	@Column
private String phoneNumber;
	
	@Column
private String website;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "address")
	private Set<Event> events=new HashSet<Event>();// nu  duplicate;HashSet(nu respecta ordinea) TreeHashSet( elem in ordine crescatoare), LinkedHashset( elem ordinea in care au fost adaugate)
	


	@Override
	public String toString() {
		return "\nAddress id=" + id + ", name=" + name + ", streetName=" + streetName + ", number=" + number
				+ ", phoneNumber=" + phoneNumber + ", website=" + website  ;
	}
	
	

}
