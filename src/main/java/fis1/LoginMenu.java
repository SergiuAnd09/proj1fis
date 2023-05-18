package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import ui.LayoutStack;

public class LoginMenu extends Composite {
	private Text text;
	private Text textEmail;
	private Text textPassword;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public LoginMenu(Composite parent, int style) {
		super(parent, style);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(29, 112, 90, 26);
		lblNewLabel.setText("Password");

		final Label lblFeedback = new Label(this, SWT.CENTER);
		lblFeedback.setBounds(80, 159, 280, 25);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textEmail.getText().equals("admin@email.com") && textPassword.getText().equals("admin")) {
					lblFeedback.setText("Admin Login Successful.");
					Display display = lblFeedback.getDisplay();
					display.timerExec(1500, new Runnable() {
						public void run() {
							LoginPage.getShell().setText("Admin Page");
							LayoutStack.getInstance().changeLayout(2);
						}
					});
				} else {
					if (authenticateUser(textEmail.getText(), textPassword.getText())) {
						lblFeedback.setText("Login Successful.");
						Display display = lblFeedback.getDisplay();
						display.timerExec(1500, new Runnable() {
							public void run() {
								LoginPage.getShell().setText("Main Menu");
								LayoutStack.getInstance().changeLayout(2);
							}
						});
					}
					else {
						lblFeedback.setText("Invalid email or password.");
					}
				}
			}
		});
		btnNewButton.setBounds(80, 199, 120, 43);
		btnNewButton.setText("Login");

		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginPage.getShell().setText("Register");
				LayoutStack.getInstance().changeLayout(1);
			}
		});
		btnNewButton_1.setBounds(240, 199, 120, 43);
		btnNewButton_1.setText("Register");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(29, 57, 70, 20);
		lblNewLabel_1.setText("Email");

		textEmail = new Text(this, SWT.BORDER);
		textEmail.setBounds(125, 54, 222, 26);

		textPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		textPassword.setBounds(125, 112, 222, 26);

	}

	public boolean authenticateUser(String email, String password) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);

			String query = "SELECT * FROM users WHERE email = ? AND password = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);

			resultSet = statement.executeQuery();

			boolean userExists = resultSet.first(); // Checks if user exists in the result set

			return userExists;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Close the connections, statements, and result sets in a finally block
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
