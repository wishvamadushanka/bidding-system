import java.net.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


//server class
public class Server{
	
	private static ServerSocket serversocket;		//server socket
	private static int port = 2000;						//port number
	public static ArrayList<Client> clients = new ArrayList<>(); 		//list of clients
	
	Server () throws IOException
	{
		//create server socket
		serversocket = new ServerSocket(port);
		System.out.println("Server is running.....");
		//gui
		Display gui = new Display();
		gui.setVisible(true);
		
	}
	
	//loop to connect client
	public static void loop() throws IOException
	{
		while(true)
		{
			Socket socket = serversocket.accept();
			Client client = new Client(socket);
			clients.add(client);
			Thread clientThread = new Thread(client);
			clientThread.start();
			
		}
		
		
	}
	
	
	//main method	
	public static void main(String [] args) throws IOException
	{
		
		try
		{
			Stock stock = new Stock("stocks.csv");	//create datastore
			new Server();
			Server.loop();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
}
