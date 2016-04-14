package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.Receipt.POSReceipt;
import GUI.UserAccount.Login;

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
	private JButton btnViewDailyReport = new JButton("Today's Report");
	private JButton btnPushDailyReport = new JButton("Send Daily Report");
	private JButton btnLogout = new JButton("Logout");		
	private ActListener act = new ActListener();
	private MainGUI mainGUI;

	
	private Login login = new Login(mainGUI);

	public GUIUpperControlPanel(MainGUI mainGUI){
		
		this.mainGUI = mainGUI;
		jPanel.setSize(207,450);
		jPanel.setLayout(null);
		lblControlPanel.setBounds(7, 11, 190, 20);
		
		lblControlPanel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblControlPanel.setText(mainGUI.BRANCH.getBranchName());
		jPanel.add(lblControlPanel);
		btnPushDailyReport.setBounds(7, 107, 190, 23);
		

			
		btnPushDailyReport.addActionListener(act);
		btnAddSales.setBounds(7, 56, 190, 20);
		btnAddSales.addActionListener(act);
		btnViewDailyReport.setBounds(7, 134, 190, 23);
		btnViewDailyReport.addActionListener(act);
		btnLogout.setBounds(7, 309, 190, 33);
		btnLogout.addActionListener(act);

		
		//jPanel.add(btnAddSales);
		//jPanel.add(btnPushDailyReport );
		//jPanel.add(btnViewDailyReport );
		jPanel.add(btnLogout );
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnAddSales){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    			POSReceipt posReceipt = new POSReceipt(mainGUI);
    			mainGUI.removeAllRightSplit();
    			mainGUI.setRightSplit(posReceipt.getJPanel());
    		}
    		if(e.getSource() == btnPushDailyReport){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");
    		}
    		if(e.getSource() == btnViewDailyReport){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");
    		}
    		if(e.getSource() == btnLogout){
    			//Kung ano mangyayari kapag pinindot button
    			
    			
    			MainGUI.BRANCH.setBranchID(-1);
//
//    			mainGUI.removeAllRightSplit();
//    			mainGUI.setRightSplit(login.getJPanel());
//				
//    			
//    			mainGUI.removeAllLeftSplit();
//    			
//    			
//    			System.out.println("t(O_O)t");	
    			
    			mainGUI.logout();
    		}
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
