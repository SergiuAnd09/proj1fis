package ui;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import fis1.LoginPage;
import fis1.MainMenu;
import fis1.LoginMenu;
import fis1.AdminPage;
import fis1.RegisterPage;

public class LayoutStack {

	int crtActive;
	StackLayout stack;
	Composite[] layouts;
	Shell shell;
	
	public LayoutStack(Shell shell) throws SQLException {
		this.shell = shell;
		stack = new StackLayout();
		shell.setLayout(stack);
		
		layouts = new Composite[11];
		
		layouts[0] = new LoginMenu(shell, SWT.NONE);
		
		layouts[1] = new RegisterPage(shell, SWT.NONE);
		
		layouts[2] = new AdminPage(shell, SWT.NONE);
		
	}
	

	public void addLayout(int index, Composite layout) {
		this.layouts[index] = layout;
	}
	public void deleteLayout(int index) {
		this.layouts[index] = null;
	}

	public void changeLayout(int winNumber) {
		layouts[crtActive].setVisible(!layouts[crtActive].isVisible());
		stack.topControl = layouts[winNumber];
		crtActive = winNumber;
		shell.layout();
	}
	
	private static LayoutStack _instance;
	
	public static void createInstance(Shell shell) throws SQLException {
		_instance = new LayoutStack(shell);
	}
	
	public static LayoutStack getInstance() {
		if(_instance == null)
			throw new RuntimeException("Stack not instanciated but used");
		return _instance;
	}
}
