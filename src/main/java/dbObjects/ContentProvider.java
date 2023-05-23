package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import fis1.DBConnection;

public class ContentProvider implements IStructuredContentProvider {

	 public static List<Cerere> cereri = new ArrayList<Cerere>();

	public ContentProvider() {
		try {
			Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			PreparedStatement takerequests = connection.prepareStatement("select * from requests");
			ResultSet tot = takerequests.executeQuery();
			cereri.clear();
			
			while (tot.next()) {
				Cerere cerere = new Cerere(tot.getString("email"), tot.getString("message"));
				cereri.add(cerere);
			}
	
			tot.close();
			takerequests.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[] getElements(Object arg0) {
		return  cereri.toArray();
	}
}
