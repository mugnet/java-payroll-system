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
import payroll_system.TableHelper;

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
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
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DashboardHr extends Base  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblDatasets;
	private Database DB;
	private String formState;
	public static JPanel panelControls;
	private int frameWidth;
	private JLabel lblModuleName;
	
	public String getFormState() {
		return formState;
	}

	public void setFormState(String formState) {
		this.formState = formState;
		 
	}

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
		
		
		this.DB= new Database();
		
		this.init();

		
		 
		
	}
	
	
	public class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            String buttonName = e.getActionCommand(); 
            
            setFormState(buttonName);
            
            switch(buttonName) {
            case "Employee":
            	panelControls.setVisible(true);
            	break;
            case "Attendance":
            	panelControls.setVisible(false);
            	break;
            case "Payslip":
            	panelControls.setVisible(false);
            	break;
            case "Position":
            	panelControls.setVisible(true);
            	break;
            case "Department":
            	panelControls.setVisible(true);
            	break;
            case "Deduction":
            	panelControls.setVisible(true);
            	break;
            case "Users":
            	panelControls.setVisible(true); 
            	break;
            default:
            	panelControls.setVisible(false);
            		
            }
        }
    }
	
	private void init() {
		Assets asset = new Assets(); 
		 setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	 
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1106, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		
		
		JPanel hrPanel = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		
		JPanel panelAvatar = new JPanel();
		panelAvatar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAvatar = new JLabel("avatar");
		panelAvatar.add(lblAvatar);
		
		asset.loadImage(lblAvatar,"user-avatar.png",110,100);
		
		JPanel hrSidebarPanel = new JPanel();
		hrSidebarPanel.setLayout(null);     				
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setForeground(new Color(84, 93, 85));
		lblNewLabel.setBounds(6, 5, 165, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hrSidebarPanel.add(lblNewLabel);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ButtonClickListener());
		btnEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmployee.setBounds(0, 23, 223, 42);
		hrSidebarPanel.add(btnEmployee);
		
		JButton btnAttendance = new JButton("Attendance");
		btnAttendance.addActionListener(new ButtonClickListener());
		btnAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		btnAttendance.setBounds(0, 66, 223, 42);
		hrSidebarPanel.add(btnAttendance);
		
		JButton btnPayroll = new JButton("Payroll");
		btnPayroll.addActionListener(new ButtonClickListener());
		btnPayroll.setHorizontalAlignment(SwingConstants.LEFT);
		btnPayroll.setBounds(0, 111, 223, 42);
		hrSidebarPanel.add(btnPayroll);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setForeground(new Color(84, 93, 85));
		lblSettings.setBounds(6, 160, 165, 16);
		hrSidebarPanel.add(lblSettings);
		
		 
		
		JPanel pnlControls = new JPanel();
		
		
		
		panelControls = new JPanel();
	 
		panelControls.add(asset.configButton("SEARCH","search.png"));
		panelControls.add(asset.configButton("ADD","add.png"));
		panelControls.add(asset.configButton("EDIT","edit.png"));
		panelControls.add(asset.configButton("DELETE","delete.png"));
		
		GroupLayout gl_pnlControls = new GroupLayout(pnlControls);
		gl_pnlControls.setHorizontalGroup(
			gl_pnlControls.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlControls.createSequentialGroup()
					.addContainerGap(300, Short.MAX_VALUE)
					.addComponent(panelControls, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlControls.setVerticalGroup(
			gl_pnlControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlControls.createSequentialGroup()
					.addComponent(panelControls, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnlControls.setLayout(gl_pnlControls);
		
		
		panelControls.setVisible(false);
		
		
		

		JInternalFrame iFPosition = new JInternalFrame("Position iFrame");
		iFPosition.setBorder(null);
		iFPosition.setVisible(false);
		
		
		JInternalFrame iFDepartment = new JInternalFrame("Department iFrame");
		iFDepartment.setBorder(null);
		iFDepartment.setVisible(false);
		
		JInternalFrame iFDeduction = new JInternalFrame("Deduction iFrame");
		iFDeduction.setBorder(null);
		iFDeduction.setVisible(false);
		
		JInternalFrame iFUsers = new JInternalFrame("Users iFrame");
		iFUsers.setBorder(null);
		iFUsers.setVisible(false);
		
		
		
		JButton btnPosition = new JButton("Position");
		btnPosition.addActionListener(new ButtonClickListener());
		btnPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				iFPosition.setVisible(true);
				iFDepartment.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				
				TableHelper table = new TableHelper(); 
		        
		        String[] headers = {"#","Name","Created At","Updated At"};
		        table.setHeaders(headers);
		        table.getHeaders();
		        table.setModel(table.getModel());
		         
				
				try {
					PreparedStatement statement = DB.query("SELECT * FROM positions");
					ResultSet resultSet = statement.executeQuery();

			    	 while (resultSet.next()) { 
			    		 table.getModel().addRow(new Object[]{
			    				 resultSet.getString("id"),
			    				 resultSet.getString("name"),  
			    				 resultSet.getString("users.created_at"),
			    				 resultSet.getString("users.updated_at")
			    				 }); 
			           
			         }
			    					    	 
			   
			 		DB.dbClose(resultSet, statement);
				}catch(SQLException ex) {
					  ex.printStackTrace();
				}
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFPosition.getContentPane().add(scrollPane, BorderLayout.CENTER); 
			        
			       
			        
				
			}

			 
		});
		
		btnPosition.setHorizontalAlignment(SwingConstants.LEFT);
		btnPosition.setBounds(0, 181, 223, 42);
		hrSidebarPanel.add(btnPosition);
		
		JButton btnDepartment = new JButton("Department");
		btnDepartment.addActionListener(new ButtonClickListener());
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				lblModuleName.setText("Departments");
//				setFormState(lblModuleName.getText().toLowerCase());
				
				iFDepartment.setVisible(true);
				iFPosition.setVisible(false);
				iFDeduction.setVisible(false);
				iFUsers.setVisible(false);
				
				
				TableHelper table = new TableHelper(); 
		        
		        String[] headers = {"#","Name","Created At","Updated At"};
		        table.setHeaders(headers);
		        table.getHeaders();
		        table.setModel(table.getModel());
		         
				
				try {
					PreparedStatement statement = DB.query("SELECT * FROM departments");
					ResultSet resultSet = statement.executeQuery();

			    	 while (resultSet.next()) { 
			    		 table.getModel().addRow(new Object[]{
			    				 resultSet.getString("id"),
			    				 resultSet.getString("name"),  
			    				 resultSet.getString("users.created_at"),
			    				 resultSet.getString("users.updated_at")
			    				 }); 
			           
			         }
			    					    	 
			   
			 		DB.dbClose(resultSet, statement);
				}catch(SQLException ex) {
					  ex.printStackTrace();
				}
		 		
 
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFDepartment.getContentPane().add(scrollPane, BorderLayout.CENTER); 
			        
			       
				
			}
			
			 
		});
		
		
		
		
		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setBounds(0, 226, 223, 42);
		hrSidebarPanel.add(btnDepartment);
		
		JButton btnDeduction = new JButton("Deduction");
		btnDeduction.addActionListener(new ButtonClickListener());
		btnDeduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				lblModuleName.setText("Deductions");
