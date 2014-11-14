package sandboxjavaserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	private String command;
	private HashMap data;
	
	public CommunicationBean() {
	}
	
	public CommunicationBean(String command, HashMap data) {
		this.command = command;
		this.data = data;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public HashMap getData() {
		return this.data;
	}

	public void setData(HashMap data) {
		this.data = data;
	}
	
	
}
