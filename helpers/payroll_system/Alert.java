package payroll_system;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

public class Alert {
	
 private String message;
 private String alertType;

 
 public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getAlertType() {
	return alertType;
}
public void setAlertType(String alertType) {
	this.alertType = alertType;
}
 

public void success()  {
	
		 JOptionPane.showMessageDialog(null, message,"Success",JOptionPane.INFORMATION_MESSAGE);
	 
}

public void danger() {
	JOptionPane.showMessageDialog(null, message,"Danger",JOptionPane.ERROR_MESSAGE);
}

public void warning() {
	JOptionPane.showMessageDialog(null, message,"Warning",JOptionPane.WARNING_MESSAGE);
}
public void info() {
	JOptionPane.showMessageDialog(null, message,"Info",JOptionPane.INFORMATION_MESSAGE);
}
 
}
