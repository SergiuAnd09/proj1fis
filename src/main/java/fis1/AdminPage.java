package fis1;

import org.eclipse.swt.widgets.Composite;

public class AdminPage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AdminPage(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
