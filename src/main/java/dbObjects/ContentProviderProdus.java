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
		
		public ContentProviderProdus(String email) {
			try {
				Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
				PreparedStatement takeproducts = connection.prepareStatement("SELECT * FROM products WHERE email= ?");
				takeproducts.setString(1, email);
				ResultSet tot = takeproducts.executeQuery();
				produse.clear();
				while(tot.next()) {
					 Produs produs = new Produs(tot.getInt("id"), tot.getString("nume"),tot.getFloat("pret"), tot.getString("email"),tot.getString("descriere"), tot.getFloat("pret_minim"));
					 produse.add(produs);
				}
				
				tot.close();
				takeproducts.close();
				connection.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		public ContentProviderProdus() {
			try {
				Connection connection = DriverManager.getConnection(DBConnection.url, DBConnection.username, DBConnection.password);
				PreparedStatement takeproducts = connection.prepareStatement("select * from products");
				ResultSet tot = takeproducts.executeQuery();
				produse.clear();
				
				while(tot.next()) {
					 Produs produs = new Produs(tot.getInt("id"), tot.getString("nume"),tot.getFloat("pret"), tot.getString("email"),tot.getString("descriere"),tot.getFloat("pret_minim"));
					 produse.add(produs);
				}
				
				tot.close();
				takeproducts.close();
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
