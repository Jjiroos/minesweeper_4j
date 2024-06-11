/**
 * 
 */
package sc0vil.minsweeper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author cloclo hacker
 *
 */
public class Server implements Runnable {

	final static int PORT = 10002;
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	private ServerSocket server;
	private Socket sock;
	
	private String msg;
	
	Server(){
		System.out.println("Serveur d�marr�");
		
		try {
			server = new ServerSocket(PORT);
			Socket sock = server.accept();
			
			Thread thread = new Thread(this);
			thread.start();
			
			inputStream = new DataInputStream(sock.getInputStream());
			outputStream = new DataOutputStream(sock.getOutputStream());
			
			System.out.println("Client connected");
			
			msg=inputStream.readUTF();
			System.out.println(msg+" Ouais je sais mon pote");
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		new Server();
	}

	@Override
	public void run() {
		/*Socket sock = server.accept();
		inputStream = new DataInputStream(sock.getInputStream());
		outputStream = new DataOutputStream(sock.getOutputStream());
		
		System.out.println("Client connected");*/
	}

}
