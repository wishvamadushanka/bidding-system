*********************************************************
		Instructions for the Server
*********************************************************

** Compile and run Server.java

	To compile	: javac Server.java
	To run		: java Server

** GUI controls

  * The GUI displays the current stock prices of FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA and TXN.
  * 'Current Price' column includes current price and it gets updated when new bid is placed and also updated in every 500ms
  * 'Show Bid History' Column includes buttons.
  * By clicking the button, can view all valid bids placed on symbol that is corresponds to the button in a new window.
  * Text area below the table displays all the bid History of all the symbols. It gets updated when a valid bid is placed.

*********************************************************
		Instrucitons for the Clients
*********************************************************

**The client can use the terminal to connect to the server.
**Connect to the server using nc command through port no 2000("nc localhost 2000").

After connecting,
  * The client required to provide a Name. 
  * If that Name already exists, user sholud provide another name.
  * If a client quit, his name can take another client after.
  * Then client required to provide Symbol of the security he/she is willing to bid on. 
  * If client enter a valid symbol then client can start Bidding.
  * But client can change his symbol at anytime by typing "change" (without quotes).
  * Client can only enter valid bids (integers or floats).
  * The bid will be accepted only if the client's bid is larger than the stock's current price.
  * Client can see updated bid price in his terminal related to current symbol.
  * If another client bids higher than your currently bidding item, you get a message in terminal.
  * The word "quit" can be entered(without quotes) at any point in the program to exit it.

<img src="https://github.com/kavindumadushanka972/bidding-system/blob/master/1.PNG">
