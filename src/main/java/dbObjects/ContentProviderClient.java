package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import fis1.DBConnection;

public class ContentProviderClient implements IStructuredContentProvider {

	public static List<Client> clienti = new ArrayList<Client>();
	
	public ContentProviderClient () {
		
		try {
			Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			PreparedStatement takeid = connection.prepareStatement("select id from users");
			ResultSet iduri = takeid.executeQuery();
			PreparedStatement takeemail = connection.prepareStatement("select email from users");
			ResultSet emailuri = takeemail.executeQuery();
			PreparedStatement takepassword = connection.prepareStatement("select password from users");
			ResultSet parole = takepassword.executeQuery();
			PreparedStatement takeseller = connection.prepareStatement("select seller from users");
			ResultSet selleri = takeseller.executeQuery();
			
			while(iduri.next() && emailuri.next() && parole.next() && selleri.next()) {
				Client client = new Client(iduri.getInt("id"), emailuri.getString("email"), parole.getString("password"), selleri.getInt("seller"));
				clienti.add(client);
			}
			
			iduri.close();
			takeid.close();
			emailuri.close();
			takeemail.close();
			parole.close();
			takepassword.close();
			selleri.close();
			takeseller.close();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[] getElements(Object arg0) {
		return  clienti.toArray();
	}
}
