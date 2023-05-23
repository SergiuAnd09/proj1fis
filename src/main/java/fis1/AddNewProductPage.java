package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import ui.LayoutStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddNewProductPage extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddNewProductPage(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(111, 26, 96, 26);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(111, 65, 96, 26);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(111, 108, 294, 26);
		
		Button btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setSelection(true);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!btnCheckButton.getSelection()) {
					text_3.setEditable(false);
					text_3.setText("");
				}
				else {
					text_3.setEditable(true);
				}
			}
		});
		btnCheckButton.setBounds(267, 29, 111, 20);
		btnCheckButton.setText("Negotiable");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(328, 65, 78, 26);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(23, 29, 70, 20);
		lblNewLabel.setText("Name");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(23, 68, 70, 20);
		lblNewLabel_1.setText("Price");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setBounds(10, 111, 83, 20);
		lblNewLabel_2.setText("Description");
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.RIGHT);
		lblNewLabel_3.setBounds(241, 68, 70, 20);
		lblNewLabel_3.setText("Min Price");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText();
				float price = Float.parseFloat(text_1.getText()); 
				String description = text_2.getText();
				String inputText = text_3.getText().trim();
				float min_price;
				if (!inputText.isEmpty()) {
				    min_price = Float.parseFloat(inputText);
				} else {
				    min_price = 0.0f;
				}
				 try {
	                 Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
	                 if (min_price > 1) {
	                	 PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products (nume,pret,email,descriere, pret_minim) VALUES (?,?,?,?,?)");
	                	 insertProduct.setString(1, name);
	                     insertProduct.setFloat(2, price);
	                     insertProduct.setString(3, LoginMenu.email);
	                     insertProduct.setString(4, description);
	                     insertProduct.setFloat(5, min_price);
	                     insertProduct.executeUpdate();
	                     insertProduct.close();
	                 }else {
	                	 PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products (nume,pret,email,descriere) VALUES (?,?,?,?)");
	                	 insertProduct.setString(1, name);
	                     insertProduct.setFloat(2, price);
	                     insertProduct.setString(3, LoginMenu.email);
	                     insertProduct.setString(4, description);
	                     insertProduct.executeUpdate();
	                     insertProduct.close(); 
	                 } 
                     connection.close();
	                    } catch (SQLException ex) {
                         ex.printStackTrace();
	                    }
				LayoutStack.getInstance().deleteLayout(5);
                LayoutStack.getInstance().addLayout(5, new MyAdsPage(LoginPage.getShell(),SWT.NONE));
				LayoutStack.getInstance().changeLayout(5);
				LoginPage.getShell().setText("My Ads");
				LoginPage.getShell().setSize(650, 510);
			}
		});
		btnNewButton_1.setBounds(76, 160, 111, 45);
		btnNewButton_1.setText("Confirm");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LayoutStack.getInstance().changeLayout(5);
				LoginPage.getShell().setText("My Ads");
				LoginPage.getShell().setSize(650, 510);
			}
		});
		btnNewButton.setBounds(250, 160, 111, 45);
		btnNewButton.setText("Cancel");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
