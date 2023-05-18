package fis1;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import ui.LayoutStack;

import java.sql.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import dbObjects.Client;

public class RegisterPage extends Composite {
    private final Text emailTxt;
    private final Text passwordTxt;
    private final Text passwordConfirmTxt;

    /**
     * Create the composite.
     *
     * @param parent
     * @param style
     */
    public RegisterPage(Composite parent, int style) {
        super(parent, style);

        emailTxt = new Text(this, SWT.BORDER);
        emailTxt.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
        emailTxt.setBounds(173, 38, 202, 28);

        passwordTxt = new Text(this, SWT.BORDER | SWT.PASSWORD);
        passwordTxt.setBounds(173, 78, 202, 28);

        final Label warningLabel = new Label(this, SWT.CENTER);
        warningLabel.setText("");
        warningLabel.setBounds(39, 158, 374, 25);

        Button registerButton = new Button(this, SWT.NONE);
        registerButton.addSelectionListener(new SelectionAdapter() {
            private final Display display = Display.getDefault();

            @Override
            public void widgetSelected(SelectionEvent e) {

                display.asyncExec(new Runnable() {
                    public void run() {
                        String email = emailTxt.getText();
                        String password = passwordTxt.getText();
                        String confirmPassword = passwordConfirmTxt.getText();

                        if (!isValidEmail(email)) {
                            emailTxt.setFocus();
                            emailTxt.setForeground(display.getSystemColor(SWT.COLOR_RED));
                            emailTxt.setToolTipText("Invalid email address");
                            warningLabel.setText("Invalid email address.");
                            return;
                        }
                        
                        emailTxt.setToolTipText("");
                        emailTxt.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                        
                        if (password.isEmpty()) {
                        	warningLabel.setText("Password cannot be null.");
                        	passwordTxt.setFocus();
                            return;
                        }
                        
                       warningLabel.setText("");
                        
                        if(!arePasswordsEqual(password, confirmPassword)){
                            passwordConfirmTxt.setFocus();
                            passwordConfirmTxt.setForeground(display.getSystemColor(SWT.COLOR_RED));
                            passwordConfirmTxt.setToolTipText("Passwords are different");
                            warningLabel.setText("Passwords are different.");
                            return;
                        }

                        passwordConfirmTxt.setToolTipText("");
                        passwordConfirmTxt.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                        
                        Connection connection = null;
                        PreparedStatement registerUser = null, checkUser = null;
                        ResultSet result = null;
                        
                        try {
                            connection= DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
                            checkUser = connection.prepareStatement("SELECT * FROM users WHERE email=?");
                            checkUser.setString(1, email);
                            result = checkUser.executeQuery();

                            if (result.next()) {
                                // the email is already in use
                                emailTxt.setForeground(display.getSystemColor(SWT.COLOR_RED));
                                emailTxt.setToolTipText("Email address is already in use");
                                warningLabel.setText("Email address is already in use.");
                                return;
                            }
                            
                            emailTxt.setToolTipText("");
                            emailTxt.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                            warningLabel.setText("");

                            // the email is not in use, so insert the new user
                            registerUser = connection.prepareStatement("INSERT INTO users (email,password) VALUES (?,?)");
                            registerUser.setString(1, email);
                            registerUser.setString(2, password);
                            registerUser.executeUpdate();
                            warningLabel.setText("Registration Successful!");
                            // wait 1.5 seconds and change the layout
                            Display display = emailTxt.getDisplay();
                            display.timerExec(1500, new Runnable() {
                                public void run() {
                                	LoginPage.getShell().setText("Login");
                                    LayoutStack.getInstance().changeLayout(0);
                                    emailTxt.setText("");
                                    passwordTxt.setText("");
                                    passwordConfirmTxt.setText("");
                                    warningLabel.setText("");
                                }
                            });
                        } catch (SQLException ex) {
                        	warningLabel.setText("Error! Try again.");
                            ex.printStackTrace();
                        } finally {
                        	 try {
                        	        if (result != null) {
                        	            result.close();
                        	        }
                        	        if (checkUser != null) {
                        	            checkUser.close();
                        	        }
                        	        if (registerUser != null) {
                        	            registerUser.close();
                        	        }
                        	        if (connection != null) {
                        	            connection.close();
                        	        }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
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
        emailLabel.setAlignment(SWT.RIGHT);
        emailLabel.setBounds(77, 41, 81, 25);
        emailLabel.setText("Email");

        Label passwordLabel = new Label(this, SWT.NONE);
        passwordLabel.setAlignment(SWT.RIGHT);
        passwordLabel.setBounds(77, 81, 81, 25);
        passwordLabel.setText("Password");

        Label lblConfirmPassword = new Label(this, SWT.NONE);
        lblConfirmPassword.setAlignment(SWT.RIGHT);
        lblConfirmPassword.setText("Confirm Password");
        lblConfirmPassword.setBounds(1, 121, 157, 25);

        passwordConfirmTxt = new Text(this, SWT.BORDER | SWT.PASSWORD);
        passwordConfirmTxt.setBounds(173, 118, 202, 28);

    }

    public boolean isValidEmail(String email) {
        String pattern = "^[^@]+@[^@]+\\.[^@]+$";
        return email.matches(pattern);
    }

    public boolean arePasswordsEqual(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
}
