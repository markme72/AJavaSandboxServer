package handlers;

import org.quickconnectfamily.json.JSONOutputStream;

import sandboxjavaserver.CommunicationBean;

public interface Handler {
	public void handleIt(String operation, Object parameters, JSONOutputStream outToClient);
}
