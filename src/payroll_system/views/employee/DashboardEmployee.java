package payroll_system.views.employee;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.ButtonHelper;
import payroll_system.FormHelper;
import payroll_system.services.EmployeeService;
import payroll_system.views.hr.DashboardHr.ButtonCtrlsClickListener;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Color;

public class DashboardEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	private frmAttendanceSet frmAttendance;

	/**
	 * Launch the application.
	 */
	
	
	 
	public class ButtonCtrlsClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand(); 
			 
			
			
			 
			 
			
			if (buttonName.equals("ATTENDANCE")) {
					FormHelper.show(frmAttendance, true);
			} else if (buttonName.equals("PAYROLL")) {
				FormHelper.show(frmAttendance, true);
		}else if (buttonName.equals("ACCOUNT")) {
			FormHelper.show(frmAttendance, true);
	}
		 
			else {
				System.exit(0);
			}
		}
	} 
	
	
	public DashboardEmployee(int emp_id) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 416);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		JPanel panelcontrols = new JPanel();
		panelcontrols.setBackground(Color.LIGHT_GRAY);
		panelcontrols.setLayout(new BoxLayout(panelcontrols, BoxLayout.X_AXIS));
		
		ButtonHelper btnHelper = new ButtonHelper(); 
		
		panelcontrols.add(btnHelper.configButton("ATTENDANCE", "clock.png", new ButtonCtrlsClickListener()));
		panelcontrols.add(btnHelper.configButton("PAYROLL", "pay.png", new ButtonCtrlsClickListener()));
		panelcontrols.add(btnHelper.configButton("ACCOUNT", "user.png", new ButtonCtrlsClickListener()));
		panelcontrols.add(btnHelper.configButton("EXIT", "exit.png", new ButtonCtrlsClickListener()));
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE PAYROLL SYSTEM");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
					.addComponent(panelcontrols, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panelcontrols, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		panel.setLayout(gl_panel);
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 825, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(270, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		EmployeeService es = new EmployeeService();
		es.setEmployeeId(emp_id);
		
		frmAttendance = new frmAttendanceSet(es);
		frmAttendance.setEmployeeId(es.getEmployeeId());
		
	}
}
