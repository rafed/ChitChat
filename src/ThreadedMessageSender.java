import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedMessageSender implements Runnable  {

	String name;
	Socket socket;
	
	public ThreadedMessageSender(String name, Socket socket){
		this.name = name;
		this.socket = socket;
	}
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		
		try {
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			
			while(true){
				String sendMsg = sc.nextLine();
				writer.println(name + ": " + sendMsg);
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
