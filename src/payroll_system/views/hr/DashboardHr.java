package payroll_system.views.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import payroll_system.Alert;
import payroll_system.Assets;
import payroll_system.Base;
import payroll_system.Database;
import payroll_system.Table;

import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;

public class DashboardHr extends Base  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblDatasets;
	private Database DB;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardHr frame = new DashboardHr();
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
	public DashboardHr() {
		
		
		this.init();
		this.DB= new Database();
		
		
		
		
		
	}
	
	private void init() {
Assets asset = new Assets();

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(427, 19, 1, 1);
		contentPane.add(desktopPane);
		
		
		
		
		
		JPanel hrPanel = new JPanel();
		hrPanel.setBounds(6, 6, 221, 556);
		contentPane.add(hrPanel);
		hrPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(94, 5, 1, 1);
		hrPanel.add(panel);
		panel.setLayout(null);
		
		
		
		JPanel panelAvatar = new JPanel();
		panelAvatar.setBounds(6, 5, 209, 111);
		hrPanel.add(panelAvatar);
		
		JLabel lblAvatar = new JLabel("avatar");
		panelAvatar.add(lblAvatar);
		
		asset.loadImage(lblAvatar,"user-avatar.png",110,100);
		
		JPanel hrSidebarPanel = new JPanel();
		hrSidebarPanel.setBounds(6, 117, 209, 433);
		hrPanel.add(hrSidebarPanel);
		hrSidebarPanel.setLayout(null);
		
		  
	        
 
				
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setForeground(new Color(84, 93, 85));
		lblNewLabel.setBounds(6, 5, 165, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hrSidebarPanel.add(lblNewLabel);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmployee.setBounds(0, 23, 209, 42);
		hrSidebarPanel.add(btnEmployee);
		
		JButton btnAttendance = new JButton("Attendance");
		btnAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		btnAttendance.setBounds(0, 66, 209, 42);
		hrSidebarPanel.add(btnAttendance);
		
		JButton btnPayroll = new JButton("Payroll");
		btnPayroll.setHorizontalAlignment(SwingConstants.LEFT);
		btnPayroll.setBounds(0, 111, 209, 42);
		hrSidebarPanel.add(btnPayroll);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setForeground(new Color(84, 93, 85));
		lblSettings.setBounds(6, 160, 165, 16);
		hrSidebarPanel.add(lblSettings);
		
		
	
		
		JInternalFrame iFPosition = new JInternalFrame("Position iFrame");
		iFPosition.setBorder(null);
		iFPosition.setBounds(227, 100, 704, 462);
		contentPane.add(iFPosition);
		iFPosition.setVisible(false);
		
		
		JInternalFrame iFDepartment = new JInternalFrame("Department iFrame");
		iFDepartment.setBorder(null);
		iFDepartment.setBounds(227, 100, 704, 462);
		contentPane.add(iFDepartment);
		iFDepartment.setVisible(false);
		
		JInternalFrame iFDeduction = new JInternalFrame("Deduction iFrame");
		iFDeduction.setBorder(null);
		iFDeduction.setBounds(227, 100, 704, 462);
		contentPane.add(iFDeduction);
		iFDeduction.setVisible(false);
		
		JInternalFrame iFUsers = new JInternalFrame("Users iFrame");
		iFUsers.setBorder(null);
		iFUsers.setBounds(227, 100, 704, 462);
		contentPane.add(iFUsers);
		iFUsers.setVisible(false);
		
		
		
		JButton btnPosition = new JButton("Position");
		btnPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				iFPosition.setVisible(true);
				iFDepartment.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				
				 DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("#");
			        model.addColumn("Name");
			        model.addColumn("Created At");
			        
			        Table table = new Table();
			        table.setModel(model);
			        
			        
			        model.addRow(new Object[]{"John", 25, "Engineer"});
			        model.addRow(new Object[]{"Alice", 30, "Manager"});
			        model.addRow(new Object[]{"Bob", 35, "Developer"});
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFPosition.getContentPane().add(scrollPane, BorderLayout.CENTER); 
			        
			       
			        
				
			}
		});
		
		btnPosition.setHorizontalAlignment(SwingConstants.LEFT);
		btnPosition.setBounds(0, 181, 209, 42);
		hrSidebarPanel.add(btnPosition);
		
		JButton btnDepartment = new JButton("Department");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				iFDepartment.setVisible(true);
				iFPosition.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				
				
				 DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("#");
			        model.addColumn("Name");
			        model.addColumn("Created At");
			        
			        Table table = new Table();
			        table.setModel(model);
			        			        
			        model.addRow(new Object[]{"John", 25, "Engineer"});
			        model.addRow(new Object[]{"Alice", 30, "Manager"});
			        model.addRow(new Object[]{"Bob", 35, "Developer"});
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFDepartment.getContentPane().add(scrollPane, BorderLayout.CENTER); 
			        
			       
				
			}
			
			 
		});
		
		
		
		
		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setBounds(0, 226, 209, 42);
		hrSidebarPanel.add(btnDepartment);
		
		JButton btnDeduction = new JButton("Deduction");
		btnDeduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				iFDeduction.setVisible(true);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFUsers.setVisible(false);
				
				
				 DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("#");
			        model.addColumn("Name");
			        model.addColumn("Created At");
			        
			        Table table = new Table();
			        table.setModel(model);
			        
			        
			        model.addRow(new Object[]{"John", 25, "Engineer"});
			        model.addRow(new Object[]{"Alice", 30, "Manager"});
			        model.addRow(new Object[]{"Bob", 35, "Developer"});
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFDeduction.getContentPane().add(scrollPane, BorderLayout.CENTER); 
				
			}
		});
		btnDeduction.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeduction.setBounds(0, 269, 209, 42);
		hrSidebarPanel.add(btnDeduction);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				iFDeduction.setVisible(false);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFUsers.setVisible(true);
				 
			       			        
			        Table table = new Table(); 
			        
			        String[] headers = {"#","Name","Username","User Type","Registered At","Updated At"};
			        table.setHeaders(headers);
			        table.getHeaders();
			        table.setModel(table.getModel());
			         
					
					try {
						PreparedStatement statement = DB.query("SELECT users.*,user_types.name FROM users INNER JOIN user_types ON users.user_type_id = user_types.id");
						ResultSet resultSet = statement.executeQuery();

				     
				    	 while (resultSet.next()) { 
				    		 table.getModel().addRow(new Object[]{
				    				 resultSet.getString("users.id"),
				    				 resultSet.getString("users.name"), 
				    				 resultSet.getString("users.email"),
				    				 resultSet.getString("user_types.name"),
				    				 resultSet.getString("users.created_at"),
				    				 resultSet.getString("users.updated_at")
				    				 }); 
				           
				         }
				    	 
				    	 
				   
				 		DB.dbClose(resultSet, statement);
					}catch(SQLException ex) {
						  ex.printStackTrace();
					}
			 		
			   
			       
			       
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFUsers.getContentPane().add(scrollPane, BorderLayout.CENTER); 
				
			}
		});
		btnUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsers.setBounds(0, 314, 209, 42);
		hrSidebarPanel.add(btnUsers);
		
		
	}
}
