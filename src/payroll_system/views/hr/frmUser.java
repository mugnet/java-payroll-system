package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Base;
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

public class frmUser extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;

	 
 
	public frmUser(String title, String state, UserService ps) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 330);
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
		
		 String[] items = {"- select - ", "Empoyee", "HR"};
		JComboBox<String> txtRole = new JComboBox<>(items);
		txtRole.setPreferredSize(new Dimension(342, 39)); // width, height
		txtRole.setBounds(118, 164, 342, 39);
		
	        
		contentPane.add(txtRole);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ROLE");
		lblNewLabel_1_1_1.setBounds(50, 174, 61, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 226, 500, 23);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(343, 248, 117, 39);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(214, 248, 117, 39);
		contentPane.add(btnCancel);
	}
}
