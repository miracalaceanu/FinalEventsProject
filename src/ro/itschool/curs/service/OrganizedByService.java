package ro.itschool.curs.service;

import java.util.ArrayList;
import java.util.List;

import ro.itschool.curs.Dao.OrganizedByDao;
import ro.itschool.curs.entity.OrganizedBy;
import ro.itschool.curs.entity.OrganizedBy;


public class OrganizedByService {
	
private OrganizedByDao organizedByDao;
	
	public OrganizedByService() {
		this.organizedByDao = new OrganizedByDao();
	 }
	

	public OrganizedBy findOrganizedByById(int id) {
		organizedByDao.openCurrentSession();
		OrganizedBy organizedBy = organizedByDao.findById(id);
		organizedByDao.closeCurrentSession();
		return organizedBy;

	}
	
	public void saveOrganizedBy(OrganizedBy OB) {
		organizedByDao.openCurrentSessionwithTransaction();
		organizedByDao.persist(OB);
		organizedByDao.closeCurrentSessionwithTransaction();
	}
	
	public void updateOrganizedBy(OrganizedBy OB) {
		organizedByDao.openCurrentSessionwithTransaction();
		organizedByDao.update(OB);
		organizedByDao.closeCurrentSessionwithTransaction();
	}
	public void deleteOrganizedBy(OrganizedBy OB) {
		organizedByDao.openCurrentSessionwithTransaction();
		organizedByDao.delete(OB);
		organizedByDao.closeCurrentSessionwithTransaction();
	}
	public List<OrganizedBy> findAllOrganizedBy() {
		organizedByDao.openCurrentSession();
		List<OrganizedBy> lista = organizedByDao.findAll();
		organizedByDao.closeCurrentSession();
		return lista;
	}
	

	public List<OrganizedBy> findOrganizerByName(String name) {
		organizedByDao.openCurrentSession();
		List<OrganizedBy> organizer = new ArrayList<OrganizedBy>();
		try {
			organizer =	organizedByDao.findOrganizerByName(name);
		} catch (Exception e) {
			System.out.println("ERROR HANDLING");
			e.printStackTrace();
		}finally {
			System.out.println("Finally");
		}
		organizedByDao.closeCurrentSession();	
		return organizer;
	}
	
	public void deleteAllOrganizedBy() {
		organizedByDao.openCurrentSessionwithTransaction();
		organizedByDao.deleteAll();
		organizedByDao.closeCurrentSessionwithTransaction();

}
}
