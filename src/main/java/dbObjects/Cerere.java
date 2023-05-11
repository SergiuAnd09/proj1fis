package dbObjects;

import java.awt.Composite;
import fis1.AdminPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import ui.LayoutStack;

public class Cerere {
	
	private String email;
	private String message;
	
	public Cerere(String email, String message) {
        this.email = email;
        this.message = message;
    }
	
	public Cerere() {}
	
	public String getEmail() {
		
		return this.email;
	}
	
	public String getMessage() {
		
		return this.message;
	}
	
	 public Button createButton(Composite AdminPage) {
	        Button button = new Button((org.eclipse.swt.widgets.Composite) AdminPage, SWT.NONE);
	        button.setText("Accept Request");
	        button.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                // Code to accept the request
	            }
	        });
	        return button;
	    }
}
