package dbObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import fis1.DBConnection;


public class ContentProviderProdus implements IStructuredContentProvider{

		public static List<Produs> produse = new ArrayList<Produs>();
		
		public ContentProviderProdus() {
			try {
				Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
				PreparedStatement takeid = connection.prepareStatement("select id from products");
				ResultSet iduri = takeid.executeQuery();
				PreparedStatement takenume = connection.prepareStatement("select nume from products");
				ResultSet nume = takenume.executeQuery();
				PreparedStatement takepret = connection.prepareStatement("select pret from products");
				ResultSet preturi = takepret.executeQuery();
				PreparedStatement takeidvanz = connection.prepareStatement("seelct id_vanzator from products");
				ResultSet idurivanz = takeidvanz.executeQuery();
				PreparedStatement takedescriere = connection.prepareStatement("select descriere from products");
				ResultSet descrieri = takedescriere.executeQuery();
				
				while(iduri.next() && nume.next() && preturi.next() && idurivanz.next() && descrieri.next()) {
					
					Produs produs = new Produs(iduri.getInt("id"), nume.getString("nume"), preturi.getFloat("pret"), idurivanz.getInt("id_vanzator"), descrieri.getString("descriere"));
					produse.add(produs);
				}
				
				iduri.close();
				takeid.close();
				nume.close();
				takenume.close();
				preturi.close();
				takepret.close();
				idurivanz.close();
				takeidvanz.close();
				descrieri.close();
				takedescriere.close();
				connection.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public Object[] getElements(Object arg0) {
			return  produse.toArray();
		}
		
}
