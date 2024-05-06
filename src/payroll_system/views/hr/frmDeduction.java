package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import payroll_system.Alert;
import payroll_system.Base;
import payroll_system.Database;
import payroll_system.FormHelper;
import payroll_system.services.DeductionService;
import payroll_system.services.DepartmentService;
import payroll_system.services.PositionService;

import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class frmDeduction extends Base {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtName;
	private DepartmentService ds;
 
	private String frmState;
	private Database DB;
	private Alert alert;
	
	private Object toUpdateId;

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
	public frmDeduction(String title, String state, DeductionService ds) {

		DB = new Database();
		alert = new Alert();

		setTitle(title);
		this.frmState = state;

		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 202);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(16, 75, 61, 16);
		contentPane.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setToolTipText("Enter deduction name");
		txtName.setBounds(63, 65, 313, 36);
		contentPane.add(txtName);
		txtName.setColumns(10);

		 
		
		JButton btnSubmit = new JButton(state);
		btnSubmit.setText(state);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (frmState) {
					case "SAVE":

						String[] createAr = {
								txtName.getText()
						};
						
						ds.create(createAr);
						 
						frmState = null;
						break;
					case "UPDATE":
						String[] updateAr = {
								txtName.getText()
						};
						 
						ds.update(updateAr,ds.getSelectedId());
						 
						frmState = null;
						break;
					case "DELETE":
						 
						 
						ds.delete(ds.getSelectedId());
						 
						frmState = null;
						break;

					default:

				}
				setVisible(false);
				 

			}
		});
		btnSubmit.setBounds(274, 129, 102, 36);
		contentPane.add(btnSubmit);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 394, 47);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("DEDUCTION CATEGORY");
		lblTitle.setForeground(new Color(153, 204, 255));
		lblTitle.setBorder(new EmptyBorder(5, 10, 5, 10));
		panel.add(lblTitle);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(170, 129, 102, 36);
		contentPane.add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 115, 394, 0);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 116, 394, 14);
		contentPane.add(separator_1);
	}
}
