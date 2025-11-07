package ro.itschool.curs.test;


import static org.junit.jupiter.api.Assertions.*;


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
		organizedByDao.openCurrentSession();
		initialOrganizers= organizedByDao.findAll();
		
		  expectedOrganizers.add(organizedByDao.findById(10));
		  expectedOrganizers.add(organizedByDao.findById(12));
		 	  
		  System.out.println(expectedOrganizers);
	}

	@AfterEach
	void after(){
		organizedByDao.closeCurrentSession();
			}
	
	@Test
	public void filterOrganizersByFoundedTest_happyPath() {
		System.out.println(organizedByDao.filterOrganizersByFounded( 2015));
		  assertNotNull(organizedByDao.filterOrganizersByFounded( 2015));
		  assertEquals(expectedOrganizers, organizedByDao.filterOrganizersByFounded( 2015));
		}
	
//	@Test
//	public void filterOrganizersByFoundedTest_foundedNotFound() throws Exception {
//		assertEquals(expectedOrganizers, organizedByDao.filterOrganizersByFounded(initialOrganizers, 3000));
//		assertThrows(Exception.class, () ->{
//			organizedByDao.filterOrganizersByFounded(initialOrganizers, 3000);
//		});
	
	}
	
	

