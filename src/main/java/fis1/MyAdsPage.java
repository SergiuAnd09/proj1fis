package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import dbObjects.Produs;
import ui.LayoutStack;
import dbObjects.Cerere;
import dbObjects.ContentProvider;
import dbObjects.ContentProviderProdus;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MyAdsPage extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyAdsPage(Composite parent, int style) {
		super(parent, style);
		ContentProviderProdus contentProvider = new ContentProviderProdus(LoginMenu.email);
		TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(46, 60, 545, 330);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnName = tableViewerColumn.getColumn();
		tblclmnName.setWidth(100);
		tblclmnName.setText("name");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			  public String getText(Object element) {
		        return ((Produs)element).getNume();
		    }
			
		});
		
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrice = tableViewerColumn_1.getColumn();
		tblclmnPrice.setWidth(70);
		tblclmnPrice.setText("price");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			  public String getText(Object element) {
		        return ((Float)((Produs)element).getPret()).toString();
		    }
			
		});
		
		TableViewerColumn tableViewerColumn_1_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDescription = tableViewerColumn_1_1.getColumn();
		tblclmnDescription.setWidth(200);
		tblclmnDescription.setText("description");
		tableViewerColumn_1_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			  public String getText(Object element) {
		        return ((Produs)element).getDescriere();
		    }
			
		});
		
		TableViewerColumn tableViewerColumn_1_1_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnMinprice = tableViewerColumn_1_1_1.getColumn();
		tblclmnMinprice.setWidth(70);
		tblclmnMinprice.setText("minPrice");
		tableViewerColumn_1_1_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			  public String getText(Object element) {
				if (((Produs)element).getPret_minim() < 1) {
					return "FIXED";
				}
		        return ((Float)((Produs)element).getPret_minim()).toString();
		    }
			
		});
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Remove");
		
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			  
			  @Override public void update(ViewerCell cell) {
			  
			  TableItem tableItem = (TableItem)cell.getItem();
			  final TableEditor editor = new TableEditor(tableItem.getParent());
			  
			  final Button button = new Button(tableItem.getParent(), SWT.PUSH);
			  button.setText("Remove");
			  button.setData(tableItem);
			  button.addSelectionListener(new SelectionAdapter() {
				  
				  @Override
				  public void widgetSelected(SelectionEvent e) {
					  // Get the TableItem associated with the clicked button
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    // Get the corresponding data object (Produs)
		                    Produs selectedProduct = (Produs)selectedTableItem.getData();
		                    // Access the data of the selected row
		                    String email = selectedProduct.getEmail();
		                    String name = selectedProduct.getNume();
		                    // Perform actions with the data
		                    try {
			                    Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
	                            PreparedStatement deleteAd = connection.prepareStatement("DELETE FROM products WHERE email = ? AND nume = ?");
	                            deleteAd.setString(1, email);
	                            deleteAd.setString(2, name);
	                            deleteAd.executeUpdate();
	                            deleteAd.close();
	                            connection.close();
			                    } catch (SQLException ex) {
		                            ex.printStackTrace();
			                    }
		                    LayoutStack.getInstance().changeLayout(3);
                            LayoutStack.getInstance().deleteLayout(5);
                            LayoutStack.getInstance().addLayout(5, new MyAdsPage(LoginPage.getShell(),SWT.NONE));
                            LayoutStack.getInstance().changeLayout(5);
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
		
		
		Button btnBack = new Button(this, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Main Menu");
				LoginPage.getShell().setSize(450, 300);
				LayoutStack.getInstance().changeLayout(3);
			}
		});
		btnBack.setBounds(501, 406, 90, 30);
		btnBack.setText("Back");
		
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setInput(contentProvider.getElements(null));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
