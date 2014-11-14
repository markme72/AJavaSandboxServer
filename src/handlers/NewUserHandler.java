package handlers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

import sandboxjavaserver.AccountBean;
import sandboxjavaserver.AddressBean;
import sandboxjavaserver.CommunicationBean;
import sandboxjavaserver.HibernateUtilSingleton;
import sandboxjavaserver.PhoneNumberBean;
import sandboxjavaserver.UserBean;

public class NewUserHandler implements Handler {
	@Override
	public void handleIt(String operation, Object parameters, JSONOutputStream outToClient) {
		HashMap message = new HashMap();
		message.put("message", "defaultText");
		HashMap aDataMap = (HashMap) parameters;
		AccountBean anAccount = new AccountBean();
		AddressBean anAddress = new AddressBean();
		PhoneNumberBean aPhoneNumber = new PhoneNumberBean();
		UserBean aUser = new UserBean();
		
		anAccount.setFirstName((String)aDataMap.get("firstName"));
		anAccount.setMiddleInitial((String)aDataMap.get("middleInitial"));
		anAccount.setLastName((String)aDataMap.get("lastName"));
		anAccount.setEmail((String)aDataMap.get("email"));
		aUser.setUname((String)aDataMap.get("uname"));
		aUser.setPword((String)aDataMap.get("pword"));
		anAddress.setStreet((String)aDataMap.get("street"));
		anAddress.setStreet2((String)aDataMap.get("street2"));
		anAddress.setCity((String)aDataMap.get("city"));
		anAddress.setState((String)aDataMap.get("state"));
		anAddress.setZip((String)aDataMap.get("zip"));
		anAddress.setCountry((String)aDataMap.get("country"));
		aPhoneNumber.setPhoneNumber((String)aDataMap.get("phoneNumber"));
		
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query singleUserQuery = session.createQuery("select u from UserBean as u where u.uname='" + aUser.getUname() + "'");
		UserBean queriedUser = (UserBean)singleUserQuery.uniqueResult();
		
		if (queriedUser != null) {	
			session.close();
			message.put("message", "User already exists");
			try {
				outToClient.writeObject(new CommunicationBean("Error", message));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Many to many relationship
		anAccount.getAddresses().add(anAddress);
		
		//One to many relationship
		aPhoneNumber.setAnAccount(anAccount);
		
		session.save(anAccount);
		session.save(anAddress);
		session.save(aPhoneNumber);
		session.save(aUser);
		transaction.commit();
	
		message.put("message", "User successfully created!");
		try {
			outToClient.writeObject(new CommunicationBean("Success", message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
