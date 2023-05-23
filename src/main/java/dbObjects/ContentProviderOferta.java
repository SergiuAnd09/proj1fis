package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import fis1.DBConnection;

public class ContentProviderOferta implements IStructuredContentProvider{
	
	public static List<Oferta> oferte = new ArrayList<Oferta>();
	
	public ContentProviderOferta() {
		
		try {
			Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			PreparedStatement takeidp = connection.prepareStatement("select id_produs from offers");
			ResultSet idurip = takeidp.executeQuery();
			PreparedStatement takeidc = connection.prepareStatement("select id_cumparator from offers");
			ResultSet iduric = takeidc.executeQuery();
			PreparedStatement takepret = connection.prepareStatement("select pret from offers");
			ResultSet preturi = takepret.executeQuery();
			
			while(idurip.next() && iduric.next() && preturi.next()) {
				
				Oferta oferta = new Oferta(idurip.getInt("id_produs"), iduric.getInt("id_cumparator"), preturi.getFloat("pret"));
				oferte.add(oferta);
			}
			
			idurip.close();
			takeidp.close();
			iduric.close();
			takeidc.close();
			preturi.close();
			takepret.close();
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
