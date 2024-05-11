package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import payroll_system.Alert;
import payroll_system.Database;
import payroll_system.services.EmployeeService;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class frmPayroll extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser jdateFrom;
	private EmployeeService es;
	private Database DB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPayroll frame = new frmPayroll();
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
	public frmPayroll() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 598, 263);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		
		jdateFrom = new JDateChooser();
		jdateFrom.setBounds(25, 83, 272, 37);
		contentPane.add(jdateFrom);
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("DATE FROM");
		lblNewLabel.setBounds(25, 63, 119, 16);
		contentPane.add(lblNewLabel);
		
		JDateChooser jdateTo = new JDateChooser();
		jdateTo.setBounds(310, 83, 265, 37);
		contentPane.add(jdateTo);
		
		JLabel lblDateTo = new JLabel("DATE TO");
		lblDateTo.setBounds(309, 63, 119, 16);
		contentPane.add(lblDateTo);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alert alert = new Alert();
				String[] employee = {};
				try {
					PreparedStatement statement = DB.query("select * from employees");
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						
					}
				}catch(SQLException es) {
					
				}
				
				
				alert.setMessage("Payroll has been generate");
				alert.success();
				setVisible(false);
			}
		});
		btnGenerate.setBounds(342, 171, 233, 54);
		contentPane.add(btnGenerate);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 147, 619, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("GENERATE A PAYROLL");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(18, 6, 279, 28);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-11, 39, 619, 12);
		contentPane.add(separator_1);
	}
}
