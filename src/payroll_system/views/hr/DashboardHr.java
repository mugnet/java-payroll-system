package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import payroll_system.Alert;
import payroll_system.Assets;
import payroll_system.Base;
import payroll_system.ButtonHelper;
import payroll_system.Database;
import payroll_system.FormHelper;
import payroll_system.TableHelper;
import payroll_system.services.AttendanceService;
import payroll_system.services.DeductionService;
import payroll_system.services.DepartmentService;
import payroll_system.services.EmployeeService;
import payroll_system.services.PositionService;
import payroll_system.services.UserService;
import payroll_system.views.employee.frmEmployee;

import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;

import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.SystemColor;

import java.awt.event.*;

public class DashboardHr extends Base {

	private static final long serialVersionUID = 1L;
	
	
	
	private JPanel contentPane;
	private JTable tblDatasets;
	private Database DB;
	private String formState;
	public static JPanel panelControls;
	private int frameWidth;
	private JLabel lblModuleName;
	public String btnCtrlName;
	
	public JInternalFrame iFPosition;
	public JInternalFrame iFDepartment;
	public JInternalFrame iFDeduction;
	public JInternalFrame iFEmployees;
	public JInternalFrame iFAttendance;
	public JInternalFrame iFPayroll;
	
	public JButton btnPosition;
	public JButton btnDeduction;
	public JButton btnDepartment;
	public JButton btnUsers; 
		
	public JScrollPane scrollPane;
	private TableHelper table;
	
	private int selectedId;
	
	public PositionService ps;
	public DepartmentService ds;
	public DeductionService dds;
	public UserService us;
	public EmployeeService es;
	public AttendanceService as;
	
	public Alert alert;
	
	private JLabel lblTitle;
	private JInternalFrame iFUsers;
	private JLabel lblNewLabel_1;

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public String getFormState() {
		return formState;
	}

	public void setFormState(String formState) {
		this.formState = formState;

	}

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DashboardHr frame = new DashboardHr();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DashboardHr() {

		this.DB = new Database();

		this.init();
		table = new TableHelper();
		
		
		ps = new PositionService();
		ds = new DepartmentService();
		dds = new DeductionService();
		us = new UserService();
		es = new EmployeeService();
		as = new AttendanceService();
		alert = new Alert();
		 
		 
	}

	public class ButtonCtrlsClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand();
			btnCtrlName = buttonName;

			String add = "ADD";
			String edit = "EDIT";
			String delete = "DELETE";
			String search = "REFRESH";

			System.out.println(formState);
			
			lblTitle.setText(formState.toUpperCase());
			

