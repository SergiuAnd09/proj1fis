package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolBar;

public class AdminPage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AdminPage(Composite parent, int style) {
		super(parent, style);
		
		Label lblHello = new Label(this, SWT.NONE);
		lblHello.setBounds(56, 132, 70, 20);
		lblHello.setText("hello");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
