package payroll_system;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	
}