			if (buttonName.equals(add) && formState.equals("users")) {

				FormHelper.show(new frmUser("Add User Account", "SAVE",us), true);
				
			} else if (buttonName.equals(add) && formState.equals("employee")) {

				FormHelper.show(new frmEmployee("Add New Employee", "SAVE",es), true);
				
			}  else if (buttonName.equals(add) && formState.equals("position")) {

				FormHelper.show(new frmPosition("Add New Position", "SAVE",ps), true);

			} else if (buttonName.equals(add) && formState.equals("department")) {

				FormHelper.show(new frmDepartment("Add New Department", "SAVE",ds), true);
				
			} else if (buttonName.equals(add) && formState.equals("deduction")) {

				FormHelper.show(new frmDeduction("Add New Deduction", "SAVE",dds), true);
				
			} else if (buttonName.equals(edit) && formState.equals("users")) { 
				
				frmUser userForm = new frmUser("Edit Position","UPDATE",us);
				userForm.setToUpdateId(us.getSelectedId());
				us.show(ps.getSelectedId());
				userForm.txtName.setText(us.getName());
				FormHelper.show(userForm, true);
				
			} else if (buttonName.equals(edit) && formState.equals("position")) {
				
				frmPosition positionForm = new frmPosition("Edit Position","UPDATE",ps);
				positionForm.setToUpdateId(ps.getSelectedId());
				ps.show(ps.getSelectedId());
				positionForm.txtName.setText(ps.getName());
				FormHelper.show(positionForm, true);
			
			

			} else if (buttonName.equals(edit) && formState.equals("employee")) {
				
				frmEmployee employeeForm = new frmEmployee("Edit Employee","UPDATE",es);
				employeeForm.setToUpdateId(es.getSelectedId());
				es.show(es.getSelectedId());
				
				try {
					DB = new Database();
					PreparedStatement statement = DB.first(es.getSql() + " where employees.id = ?",es.getSelectedId());
					
					
					ResultSet resultSet = statement.executeQuery();
		 			
					 if (resultSet.next()) { 
						 employeeForm.txtFirstName.setText(resultSet.getString("first_name"));  
							employeeForm.txtMiddleName.setText(resultSet.getString("middle_name"));
							employeeForm.txtLastName.setText(resultSet.getString("last_name"));
							employeeForm.cboSex.setSelectedItem(resultSet.getString("sex"));
							employeeForm.txtRate.setText(resultSet.getString("rate"));
							employeeForm.cboPosition.setSelectedIndex(resultSet.getInt("position_id")); 
							employeeForm.cboDepartment.setSelectedIndex(resultSet.getInt("department_id"));
							employeeForm.cboUser.setSelectedIndex(resultSet.getInt("user_id")); 
							FormHelper.show(employeeForm,true);
			         } else {
			            alert.setMessage("No data is found!");
			            alert.danger();
			         }

					DB.dbClose(resultSet, statement);
					
			 
					
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				
				
				
				 
			
			

			} else if (buttonName.equals(edit) && formState.equals("department")) {
				
				frmDepartment departmentForm = new frmDepartment("Edit Department","UPDATE",ds);
				departmentForm.setToUpdateId(ds.getSelectedId());
				ds.show(ds.getSelectedId());
				departmentForm.txtName.setText(ds.getName());
				FormHelper.show(departmentForm, true);

			} else if (buttonName.equals(edit) && formState.equals("deduction")) {
				
				frmDeduction deductionForm = new frmDeduction("Edit Deduction","UPDATE",dds);
				deductionForm.setToUpdateId(dds.getSelectedId());
				dds.show(dds.getSelectedId());
				deductionForm.txtName.setText(dds.getName());
				deductionForm.txtAmount.setText(String.valueOf(dds.getAmount()));
				
				FormHelper.show(deductionForm, true);

			} else if (buttonName.equals(delete) && formState.equals("users")) {
				us.delete(us.getSelectedId());  

			} else if (buttonName.equals(delete) && formState.equals("employee")) {
				es.delete(es.getSelectedId());  

			} else if (buttonName.equals(delete) && formState.equals("position")) {
				
				ps.delete(ps.getSelectedId());  

			} else if (buttonName.equals(delete) && formState.equals("department")) {
				
				ds.delete(ds.getSelectedId());  

			} else if (buttonName.equals(delete) && formState.equals("deduction")) {
				
				dds.delete(dds.getSelectedId());  
				
			} else if (buttonName.equals(search))  {
				FormHelper.show(DashboardHr.this, false);
				FormHelper.show(new DashboardHr(), true);
			} else if (buttonName == "GENERATE PAYROLL"){
				FormHelper.show(new frmPayroll(), true);
			}else {
			
				
			}

		}
	}

	public class ButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand();

			setFormState(buttonName.toLowerCase());

			System.out.println(getFormState());
			
			lblTitle.setText(getFormState().toUpperCase());
			
			switch (buttonName.toLowerCase()) {
				case "employee":
					panelControls.setVisible(true);
				
					break;
				case "attendance":
					panelControls.setVisible(false);
					break;
				case "payroll":
					panelControls.setVisible(true); 
					break;
				case "position":
					panelControls.setVisible(true);
					 
					break;
				case "department":
					panelControls.setVisible(true);
					break;
				case "deduction":
					panelControls.setVisible(true);
					break;
				case "users":
					panelControls.setVisible(true);
					break;
				default:
					panelControls.setVisible(false);

			}

		}
	}

	private void init() {
		Assets asset = new Assets();
		ButtonHelper btnHelper = new ButtonHelper();
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));

		setContentPane(contentPane);

		JDesktopPane desktopPane = new JDesktopPane();

		JPanel hrPanel = new JPanel();
		hrPanel.setBackground(SystemColor.inactiveCaption);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JPanel panelAvatar = new JPanel();
		panelAvatar.setBackground(SystemColor.inactiveCaptionBorder);
		panelAvatar.setLayout(new BorderLayout(0, 0));

		JPanel hrSidebarPanel = new JPanel();
		hrSidebarPanel.setBackground(SystemColor.inactiveCaptionBorder);
		hrSidebarPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBounds(6, 5, 165, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hrSidebarPanel.add(lblNewLabel);

		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ButtonClickListener());
		btnEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmployee.setBounds(0, 23, 223, 42);
		
		
		
		

		
		
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = "Employees";
				setFormState(title.toLowerCase());

				iFEmployees.setVisible(true);
				iFPosition.setVisible(false);
				iFDepartment.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				iFAttendance.setVisible(false);
				iFPayroll.setVisible(false);
				 scrollPane = new JScrollPane(es.get());
				 
			     
				 iFEmployees.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}

		});

		
		
		hrSidebarPanel.add(btnEmployee);

		JButton btnAttendance = new JButton("Attendance");
		btnAttendance.addActionListener(new ButtonClickListener());
		btnAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		btnAttendance.setBounds(0, 66, 223, 42);
		hrSidebarPanel.add(btnAttendance);
		
		btnAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iFAttendance.setVisible(true);
				iFEmployees.setVisible(false);
				iFPosition.setVisible(false);
				iFDepartment.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				iFPayroll.setVisible(false);
				 scrollPane = new JScrollPane(as.get());
					iFAttendance.getContentPane().add(scrollPane, BorderLayout.CENTER);
				
			}
		});
		

		JButton btnPayroll = new JButton("Payroll");
		btnPayroll.addActionListener(new ButtonClickListener());
		btnPayroll.setHorizontalAlignment(SwingConstants.LEFT);
		btnPayroll.setBounds(0, 111, 223, 42);
		hrSidebarPanel.add(btnPayroll);
		
		
		btnPayroll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String title = "Users";
				setFormState(title.toLowerCase());

				iFPayroll.setVisible(true);
				iFUsers.setVisible(false);
				iFDeduction.setVisible(false);
				iFEmployees.setVisible(false);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFAttendance.setVisible(false);
 
				 scrollPane = new JScrollPane(us.get());
				 iFPayroll.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}
		});
		

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setForeground(SystemColor.window);
		lblSettings.setBounds(6, 160, 165, 16);
		hrSidebarPanel.add(lblSettings);

		JPanel pnlControls = new JPanel();

		panelControls = new JPanel();

		panelControls.add(btnHelper.configButton("TEST", "search.png", new ButtonCtrlsClickListener()));
		panelControls.add(btnHelper.configButton("REFRESH", "search.png", new ButtonCtrlsClickListener()));
		panelControls.add(btnHelper.configButton("ADD", "add.png", new ButtonCtrlsClickListener()));
		panelControls.add(btnHelper.configButton("EDIT", "edit.png", new ButtonCtrlsClickListener()));
		panelControls.add(btnHelper.configButton("DELETE", "delete.png", new ButtonCtrlsClickListener()));
		panelControls.add(btnHelper.configButton("GENERATE PAYROLL", "pay.png", new ButtonCtrlsClickListener()));
		
		lblTitle = new JLabel("Dashboard");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));

		GroupLayout gl_pnlControls = new GroupLayout(pnlControls);
		gl_pnlControls.setHorizontalGroup(
			gl_pnlControls.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlControls.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
					.addComponent(panelControls, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlControls.setVerticalGroup(
			gl_pnlControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlControls.createSequentialGroup()
					.addGroup(gl_pnlControls.createParallelGroup(Alignment.LEADING)
						.addComponent(panelControls, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlControls.createSequentialGroup()
							.addGap(27)
							.addComponent(lblTitle)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlControls.setLayout(gl_pnlControls);

		panelControls.setVisible(false);

		iFEmployees = new JInternalFrame("Employees"); 
		iFEmployees.setBorder(null);
		iFEmployees.setVisible(false);
		
		 
						
		iFAttendance = new JInternalFrame("Attendances"); 
		iFAttendance.setBorder(null);
		iFAttendance.setVisible(false);
		
		iFPayroll = new JInternalFrame("Payroll"); 
		iFPayroll.setBorder(null);
		iFPayroll.setVisible(false);
		
		
		
		
		
		iFPosition = new JInternalFrame("Positions"); 
		iFPosition.setBorder(null);
		iFPosition.setVisible(false);
		
		
		iFDepartment = new JInternalFrame("Departments"); 
		iFDepartment.setBorder(null);
		iFDepartment.setVisible(false);
		
		
		iFDeduction = new JInternalFrame("Deductions");
		iFDeduction.setBorder(null);
		iFDeduction.setVisible(false);
		
		iFUsers = new JInternalFrame("Users");
		iFUsers.setBorder(null);
		iFUsers.setVisible(false); 
 

		btnPosition = new JButton("Position");
		btnPosition.addActionListener(new ButtonClickListener());
		btnPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = "Position";
				setFormState(title.toLowerCase());

				iFPosition.setVisible(true);
				iFEmployees.setVisible(false);
				iFDepartment.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				iFAttendance.setVisible(false);
				iFPayroll.setVisible(false);

				 scrollPane = new JScrollPane(ps.get());
				iFPosition.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}

		});

		btnPosition.setHorizontalAlignment(SwingConstants.LEFT);
		btnPosition.setBounds(0, 181, 223, 42);
		hrSidebarPanel.add(btnPosition);

		btnDepartment = new JButton("Department");
		btnDepartment.addActionListener(new ButtonClickListener());
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = "Department";
				setFormState(title.toLowerCase());

				iFDepartment.setVisible(true);
				iFEmployees.setVisible(false);
				iFPosition.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				iFAttendance.setVisible(false);
				iFPayroll.setVisible(false);
				
				 scrollPane = new JScrollPane(ds.get());
				iFDepartment.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}

		});

		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setBounds(0, 226, 223, 42);
		hrSidebarPanel.add(btnDepartment);

		btnDeduction = new JButton("Deduction");
		btnDeduction.addActionListener(new ButtonClickListener());
		btnDeduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = "Deduction";
				setFormState(title.toLowerCase());

				iFDeduction.setVisible(true);
				iFEmployees.setVisible(false);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFUsers.setVisible(false);
				iFAttendance.setVisible(false);
				iFPayroll.setVisible(false);
				 
				 scrollPane = new JScrollPane(dds.get());
				iFDeduction.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}
		});
		btnDeduction.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeduction.setBounds(0, 269, 223, 42);
		hrSidebarPanel.add(btnDeduction);

		btnUsers = new JButton("Users");
		btnUsers.addActionListener(new ButtonClickListener());
		btnUsers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String title = "Users";
				setFormState(title.toLowerCase());

				iFUsers.setVisible(true);
				iFDeduction.setVisible(false);
				iFEmployees.setVisible(false);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFAttendance.setVisible(false);
				iFPayroll.setVisible(false);
 
				 scrollPane = new JScrollPane(us.get());
				iFUsers.getContentPane().add(scrollPane, BorderLayout.CENTER);

			}
		});
		btnUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsers.setBounds(0, 314, 223, 42);
		hrSidebarPanel.add(btnUsers);
		GroupLayout gl_hrPanel = new GroupLayout(hrPanel);
		gl_hrPanel.setHorizontalGroup(
			gl_hrPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hrPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_hrPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hrPanel.createSequentialGroup()
							.addComponent(panelAvatar, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_hrPanel.createSequentialGroup()
							.addComponent(hrSidebarPanel, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_hrPanel.createSequentialGroup()
							.addGap(88)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		gl_hrPanel.setVerticalGroup(
			gl_hrPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hrPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_hrPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_hrPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(hrSidebarPanel, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 0, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		lblNewLabel_1 = new JLabel("PAYROLL SYSTEM");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(SystemColor.activeCaption);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelAvatar.add(lblNewLabel_1, BorderLayout.CENTER);
		hrPanel.setLayout(gl_hrPanel);
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(hrPanel, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(iFUsers, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(683)
									.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(pnlControls, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFAttendance, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFPosition, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFDeduction, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFPayroll, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFDepartment, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addComponent(iFEmployees, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
							.addGap(2))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlControls, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(iFAttendance, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(iFPosition, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(iFDeduction, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(iFPayroll, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(iFDepartment, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(iFUsers, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(iFEmployees, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)))
				.addComponent(hrPanel, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);

	}
	
	public void triggerPosition() {
		 

	
	}
	
	 
 
}

	

