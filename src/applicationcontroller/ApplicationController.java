package applicationcontroller;

import handlers.Handler;

import java.util.HashMap;

import org.quickconnectfamily.json.JSONOutputStream;

import sandboxjavaserver.CommunicationBean;

public class ApplicationController {
	private HashMap<String, Object> applicationFeatures = new HashMap<String, Object>();
	
	public ApplicationController() {
	}
	
	public void handleRequest(String operation, Object parameters, JSONOutputStream outToClient) {
		Handler aHandler = (Handler) applicationFeatures.get(operation);
		
		aHandler.handleIt(operation, parameters, outToClient);
	}
	
	public void addFeature(String feature, Object classReference) {
		applicationFeatures.put(feature, classReference);
	}
}
