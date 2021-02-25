package ro.itschool.curs.entity;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrganizedBy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(nullable = false)
	private String name;

	
//one host one address many events
	
//	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "organizedBy")
//	private List<Event> events;

	@Override
	public String toString() {
		return "\nOrganizedBy id=" + id + ", name=" + name ;
	}

}
