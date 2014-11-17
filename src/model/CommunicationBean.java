package model;

import java.io.Serializable;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	private String command;
	private HashMap data;
	
	public CommunicationBean() {
	}
	
	public CommunicationBean(String command, HashMap message) {
		this.command = command;
		this.data = message;
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
