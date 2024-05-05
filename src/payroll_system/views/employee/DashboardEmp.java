package payroll_system.views.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DashboardEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardEmp frame = new DashboardEmp();
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
	public DashboardEmp() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(448, 6, 88, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(348, 6, 88, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Employee Portal | ");
		lblNewLabel.setBounds(16, 18, 114, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("--");
		lblUserName.setBounds(130, 18, 114, 16);
		contentPane.add(lblUserName);
	}
}
