package ro.itschool.curs.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.itschool.curs.Dao.OrganizedByDao;
import ro.itschool.curs.entity.OrganizedBy;


public class OrganizedByDaoTest {
	private OrganizedByDao organizedByDao;
	List<OrganizedBy> initialOrganizers = new ArrayList <>();
	  List<OrganizedBy> expectedOrganizers = new ArrayList <>();
	
	@BeforeEach
	void setup() throws Exception{
		 OrganizedBy organizedBy1= new OrganizedBy(19, "name1", 2000);
		 OrganizedBy organizedBy2= new OrganizedBy(19, "name2", 2003);
		 OrganizedBy organizedBy3= new OrganizedBy(20, "name3", 2010);
		 OrganizedBy organizedBy4= new OrganizedBy(21, "name4", 2000);
		 OrganizedBy organizedBy5= new OrganizedBy(22, "name5", 2000);
		  initialOrganizers.add(organizedBy1);
		  initialOrganizers.add(organizedBy2);
		  initialOrganizers.add(organizedBy3);
		  initialOrganizers.add(organizedBy4);
		  initialOrganizers.add(organizedBy5);
		  
		  expectedOrganizers.add(organizedBy1);
		  expectedOrganizers.add(organizedBy4);
		  expectedOrganizers.add(organizedBy5);
	}

	@AfterEach
	void after(){
		System.out.println("after each good for closing sessions for example");
	}
	
	@Test
	public void filterOrganizersByFoundedTest_happyPath() throws Exception {
		  assertNotNull(organizedByDao.filterOrganizersByFounded(initialOrganizers, 2000));
		  assertEquals(expectedOrganizers, organizedByDao.filterOrganizersByFounded(initialOrganizers, 2000));
		}
	
	@Test
	public void filterOrganizersByFoundedTest_foundedNotFound() throws Exception {
		assertEquals(expectedOrganizers, organizedByDao.filterOrganizersByFounded(initialOrganizers, 3000));
		assertThrows(Exception.class, () ->{
			organizedByDao.filterOrganizersByFounded(initialOrganizers, 3000);
		});
	
	}
	
	
}
