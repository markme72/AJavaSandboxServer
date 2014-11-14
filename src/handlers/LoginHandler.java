package handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

import sandboxjavaserver.CommunicationBean;
import sandboxjavaserver.HibernateUtilSingleton;
import sandboxjavaserver.UserBean;

public class LoginHandler implements Handler {
	@Override
	public void handleIt(String operation, Object parameters, JSONOutputStream outToClient) {
		HashMap message = new HashMap();
		message.put("message","Default text");
		HashMap aDataMap = (HashMap) parameters;
		UserBean aUser = new UserBean();
		aUser.setUname((String)aDataMap.get("uname"));
		aUser.setPword((String)aDataMap.get("pword"));
	
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		Query singleUserQuery = session.createQuery("select u from UserBean as u where u.uname='" + aUser.getUname() + "'");
		UserBean queriedUser = (UserBean)singleUserQuery.uniqueResult();
		
		session.close();
		
		if ((queriedUser == null) || (!queriedUser.getPword().equals(aUser.getPword()))) {
	        message.put("message", "User credentials incorrect");
			try {
				outToClient.writeObject(new CommunicationBean("Error", message));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		message.put("message", "Successfully logged in!");
		try {
			outToClient.writeObject(new CommunicationBean("Success", message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
