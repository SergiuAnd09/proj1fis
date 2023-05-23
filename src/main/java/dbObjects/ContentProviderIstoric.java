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
			PreparedStatement takehistories = connection.prepareStatement("select * from sellhistory");
			ResultSet tot = takehistories.executeQuery();
			istorii.clear();
			while(tot.next()) {
				IstoricVanzari istoric = new IstoricVanzari(tot.getInt("id"), tot.getString("nume"), tot.getFloat("pret"), tot.getString("email_cumparator"), tot.getString("email_vanzator"));
				istorii.add(istoric);
			}
			
			tot.close();
			takehistories.close();
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
