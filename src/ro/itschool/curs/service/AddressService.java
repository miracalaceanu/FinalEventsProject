package ro.itschool.curs.service;

import java.util.List;

import ro.itschool.curs.Dao.AddressDao;
import ro.itschool.curs.entity.Address;

public class AddressService {
	
	private AddressDao addressDao;

	public AddressService(AddressDao addressDao) {
		this.addressDao = new AddressDao();
	
	}
	
	public Address findAddressById(int id) {
		addressDao.openCurrentSession();
		Address address = addressDao.findById(id);
		addressDao.closeCurrentSession();
		return address;

	}
	public void saveAddress(Address address) {
		addressDao.openCurrentSessionwithTransaction();
		addressDao.persist(address);
		addressDao.closeCurrentSessionwithTransaction();
	}
	
	public void updateAddress(Address address) {
		addressDao.openCurrentSessionwithTransaction();
		addressDao.update(address);
		addressDao.closeCurrentSessionwithTransaction();
	}
	public void deleteAddress(Address address) {
		addressDao.openCurrentSessionwithTransaction();
		addressDao.delete( address);
		addressDao.closeCurrentSessionwithTransaction();
	}
	public List<Address > findAllAddresses() {
		addressDao.openCurrentSession();
		List<Address> lista = addressDao.findAll();
		addressDao.closeCurrentSession();
		return lista;
	}
	public void deleteAllAddresses() {
		addressDao.openCurrentSessionwithTransaction();
		addressDao.deleteAll();
		addressDao.closeCurrentSessionwithTransaction();

}
	

}
