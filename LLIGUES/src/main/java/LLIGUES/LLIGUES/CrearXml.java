package LLIGUES.LLIGUES;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class CrearXml {
	private JPanel contentPane;
	private int contador=1;
	CrearXml(){
	
		
	
	
	}
	
	public void guardardades(Lliga lligaaguardar){
	
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(lligaaguardar.nom);
		doc.appendChild(rootElement);
 
		Iterator it = lligaaguardar.getMapequips().entrySet().iterator();
		while(it.hasNext()){
      	  Map.Entry eq =  (Entry) it.next();
      	  Equip rows = (Equip) eq.getValue();
	
		// set attribute to staff element
    	   
        Element staff = doc.createElement("Equip");
   		rootElement.appendChild(staff);   
    	   
		Attr attr = doc.createAttribute("id");
		attr.setValue(""+contador);
		staff.setAttributeNode(attr);
		
		
		Element name = doc.createElement("Nom_Equip");
		Attr attr1 = doc.createAttribute("nom");
		attr1.setValue(rows.getNom());
		name.setAttributeNode(attr1);
		staff.appendChild(name);
 
		
		Element punts = doc.createElement("Punts");
		Attr attr2 = doc.createAttribute("punts");
		attr2.setValue(""+rows.getPuntuacio());
		punts.setAttributeNode(attr2);
		name.appendChild(punts);
 
		
		Element guanyats = doc.createElement("Partits_Guanyats");
		Attr attr3 = doc.createAttribute("partits_guanyats");
		attr3.setValue(""+rows.getPartitsguanyats());
		guanyats.setAttributeNode(attr3);
		name.appendChild(guanyats);
 
		
		Element empatats = doc.createElement("Partits_Empatats");
		Attr attr4 = doc.createAttribute("partits_empatats");
		attr4.setValue(""+rows.getPartitsempatats());
		empatats.setAttributeNode(attr4);
		name.appendChild(empatats);
		
		
		Element perduts = doc.createElement("Partits_Perduts");
		Attr attr5 = doc.createAttribute("partits_perduts");
		attr5.setValue(""+rows.getPartitsperduts());
		perduts.setAttributeNode(attr5);
		name.appendChild(perduts);
		
		contador++;
       }
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(lligaaguardar.getNom()+".xml"));
 
		
 
		transformer.transform(source, result);
 
		
		JOptionPane.showMessageDialog(contentPane,
				"lliga "+lligaaguardar.getNom()+" "+"guardada.",
				"Incorrecte",
				JOptionPane.INFORMATION_MESSAGE);
		
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
