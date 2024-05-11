package payroll_system.views.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Alert;
import payroll_system.Base;
import payroll_system.ButtonHelper;
import payroll_system.Database;
import payroll_system.FormHelper;
import payroll_system.services.EmployeeService;
import payroll_system.views.hr.DashboardHr;
import payroll_system.views.hr.frmDeduction;
import payroll_system.views.hr.frmDepartment;
import payroll_system.views.hr.frmPosition;
import payroll_system.views.hr.frmUser;
import payroll_system.views.hr.DashboardHr.ButtonCtrlsClickListener;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;

public class DashboardEmp extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Database DB;
	private frmAttendanceSet frmAttendance;

 

	
	public class ButtonCtrlsClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand(); 
			DB = new Database();
		 
			
			
			String in = "ATTENDANCE";
			String out = "TIMEOut"; 
  
			Alert alert = new Alert();
			
			if (buttonName.equals(in)) {
					FormHelper.show(frmAttendance, true);
			} else if (buttonName.equals(out)) {
					FormHelper.show(frmAttendance, true);
			}else {
				
			}
		}
	} 
	
	/**
	 * Create the frame.
	 */
	public DashboardEmp(int emp_id) {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		EmployeeService es = new EmployeeService();
		es.setEmployeeId(emp_id);
		
		frmAttendance = new frmAttendanceSet(es);
		frmAttendance.setEmployeeId(es.getEmployeeId());
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControls = new JPanel();
		panel.add(panelControls, BorderLayout.EAST);
		panelControls.setLayout(new BoxLayout(panelControls, BoxLayout.X_AXIS));
		
		 
		ButtonHelper btnHelper = new ButtonHelper();
		
//		panelControls.add(btnHelper.configButton("PAYSLIP", "search.png", new ButtonCtrlsClickListener())); 
//		panelControls.add(btnHelper.configButton("ATTENDANCE", "search.png", new ButtonCtrlsClickListener())); 
//		panelControls.add(btnHelper.configButton("SETTINGS", "search.png", new ButtonCtrlsClickListener())); 
//		panelControls.add(btnHelper.configButton("ACCOUNT", "search.png", new ButtonCtrlsClickListener())); 
//		panelControls.add(btnHelper.configButton("EXIT", "search.png", new ButtonCtrlsClickListener())); 
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Employee Portal | ");
		panel_2.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("--");
		panel_2.add(lblUserName);
		
		JPanel panel_3 = new JPanel();
		 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGap(36)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGap(6)
//							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED) ))
//					.addGap(-36))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
//					.addContainerGap())
//		);
		contentPane.setLayout(gl_contentPane);
	}
}
