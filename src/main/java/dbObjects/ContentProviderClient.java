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
			PreparedStatement takeusers = connection.prepareStatement("select * from users");
			ResultSet tot = takeusers.executeQuery();
			clienti.clear();
			
			while(tot.next()) {
				Client client = new Client(tot.getString("email"), tot.getString("password"), tot.getInt("seller"));
				clienti.add(client);
			}
			
			tot.close();
			takeusers.close();
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
