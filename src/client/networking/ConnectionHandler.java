package client.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

import shared.networking.Message;

public class ConnectionHandler extends Thread{
	
	private Socket socket;
	private boolean isOpen = false;	
	
	DataOutputStream out;
	DataInputStream in;

	/**
	 * Opens up a connection to the server.
	 * @param ip The IP address of the server that we want to connect to.
	 */
	public ConnectionHandler(String ip){
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {}
		}
		try{
			socket = new Socket(ip,server.networking.ConnectionPool.port);  // Connect the socket
			
			this.out = new DataOutputStream(this.socket.getOutputStream()); // Get the input and
			this.in = new DataInputStream(this.socket.getInputStream());	// output streams up and running.
			
			isOpen = true; // Set open status to true.
		}
		catch(IOException ioe){
			isOpen = false; // Set open status to false.
		}
	}
	
	@Override
	public void start(){
		listen();
	}
	
	public void listen(){
		ByteBuffer buff ;
		while(true){
			try {
				int size = in.readInt();
				buff = ByteBuffer.allocate(size);
				
				for(int i=0;i<size;i++){
					byte cur = in.readByte();
					buff.put(cur);
				}
				
				ClientMessageHandler.handleMessage(buff.array());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void say(Message m) throws IOException{
		say(m.toBytes());
	}
	private void say(byte[] b) throws IOException{
		out.write(b);
	}
	
	public boolean isOpen() {
		return isOpen;
	}
}
