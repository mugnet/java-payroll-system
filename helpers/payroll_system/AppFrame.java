package payroll_system;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame; 

public class AppFrame extends JFrame implements AppFrameInterface{

	public JComponent component;
	
	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JComponent component) {
		this.component = component;
	}
	
 

	public void padding(int t, int r, int b, int l) {
		this.component.setBorder(BorderFactory.createEmptyBorder(t,r,b,l));
	}
	
	public void bounds(int t, int r, int b, int l) {
		this.component.setBounds(t,r,b,l);
	}
	

	
	
}
