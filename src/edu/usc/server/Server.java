package edu.usc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import edu.usc.game.Game;
import edu.usc.util.*;

public class Server {
	private int portNum;
	private int maxPlayers;
	private ServerSocket serverSocket;
	private Vector<ClientHandlerThread> threads;
	private Game game;
	
	
	public Server(int port, int maxPlayers){
		this.portNum = port;
		try {
			System.out.println("Attempting to instantiate ServerSocket at port " + port + ".");
			this.serverSocket = new ServerSocket(port);
			System.out.println("Succeeded in instantiating ServerSocket at port " + port + ".");
		} catch (IOException e) {
			System.out.println("Failed to instantiate ServerSocket at port " + port + ", error:");
			e.printStackTrace();
		}
		this.maxPlayers = maxPlayers;
		this.game = new Game();
		threads = new Vector<ClientHandlerThread>();
	}
	
	public void startServer(){
		while(true){
			Socket s;
			try {
				s = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Error in connecting with client:");
				e.printStackTrace();
			}
			//TODO: figure out what to put in constructor
			ClientHandlerThread thread = new ClientHandlerThread();
			if(threads.size() >= maxPlayers){
				thread.sendPoolIsFullResponse();
			}
			else{
				threads.add(thread);
				thread.start();
			}
		}
	}
	
	public static void main(String[] args){
		ConfigFileReader reader = new ConfigFileReader();
		int port = Integer.parseInt(reader.getProperty("port"));
		int maxPlayers = Integer.parseInt(reader.getProperty("maxPlayers"));
		Server server = new Server(port, maxPlayers);
		server.startServer();
		
	}
}
