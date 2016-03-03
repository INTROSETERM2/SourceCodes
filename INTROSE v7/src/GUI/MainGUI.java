package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import Branch.Branch;
import GUI.Product.AddProduct;
import GUI.BranchUI.AddBranch;
import GUI.ControlPanel.GUIClientLandingPanel;
import GUI.ControlPanel.GUIMainControlPanel;
import GUI.Receipt.POSReceipt;
import GUI.ReportUI.ClientDailyReport;
import GUI.UserAccount.Login;


public class MainGUI {
	private JLabel leftLabel = new JLabel();
	private JLabel rightLabel = new JLabel();	
	
	

	private JScrollPane rightPanel = new JScrollPane(rightLabel);
	private JScrollPane leftPanel = new JScrollPane(leftLabel);
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
	private JFrame frame = new JFrame("POS MAKER CONTACT US: 09$$$$$$$$");
	
	private Login login = new Login(this);
	private AddProduct addProduct = new AddProduct();
	private POSReceipt posReceipt = new POSReceipt();
	
	private ClientDailyReport clientDailyReport = new ClientDailyReport();

	
	private GUIClientLandingPanel guiClientLandingPanel= new GUIClientLandingPanel();
	private GUIMainControlPanel guiControlPanel;
	

	public static Branch BRANCH = new Branch();
	
	
	public MainGUI(){
		  
		frame.setLayout(new BorderLayout());
		
		AddProduct addProduct = new AddProduct();
		AddBranch addBranch = new AddBranch();
		

			

		rightPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		rightPanel.setPreferredSize(new Dimension(2000, 600));
		
		
//		leftPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		leftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		leftPanel.setPreferredSize(new Dimension(2000, 600));

		splitPane.setEnabled(false);
		splitPane.setResizeWeight(.2d);
//		leftLabel.add(guiControlPanel.getJPanel());
		
//		leftLabel.setVisible(false);
//		rightLabel.add(login.getJPanel());
		rightLabel.add(login.getJPanel());
		
		
        frame.add(splitPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);	  
		frame.setSize(790,450);
		frame.setLocationRelativeTo(null);
	}
	
	public void removeLeftSplit(){
		leftLabel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	public void removeAllRightSplit(){
		rightLabel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	public void setRightSplit(JPanel guiClass) {
		rightLabel.add(guiClass);
	}
	
	public void removeRightSplit(JPanel guiClass)
	{
		rightLabel.remove(guiClass);
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	public void setLeftSplit()
	{
		guiControlPanel = new GUIMainControlPanel(this);
		leftLabel.add(guiControlPanel.getJPanel());
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
		leftLabel.revalidate();
		leftLabel.repaint();
	}
	
	public void removeAllLeftSplit()
	{
		leftLabel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{ 
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		} 
		catch(Exception e){ 
		}
	MainGUI sp = new MainGUI();
	}
}