package payroll_system;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class TableHelper {
	
	private DefaultTableModel model;
	private String[] Headers;
	private Object selectedId;
	
 



	public Object getSelectedId() {
		return selectedId;
	}



	public void setSelectedId(Object selectedId) {
		this.selectedId = selectedId;
	}



	public String[] getHeaders() {
		
		model = new DefaultTableModel();
		for(int i = 0; i <= Headers.length - 1; i++) {
			 model.addColumn(Headers[i]);
		}
		
		return Headers;
	}

 
	
	public void setHeaders(String[] headers) {
		Headers = headers;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	
	
	
	public JTable config() {
		JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false);
        table.setBorder(BorderFactory.createEtchedBorder()); 
        table.setDefaultRenderer(Object.class, new RowBorderRenderer());
        table.setDefaultRenderer(Object.class, new PaddedCellRenderer());
      
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new PaddedHeaderRenderer(header.getDefaultRenderer()));
        
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object id = model.getValueAt(selectedRow, 0);
//                        Object name = model.getValueAt(selectedRow, 1); 
                        setSelectedId(id);
                        
                        
                         
                    }
                }
            }
        });
        
        return table;
	}
	
	static class RowBorderRenderer extends DefaultTableCellRenderer {
		
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
            if (row == 0 || row == table.getRowCount() - 1) {
                Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);  
                ((JComponent) component).setBorder(border); 
            } else {
                ((JComponent) component).setBorder(null);  
            }

            return component;
        }
    }
 
 static class PaddedCellRenderer extends DefaultTableCellRenderer {
        private static final int CELL_PADDING = 5;  

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            
            if (component instanceof JComponent) {
                ((JComponent) component).setBorder(BorderFactory.createEmptyBorder(CELL_PADDING, CELL_PADDING, CELL_PADDING, CELL_PADDING));
            }

            return component;
        }
    }
 
 static class PaddedHeaderRenderer implements TableCellRenderer {
     private final TableCellRenderer defaultRenderer;

     public PaddedHeaderRenderer(TableCellRenderer defaultRenderer) {
         this.defaultRenderer = defaultRenderer;
     }

     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         JLabel label = (JLabel) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

         // Add padding to the header cell
         label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust padding as needed

         return label;
     }
 }
}
