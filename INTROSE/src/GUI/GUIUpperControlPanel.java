package GUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;


public class GUIUpperControlPanel {
	
	private JPanel jPanel = new JPanel();
	
	public GUIUpperControlPanel()
	{
		jPanel.setSize(150,450);
		jPanel.setLayout(new MigLayout("", "[136px]", "[23px][23px][23px][][][][][][][][][][][]"));
		
		JLabel lblControlPanel = new JLabel("Main Panel");
		lblControlPanel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jPanel.add(lblControlPanel, "cell 0 0,alignx left,aligny top");
		
		JButton btnAddSales = new JButton("Add Sales");
		btnAddSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}	
		});
		jPanel.add(btnAddSales, "cell 0 1,growx,aligny top");
		
		JButton btnStaff = new JButton("STAFF");
		jPanel.add(btnStaff, "cell 0 3,growx");
		
		JButton btnBranch = new JButton("BRANCH");
		jPanel.add(btnBranch, "cell 0 4,growx");
		
		JButton btnProducts = new JButton("PRODUCTS");
		jPanel.add(btnProducts, "cell 0 5,growx");
		
		JButton btnReports = new JButton("Reports");
		jPanel.add(btnReports, "cell 0 12,growx");
		
		JButton btnLogout = new JButton("Logout");
		jPanel.add(btnLogout, "cell 0 13,growx,aligny top");	 

	}
	public JPanel getJPanel() {
		return jPanel;
	}
}
