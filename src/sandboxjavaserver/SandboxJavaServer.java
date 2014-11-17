package sandboxjavaserver;

import handlers.LoginHandler;
import handlers.NewUserHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import applicationcontroller.ApplicationController;

public class SandboxJavaServer {
	private static ApplicationController appController;
	
	static{
		appController = new ApplicationController();
		appController.addFeature("login", new LoginHandler());
		appController.addFeature("newUser", new NewUserHandler());
	}
	public static void main(String[] args) {
		while (true){
				try {
					//setup an open socket
					ServerSocket aListeningSocket = new ServerSocket(9292);
					//wait for client to connect
					System.out.println("Waiting for client connection");
					Socket clientSocket = aListeningSocket.accept();
					//setup JSON streams
					JSONInputStream inFromClient = new JSONInputStream(clientSocket.getInputStream());
					JSONOutputStream outToClient = new JSONOutputStream(clientSocket.getOutputStream());
					//read until client closes the connection
					while(true) {
						System.out.println("Waiting for a message from client");
						//get data from client
						HashMap aMap = (HashMap) inFromClient.readObject();
						
						//set data
						String command = (String) aMap.get("command");
						HashMap aDataMap = (HashMap) aMap.get("data");
						
						appController.handleRequest(command, aDataMap, outToClient);
					}
				} catch (IOException | JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} 
		}

	}

}
