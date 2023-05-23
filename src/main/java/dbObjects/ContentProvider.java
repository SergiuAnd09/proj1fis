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
			PreparedStatement takemails = connection.prepareStatement("select email from requests");
			ResultSet emailuri = takemails.executeQuery();
			PreparedStatement takemessages = connection.prepareStatement("select message from requests");
			ResultSet mesaje = takemessages.executeQuery();
			cereri.clear();
			while (emailuri.next() && mesaje.next()) {
				String email = emailuri.getString("email");
				String mesaj = mesaje.getString("message");
				Cerere cerere = new Cerere();
				cerere.setEmail(email);
				cerere.setMessage(mesaj);
				cereri.add(cerere);
			}
			//System.out.println(cereri);
			// Close the resources
			mesaje.close();
			takemessages.close();
			emailuri.close();
			takemails.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[] getElements(Object arg0) {
		return  cereri.toArray();
	}
}
