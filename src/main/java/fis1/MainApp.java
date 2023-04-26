package fis1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainApp {
	
	public static void scriere(List<Cumparator> lista) throws ParserConfigurationException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        Element rootElement = doc.createElement("lista");
        doc.appendChild(rootElement);
        
        for(Cumparator cumparator:lista) {
        	
        	Element cumparatorElement = doc.createElement("cumparator");
            rootElement.appendChild(cumparatorElement);
            
            Element numeElement = doc.createElement("nume");
            numeElement.appendChild(doc.createTextNode(cumparator.getNume()));
            cumparatorElement.appendChild(numeElement);
            
            Element statusElement = doc.createElement("status");
            statusElement.appendChild(doc.createTextNode(cumparator.getStatus().name().toLowerCase()));
            cumparatorElement.appendChild(statusElement);
        }
        
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/vanzatori.xml"));
            transformer.transform(source, result);
            System.out.println("XML file saved successfully");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
	}
	
	public static List<Cumparator> citire() throws IOException, SAXException, ParserConfigurationException{
		
		
			
			File xmlFile = new File("src/main/resources/vanzatori.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            Element rootElement = doc.getDocumentElement();
            
            NodeList bookList = rootElement.getElementsByTagName("cumparator");
            List<Cumparator> cumps = new ArrayList<Cumparator>();
            for (int i = 0; i < bookList.getLength(); i++) {
                Element bookElement = (Element) bookList.item(i);
 
                String nume = bookElement.getElementsByTagName("nume").item(0).getTextContent();
                String status = bookElement.getElementsByTagName("status").item(0).getTextContent();

                Cumparator cump = new Cumparator(nume, Status.valueOf(status.toUpperCase()));
                cumps.add(cump);
            }
            return cumps;
	}
	
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		
		List<Cumparator> lista = new ArrayList<Cumparator>();
		lista = citire();
		System.out.println(lista);
		Cumparator c = new Cumparator("gicu", Status.ANGAJAT);
		lista.add(c);
		scriere(lista);
		System.out.println(lista);
		
		Client client = new Client("aaaaaaaaaaa", "ze");
		int p = client.getId();
		System.out.println(p);

		

	}

}
