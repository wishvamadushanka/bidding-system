import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea textArea = new JTextArea(); //text Area to print
	/*J labels to show current prices*/
	JLabel price1 = new JLabel(); //FB
	JLabel price2 = new JLabel(); //VRTU
	JLabel price3 = new JLabel(); //MSFT
	JLabel price4 = new JLabel(); //GOOGL
	JLabel price5 = new JLabel(); //YHOO
	JLabel price6 = new JLabel(); //XLNX
	JLabel price7 = new JLabel(); //TSLA
	JLabel price8 = new JLabel(); //TXN
	int logSize;
	boolean ifStart;
	public Timer timer;
	
	public Display() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (int)((d.getWidth()-getWidth())/2);
				int y = (int)((d.getHeight()-getHeight())/2);
				setLocation(x, y);
			}
		});

		timer = new Timer(500, this);
		timer.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//jlabel to show symboles column
		JLabel header1 = new JLabel("Symbol");
		header1.setBackground(Color.BLACK);
		header1.setForeground(Color.RED);
		header1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		header1.setHorizontalAlignment(SwingConstants.CENTER);
		header1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		header1.setBounds(0, 0, 130, 24);
		contentPane.add(header1);
		
		//security name column
		JLabel lblSecurityName = new JLabel("Security Name");
		lblSecurityName.setBackground(Color.BLACK);
		lblSecurityName.setForeground(Color.RED);
		lblSecurityName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecurityName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		lblSecurityName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblSecurityName.setBounds(128, 0, 254, 24);
		contentPane.add(lblSecurityName);
		
		//current price column(updates after bidding)
		JLabel lblCurrrentPrice = new JLabel("Currrent Price");
		lblCurrrentPrice.setBackground(Color.BLACK);
		lblCurrrentPrice.setForeground(Color.RED);
		lblCurrrentPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrrentPrice.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		lblCurrrentPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblCurrrentPrice.setBounds(380, 0, 145, 24);
		contentPane.add(lblCurrrentPrice);
		
		//show bid history column
		JLabel lblShowBidHistory = new JLabel("Show Bid History");
		lblShowBidHistory.setBackground(Color.BLACK);
		lblShowBidHistory.setForeground(Color.RED);
		lblShowBidHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowBidHistory.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		lblShowBidHistory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblShowBidHistory.setBounds(524, 0, 178, 24);
		contentPane.add(lblShowBidHistory);
		
		/*************Symbol column, this show first b symbols in sorted map***********************/
		
		JLabel FB = new JLabel("FB");
		FB.setFont(new Font("Tahoma", Font.BOLD, 12));
		FB.setHorizontalAlignment(SwingConstants.CENTER);
		FB.setBounds(0, 23, 129, 24);
		contentPane.add(FB);
		FB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel VRTU = new JLabel("VRTU");
		VRTU.setFont(new Font("Tahoma", Font.BOLD, 12));
		VRTU.setHorizontalAlignment(SwingConstants.CENTER);
		VRTU.setBounds(0, 46, 129, 24);
		VRTU.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(VRTU);
		
		JLabel MSFT = new JLabel("MSFT");
		MSFT.setFont(new Font("Tahoma", Font.BOLD, 12));
		MSFT.setHorizontalAlignment(SwingConstants.CENTER);
		MSFT.setBounds(0, 69, 129, 24);
		MSFT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(MSFT);
		
		JLabel GOOGL = new JLabel("GOOGL");
		GOOGL.setFont(new Font("Tahoma", Font.BOLD, 12));
		GOOGL.setHorizontalAlignment(SwingConstants.CENTER);
		GOOGL.setBounds(0, 92, 129, 24);
		GOOGL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(GOOGL);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 212, 702, 147);
		contentPane.add(scrollPane);
		
		//text area to show bid history, this area updates about all bidding happening
		//JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(new Color(153, 204, 255));
		textArea.setEditable(false);
		textArea.append("Bid History...\n");
		
		JLabel YHOO = new JLabel("YHOO");
		YHOO.setFont(new Font("Tahoma", Font.BOLD, 12));
		YHOO.setHorizontalAlignment(SwingConstants.CENTER);
		YHOO.setBounds(0, 114, 129, 24);
		YHOO.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(YHOO);
		
		JLabel XLNX = new JLabel("XLNX");
		XLNX.setFont(new Font("Tahoma", Font.BOLD, 12));
		XLNX.setHorizontalAlignment(SwingConstants.CENTER);
		XLNX.setBounds(0, 138, 129, 24);
		XLNX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(XLNX);
		
		JLabel TSLA = new JLabel("TSLA");
		TSLA.setFont(new Font("Tahoma", Font.BOLD, 12));
		TSLA.setHorizontalAlignment(SwingConstants.CENTER);
		TSLA.setBounds(0, 162, 129, 24);
		TSLA.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(TSLA);
		
		JLabel TXN = new JLabel("TXN");
		TXN.setFont(new Font("Tahoma", Font.BOLD, 12));
		TXN.setHorizontalAlignment(SwingConstants.CENTER);
		TXN.setBounds(0, 187, 129, 24);
		TXN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(TXN);
		/************************************************************************************/
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(0, 138, 568, 0);
		contentPane.add(separator);
		
		
		/****************************buttons to check bid history****************************/
		JButton H_FB = new JButton("FB");
		H_FB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
					ifStart = Stock.isBidHistry("FB");
					if(!ifStart){
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}else {
						logSize = Stock.numberOfBidHistory("FB");
						if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("FB", logSize) + "\n");
					}
					}
			}
		});
		H_FB.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_FB.setBounds(524, 23, 178, 24);
		contentPane.add(H_FB);
		
		JButton H_VRTU = new JButton("VRTU");
		H_VRTU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ifStart = Stock.isBidHistry("VRTU");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("VRTU");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("VRTU", logSize) + "\n");
					}
				}
			}
		});
		H_VRTU.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_VRTU.setBounds(524, 46, 178, 24);
		contentPane.add(H_VRTU);
		
		JButton H_MSFT = new JButton("MSFT");
		H_MSFT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("MSFT");
				ifStart = Stock.isBidHistry("MSFT");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("MSFT");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("MSFT", logSize) + "\n");
					}
				}
			}
		});
		H_MSFT.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_MSFT.setBounds(524, 69, 178, 24);
		contentPane.add(H_MSFT);
		
		JButton H_GOOGL = new JButton("GOOGL");
		H_GOOGL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("GOOGL");
				ifStart = Stock.isBidHistry("GOOGL");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("GOOGL");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("GOOGL", logSize) + "\n");
					}
				}
			}
		});
		H_GOOGL.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_GOOGL.setBounds(524, 92, 178, 24);
		contentPane.add(H_GOOGL);
		
		JButton H_YHOO = new JButton("YHOO");
		H_YHOO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("YHOO");
				ifStart = Stock.isBidHistry("YHOO");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("YHOO");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("YHOO", logSize) + "\n");
					}
				}
			}
		});
		H_YHOO.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_YHOO.setBounds(524, 114, 178, 24);
		contentPane.add(H_YHOO);
		
		JButton H_XLNX = new JButton("XLNX");
		H_XLNX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("XLNX");
				ifStart = Stock.isBidHistry("XLNX");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("XLNX");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("XLNX", logSize) + "\n");
					}
				}
			}
		});
		H_XLNX.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_XLNX.setBounds(524, 138, 178, 24);
		contentPane.add(H_XLNX);
		
		JButton H_TSLA = new JButton("TSLA");
		H_TSLA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("TSLA");
				ifStart = Stock.isBidHistry("TSLA");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("TSLA");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("TSLA", logSize) + "\n");
					}
				}
			}
		});
		H_TSLA.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_TSLA.setBounds(524, 162, 178, 24);
		contentPane.add(H_TSLA);
		
		JButton H_TXN = new JButton("TXN");
		H_TXN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//logSize = Stock.numberOfBidHistory("TXN");
				ifStart = Stock.isBidHistry("TXN");
				if(!ifStart){
					JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
				}else {
					logSize = Stock.numberOfBidHistory("TXN");
					if(logSize == 1)
					{
						JOptionPane.showMessageDialog(null, "No Bids Placed Yet");
					}
					else
					{
						//for(int i = logSize -1 ; i >=0; i--)
						JOptionPane.showMessageDialog(null,Stock.getBidHistry("TXN", logSize) + "\n");
					}
					
				}
			}
		});
		H_TXN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		H_TXN.setBounds(524, 187, 178, 24);
		contentPane.add(H_TXN);
		/***************************************************************************************************/
		
		/*******************price column*********************************************************************/
		price1.setForeground(new Color(0, 0, 255));
		
		//price1 = new JLabel(Integer.toString(this.alI.get(0)));
		price1.setHorizontalAlignment(SwingConstants.CENTER);
		price1.setBounds(380, 23, 145, 24);
		price1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price1);
		price2.setForeground(new Color(0, 0, 255));
		
		//price2 = new JLabel(Integer.toString(this.alI.get(1)));
		price2.setHorizontalAlignment(SwingConstants.CENTER);
		price2.setBounds(380, 46, 145, 24);
		price2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price2);
		price3.setForeground(new Color(0, 0, 255));
		
		//price3 = new JLabel(Integer.toString(this.alI.get(2)));
		price3.setHorizontalAlignment(SwingConstants.CENTER);
		price3.setBounds(380, 69, 145, 24);
		price3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price3);
		price4.setForeground(new Color(0, 0, 255));
		
		//price4 = new JLabel(Integer.toString(this.alI.get(3)));
		price4.setHorizontalAlignment(SwingConstants.CENTER);
		price4.setBounds(380, 92, 145, 24);
		price4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price4);
		price5.setForeground(new Color(0, 0, 255));
		
		//price5 = new JLabel(Integer.toString(this.alI.get(4)));
		price5.setHorizontalAlignment(SwingConstants.CENTER);
		price5.setBounds(380, 114, 145, 24);
		price5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price5);
		price6.setForeground(new Color(0, 0, 255));
		
		//price6 = new JLabel(Integer.toString(this.alI.get(5)));
		price6.setHorizontalAlignment(SwingConstants.CENTER);
		price6.setBounds(380, 138, 145, 24);
		price6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price6);
		price7.setForeground(new Color(0, 0, 255));
		
		//price7 = new JLabel(Integer.toString(this.alI.get(6)));
		price7.setHorizontalAlignment(SwingConstants.CENTER);
		price7.setBounds(380, 162, 145, 24);
		price7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price7);
		price8.setForeground(new Color(0, 0, 255));
		
		//price8 = new JLabel(Integer.toString(this.alI.get(7)));
		price8.setHorizontalAlignment(SwingConstants.CENTER);
		price8.setBounds(380, 187, 145, 24);
		price8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(price8);
		
		JLabel SN_VRTU = new JLabel("Virtusa Corporation - common stock");
		SN_VRTU.setHorizontalAlignment(SwingConstants.CENTER);
		SN_VRTU.setBounds(128, 46, 254, 24);
		SN_VRTU.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_VRTU);
		
		JLabel SN_TSLA = new JLabel("Tesla Motors");
		SN_TSLA.setHorizontalAlignment(SwingConstants.CENTER);
		SN_TSLA.setBounds(128, 162, 254, 24);
		SN_TSLA.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_TSLA);
		
		JLabel SN_GOOGL = new JLabel("Google Inc. - Class C Capital Stock");
		SN_GOOGL.setHorizontalAlignment(SwingConstants.CENTER);
		SN_GOOGL.setBounds(128, 92, 254, 24);
		SN_GOOGL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_GOOGL);
		
		JLabel SN_YHOO = new JLabel("Yahoo! Inc. - Common Stock");
		SN_YHOO.setHorizontalAlignment(SwingConstants.CENTER);
		SN_YHOO.setBounds(128, 114, 254, 24);
		SN_YHOO.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_YHOO);
		
		JLabel SN_FB = new JLabel("Facebook");
		SN_FB.setHorizontalAlignment(SwingConstants.CENTER);
		SN_FB.setBounds(128, 23, 254, 24);
		SN_FB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_FB);
		
		JLabel SN_XLNX = new JLabel("Xilinx");
		SN_XLNX.setHorizontalAlignment(SwingConstants.CENTER);
		SN_XLNX.setBounds(128, 138, 254, 24);
		SN_XLNX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_XLNX);
		
		JLabel SN_TXN = new JLabel("Texas Instruments Incorporated - Common Stock");
		SN_TXN.setHorizontalAlignment(SwingConstants.CENTER);
		SN_TXN.setBounds(128, 187, 254, 24);
		SN_TXN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_TXN);
		
		JLabel SN_MSFT = new JLabel("Microsoft Corporation - Common Stock");
		SN_MSFT.setHorizontalAlignment(SwingConstants.CENTER);
		SN_MSFT.setBounds(128, 69, 254, 24);
		SN_MSFT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(SN_MSFT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Client s: Server.clients)
		{
			if(s.newBid)
			{
				Stock.addBidHistry(s.symble, s.clientName, s.newBidDate, s.bidPrice);
				//prints bid in the text Area
				textArea.append(s.newBidDate + " : " + s.clientName + " placed a Bid of "+  s.bidPrice  +" on "+ s.symble  +".\n");
				s.newBid = false;
			}	
		}
		//update current Price in GUI
		price1.setText(Double.toString(Stock.currentPrice("FB")));
		price2.setText(Double.toString(Stock.currentPrice("VRTU")));
		price3.setText(Double.toString(Stock.currentPrice("MSFT")));
		price4.setText(Double.toString(Stock.currentPrice("GOOGL")));
		price5.setText(Double.toString(Stock.currentPrice("YHOO")));
		price6.setText(Double.toString(Stock.currentPrice("XLNX")));
		price7.setText(Double.toString(Stock.currentPrice("TSLA")));
		price8.setText(Double.toString(Stock.currentPrice("TXN")));
	}
}
