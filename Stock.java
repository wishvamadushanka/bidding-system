import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Stock
{
	private static Map<String, Details>  dataStock = new HashMap<String, Details>();		//this is map updated price with name symble
	private static Map<String,ArrayList<BidHistory>> bidhistry = new HashMap<>();			//this is bid histry map
	private static String[] head;															//for read line from file
	private static Date beginSever = Calendar.getInstance().getTime();						//started date
	
	Stock (String file)
	{
		try 
		{
			String line = "";
			//object of reader
			BufferedReader reader = new BufferedReader(new FileReader(file));
			//read line first
			line = reader.readLine();
			//splitting
			head = line.split(",");
			//find index of symbol
			int indexSymble = getIndex("Symbol");
			//find index of name
			int indexName = getIndex("Security Name");
			
			//if have not that field
			if(indexName == -1 || indexSymble == -1)
			{
				System.out.println("error in file");
				System.exit(0);
			}
			
			//random number generator for price
			Random r = new Random();
			
			//read file
			while(!((line = reader.readLine()) == null))
			{
				//Generate randon double number
				double i = r.nextDouble()*100 + 1;
				//spiltting
				head = line.split(",");
				double price = Math.round(i*10)/10.0;
				//store data
				dataStock.put(head[indexSymble], new Details(head[indexName], price));
			}
			reader.close();

			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Enter correct file.....");
			System.exit(0);
		}
	}

	//this is method gives whether the given symble is in datastore
	public static boolean isSymbleIn(String sym)
	{
		
		return dataStock.containsKey(sym);
	}
	
	//this is method which give index of given string
	public int getIndex(String word)
	{
		for(int i = 0; i < head.length; i++)
		{
			if(head[i].equals(word))
			{
				return i;
			}
		}
		return -1;
	}
	
	//this is method which add new histry for given symble
	public static synchronized void addBidHistry(String sym, String name, Date t, double price)
	{
		bidhistry.get(sym).add(new BidHistory(t, name, price));
	}
	
	//this returns number of bid histry of given symble
	public static int numberOfBidHistory(String sym)
	{
		return bidhistry.get(sym).size();
	}

	//returns whethr the symble has histry
	public static boolean isBidHistry(String sym)
	{
		return bidhistry.containsKey(sym);
	}
	
	//returns histry of a given bid
	public static String updateBidtHistry(String sym, int place)
	{
		return bidhistry.get(sym).get(place).clientHistry();
	}
	
	//returns name of client given histry
	public static String clientNameOfHistry(String sym, int place)
	{
		return bidhistry.get(sym).get(place).getname();
	}
	
	//creat new bid histry for given symble
	public static synchronized void newBidHistory(String sym)
	{
		bidhistry.put(sym ,new ArrayList<BidHistory>());
		bidhistry.get(sym).add(new BidHistory(beginSever, "Initially", dataStock.get(sym).getPrice()));
	}

	//return histry of given symble
	public static String getBidHistry(String sym, int length)
	{
		String word = "";
		for(int i = length - 1; i > 0; i--)
		{
			word += bidhistry.get(sym).get(i).bidHistryPop();
		}
		return word;
	}
	
	
	//return current price of given symble
	public static synchronized double currentPrice(String sym) //using this method, get current price to show in GUI
	{
		return dataStock.get(sym).getPrice();
	}

	//set new price
	public static synchronized void setNewPrice(String sym, double val)
	{
		dataStock.get(sym).setPrice(val);
	}
	

}

//this is class which can store name and price of a given symble
class Details
{
	private String security_name ;
	private double price;
	
	//cunstruoctr
	public Details(String security_name, double price) {
		super();
		this.security_name = security_name;
		this.price = price;
	}
	
	//retuns name
	public String getName() {
		return security_name;
	}

	//return price
	public double getPrice() {
		return price;
	}

	//set price
	public void setPrice(double price) {
		this.price = price;
	}
	
}


//this class can store detail of a bid histry.each one histry has this obj
class BidHistory
{
	private Date date;
	private String clientName;
	private double price;
	
	//cunstructor
	public BidHistory(Date t, String name, double pri)
	{
		this.date = t;
		this.clientName = name;
		this.price = pri;
	}
	
	//return time
	public Date getTime()
	{
		return date;
	}
	
	//return client name
	public String getname()
	{
		return clientName;
	}
	
	//return price client bided
	public double getprice()
	{
		return price;
	}

	//return histry for the bid histry dialog box
	public String bidHistryPop()
	{
		return "> " + price + " Bid by " + clientName + " at " + date + ".\n";
	}
	
	//return histry for client
	public String clientHistry()
	{
		return "New bid " + price + " from " + clientName + " at " + date + ". ";
	}
}

