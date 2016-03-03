package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
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
		jPanel.setSize(150,450);
		jPanel.setLayout(new MigLayout("", "[136px]", "[23px][23px][23px][][][][][][][][][][][]"));
		
		lblControlPanel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jPanel.add(lblControlPanel, "cell 0 0,alignx left,aligny top");
		

			
		btnPushDailyReport.addActionListener(act);
		btnAddSales.addActionListener(act);
		btnViewDailyReport.addActionListener(act);
		btnLogout.addActionListener(act);

		
		jPanel.add(btnAddSales, "cell 0 1,growx");
		jPanel.add(btnPushDailyReport, "cell 0 3,growx" );
		jPanel.add(btnViewDailyReport, "cell 0 4,growx" );
		jPanel.add(btnLogout, "cell 0 5,growx" );
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnAddSales){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
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
    			
    			
    			MainGUI.RANK= -1;
    			MainGUI.ACCOUNT_NUMBER = -1;
    			mainGUI.removeAllRightSplit();
    			mainGUI.setRightSplit(login.getJPanel());
				
    			
    			mainGUI.removeAllLeftSplit();
    			
    			
    			System.out.println("t(O_O)t");	
    			
    		}
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
