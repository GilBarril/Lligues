package LLIGUES.LLIGUES;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Llegirxml extends DefaultHandler {
	ArrayList<Attributes> resultats = new ArrayList<Attributes>();
	int comparador = 0;
	String score = "";
	Equip equip;
	int contaetiqueta = 0;
	private Lliga lligueta = new Lliga(" ");
	Map<String, Equip> Mapequips = new HashMap<String, Equip>();
	private JTable table = new JTable(new DefaultTableModel(new Object[] {
			"Nom Equip", "Punts", "P G", "P P", "P E" }, 0));
	DefaultTableModel model3 = (DefaultTableModel) table.getModel();

	public void endDocument() throws SAXException {
		lligueta.setMapequips(Mapequips);
		Iterator it = lligueta.getMapequips().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry eq = (Entry) it.next();
			Equip rows = (Equip) eq.getValue();
			model3.addRow(new Object[] { rows.nom, rows.puntuacio,
					rows.partitsguanyats, rows.partitsempatats,
					rows.partitsperduts });
		}

		table.setModel(model3);

	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		if (contaetiqueta == 0) {
			lligueta.setNom(qName);
			contaetiqueta++;
		}

		if (qName.equals("Nom_Equip")) {

			String nom = attributes.getValue("nom");

			equip = new Equip(nom);
		}
		if (equip != null) {

			if (qName.equals("Punts")) {

				int punts = Integer.parseInt(attributes.getValue("punts"));

				equip.setPuntuacio(punts);

			}

			if (qName.equals("Partits_Guanyats")) {
				int pg = Integer.parseInt(attributes
						.getValue("partits_guanyats"));
				equip.setPartitsguanyats(pg);
			}
			if (qName.equals("Partits_Empatats")) {
				int pe = Integer.parseInt(attributes
						.getValue("partits_empatats"));
				equip.setPartitsempatats(pe);
			}
			if (qName.equals("Partits_Perduts")) {
				int pp = Integer.parseInt(attributes
						.getValue("partits_perduts"));
				equip.setPartitsperduts(pp);
			}
			Mapequips.put(equip.getNom(), equip);

		}
	}

	public Lliga getLligueta() {
		return lligueta;
	}

	public void setLligueta(Lliga lligueta) {
		this.lligueta = lligueta;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
