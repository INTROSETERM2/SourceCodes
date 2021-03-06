package GUI.ControlPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import GUI.MainGUI;
import GUI.BranchUI.AddBranch;
import GUI.Receipt.POSReceipt;

public class GUIMainControlPanel {
	private JLabel toplabel = new JLabel();
	private JLabel bottomlabel = new JLabel();	
	private JScrollPane topPanel = new JScrollPane(toplabel);
	private JScrollPane bottomPanel = new JScrollPane(bottomlabel);
	private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
	private JPanel panel = new JPanel();
	
	public GUIMainControlPanel(MainGUI guiMain){
		  
		panel.setLayout(new BorderLayout());
		
		//POSReceipt posReceipt = new POSReceipt();
		//AddBranch addBranch = new AddBranch();		
		
//		topPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		topPanel.setPreferredSize(new Dimension(2000, 600));
		splitPane.setOneTouchExpandable(true);
		
		
//		bottomPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		bottomPanel.setPreferredSize(new Dimension(2000, 600));	
		
		GUIUpperControlPanel guiUpperControlPanel = new GUIUpperControlPanel(guiMain);
		GUIPictureControlPanel guiBottomControlPanel = new GUIPictureControlPanel();
		GUIClientUpperControlPanel guiClientUpperControlPanel = new GUIClientUpperControlPanel(guiMain);

		splitPane.setEnabled(true);
		splitPane.setResizeWeight(.5d);
		
		if(MainGUI.BRANCH.getBranchID()==0)
		{
			toplabel.add(guiClientUpperControlPanel.getJPanel());

		}
		else{
			toplabel.add(guiUpperControlPanel.getJPanel());

		}

		bottomlabel.add(guiBottomControlPanel.getJPanel());
        panel.add(splitPane, BorderLayout.CENTER);
        panel.setVisible(true);	  
        
		panel.setSize(170,410);
	}
	
	public JPanel getJPanel(){
		return panel;
	}
	
	public void setTopSplit(JPanel guiClass) {
		toplabel.add(guiClass);
	}
	
	public void setBottomSplit(JPanel guiClass) {
		bottomlabel.add(guiClass);
	}
}
