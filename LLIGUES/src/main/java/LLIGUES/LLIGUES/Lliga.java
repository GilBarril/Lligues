package LLIGUES.LLIGUES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Lliga {

	String nom;
	Map<String, Equip> Mapequips = new HashMap<String,Equip>();
	
	public Lliga(String nomlliga) {
		nom = nomlliga;
	}

	public Map<String, Equip> getMapequips() {
		return Mapequips;
	}

	public void setMapequips(Map<String, Equip> mapequips) {
		Mapequips = mapequips;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	

	
	
	
}
