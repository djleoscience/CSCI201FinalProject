package edu.usc.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.Thread;
import java.net.Socket;

public class ClientHandlerThread extends Thread {
	private Socket socket;
	private PrintStream out;
	private BufferedReader br;
	
	public ClientHandlerThread(Socket s){
		this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
		this.socket = s;
	}
	
	public void run(){
		
	}

	public void sendPoolIsFullResponse() {
		
	}
}
