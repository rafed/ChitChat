import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	public static final int PORT = 9999;
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket = serverSocket.accept();
		
		System.out.println("Connected to " + socket.getLocalAddress());

		ThreadedMessageSender messageSender = new ThreadedMessageSender("Server", socket);
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