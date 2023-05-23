package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import ui.LayoutStack;

import org.eclipse.swt.widgets.Button;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainMenu extends Composite {

	public static int seller;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MainMenu(Composite parent, int style) {
		super(parent, style);
		
		Button btnViewProducts = new Button(this, SWT.NONE);
		btnViewProducts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnViewProducts.setBounds(58, 75, 145, 35);
		btnViewProducts.setText("View Products");
		
		Button btnRequestLicense = new Button(this, SWT.NONE);
		btnRequestLicense.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRequestLicense.setBounds(58, 116, 145, 35);
		btnRequestLicense.setText("Request License");
		
		Button btnViewSaleHistory = new Button(this, SWT.NONE);
		btnViewSaleHistory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnViewSaleHistory.setBounds(58, 157, 145, 35);
		btnViewSaleHistory.setText("View Sale History");
		
		Button btnViewOffers = new Button(this, SWT.NONE);
		btnViewOffers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnViewOffers.setText("View Offers");
		btnViewOffers.setBounds(217, 75, 145, 35);
		
		Button btnViewMyAds = new Button(this, SWT.NONE);
		btnViewMyAds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnViewMyAds.setText("View My Ads");
		btnViewMyAds.setBounds(217, 116, 145, 35);
		
		Button btnLogout = new Button(this, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 LayoutStack.getInstance().deleteLayout(2);
				 LayoutStack.getInstance().deleteLayout(1);
				 LayoutStack.getInstance().deleteLayout(0);
				 LoginPage.getShell().setText("Login");
				 LoginPage.getShell().setSize(430, 300);
				 LayoutStack.getInstance().addLayout(0, new LoginMenu(LoginPage.getShell(), SWT.NONE));
				 LayoutStack.getInstance().addLayout(1, new RegisterPage(LoginPage.getShell(), SWT.NONE));
				 try {
					LayoutStack.getInstance().addLayout(2, new AdminPage(LoginPage.getShell(), SWT.NONE));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				 LayoutStack.getInstance().changeLayout(0);
				 LayoutStack.getInstance().deleteLayout(3);
			}
		});
		btnLogout.setText("Logout");
		btnLogout.setBounds(217, 157, 145, 35);
		
		if (MainMenu.seller == 1) {
			btnRequestLicense.setEnabled(false);
			btnRequestLicense.setGrayed(true);
		}else {
			btnViewOffers.setEnabled(false);
			btnViewOffers.setGrayed(true);
			btnViewMyAds.setEnabled(false);
			btnViewMyAds.setGrayed(true);
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
