package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import ui.LayoutStack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegisterPage extends Composite {
	private Text emailTxt;
	private Text passwordTxt;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RegisterPage(Composite parent, int style) {
		super(parent, style);
		
		emailTxt = new Text(this, SWT.BORDER);
		emailTxt.setBounds(131, 73, 202, 28);
		
		passwordTxt = new Text(this, SWT.BORDER | SWT.PASSWORD);
		passwordTxt.setBounds(131, 113, 202, 28);
		
		Button registerButton = new Button(this, SWT.NONE);
		registerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		registerButton.setBounds(99, 171, 105, 35);
		registerButton.setText("Register");
		
		Button cancelButton = new Button(this, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LayoutStack.getInstance().changeLayout(0);
			}
		});
		cancelButton.setBounds(248, 171, 105, 35);
		cancelButton.setText("Cancel");
		
		Label emailLabel = new Label(this, SWT.NONE);
		emailLabel.setBounds(69, 76, 47, 25);
		emailLabel.setText("Email");
		
		Label passwordLabel = new Label(this, SWT.NONE);
		passwordLabel.setBounds(35, 116, 81, 25);
		passwordLabel.setText("Password");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
