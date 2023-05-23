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
import dbObjects.ContentProviderIstoric;
import dbObjects.IstoricVanzari;
import dbObjects.Produs;
import ui.LayoutStack;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;

public class AdminPage2 extends Composite {
	private Table table_1;

	/**
	 *
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public AdminPage2(Composite parent, int style) throws SQLException {
		super(parent, style);
		//table viewer
		ContentProviderIstoric contentProvider = new ContentProviderIstoric();


		final TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setBounds(33, 65, 704, 330);


		
		TableViewerColumn tableViewerColumn_id = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn_id.getColumn();
		tblclmnId.setWidth(50);

		tableViewerColumn_id.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Integer)((IstoricVanzari)element).getId()).toString();
			}
		});

		
		final TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn tblclmnName = tableViewerColumn.getColumn();
		tblclmnName.setWidth(120);

		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IstoricVanzari)element).getNume();
			}
		});


		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPret = tableViewerColumn_2.getColumn();
		tblclmnPret.setWidth(100);

		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Float)((IstoricVanzari)element).getPret()).toString();
			}
		});
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmailSeller = tableViewerColumn_1.getColumn();
		tblclmnEmailSeller.setWidth(215);

		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IstoricVanzari)element).getEmailSeller();
			}
		});
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmail = tableViewerColumn_3.getColumn();
		tblclmnEmail.setWidth(215);

		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IstoricVanzari)element).getEmail();
			}
		});

		tableViewer.setContentProvider(contentProvider);
		tableViewer.setInput(contentProvider.getElements(null));

		Button btnLogout = new Button(this, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Login");
				LoginPage.getShell().setSize(430, 300);
				LayoutStack.getInstance().deleteLayout(0);
				LayoutStack.getInstance().addLayout(0, new LoginMenu(LoginPage.getShell(), SWT.NONE));
				LayoutStack.getInstance().changeLayout(0);
			}
		});
		btnLogout.setBounds(647, 404, 90, 30);
		btnLogout.setText("Logout");
		
		Button btnSaleHistory = new Button(this, SWT.NONE);
		btnSaleHistory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LayoutStack.getInstance().changeLayout(2);
				LayoutStack.getInstance().deleteLayout(8);
			}
		});
		btnSaleHistory.setText("Manage Approvals");
		btnSaleHistory.setBounds(476, 404, 154, 30);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
