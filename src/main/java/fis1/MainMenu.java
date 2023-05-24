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
				
				LayoutStack.getInstance().addLayout(6, new ViewProductsPage(LoginPage.getShell(), SWT.NONE));
				LoginPage.getShell().setText("View Products");
				LoginPage.getShell().setSize(900, 650);
    			LayoutStack.getInstance().changeLayout(6);
			}
		});
		btnViewProducts.setBounds(58, 75, 145, 35);
		btnViewProducts.setText("View Products");
		
		Button btnRequestLicense = new Button(this, SWT.NONE);
		btnRequestLicense.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Approval Request");
				LayoutStack.getInstance().changeLayout(4);
			}
		});
		btnRequestLicense.setBounds(58, 116, 145, 35);
		btnRequestLicense.setText("Request Approval");
		
		Button btnViewOffers = new Button(this, SWT.NONE);
		btnViewOffers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("My Offers");
				LoginPage.getShell().setSize(790, 510);
				 try {
						LayoutStack.getInstance().addLayout(10, new OffersPage(LoginPage.getShell(),SWT.NONE));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				LayoutStack.getInstance().changeLayout(10);
			}
		});
		btnViewOffers.setText("View Offers");
		btnViewOffers.setBounds(217, 75, 145, 35);
		
		Button btnViewMyAds = new Button(this, SWT.NONE);
		btnViewMyAds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("My Ads");
				LoginPage.getShell().setSize(650, 510);
				LayoutStack.getInstance().changeLayout(5);
			}
		});
		btnViewMyAds.setText("View My Ads");
		btnViewMyAds.setBounds(217, 116, 145, 35);
		
		Button btnLogout = new Button(this, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 LayoutStack.getInstance().deleteLayout(4);
				 LayoutStack.getInstance().deleteLayout(1);
				 LayoutStack.getInstance().deleteLayout(0);
				 LayoutStack.getInstance().deleteLayout(5);
				 LoginPage.getShell().setText("Login");
				 LoginPage.getShell().setSize(430, 300);
				 LayoutStack.getInstance().addLayout(0, new LoginMenu(LoginPage.getShell(), SWT.NONE));
				 LayoutStack.getInstance().addLayout(1, new RegisterPage(LoginPage.getShell(), SWT.NONE));
				 LayoutStack.getInstance().changeLayout(0);
				 LayoutStack.getInstance().deleteLayout(3);
			}
		});
		btnLogout.setText("Logout");
		btnLogout.setBounds(276, 207, 145, 35);
		
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
