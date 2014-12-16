package towergame;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	public static final int LISTEN_PORT = 15248;
	
	public GameServer() {
		ServerListen server = new ServerListen();
		server.start();
	}
	
	public static class ServerListen extends Thread {
		ServerSocket listener;

		public void run() {
			Socket player1;
			Socket player2;
			try {
				listener = new ServerSocket(LISTEN_PORT);
				player1 = listener.accept();
				System.out.println("Client 1 connected.");
				player2 = listener.accept();
				System.out.println("Client 2 connected.");
				ConnectionHandler handler = new ConnectionHandler(player1, player2);
				handler.start();
				ConnectionHandler handler2 = new ConnectionHandler(player2, player1);
				handler2.start();
			} catch (Exception e) {
				System.out.println("Error: Server error: " + e);
			}
		}
	}
	
	public static class ConnectionHandler extends Thread {
		Socket player1;
		Socket player2;
		ConnectionHandler(Socket p1, Socket p2) {
			player1 = p1;
			player2 = p2;
		}
		public void run() {
			try {
				InputStream in = player1.getInputStream();
				OutputStream out = player2.getOutputStream();
				
				for (int b = in.read(); b != -1; b = in.read()) {
					out.write(b);
				}
			} catch (Exception e) {
				System.out.println("Error: Network: " + e);
			}
		}
	}

}
