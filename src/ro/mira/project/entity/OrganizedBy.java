package ro.mira.project.entity;


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

public class OrganizedBy extends Identification {
	
	@Column
	private int founded;

	@Override
	public String toString() {
		return "\nOrganizedBy " + super.toString()+ "founded=" + founded + " "  ;
	}

}