//				setFormState(lblModuleName.getText().toLowerCase());
				
				iFDeduction.setVisible(true);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFUsers.setVisible(false);
				
				TableHelper table = new TableHelper(); 
		        
		        String[] headers = {"#","Name","Amount","Created At","Updated At"};
		        table.setHeaders(headers);
		        table.getHeaders();
		        table.setModel(table.getModel());
		         
				
				try {
					PreparedStatement statement = DB.query("SELECT * FROM deductions");
					ResultSet resultSet = statement.executeQuery();

			    	 while (resultSet.next()) { 
			    		 table.getModel().addRow(new Object[]{
			    				 resultSet.getString("id"),
			    				 resultSet.getString("name"), 
			    				 resultSet.getString("amount"), 
			    				 resultSet.getString("users.created_at"),
			    				 resultSet.getString("users.updated_at")
			    				 }); 
			           
			         }
			    					    	 
			   
			 		DB.dbClose(resultSet, statement);
				}catch(SQLException ex) {
					  ex.printStackTrace();
				}
		 		
		   
		       
			        JScrollPane scrollPane = new JScrollPane(table.config());
			        iFDeduction.getContentPane().add(scrollPane, BorderLayout.CENTER); 
				
			}
		});
		btnDeduction.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeduction.setBounds(0, 269, 223, 42);
		hrSidebarPanel.add(btnDeduction);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.addActionListener(new ButtonClickListener());
		btnUsers.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
			 
//				lblModuleName.setText("Users");
//				setFormState(lblModuleName.getText().toLowerCase());
//				
				
				iFDeduction.setVisible(false);
				iFDepartment.setVisible(false);
				iFPosition.setVisible(false);
				iFUsers.setVisible(true);
				 
			       			        
			        TableHelper table = new TableHelper(); 
			        
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
		btnUsers.setBounds(0, 314, 223, 42);
		hrSidebarPanel.add(btnUsers);
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.X_AXIS));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(hrPanel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(pnlTitle, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlControls, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE))
						.addComponent(iFDeduction, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addComponent(iFUsers, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addComponent(iFPosition, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addComponent(iFDepartment, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(hrPanel, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(13)
									.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(pnlTitle, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlControls, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(iFDeduction, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
								.addComponent(iFUsers, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
								.addComponent(iFPosition, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
								.addComponent(iFDepartment, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
							.addGap(1))))
		);
		GroupLayout gl_hrPanel = new GroupLayout(hrPanel);
		gl_hrPanel.setHorizontalGroup(
			gl_hrPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hrPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_hrPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hrPanel.createSequentialGroup()
							.addGap(88)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_hrPanel.createSequentialGroup()
							.addGroup(gl_hrPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelAvatar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
								.addComponent(hrSidebarPanel, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_hrPanel.setVerticalGroup(
			gl_hrPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hrPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_hrPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_hrPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(111)
							.addComponent(hrSidebarPanel, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		hrPanel.setLayout(gl_hrPanel);
		contentPane.setLayout(gl_contentPane);
		
		
		
		 
		
	}
}
