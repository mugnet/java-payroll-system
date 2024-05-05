package payroll_system;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Assets extends Base {

	private  final  String DIR = "assets/";
	
	public Assets() {
	  assets("");	
	}
	
	public void loadImage(JLabel jlabel, String filename,int width, int height) {
		 		
		ImageIcon icon = new ImageIcon(basePath() + DIR + filename);
		
		
		
		
		 Image image = icon.getImage();
	        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledImage);
	        jlabel.setIcon(scaledIcon);
	        jlabel.setText(null);
		
	}
	
	
	public JButton configButton(String name,String filename) {
		 
		
		JButton button = new JButton(name,new ImageIcon(basePath() + DIR + filename));
 		
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setMargin(new Insets(5, 10, 5,10));
		button.addActionListener(new ButtonClickListener());
		
		return button;
	}
	
	static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the action command of the button that triggered the event
            String buttonName = e.getActionCommand();
            System.out.println("Button clicked: " + buttonName);
        }
    }
	
}
