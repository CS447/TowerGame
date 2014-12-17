package towergame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	
	public BufferedReader Reader;
	public PrintWriter Writer;
	private Socket socket;

	public GameClient() {
		
	}
	
	public GameClient(String address, int port) {		
		try {
			socket = new Socket(address, port);
			Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("NetError: " + e);
		}
		
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("NetError: Failed to close client socket.");
		}
	}
	
}
