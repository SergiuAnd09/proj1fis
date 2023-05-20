package fis1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		
		TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setBounds(33, 65, 560, 330);
		
		  
		 
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmail = tableViewerColumn.getColumn();
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
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Accept");
		
		
		  tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
		  
		  @Override public void update(ViewerCell cell) {
		  
		  TableItem tableItem = (TableItem) cell.getItem();
		  final TableEditor editor = new TableEditor(tableItem.getParent());
		  
		  final Button button = new Button(tableItem.getParent(), SWT.PUSH);
		  button.setText("Yes");
		  button.addSelectionListener(new SelectionAdapter() {
			  
			  @Override
			  public void widgetSelected(SelectionEvent e) {
				  
				  System.out.println("yey");
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
			  button.addSelectionListener(new SelectionAdapter() {
				  
				  @Override
				  public void widgetSelected(SelectionEvent e) {
					  
					  System.out.println("nay z");
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
