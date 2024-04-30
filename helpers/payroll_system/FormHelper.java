package payroll_system;

import java.awt.KeyboardFocusManager;
import java.awt.Window;

import javax.swing.JFrame;


public class FormHelper{

	public static void show(JFrame frame, boolean visibility) {
	
		
	
		frame.setVisible(visibility);
	
		
	     
		
	}
	
	
	 public  void hideActiveFrame() {
	        // Get the currently active frame using KeyboardFocusManager
	        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        Window activeWindow = manager.getActiveWindow();

	        // Check if the active window is a JFrame and hide it
	        if (activeWindow instanceof JFrame) {
	            JFrame activeFrame = (JFrame) activeWindow;
	            activeFrame.setVisible(false);
	        }
	    }
	
	
	
	 
}
