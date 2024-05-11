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

import payroll_system.views.hr.DashboardHr;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LoginForm extends AuthService {

	private static final long serialVersionUID = 1L;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	

	/**
	 * Launch the application.
	 

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		
		Alert alert = new Alert();
		Assets asset  = new Assets();
		
		
		setSize(522,349);
		setResizable(false);
		setBackground(new Color(254, 248, 249));
		setLocationRelativeTo(null);	
		setName("loginForm");
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null); 
		
		JLabel lblPassword = new JLabel("Username");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassword.setBounds(40, 72, 108, 49);
		getContentPane().add(lblPassword);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassword_2.setBounds(40, 145, 121, 28);
		getContentPane().add(lblPassword_2);
		

		txtPassword = new JPasswordField();
		txtPassword.setBounds(147, 133, 337, 50);
		getContentPane().add(txtPassword);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 	loginUser(txtUsername.getText(),txtPassword);
			 
			 
			 
			
			 
			}
		});
		btnLogin.setBounds(40, 229, 444, 64);
		getContentPane().add(btnLogin);
		
		 
		
		
		JLabel imageBanner = new JLabel("New label"); 
		getContentPane().add(imageBanner);
		
		asset.loadImage(imageBanner, "login-banner.png", 300, 200);
		
		JLabel lblNewLabel_1 = new JLabel("User Login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Noto Sans Tagalog", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(94, 16, 337, 44);
		getContentPane().add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(147, 72, 337, 55);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		 
	}
}
