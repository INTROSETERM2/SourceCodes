package GUI.ControlPanel;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;


public class GUIBottomControlPanel {
	
	private JPanel jPanel = new JPanel();
	
	public GUIBottomControlPanel()
	{
		jPanel.setSize(150,450);
		jPanel.setLayout(null);
		
		JLabel lblControlPanel = new JLabel("Sub Panel");
		lblControlPanel.setBounds(7, 7, 84, 23);
		lblControlPanel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jPanel.add(lblControlPanel);
		
		JButton btnAddSales = new JButton("Add Sales");
		btnAddSales.setBounds(7, 34, 136, 23);
		btnAddSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}	
		});
		jPanel.add(btnAddSales);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(7, 378, 136, 23);
		jPanel.add(btnLogout);	 

	}
	public JPanel getJPanel() {
		return jPanel;
	}
}
