import java.io.PrintWriter;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Client implements Runnable, ActionListener{
	
	private Socket socket;		//sockect
	public String clientName = "";	//name of client
	public String symble = null;	//symble of client bid
	public double bidPrice;			//price
	public boolean newBid;			//state of new bid
	public Date newBidDate;			//date
	private Timer timer;				//timer
	private int myPoitionInBidHistry;	//place of bid in histry
	public int currentUpdatePosition;	//updated number of histry
	private BufferedReader in;
	private PrintWriter out;
	
	public Client(Socket socket)
	{
		this.socket = socket;
		timer = new Timer(500, this);			//set timer with sense 500ms
		timer.start();							//start timer
	}	
	
	
	//thred for every client
	@Override
	public void run()
	{
		try
		{
			String userIn;		//store user input
			String currentState;		//current state
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));  
        	out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()),true); 
        	out.println("Welcome to server... To exit just type 'quit' and press enter... To change bid symbol type 'change' and press enter....");

        	out.println("Enter your name : ");
        	currentState = "Get_name";

        	//run loop
        	while(!(userIn = in.readLine()).equals("quit"))
        	{

        		//if user does not enter anything
        		if(userIn.equals(""))
        		{
        			continue;
        		}

        		//if client want to change bid symbol
        		if(userIn.equals("change"))
        		{
        			out.println("You are quit bidding on " + symble + " \n");
        			out.println("Enter the new Symbol that you willing to bid : ");
        			currentState = "Get_symble";
        			continue;
        		}

        		switch (currentState)
        		{
        			//getting user name
        			case "Get_name":

        				//check whether the user is already in
        				boolean validUser = true;
        				for(Client s : Server.clients)
        				{
        					if(s.clientName.equals(userIn))
        					{
        						out.println("Name is alredy use...please try another name...\n" + "Enter new name : ");
        						validUser = false;
        						break;
        					}
        				}

        				//if user name is correct
        				if(validUser)
        				{
        					this.clientName = userIn;
			        		out.println("Hello " + userIn + "...");
			        		out.println("Enter the Symbol that you willing to bid : ");
			        		currentState = "Get_symble";
        				}
        				break;
        				

		        	//getting symble
		        	case "Get_symble":

		        		this.symble = userIn;
		        		//if symble valid
		        		if(Stock.isSymbleIn(this.symble))
		        		{
		        			//if that symble have not histry create new histry
		        			if(!Stock.isBidHistry(symble))
		        			{
		        				Stock.newBidHistory(symble);
		        			}
		        			myPoitionInBidHistry = Stock.numberOfBidHistory(symble);
		        			out.println("Current price of Bidding item [ " + this.symble + " ] is " + Stock.currentPrice(symble));
		        			out.println("Enter bid price : ");
		        			currentState = "Get_bid";
		        		}
		        		else
		        		{
		        			out.println("-1\nEnter the Symbol that you willing to bid :");
		        			currentState = "Get_symble";
		        		}

		        		break;

		        	//getting new bid always
		        	case "Get_bid":

		        		try
		        		{
		        			bidPrice = Double.parseDouble(userIn);		//user bid price
		        			//if bid is high current price
		        			if(bidPrice > Stock.currentPrice(symble))
		        			{
		        				newBid = true; 	//clien entr new bid
		        				newBidDate = Calendar.getInstance().getTime();		//get time that bid enter
		        				Stock.setNewPrice(symble, bidPrice);			//set new price
		        				out.println("your bid is higher.....");
		        				out.println("Current price of Bidding item [ " + this.symble + " ] is " + Stock.currentPrice(symble) + "\n");
		        			}
		        			else
		        			{
		        				out.println("Your bid is not enough .....");
		        				out.println("you can bid again higher than " + Stock.currentPrice(symble) + "\n");
		        			}
		        			//out.println("Current price of Bidding item [ " + this.symble + " ] is " + Stock.currentPrice(symble));
		        			myPoitionInBidHistry = Stock.numberOfBidHistory(symble);
		        			out.println("Enter new bid price : ");
		        		}
		        		catch(Exception e)
		        		{
		        			out.println("invalid format ... \n");
		        			out.println("Please Enter a valid Price : ");
		        		}
		        		break;
        		}
        		
        	}

        	for(Client s : Server.clients)
        	{
        		if(s.clientName.equals(this.clientName))
        		{
        			Server.clients.remove(s);
        			break;
        		}
        	}

        	//close when client exit
        	out.println("Are you sure? Press Enter Key to Quit");
        	in.close();
        	out.close();
        	this.socket.close();

		}   
		catch(Exception e)
		{
			out.println("Error in reader...");
			
		}   
		
	}


	//listner for bidhistry
	//every 500ms client can known bid updates
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Stock.isBidHistry(symble))
		{
			currentUpdatePosition = Stock.numberOfBidHistory(symble);
			if(myPoitionInBidHistry < currentUpdatePosition)
			{
				while(myPoitionInBidHistry != currentUpdatePosition)
				{
					if(!clientName.equals(Stock.clientNameOfHistry(symble, myPoitionInBidHistry)))
					{
						//print updated new bids
						out.println(" \n");
						out.println("*************************************************************");
						out.println(Stock.updateBidtHistry(symble, myPoitionInBidHistry));
						out.println("************************************************************* \n");
						out.println("Enter your new Bid : ");

					}
					myPoitionInBidHistry++;
				}
			}
		}
		
	}
	





}
