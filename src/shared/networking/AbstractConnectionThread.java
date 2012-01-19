package shared.networking;

import java.net.*;
import java.io.*;

/**
 * Abstract base class for Client and Server connection threads
 * manages the input and output stream creation and exception handling
 * @author Dustin Lundquist <dustin@null-ptr.net>
 */
public abstract class AbstractConnectionThread extends Thread {
	public static final int PORT = 2390;
	
    private Socket socket;
    private boolean running;
    
    ObjectOutputStream out = null;
    ObjectInputStream in = null;

    protected AbstractConnectionThread(Socket socket) {
        this.socket = socket;
        
        try {
			out = new ObjectOutputStream(socket.getOutputStream());
			 in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        
        running = true;
    }

    /**
     * This is the main body of the network connection thread
     */
    public void run() {
        setName(this.getClass().getSimpleName() + " [" + socket.getRemoteSocketAddress() + "]");
        Message msg;

        try {
        	
            // Main loop to receive updates and respond          
            while (running) {
                msg = (Message)in.readObject();
                handleMessage(msg);
            }
        } catch (SocketException e) {
            if (e.getMessage().equals("Connection reset")) {
                System.err.println("Connection reset");
                e.printStackTrace();
                running = false;
            } else
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Invalid network message");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                socket.close();
            } catch(Exception e) {
                // Nothing
            }
            shutdownHook();
        }
    }
    
    public void say(Message message) throws IOException{
            out.writeObject(message);
            out.flush();
            out.reset(); // Flush the object cache so the next update will actually update stuff
    }
    
    public void close() {
        running = false;
        try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Provides an interface for overriding classes to perform cleanup tasks
     */
    protected void shutdownHook() {
        // Do nothing
    }

    /**
     * Provides a hook for sending an initial message
     * Either the client or server should override this, not both
     * @return an message to send on connection establishment
     */
    protected Message helloMessage() {
        return null;
    }

    protected abstract void handleMessage(Message msg) throws IOException, InterruptedException;
}