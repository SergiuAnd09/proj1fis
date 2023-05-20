package fis1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.eclipse.swt.custom.TableEditor;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;


import dbObjects.Cerere;
import dbObjects.ContentProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;

public class AdminPage extends Composite {
	private Table table_1;

	/**
	 *
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public AdminPage(Composite parent, int style) throws SQLException {
		super(parent, style);
		
		//table viewer
		ContentProvider contentProvider = new ContentProvider();
		
		
		final TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setBounds(33, 65, 560, 330);
		
		  
		 
		
		final TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn tblclmnEmail = tableViewerColumn.getColumn();
		tblclmnEmail.setWidth(100);
		tblclmnEmail.setText("email");
		
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			  public String getText(Object element) {
		        Cerere cerere = (Cerere) element;
		        return cerere.getEmail(); // Return the value for the column
		    }
			
		});
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnMessage = tableViewerColumn_1.getColumn();
		tblclmnMessage.setWidth(100);
		tblclmnMessage.setText("message");
		
				tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
					@Override
					  public String getText(Object element) {
				        Cerere cereree = (Cerere) element;
				        return cereree.getMessage(); // Return the value for the column
				    }
					
				});
		
				ArrayList<Button> buttonListYes = new ArrayList<>();
				ArrayList<Button> buttonListNo = new ArrayList<>();		
				
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Accept");
		
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public void update(ViewerCell cell) {
		        TableItem tableItem = (TableItem) cell.getItem();
		        final TableEditor editor = new TableEditor(tableItem.getParent());

		        final Button button = new Button(tableItem.getParent(), SWT.PUSH);
		        button.setText("Yes");
		        button.setData(tableItem);
		        buttonListYes.add(button);

		        button.addSelectionListener(new SelectionAdapter() {
		            @Override
		            public void widgetSelected(SelectionEvent e) {
		                // Get the TableItem associated with the clicked button
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    // Get the corresponding data object (Cerere)
		                    Cerere selectedCerere = (Cerere) selectedTableItem.getData();

		                    // Access the data of the selected row
		                    final String email = selectedCerere.getEmail();
		                    String message = selectedCerere.getMessage();
		                    int i = -1;
		                    for (Button buttonNo : buttonListNo) {
		                    	i++;
		                        Object buttonData = (TableItem)button.getData();
		                        // Compare the button's data with the specified data
		                        if (buttonData.equals((TableItem) buttonNo.getData())) {
		                            Button foundButton = buttonNo;
		                            buttonListNo.remove(i);
		                            buttonListYes.remove(i);
		                            foundButton.dispose();
		                            break; // Exit the loop if a matching button is found
		                        }
		                    }
		                    // Perform actions with the data
		                    System.out.println("Email: " + email);
		                    System.out.println("Message: " + message);
		                    button.dispose();

		                 // Remove the buttons from the selected T
		                    
//		                    ContentProvider.cereri.removeIf(entry -> entry.getEmail().equals(email));
//		                    
//		                    table_1.remove(table_1.indexOf(selectedTableItem));
//		                    tableViewer.refresh();

		                }
		            }
		        });

		        editor.grabHorizontal = true;
		        editor.setEditor(button, tableItem, cell.getColumnIndex());
		        editor.layout();
		        

		        tableItem.addDisposeListener(new DisposeListener() {
		            public void widgetDisposed(DisposeEvent e) {
		                button.dispose();
		                editor.dispose();
		            }
		        });
		    }
		});


		 
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Decline");
		
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			  
			  @Override public void update(ViewerCell cell) {
			  
			  TableItem tableItem = (TableItem) cell.getItem();
			  final TableEditor editor = new TableEditor(tableItem.getParent());
			  
			  final Button button = new Button(tableItem.getParent(), SWT.PUSH);
			  button.setText("No");
			  button.setData(tableItem);
			  buttonListNo.add(button);
			  button.addSelectionListener(new SelectionAdapter() {
				  
				  @Override
				  public void widgetSelected(SelectionEvent e) {
//<<<<<<< Updated upstream
					  // Get the TableItem associated with the clicked button
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    // Get the corresponding data object (Cerere)
		                    Cerere selectedCerere = (Cerere) selectedTableItem.getData();

		                    // Access the data of the selected row
		                    final String email = selectedCerere.getEmail();
		                    String message = selectedCerere.getMessage();
		                    int i = -1;
		                    for (Button buttonYes : buttonListYes) {
		                    	i++;
		                        Object buttonData = (TableItem)button.getData();
		                        // Compare the button's data with the specified data
		                        if (buttonData.equals((TableItem) buttonYes.getData())) {
		                            Button foundButton = buttonYes;
		                            buttonListNo.remove(i);
		                            buttonListYes.remove(i);
		                            foundButton.dispose();
		                            break; // Exit the loop if a matching button is found
		                        }
		                    }
		                    // Perform actions with the data
		                    System.out.println("Email: " + email);
		                    System.out.println("Message: " + message);
		                    button.dispose();

		                 // Remove the buttons from the selected T
		                    
//		                    ContentProvider.cereri.removeIf(entry -> entry.getEmail().equals(email));
//		                    
//		                    table_1.remove(table_1.indexOf(selectedTableItem));
//		                    tableViewer.refresh();

		                }
//=======
					  //TableItem tt = new TableItem(table_1, SWT.NONE);
//>>>>>>> Stashed changes
					  System.out.println("nay z");
					  //System.out.println(tt.getText(1));
					  
				  }
			  });
			  
			  editor.grabHorizontal = true;
		        editor.setEditor(button, tableItem, cell.getColumnIndex());
		        editor.layout();
		        
		        tableItem.addDisposeListener(new DisposeListener() {
		            public void widgetDisposed(DisposeEvent e) {
		                button.dispose();
		                editor.dispose();
		            }
		        });
			  }
			  
			  });
		
		tableViewer.setContentProvider(contentProvider);
		  tableViewer.setInput(contentProvider.getElements(null));
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
