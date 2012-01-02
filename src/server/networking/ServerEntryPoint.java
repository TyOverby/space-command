package server.networking;

import java.io.IOException;

public class ServerEntryPoint {

	public static void main(String[] args) throws IOException {
		ConnectionPool cp = new ConnectionPool();
		
		System.out.println("Server Starting");
		cp.start();
	}
}
