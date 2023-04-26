package fis1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Japonia {

	protected Shell shlYeyeye;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Japonia window = new Japonia();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlYeyeye.open();
		shlYeyeye.layout();
		while (!shlYeyeye.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlYeyeye = new Shell();
		shlYeyeye.setSize(450, 300);
		shlYeyeye.setText("yeyeye");
		
		Label lblNewLabel = new Label(shlYeyeye, SWT.NONE);
		lblNewLabel.setBounds(23, 51, 55, 15);
		lblNewLabel.setText("Nume");
		
		text = new Text(shlYeyeye, SWT.BORDER);
		text.setBounds(151, 51, 76, 21);
		
		Label lblNewLabel_1 = new Label(shlYeyeye, SWT.NONE);
		lblNewLabel_1.setBounds(23, 102, 55, 15);
		lblNewLabel_1.setText("Parola");
		
		
		text_1 = new Text(shlYeyeye, SWT.BORDER);
		text_1.setBounds(151, 99, 76, 21);
		
		Button btnNewButton = new Button(shlYeyeye, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				zoe zoee = new zoe();
				zoee.open();
			}
		});
		btnNewButton.setBounds(108, 171, 75, 25);
		btnNewButton.setText("Spre alta");

	}
}
