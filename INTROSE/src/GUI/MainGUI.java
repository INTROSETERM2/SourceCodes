package GUI;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.*;

import Branch.Branch;
import GUI.Product.AddProduct;
import GUI.ControlPanel.GUIClientLandingPanel;
import GUI.ControlPanel.GUIMainControlPanel;
import GUI.Receipt.POSReceipt;
import GUI.UserAccount.Login;


public class MainGUI {
	private JPanel leftJPanel = new JPanel();
	private JPanel rightJPanel = new JPanel();	
	private JScrollPane rightScrollPane = new JScrollPane(rightJPanel);
	private JScrollPane leftScrollPane = new JScrollPane(leftJPanel);
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightScrollPane);
	private JFrame frame = new JFrame("GIOVANA");
	private Login login = new Login(this);
//	private AddProduct addProduct = new AddProduct(); TRY REMOVE
	private GUIClientLandingPanel guiClientLandingPanel= new GUIClientLandingPanel();
	private GUIMainControlPanel guiControlPanel;

	public static Branch BRANCH = new Branch();
	
	
	public MainGUI(){
		  
		frame.setLayout(new BorderLayout());
		rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		rightScrollPane.setPreferredSize(new Dimension(2000, 600));


		splitPane.setEnabled(false);
		splitPane.setResizeWeight(.15d);

		rightJPanel.setLayout(new BorderLayout());
		leftJPanel.setLayout(new BorderLayout());

		rightJPanel.add(login.getJPanel(), BorderLayout.CENTER);
		
        frame.add(splitPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);	  
//		frame.setSize(1000,450);
	

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
	}
	
	
	public void removeLeftSplit(){
		leftJPanel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}	
	
	
	public void removeAllRightSplit(){
		rightJPanel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	   
	public void setRightSplit(JPanel guiClass) {
		rightJPanel.add(guiClass, BorderLayout.LINE_START);
	}
	
	public void removeRightSplit(JPanel guiClass)
	{
		rightJPanel.remove(guiClass);
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	public void setLeftSplit()
	{
		guiControlPanel = new GUIMainControlPanel(this);
		leftJPanel.add(guiControlPanel.getJPanel());
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
		leftJPanel.revalidate();
		leftJPanel.repaint();
	}
	
	public void removeAllLeftSplit()
	{
		leftJPanel.removeAll();
		frame.revalidate(); // For Java 1.7 or above
		frame.repaint();
	}
	
	public Dimension getRightJPanelSize(){
		return rightJPanel.getSize();
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

	public void logout() {
		// TODO Auto-generated method stub
		frame.dispose(); 
		MainGUI sp = new MainGUI();
		
	}
	
	public Frame getJFrame(){
		return frame;
	}
}