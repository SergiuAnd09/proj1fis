package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import ui.LayoutStack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class RegisterPage extends Composite {
	private Text emailTxt;
	private Text passwordTxt;
	private Text passwordConfirmTxt;
	
	public boolean isValidEmail(String email) {
	    String pattern = "^[^@]+@[^@]+\\.[^@]+$";
	    return email.matches(pattern);
	}

	public boolean arePasswordsEqual(String password, String confirmPassword) {
	    return password.equals(confirmPassword);
	}


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RegisterPage(Composite parent, int style) {
		super(parent, style);
		
		emailTxt = new Text(this, SWT.BORDER);
		emailTxt.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		emailTxt.setText("testr");
		emailTxt.setBounds(173, 38, 202, 28);
		
		passwordTxt = new Text(this, SWT.BORDER | SWT.PASSWORD);
		passwordTxt.setBounds(173, 78, 202, 28);
		
		Button registerButton = new Button(this, SWT.NONE);
		registerButton.addSelectionListener(new SelectionAdapter() {
		    protected Display display = Display.getDefault();
		    
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        
		        display.asyncExec(new Runnable() {
		            public void run() {
		                if (!isValidEmail(emailTxt.getText())) {
		                    emailTxt.setFocus();
		                    emailTxt.setForeground(display.getSystemColor(SWT.COLOR_RED));
		                    emailTxt.setToolTipText("Invalid email address");
		                    
		                } else {
		                }
		            }
		        });
		    }
		});

		registerButton.setBounds(77, 200, 136, 35);
		registerButton.setText("Register");
		
		Button cancelButton = new Button(this, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Login");
				LayoutStack.getInstance().changeLayout(0);
			}
		});
		cancelButton.setBounds(240, 200, 136, 35);
		cancelButton.setText("Cancel");
		
		Label emailLabel = new Label(this, SWT.NONE);
		emailLabel.setBounds(111, 41, 47, 25);
		emailLabel.setText("Email");
		
		Label passwordLabel = new Label(this, SWT.NONE);
		passwordLabel.setBounds(77, 81, 81, 25);
		passwordLabel.setText("Password");
		
		Label lblConfirmPassword = new Label(this, SWT.NONE);
		lblConfirmPassword.setText("Confirm Password");
		lblConfirmPassword.setBounds(10, 121, 157, 25);
		
		passwordConfirmTxt = new Text(this, SWT.BORDER | SWT.PASSWORD);
		passwordConfirmTxt.setBounds(173, 118, 202, 28);
		
		Label warningLabel = new Label(this, SWT.CENTER);
		warningLabel.setText("Email");
		warningLabel.setBounds(39, 158, 374, 25);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
