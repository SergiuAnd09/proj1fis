package fis1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dbObjects.Cerere;
import org.eclipse.jface.viewers.TableViewerColumn;

public class AdminPage extends Composite {
	private Table table;

	/**
	 *
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public AdminPage(Composite parent, int style) throws SQLException {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(58, 82, 422, 298);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		
		//conexiune
		List<Cerere> cereri = new ArrayList<Cerere>();

		Connection connection = null;
        PreparedStatement takemails = null, takemessages = null;
        ResultSet emailuri, mesaje = null;
        
        connection= DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
        takemails = connection.prepareStatement("select email from requests");
        emailuri = takemails.executeQuery();
        takemessages = connection.prepareStatement("select message from requests");
        mesaje = takemessages.executeQuery();
        
        while(emailuri.next() && mesaje.next()) {
        	
        	String email = emailuri.getString("email");
        	String mesaj = mesaje.getString("message");
        	Cerere cerere = new Cerere(email, mesaj);
        	cereri.add(cerere);
        }
        
        emailuri.close();
        mesaje.close();
        takemails.close();
        takemessages.close();
        connection.close();
        
        //incheiere conexiune
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
