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


import dbObjects.Oferta;
import dbObjects.Cerere;
import dbObjects.ContentProvider;
import dbObjects.ContentProviderIstoric;
import dbObjects.ContentProviderOferta;
import dbObjects.IstoricVanzari;
import dbObjects.Produs;
import ui.LayoutStack;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;

public class OffersPage extends Composite {
	private Table table_1;

	/**
	 *
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public OffersPage(Composite parent, int style) throws SQLException {
		super(parent, style);
		//table viewer
		ContentProviderOferta contentProvider = new ContentProviderOferta();


		final TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setBounds(33, 65, 704, 330);


		
		TableViewerColumn tableViewerColumn_name = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnname = tableViewerColumn_name.getColumn();
		tblclmnname.setWidth(150);

		tableViewerColumn_name.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Oferta)element).getName();
			}
		});



		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPret = tableViewerColumn_2.getColumn();
		tblclmnPret.setWidth(135);

		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Float)((Oferta)element).getPret()).toString();
			}
		});
		
		final TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn tblclmnEmail = tableViewerColumn.getColumn();
		tblclmnEmail.setWidth(215);

		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Oferta)element).getEmail();
			}
		});
		
		TableViewerColumn tableViewerColumn_accept = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_accept.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Accept");
		
		tableViewerColumn_accept.setLabelProvider(new ColumnLabelProvider() {
			  
			  @Override public void update(ViewerCell cell) {
			  
			  TableItem tableItem = (TableItem)cell.getItem();
			  final TableEditor editor = new TableEditor(tableItem.getParent());
			  
			  final Button button = new Button(tableItem.getParent(), SWT.PUSH);
			  button.setText("Accept");
			  button.setData(tableItem);
			  button.addSelectionListener(new SelectionAdapter() {
				  
				  @Override
				  public void widgetSelected(SelectionEvent e) {
					  // Get the TableItem associated with the clicked button
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    // Get the corresponding data object (Oferta)
		                    Oferta selectedProduct = (Oferta)selectedTableItem.getData();
		                    // Access the data of the selected row
		                    String name = selectedProduct.getName();
		                    String emailSeller = selectedProduct.getEmail_vanzator();
		                    // Perform actions with the data
		                    try {
			                    Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			                    PreparedStatement logPurchase = connection.prepareStatement("INSERT INTO sellhistory (id, nume, pret, email_cumparator, email_vanzator) VALUES (?,?,?,?,?)");
			                    logPurchase.setInt(1, selectedProduct.getId_produs());
			                    logPurchase.setString(2, name);
			                    logPurchase.setFloat(3, selectedProduct.getPret());
			                    logPurchase.setString(4, selectedProduct.getEmail());
			                    logPurchase.setString(5, selectedProduct.getEmail_vanzator());
			                    logPurchase.executeUpdate();
			                    logPurchase.close();
	                            PreparedStatement deleteOffer = connection.prepareStatement("DELETE FROM offers WHERE email_vanzator = ? AND name = ?");
	                            deleteOffer.setString(1, emailSeller);
	                            deleteOffer.setString(2, name);
	                            deleteOffer.executeUpdate();
	                            deleteOffer.close();
	                            PreparedStatement deleteProduct = connection.prepareStatement("DELETE FROM products WHERE email = ? AND nume = ?");
	                            deleteProduct.setString(1, emailSeller);
	                            deleteProduct.setString(2, name);
	                            deleteProduct.executeUpdate();
	                            deleteProduct.close();
	                            connection.close();
			                    } catch (SQLException ex) {
		                            ex.printStackTrace();
			                    }
		                    LayoutStack.getInstance().changeLayout(3);
                            LayoutStack.getInstance().deleteLayout(10);
                            try {
								LayoutStack.getInstance().addLayout(10, new OffersPage(LoginPage.getShell(),SWT.NONE));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                            LayoutStack.getInstance().changeLayout(10);
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
		
		TableViewerColumn tableViewerColumn_decline = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn2 = tableViewerColumn_decline.getColumn();
		tblclmnNewColumn2.setWidth(100);
		tblclmnNewColumn2.setText("Decline");
		
		tableViewerColumn_decline.setLabelProvider(new ColumnLabelProvider() {
			  
			  @Override public void update(ViewerCell cell) {
			  
			  TableItem tableItem = (TableItem)cell.getItem();
			  final TableEditor editor = new TableEditor(tableItem.getParent());
			  
			  final Button button = new Button(tableItem.getParent(), SWT.PUSH);
			  button.setText("Decline");
			  button.setData(tableItem);
			  button.addSelectionListener(new SelectionAdapter() {
				  
				  @Override
				  public void widgetSelected(SelectionEvent e) {
					  // Get the TableItem associated with the clicked button
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    // Get the corresponding data object (Oferta)
		                    Oferta selectedProduct = (Oferta)selectedTableItem.getData();
		                    // Access the data of the selected row
		                    String email = selectedProduct.getEmail();
		                    String name = selectedProduct.getName();
		                    String emailSeller = selectedProduct.getEmail_vanzator();
		                    // Perform actions with the data
		                    try {
			                    Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
	                            PreparedStatement deleteOffer = connection.prepareStatement("DELETE FROM offers WHERE email_vanzator = ? AND name = ? AND email = ?");
	                            deleteOffer.setString(1, emailSeller);
	                            deleteOffer.setString(2, name);
	                            deleteOffer.setString(3, email);
	                            deleteOffer.executeUpdate();
	                            deleteOffer.close();
	                            connection.close();
			                    } catch (SQLException ex) {
		                            ex.printStackTrace();
			                    }
		                    LayoutStack.getInstance().changeLayout(3);
                            LayoutStack.getInstance().deleteLayout(10);
                            try {
								LayoutStack.getInstance().addLayout(10, new OffersPage(LoginPage.getShell(),SWT.NONE));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                            LayoutStack.getInstance().changeLayout(10);
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

		tableViewer.setContentProvider(contentProvider);
		tableViewer.setInput(contentProvider.getElements(null));

		Button btnLogout = new Button(this, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Main Menu");
				LoginPage.getShell().setSize(450, 300);
				LayoutStack.getInstance().changeLayout(3);
			}
		});
		btnLogout.setBounds(647, 404, 90, 30);
		btnLogout.setText("Back");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
