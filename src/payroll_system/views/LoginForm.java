package payroll_system.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import payroll_system.Alert;
import payroll_system.Assets;
import payroll_system.Base;
import payroll_system.Database;
import payroll_system.FormHelper;
import payroll_system.services.AuthService;
import payroll_system.views.employee.DashboardEmp;
import payroll_system.views.hr.DashboardHr;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Config;
import java.awt.event.ActionEvent;

public class LoginForm extends AuthService {

	private static final long serialVersionUID = 1L;
	private JPasswordField txtPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true); 
				 
				 
				 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		
		Alert alert = new Alert();
		Assets asset  = new Assets();
		
		
		setSize(550,400);
		setResizable(false);
		setBackground(new Color(254, 248, 249));
		setLocationRelativeTo(null);	
		setName("loginForm");
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		
		JTextArea txtUsername = new JTextArea();
		txtUsername.setToolTipText("Enter username"); 
		 
		setComponent(txtUsername);
		padding(9,5,5,15);
		bounds(117, 111, 300, 34);
		
		getContentPane().add(txtUsername);
		
		JLabel lblPassword = new JLabel("Username");
		lblPassword.setBounds(117, 91, 82, 16);
		getContentPane().add(lblPassword);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setBounds(117, 167, 82, 16);
		getContentPane().add(lblPassword_2);
		

		txtPassword = new JPasswordField();
		txtPassword.setBounds(117, 180, 300, 40);
		getContentPane().add(txtPassword);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 int isEmployee =	loginUser(txtUsername.getText(),txtPassword);
			 
			 FormHelper form = new FormHelper(); 
			 form.hideActiveFrame(); 
			 
			 if(isEmployee == 2) {
				
				 FormHelper.show(new DashboardEmp(),true);
			 }else if(isEmployee ==1) {
				 FormHelper.show(new DashboardHr(),true);
			 }else {
				 alert.setMessage("We could not found any details from your entry. Please try again!");
			 }
			 
			}
		});
		btnLogin.setBounds(117, 237, 300, 42);
		getContentPane().add(btnLogin);
		
		 
		
		
		JLabel imageBanner = new JLabel("New label"); 
		getContentPane().add(imageBanner);
		
		asset.loadImage(imageBanner, "login-banner.png", 300, 200);
		
		 
	}
}
