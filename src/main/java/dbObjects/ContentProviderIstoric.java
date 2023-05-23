package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import fis1.DBConnection;

public class ContentProviderIstoric implements IStructuredContentProvider{
	
	public static List<IstoricVanzari> istorii = new ArrayList<IstoricVanzari>();
	
	public ContentProviderIstoric() throws SQLException {
		
		try {
			Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
			PreparedStatement takeidp = connection.prepareStatement("select id_produs from sellhistory");
			ResultSet idurip = takeidp.executeQuery();
			PreparedStatement takepret = connection.prepareStatement("select pret from sellhistory");
			ResultSet preturi = takepret.executeQuery();
			PreparedStatement takeidc = connection.prepareStatement("select id_cumparator from sellhistory");
			ResultSet iduric = takeidc.executeQuery();
			
			while(idurip.next() && preturi.next() && iduric.next()) {
				IstoricVanzari istoric = new IstoricVanzari(idurip.getInt("id_produs"), preturi.getFloat("pret"), iduric.getInt("id_cumparator"));
				istorii.add(istoric);
			}
			
			idurip.close();
			takeidp.close();
			preturi.close();
			takepret.close();
			iduric.close();
			takeidc.close();
			connection.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public Object[] getElements(Object arg0) {
		return  istorii.toArray();
	}
}
