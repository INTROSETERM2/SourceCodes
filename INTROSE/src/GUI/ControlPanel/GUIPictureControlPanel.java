package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.Receipt.POSReceipt;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class GUIPictureControlPanel implements ActionListener{
	
	private JPanel jPanel = new JPanel();
	private JLabel lblPicturePanel = new JLabel(POSReceipt.IMAGE);
	
	public GUIPictureControlPanel(){
		
		jPanel.setSize(150,450);
		jPanel.setLayout(null);
		
		lblPicturePanel.setBounds(0, 0, 150, 450);
		jPanel.add(lblPicturePanel);

	}
	public JPanel getJPanel() {
		return jPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
