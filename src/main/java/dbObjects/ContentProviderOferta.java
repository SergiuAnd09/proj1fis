package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import fis1.DBConnection;
import fis1.LoginMenu;

public class ContentProviderOferta implements IStructuredContentProvider{
	
	public static List<Oferta> oferte = new ArrayList<Oferta>();
	
	public ContentProviderOferta() {
		
		try {
			Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			PreparedStatement takeoffers = connection.prepareStatement("select * from offers WHERE email_vanzator = ? ORDER BY name ASC, pret DESC");
			takeoffers.setString(1, LoginMenu.email);
			ResultSet tot = takeoffers.executeQuery();
			oferte.clear();
			while(tot.next()) {
				
				Oferta oferta = new Oferta(tot.getInt("id_produs"), tot.getString("email"), tot.getFloat("pret"), tot.getString("email_vanzator"), tot.getString("name"));
				oferte.add(oferta);
			}
			
			tot.close();
			takeoffers.close();
			connection.close();
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public Object[] getElements(Object arg0) {
		return  oferte.toArray();
	}
}
