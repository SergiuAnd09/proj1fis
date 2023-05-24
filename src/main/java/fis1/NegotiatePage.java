package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import dbObjects.Produs;
import ui.LayoutStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;



public class NegotiatePage extends Composite {
	private Text text;
	public static int contor = 0;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NegotiatePage(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(154, 146, 124, 45);
		//se face atribuirea la suma
		//suma = Integer.parseInt(text.getText());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(79, 158, 70, 20);
		lblNewLabel.setText("     Sum:");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(44, 29, 371, 102);
		lblNewLabel_1.setText("Atentie! Introduceti cu grija suma dorita, \r\nsumele sub minimul setat de vanzator vor fii \r\nautomat ignorate, iar apasarea butonului \"Incearca\" \r\nnu garanteaza trimiterea ofertei daca minimul banesc \r\nnu este satisfacut.");
		
		Button btnBack = new Button(this, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("ViewProducts");
				LoginPage.getShell().setSize(900, 650);
				LayoutStack.getInstance().changeLayout(6);
			}
		});
		btnBack.setBounds(298, 230, 103, 45);
		btnBack.setText("Back");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Produs produs = (Produs)ViewProductsPage.selectedTableItem.getData();
				Connection connec=null;
				ResultSet rs=null;
				try {
					connec = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
					PreparedStatement off = connec.prepareStatement("select * from offers where id_produs=? and email=?");
					off.setInt(1, produs.getId());
					off.setString(2, LoginMenu.email);
					rs = off.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(Float.parseFloat(text.getText())<ViewProductsPage.pret_min) {;}//verificare sa avem produs negociabil	 
 else
					try {
						if(rs.next()) {
							MessageBox messageBox = new MessageBox(LoginPage.shell, SWT.ICON_INFORMATION | SWT.OK);
		        	        messageBox.setText("Warning!");
		        	        messageBox.setMessage("Maxim o oferta per produs!");
		        	        
		        	        // Open the message box and wait for user interaction
		        	        int result = messageBox.open();
		        	        if (result == SWT.OK) {}
						}
						else {
							
							try {
								 String sql = "insert into offers values (?,?,?,?,?)";
								 
								PreparedStatement introducereOferte = connec.prepareStatement(sql);
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
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 
}
					
			
		});
		btnNewButton.setBounds(298, 146, 103, 45);
		btnNewButton.setText("Try");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
}
