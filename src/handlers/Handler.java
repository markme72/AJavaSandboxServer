package handlers;

import org.quickconnectfamily.json.JSONOutputStream;

public interface Handler {
	public void handleIt(String operation, Object parameters, JSONOutputStream outToClient);
}
