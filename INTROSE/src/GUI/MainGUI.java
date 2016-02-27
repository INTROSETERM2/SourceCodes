package GUI;
import java.awt.BorderLayout;

import javax.swing.*;

import GUI.Product.AddProduct;
import GUI.BranchUI.AddBranch;
import GUI.ControlPanel.GUIMainControlPanel;
import GUI.Receipt.POSReceipt;
import GUI.UserAccount.Login;


public class MainGUI {
	private JLabel leftLabel = new JLabel();
	private JLabel rightLabel = new JLabel();	
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftLabel),new JScrollPane(rightLabel));
	private JFrame frame = new JFrame("POS MAKER CONTACT US: 09$$$$$$$$");
	
	
	public MainGUI(){
		  
		frame.setLayout(new BorderLayout());
		
		Login login = new Login();
		POSReceipt posReceipt = new POSReceipt();
		AddProduct addProduct = new AddProduct();
		
		AddBranch addBranch = new AddBranch();

		GUIMainControlPanel guiControlPanel = new GUIMainControlPanel();
		
		
		
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(.2d);
		leftLabel.add(guiControlPanel.getJPanel());
		//rightLabel.add(posReceipt.getJPanel());
		//rightLabel.add(login.getJPanel());
		rightLabel.add(addProduct.getJPanel());
		
		
        frame.add(splitPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);	  
		frame.setSize(790,450);
	}
	
	public static void main(String[] args) {
		 try 
		    { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		    } 
		    catch(Exception e){ 
		    }
		MainGUI sp = new MainGUI();
		
	}
}