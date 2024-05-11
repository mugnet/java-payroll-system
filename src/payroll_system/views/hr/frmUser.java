package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Alert;
import payroll_system.Base;
import payroll_system.Database;
import payroll_system.services.PositionService;
import payroll_system.services.UserService;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class frmUser extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtName;
	public JTextField txtEmail;
	
	
	
	private Database DB; 
	
	private Object toUpdateId;
	private JPasswordField txtPassword;
	
	private UserService us;
	private String frmState;
	
	public String getFrmState() {
		return frmState;
	}

	public void setFrmState(String frmState) {
		this.frmState = frmState;
	}

	

	/**
	 * Launch the application.
	 */

	public Object getToUpdateId() {
		return toUpdateId;
	}

	public void setToUpdateId(Object toUpdateId) {
		this.toUpdateId = toUpdateId;
	}
	  
	public frmUser(String title, String state, UserService us) {
		
		this.setFrmState(state);
		
		setResizable(false);
		setBounds(100, 100, 478, 387);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 102, 204));
		panel.setBounds(0, 0, 500, 52);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("USER ACCOUNTS");
		
		lblNewLabel.setForeground(new Color(51, 204, 255));
		lblNewLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setBounds(50, 87, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(118, 76, 342, 39);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(118, 123, 342, 39);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("EMAIL");
		lblNewLabel_1_1.setBounds(50, 134, 61, 16);
		contentPane.add(lblNewLabel_1_1);
		
		 String[] items = {"- select - ", "HR", "Employee"};
		JComboBox<String> txtRole = new JComboBox<>(items);
		txtRole.setPreferredSize(new Dimension(342, 39)); // width, height
		txtRole.setBounds(118, 164, 342, 39);
		
	        
		contentPane.add(txtRole);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ROLE");
		lblNewLabel_1_1_1.setBounds(50, 174, 61, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 261, 500, 23);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton(state);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int role = txtRole.getSelectedIndex();
				
				switch (frmState) {
				case "SAVE":

					
					String[] createAr = {
							txtName.getText(),
							txtEmail.getText(),
							String.valueOf(role),
							txtPassword.getText()							
							
					};
					
					us.create(createAr);
					 
					frmState = null;
					break;
				case "UPDATE":
					String[] updateAr = {
							txtName.getText(),
							txtEmail.getText(),
							String.valueOf(role),
							txtPassword.getText()
					};
					 
					us.update(updateAr,us.getSelectedId());
					 
					frmState = null;
					break;
				case "DELETE":
					 
					 
					us.delete(us.getSelectedId());
					 
					frmState = null;
					break;

				default:

			}
			setVisible(false);
			}
		});
		btnNewButton.setBounds(343, 296, 117, 39);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(214, 296, 117, 39);
		contentPane.add(btnCancel);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(118, 206, 342, 34);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1_1_1.setBounds(50, 215, 61, 16);
		contentPane.add(lblNewLabel_1_1_1_1);
	}
}
