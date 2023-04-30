package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import fis1.Japonia;

public class LayoutStack {

	int crtActive;
	StackLayout stack;
	Composite[] layouts;
	Shell shell;
	
	public LayoutStack(Shell shell) {
		this.shell = shell;
		stack = new StackLayout();
		shell.setLayout(stack);
		
		layouts = new Composite[2];
		
		//layouts[0] = new Japonia(shell, SWT.NONE);
		
		//layouts[1] = new SqrtPage(shell, SWT.NONE);
	}
}
