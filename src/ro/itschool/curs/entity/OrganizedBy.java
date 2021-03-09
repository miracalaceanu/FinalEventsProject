package ro.itschool.curs.entity;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class OrganizedBy extends Identification {
	
	@Column
	private int founded;
	
	public OrganizedBy(int id, String name, int Founded) {
		super();
		this.founded = founded;
	}

	

	@Override
	public String toString() {
		return "\nOrganizedBy " + super.toString()+ "founded=" + founded + " "  ;
	}



	


	


	

	

}
