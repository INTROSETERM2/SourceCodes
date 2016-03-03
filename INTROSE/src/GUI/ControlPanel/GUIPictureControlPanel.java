package GUI.ControlPanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class GUIPictureControlPanel implements ActionListener{
	
	private JPanel jPanel = new JPanel();
	private JLabel lblPicturePanel = new JLabel("Picture Preview");
	private JButton btnAddSales = new JButton("Add Sales");
	
	public GUIPictureControlPanel(){
		
		ActListener act = new ActListener();
		
		jPanel.setSize(150,450);
		jPanel.setLayout(null);
		
		lblPicturePanel.setBounds(7, 7, 84, 23);
		lblPicturePanel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jPanel.add(lblPicturePanel);
		

		btnAddSales.setBounds(7, 34, 136, 23);
		btnAddSales.addActionListener(act);
		jPanel.add(btnAddSales);	 

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
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
