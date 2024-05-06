package payroll_system;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import payroll_system.views.hr.DashboardHr;
 

public class ButtonHelper extends Assets {

	private static final long serialVersionUID = 1L; 
	
	
	
	public JButton configButton(String name,String filename, ActionListener actionListener ) {
		 
		
		JButton button = new JButton(name,new ImageIcon(basePath() + "assets/" + filename));
 		
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setMargin(new Insets(5, 10, 5,10));
		button.addActionListener(actionListener);
		
		return button;
	}
	
	
}
