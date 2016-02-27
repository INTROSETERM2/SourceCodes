package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import GUI.BranchUI.AddBranch;
import GUI.UserAccount.Login;

public class GUIMainControlPanel {
	private JLabel toplabel = new JLabel();
	private JLabel bottomlabel = new JLabel();	
	private JScrollPane scrollPane = new JScrollPane(bottomlabel);
	private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(toplabel),scrollPane);
	private JPanel panel = new JPanel();
	
	public GUIMainControlPanel(){
		  
		panel.setLayout(new BorderLayout());
		
		Login login = new Login();
		POSReceipt posReceipt = new POSReceipt();
		AddBranch addBranch = new AddBranch();		
		
		GUIUpperControlPanel guiUpperControlPanel = new GUIUpperControlPanel();
		GUIBottomControlPanel guiBottomControlPanel = new GUIBottomControlPanel();
		
		splitPane.setEnabled(true);
		splitPane.setResizeWeight(.5d);
		toplabel.add(guiUpperControlPanel.getJPanel());
		//rightLabel.add(posReceipt.getJPanel());
		//rightLabel.add(login.getJPanel());
		bottomlabel.add(guiBottomControlPanel.getJPanel());
        panel.add(splitPane, BorderLayout.CENTER);
        panel.setVisible(true);	  
		panel.setSize(790,450);


	}
	
	public JPanel getJPanel(){
		return panel;
	}

}
