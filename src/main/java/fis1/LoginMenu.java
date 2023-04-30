package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class LoginMenu extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginMenu(Composite parent, int style) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(39, 122, 54, 26);
		lblNewLabel.setText("Login");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(125, 119, 78, 26);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(125, 176, 78, 30);
		btnNewButton.setText("Login");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(228, 176, 90, 30);
		btnNewButton_1.setText("Register");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
