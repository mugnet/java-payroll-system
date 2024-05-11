package payroll_system.views.employee;
 
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Base;
import payroll_system.Database; 
import payroll_system.services.EmployeeService; 

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox; 
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class frmEmployee extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	public JTextField txtFirstName;
	public JTextField txtMiddleName;
	public JTextField txtLastName; 
	public EmployeeService es;
	private Database DB;
	private String frmState;
 
	
	public JComboBox<String> cboUser;
	public JComboBox<String> cboPosition;
	public JComboBox<String> cboDepartment;
	public JComboBox<String> cboSex;
	
	public Object toUpdateId;
	public JTextField txtRate;

	/**
	 * Launch the application.
	 */

	public Object getToUpdateId() {
		return toUpdateId;
	}

	public void setToUpdateId(Object toUpdateId) {
		this.toUpdateId = toUpdateId;
	}

	 

	/**
	 * Create the frame.
	 */
	public frmEmployee(String title,String state, EmployeeService es) {
		setAutoRequestFocus(false);
		setTitle("Employee Form");
		setBounds(100, 100, 476, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.frmState = state;
		
		
		
		this.loadLists(es);
		
 
		
		
		 
		String select ="- select -";
		
		JLabel lblNewLabel = new JLabel("FIRST NAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(74, 111, 120, 16);
		contentPane.add(lblNewLabel);
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(0, 0, 842, 53);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEmployeeForm = new JLabel("EMPLOYEE FORM");
		lblEmployeeForm.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblEmployeeForm.setForeground(new Color(135, 206, 235));
		lblEmployeeForm.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployeeForm.setBorder(new EmptyBorder(5,5,5,50));
		panel.add(lblEmployeeForm);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(216, 102, 236, 35);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(216, 139, 236, 35);
		contentPane.add(txtMiddleName);
		
		JLabel lblMiddleName = new JLabel("MIDDLE NAME");
		lblMiddleName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMiddleName.setBounds(74, 148, 120, 16);
		contentPane.add(lblMiddleName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(216, 176, 236, 35);
		contentPane.add(txtLastName);
		
		JLabel lblLastName = new JLabel("LAST NAME");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(74, 185, 120, 16);
		contentPane.add(lblLastName);
		
		String[] items = {select , "Male","Female"};
		
		 cboSex = new JComboBox<String>(items);
		cboSex.setBounds(216, 214, 236, 35);
		contentPane.add(cboSex);
		
		JLabel lblSex = new JLabel("SEX");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setBounds(74, 222, 120, 16);
		contentPane.add(lblSex);
		
		try {
			DB = new Database();
		
		JLabel lblDepartment = new JLabel("DEPARTMENT");
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setBounds(74, 258, 120, 16);
		contentPane.add(lblDepartment);
		
		 cboDepartment = new JComboBox<String>();
		cboDepartment.setBounds(216, 250, 236, 35);
		contentPane.add(cboDepartment);
		
		PreparedStatement deptStatement = DB.query("select * from departments");
		ResultSet deptResultSet = deptStatement.executeQuery();
		 
		 Map<Integer, String> ddataMap = new HashMap<>();
		 

        while (deptResultSet.next()) {
        	int id = deptResultSet.getInt("id");
            String value = deptResultSet.getString("name"); 
            ddataMap.put(id, value);
            
        }
        
        cboDepartment.addItem(select);
        for (Map.Entry<Integer, String> entry : ddataMap.entrySet()) {
        	cboDepartment.addItem(entry.getValue());
        }
			
		JLabel lblPosition = new JLabel("POSITION");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setBounds(74, 294, 120, 16);
		contentPane.add(lblPosition);
		 
					
		 cboPosition = new JComboBox<String>();
		cboPosition.setPreferredSize(new Dimension(342, 39));  
		cboPosition.setBounds(118, 164, 342, 39);
		
			PreparedStatement statement = DB.query("select * from positions");
			ResultSet resultSet = statement.executeQuery();
			 
			 Map<Integer, String> dataMap = new HashMap<>();
			 
 
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
                String value = resultSet.getString("name"); 
                dataMap.put(id, value);
                
            }
            
            cboPosition.addItem(select);
            for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
            	cboPosition.addItem(entry.getValue());
            }
                        
            DB.dbClose(deptResultSet, deptStatement);
            DB.dbClose(resultSet, statement);
 			   
		
		
		cboPosition.setBounds(216, 286, 236, 35);
		contentPane.add(cboPosition);
		
		 
			
		JSeparator separator = new JSeparator();
		separator.setBounds(124, 367, 432, 16);
		contentPane.add(separator);
		
		JLabel lblUserAccount = new JLabel("Account Details");
		lblUserAccount.setForeground(new Color(105, 105, 105));
		lblUserAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserAccount.setBounds(-1, 360, 120, 23);
		contentPane.add(lblUserAccount);
		
		 cboUser = new JComboBox<String>();
		cboUser.setPreferredSize(new Dimension(342, 39));  
		cboUser.setBounds(215, 383, 236, 35);
		contentPane.add(cboUser);
		
		JLabel lblUsers = new JLabel("Select a User");
		lblUsers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsers.setBounds(73, 391, 120, 16);
		
		contentPane.add(lblUsers);
		
		PreparedStatement userStatement = DB.query("select * from users where user_type_id = ? and id NOT IN (SELECT user_id FROM employees)");
		
		int employee_type = 2;
		userStatement.setInt(1, employee_type);
		
		ResultSet userResultSet = userStatement.executeQuery();
		 
		 Map<Integer, String> udataMap = new HashMap<>();
		 

        while (userResultSet.next()) {
        	int user_id = userResultSet.getInt("id");
            String user_value = userResultSet.getString("name"); 
            udataMap.put(user_id, user_value);
            
        }
        
        cboUser.addItem(select);
        for (Map.Entry<Integer, String> entry : udataMap.entrySet()) {
        	cboUser.addItem(entry.getValue());
        }
        
        
		
		
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	
		
		
		
		 
		
		JLabel lblEmployeeDetails = new JLabel("Employee Details");
		lblEmployeeDetails.setForeground(new Color(128, 128, 128));
		lblEmployeeDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmployeeDetails.setBounds(10, 76, 120, 23);
		contentPane.add(lblEmployeeDetails);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(135, 83, 432, 16);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 438, 585, 16);
		contentPane.add(separator_2);
		
		JButton btnSubmit = new JButton(state);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (frmState) {
					case "SAVE":
					 
						
						es.create(cboPosition.getSelectedIndex(),cboDepartment.getSelectedIndex(),cboUser.getSelectedIndex(),
								txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText(),cboSex.getSelectedItem(),Float.valueOf(txtRate.getText()));
						 
						frmState = null;
						break;
					case "UPDATE":
					 
						 
						es.update(getToUpdateId(),cboPosition.getSelectedIndex(),cboDepartment.getSelectedIndex(),cboUser.getSelectedIndex(),
								txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText(),cboSex.getSelectedItem(),Float.valueOf(txtRate.getText()));
						 
						frmState = null;
						break;
					case "DELETE":
						 
						 
						es.delete(es.getSelectedId());
						 
						frmState = null;
						break;

					default:

				}
				setVisible(false);
				 

			}
		});
		btnSubmit.setBounds(354, 465, 98, 35);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(258, 465, 86, 35);
		contentPane.add(btnCancel);
		
		txtRate = new JTextField();
		txtRate.setColumns(10);
		txtRate.setBounds(216, 322, 236, 35);
		contentPane.add(txtRate);
		
		JLabel lblHourlyRate = new JLabel("HOURLY RATE");
		lblHourlyRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHourlyRate.setBounds(74, 331, 120, 16);
		contentPane.add(lblHourlyRate);
	}
	
	private void loadLists(EmployeeService empService) {
		
		 
		
	}
}

