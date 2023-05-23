package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import ui.LayoutStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ApprovalRequest extends Composite {
	private Text txtEmail;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ApprovalRequest(Composite parent, int style) {
		super(parent, style);
		
		txtEmail = new Text(this, SWT.BORDER);
		txtEmail.setText(LoginMenu.email);
		txtEmail.setEditable(false);
		txtEmail.setBounds(130, 60, 230, 26);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(130, 112, 230, 23);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(30, 63, 84, 20);
		lblNewLabel.setText("Email");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(44, 115, 70, 20);
		lblNewLabel_1.setText("Message");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(85, 165, 280, 20);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text_1.getText().isBlank()) {
					lblNewLabel_2.setText("Please provide a message.");
					return;
				}
				
				try {
				Connection connection= DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
				PreparedStatement registerRequest = connection.prepareStatement("INSERT INTO requests (email,message) VALUES (?,?)");
				registerRequest.setString(1, txtEmail.getText());
				registerRequest.setString(2, text_1.getText());
				registerRequest.executeUpdate();
                connection.close();
                registerRequest.close();
				}catch (SQLException ex) {
                	lblNewLabel_2.setText("Error! Try again.");
                    ex.printStackTrace();
				}
				lblNewLabel_2.setText("Approval request submitted.");
				Display display = lblNewLabel_2.getDisplay();
                display.timerExec(1500, new Runnable() {
                    public void run() {
                    	LoginPage.getShell().setText("Main Menu");
                        LayoutStack.getInstance().changeLayout(3);
                    }
                });
			}
		});
		btnNewButton.setBounds(98, 209, 90, 30);
		btnNewButton.setText("Confirm");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Main Menu");
				LayoutStack.getInstance().changeLayout(3);
			}
		});
		btnCancel.setBounds(257, 209, 90, 30);
		btnCancel.setText("Cancel");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
