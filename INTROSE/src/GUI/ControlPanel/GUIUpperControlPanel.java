package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class GUIUpperControlPanel implements ActionListener{
	
	private JPanel jPanel = new JPanel();

	
	private JLabel lblControlPanel = new JLabel("Main Panel");
	private JButton btnAddSales = new JButton("Add Sales");		
	private JButton btnStaff = new JButton("STAFF");
	private JButton btnBranch = new JButton("BRANCH");
	private JButton btnProducts = new JButton("PRODUCTS");
	private JButton btnReports = new JButton("Reports");
	private JButton btnLogout = new JButton("Logout");		
	private ActListener act = new ActListener();

	
	
	private JButton btnDailyReport = new JButton("Daily Report");
	
	public GUIUpperControlPanel(){
		
		
		jPanel.setSize(150,450);
		jPanel.setLayout(new MigLayout("", "[136px]", "[23px][23px][23px][][][][][][][][][][][]"));
		
		lblControlPanel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jPanel.add(lblControlPanel, "cell 0 0,alignx left,aligny top");
		

		
		btnReports.addActionListener(act);
		btnProducts.addActionListener(act);
		btnBranch.addActionListener(act);
		btnStaff.addActionListener(act);		
		btnDailyReport.addActionListener(act);
		btnAddSales.addActionListener(act);
		
		
		jPanel.add(btnAddSales, "cell 0 1,growx");
		jPanel.add(btnDailyReport, "cell 0 2,growx");
		jPanel.add(btnStaff, "cell 0 4,growx");
		jPanel.add(btnBranch, "cell 0 5,growx");
		jPanel.add(btnProducts, "cell 0 6,growx");
		jPanel.add(btnReports, "cell 0 12,growx");
		
		btnLogout.addActionListener(act);
		jPanel.add(btnLogout, "cell 0 13,growx,aligny top");

		
	}
	
	public JPanel getJPanel() {
		System.out.println(MainGUI.RANK);
		if(MainGUI.RANK ==  0) // pag client
		{
			btnStaff.setVisible(true);
			btnBranch.setVisible(true);
			btnProducts.setVisible(true);
			btnReports.setVisible(true);
			btnAddSales.setVisible(false);
			btnDailyReport.setVisible(false);
			btnLogout.setVisible(true);
		}
		
		else if (MainGUI.RANK == 1)/// pag staff
		{
			btnAddSales.setVisible(true);
			btnDailyReport.setVisible(true);
			btnStaff.setVisible(false);
			btnBranch.setVisible(false);
			btnProducts.setVisible(false);
			btnReports.setVisible(false);
			btnLogout.setVisible(false);
		}	
		else if (MainGUI.RANK == -1) //no one logged int
		{
			btnStaff.setVisible(false);
			btnBranch.setVisible(false);
			btnProducts.setVisible(false);
			btnReports.setVisible(false);
			btnAddSales.setVisible(false);
			btnDailyReport.setVisible(false);
			btnLogout.setVisible(false);
		}
		
		jPanel.revalidate();
		jPanel.repaint();
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnAddSales){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		
    		if(e.getSource() == btnStaff){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");
    			System.out.println(MainGUI.RANK);	

    		}
    		
    		if(e.getSource() == btnBranch){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		
    		if(e.getSource() == btnProducts){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		
    		if(e.getSource() == btnReports){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		
    		if(e.getSource() == btnLogout){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
