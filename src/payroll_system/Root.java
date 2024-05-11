package payroll_system;

import javax.swing.JFrame;

import payroll_system.views.LoginForm;

public class Root extends FormHelper{

	public static void main(String[] args) { 
		show(new LoginForm(),true);
		
	}
}
