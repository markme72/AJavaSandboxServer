package model;

import java.util.Set;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sandboxjavaserver.HibernateUtilSingleton;

public class HibernateModel {
	
	public HibernateModel(){	
	}
	
	public boolean save(AccountBean anAccount, AddressBean anAddress, 
			PhoneNumberBean aPhoneNumber, UserBean aUser){
		HashMap message = new HashMap();
		message.put("message","default");
		
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		
		//checks to see if username exists already
		Query singleUserQuery = session.createQuery("select u from UserBean as u where u.uname='" + aUser.getUname() + "'");
		UserBean queriedUser = (UserBean)singleUserQuery.uniqueResult();
		if (queriedUser != null) {
			session.close();
			return false;
		}
		
		//Many to many relationship
		Set<AddressBean> accountAddress = anAccount.getAddresses();
		accountAddress.add(anAddress);
		
		//One to many relationship
		aPhoneNumber.setAnAccount(anAccount);
		
		session.save(anAccount);
		session.save(anAddress);
		session.save(aPhoneNumber);
		session.save(aUser);
		transaction.commit();
		
		return true;
	}
	
	public boolean query(UserBean aUser){
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		
		HashMap message = new HashMap();
		message.put("message", "default");
		Query singleUserQuery = session.createQuery("select u from UserBean as u where u.uname='" + aUser.getUname() + "'");
		UserBean queriedUser = (UserBean)singleUserQuery.uniqueResult();
		session.close();
		
		if ((queriedUser == null) || (!queriedUser.getPword().equals(aUser.getPword()))) {
			return false;
		}
		
		return true;
	}
}
