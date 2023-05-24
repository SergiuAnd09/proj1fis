package fis1;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.TableColumn;
import dbObjects.Produs;
import ui.LayoutStack;
import dbObjects.ContentProviderProdus;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import dbObjects.*;

public class ViewProductsPage extends Composite {
	private Table table;
	public static TableItem selectedTableItem=null;
	public static float pret_min=0;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ViewProductsPage(Composite parent, int style) {
		super(parent, style);
		ContentProviderProdus contentProviderProdus = new ContentProviderProdus();
		
		TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(79, 121, 625, 304);
		
		TableViewerColumn colnume = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNume = colnume.getColumn();
		tblclmnNume.setWidth(100);
		tblclmnNume.setText("nume");
		colnume.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public String getText(Object element) {
				Produs produs = (Produs) element;
				return produs.getEmail();
			}
		});
		
		TableViewerColumn coldescriere = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDescriere = coldescriere.getColumn();
		tblclmnDescriere.setWidth(100);
		tblclmnDescriere.setText("descriere");
		coldescriere.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public String getText(Object element) {
				Produs produs = (Produs) element;
				return produs.getDescriere();
			}
		});
		
		TableViewerColumn colpret = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = colpret.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("pret");
		colpret.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public String getText(Object element) {
				
				Produs produs = (Produs) element;
				return Float.toString(produs.getPret());
			}
		});
		
		TableViewerColumn coloferteaza = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = coloferteaza.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("oferteaza");
		coloferteaza.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				
				TableItem item = (TableItem) cell.getItem();
				final TableEditor editor = new TableEditor(item.getParent());
				final Button button = new Button(item.getParent(), SWT.PUSH);
		        button.setText("Oferteaza");
		        button.setData(item);
		        
		        button.addSelectionListener(new SelectionAdapter() {
		        	
		        	
		        	@Override
		        	public void widgetSelected(SelectionEvent e) {
		        		
						Connection connection = null;
		        		selectedTableItem = (TableItem) button.getData();
		        		ResultSet rs = null;
		        		Produs produs = (Produs) selectedTableItem.getData();
		        		 
		        		 try {
		        			 String sql = "insert into offers values (?,?,?,?,?)";
		        			 connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
							PreparedStatement introducereOferte = connection.prepareStatement(sql);
							introducereOferte.setInt(1, produs.getId());
							introducereOferte.setString(2, LoginMenu.email);
							introducereOferte.setFloat(3, produs.getPret());
							introducereOferte.setString(4, produs.getEmail());
							introducereOferte.setString(5, produs.getNume());
							
							introducereOferte.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        		
		        	}
		        	
		        	
		        });
		        
				  editor.grabHorizontal = true;
			        editor.setEditor(button, item, cell.getColumnIndex());
			        editor.layout();
			        
			        item.addDisposeListener(new DisposeListener() {
			            public void widgetDisposed(DisposeEvent e) {
			                button.dispose();
			                editor.dispose();
			            }
			});
			}
		});
		
		TableViewerColumn colnegociaza = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = colnegociaza.getColumn();
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("negociaza");
		colnegociaza.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				TableItem item = (TableItem) cell.getItem();
				final Button button = new Button(item.getParent(), SWT.PUSH);
				
				final TableEditor editor = new TableEditor(item.getParent());
		        button.setText("Negociaza");
		        button.setData(item);
		        selectedTableItem = (TableItem) button.getData();
				Produs produs = (Produs) ViewProductsPage.selectedTableItem.getData();
		        if(produs.getPret_minim()<1) {
		        	button.setEnabled(false);
		        	button.setGrayed(true);
		        }
		        
		        button.addSelectionListener(new SelectionAdapter() {
		        	
		        	@Override
		        	public void widgetSelected(SelectionEvent e) {
		        		
		        		pret_min = produs.getPret_minim();
		        		
		        			LayoutStack.getInstance().addLayout(9, new NegotiatePage(LoginPage.getShell(), SWT.NONE));
							LoginPage.getShell().setText("NegotiatePage");
							LoginPage.getShell().setSize(600, 500);
							LayoutStack.getInstance().changeLayout(9);
		        	}
		        });
		        
				  editor.grabHorizontal = true;
			        editor.setEditor(button, item, cell.getColumnIndex());
			        editor.layout();
			        
			        item.addDisposeListener(new DisposeListener() {
			            public void widgetDisposed(DisposeEvent e) {
			                button.dispose();
			                editor.dispose();
			            }
			});
			}
		});
		
		TableViewerColumn colsterge = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnSterge = colsterge.getColumn();
		tblclmnSterge.setWidth(100);
		tblclmnSterge.setText("sterge");
		colsterge.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public void update(ViewerCell cell) {
		    	
		        final TableItem item = (TableItem) cell.getItem();
		        final TableEditor editor = new TableEditor(item.getParent());
		        final Button button = new Button(item.getParent(), SWT.PUSH);
		        button.setText("Sterge");
		        button.setData(item);

		        button.addSelectionListener(new SelectionAdapter() {
		        	Connection con = null;
		            @Override
		            public void widgetSelected(SelectionEvent e) {
		                TableItem selectedTableItem = (TableItem) button.getData();
		                if (selectedTableItem != null) {
		                    Produs produs = (Produs) selectedTableItem.getData();
		                    
		                    try {
								con = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
								PreparedStatement ps = con.prepareStatement("delete from offers where id_produs=? and email=? and pret=?");
								ps.setInt(1, produs.getId());
								ps.setString(2, LoginMenu.email);
								ps.setFloat(3, produs.getPret());
								ps.executeUpdate();
								//mesaj
								MessageBox messageBox = new MessageBox(LoginPage.shell, SWT.ICON_INFORMATION | SWT.OK);
			        	        messageBox.setText("Announcement");
			        	        messageBox.setMessage("Stergere efectuata!");
			        	        int result = messageBox.open();
			        	        if (result == SWT.OK) {}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
		                }
		            }
		        });

		        editor.grabHorizontal = true;
		        editor.setEditor(button, item, cell.getColumnIndex());
		        editor.layout();

		        item.addDisposeListener(new DisposeListener() {
		            public void widgetDisposed(DisposeEvent e) {
		                button.dispose();
		                editor.dispose();
		            }
		        });
		    }
		});

		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Main Menu");
				LoginPage.getShell().setSize(450, 300);
				LayoutStack.getInstance().changeLayout(3);
			}
		});
		btnNewButton.setBounds(521, 452, 90, 39);
		btnNewButton.setText("Back");
		
		//aici se dau datele catre tableviewer care se ocupa de distrubutia lor catre labelproviderurile fiecarei coloane
		tableViewer.setContentProvider(contentProviderProdus);
		tableViewer.setInput(contentProviderProdus.getElements(null));
		
		/*
		 * if (MainMenu.seller == 1) { btnRequestLicense.setEnabled(false);
		 * btnRequestLicense.setGrayed(true); }else { btnViewOffers.setEnabled(false);
		 * btnViewOffers.setGrayed(true); btnViewMyAds.setEnabled(false);
		 * btnViewMyAds.setGrayed(true); }
		 */
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
