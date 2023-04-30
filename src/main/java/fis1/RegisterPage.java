package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class RegisterPage extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RegisterPage(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(131, 73, 180, 28);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(131, 113, 180, 28);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(106, 171, 105, 35);
		btnNewButton.setText("New Button");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(228, 171, 105, 35);
		btnNewButton_1.setText("New Button");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(69, 76, 47, 25);
		lblNewLabel.setText("Email");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(35, 116, 81, 25);
		lblNewLabel_1.setText("Password");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
