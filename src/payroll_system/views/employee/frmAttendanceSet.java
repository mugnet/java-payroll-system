package payroll_system.views.employee;
 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Base; 
import payroll_system.services.EmployeeService;

import javax.swing.JButton; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.Font;  

public class frmAttendanceSet extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int employeeId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
 
	/**
	 * Create the frame.
	 */
	public frmAttendanceSet(EmployeeService es) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); 
		  
		
		JButton btnTimein = new JButton("TIME-IN");
		btnTimein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				LocalDateTime now = LocalDateTime.now(); 
		        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		        String dateTime = now.format(dateTimeFormatter);
				    
				System.out.println(es.getEmployeeId());    
		        
			 es.setAttendance(dateTime, null);
			}
		});
		btnTimein.setBounds(93, 116, 124, 54);
		contentPane.add(btnTimein);
		
		JButton btnTimeout = new JButton("TIME-OUT");
		btnTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now(); 
		        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		        String dateTime = now.format(dateTimeFormatter);
				    
				System.out.println(es.getEmployeeId());    
		        
			 es.setAttendance(null, dateTime);
			}
		});
		btnTimeout.setBounds(229, 116, 134, 54);
		contentPane.add(btnTimeout);
		
		  
		  
		    Date date = new Date();  
		
		
		JLabel lblTime = new JLabel(date.toString());
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(31, 59, 392, 45);
		contentPane.add(lblTime);
	}
}
