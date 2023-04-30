package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import ui.LayoutStack;

public class LoginMenu extends Composite {
	private Text text;
	private Text textEmail;
	private Text textPassword;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginMenu(Composite parent, int style) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(39, 122, 70, 26);
		lblNewLabel.setText("Password");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//if(textEmail.equals("admin@email.com") && textPassword.equals("admin")) {
					LayoutStack.getInstance().changeLayout(2);
				//}
				//else {;}
				
			}
		});
		btnNewButton.setBounds(125, 176, 78, 30);
		btnNewButton.setText("Login");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Register");
				LayoutStack.getInstance().changeLayout(1);
			}
		});
		btnNewButton_1.setBounds(228, 176, 90, 30);
		btnNewButton_1.setText("Register");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(39, 64, 70, 20);
		lblNewLabel_1.setText("Email");
		
		
		
		textEmail = new Text(this, SWT.BORDER);
		textEmail.setBounds(125, 64, 78, 26);
		
		textPassword = new Text(this, SWT.BORDER);
		textPassword.setBounds(125, 122, 78, 26);
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
