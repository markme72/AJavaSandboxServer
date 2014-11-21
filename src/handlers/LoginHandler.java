package handlers;

import java.util.HashMap;

import model.CommunicationBean;
import model.HibernateModel;
import model.UserBean;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

public class LoginHandler implements Handler {
	@Override
	public void handleIt(String operation, Object parameters, JSONOutputStream outToClient) {
		HashMap message = new HashMap();
		message.put("message","Default text");
		HashMap aDataMap = (HashMap) parameters;
		UserBean aUser = new UserBean();
		aUser.setUname((String)aDataMap.get("uname"));
		aUser.setPword((String)aDataMap.get("pword"));
	
		HibernateModel hibernate = new HibernateModel();
		
		boolean done = hibernate.query(aUser);
		
		if (!done) {
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
