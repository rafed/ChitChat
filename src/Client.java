import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static final String HOST= "localhost";
	
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket(HOST, Server.PORT);
		System.out.println("Connected to " + HOST);
		
		ThreadedMessageSender messageSender = new ThreadedMessageSender("Client", socket);
		Thread t = new Thread(messageSender);
		t.start();
		
		InputStream inputStream = socket.getInputStream();
		Scanner messageGetter = new Scanner(inputStream);
		
		String receivedMessage;
		while (true) {
			if ((receivedMessage = messageGetter.nextLine()) != null) {
				System.out.println(receivedMessage);
			}
		}
	}
}