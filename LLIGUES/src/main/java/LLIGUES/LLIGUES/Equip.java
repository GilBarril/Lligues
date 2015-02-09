package LLIGUES.LLIGUES;

public class Equip {

	String nom;
	int puntuacio;
	int partitsguanyats;
	int partitsperduts;
	int partitsempatats;
	
    public Equip(String n){
     
    	nom = n;
    	puntuacio = 0;
    	partitsguanyats = 0;
    	partitsperduts = 0;
    	partitsempatats = 0;   	
    	
    }

	@Override
	public String toString() {
		return this.nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int p) {
		this.puntuacio = puntuacio + p;
	}

	public int getPartitsguanyats() {
		return partitsguanyats;
	}

	public void setPartitsguanyats(int par) {
		this.partitsguanyats = partitsguanyats + par;
	}

	public int getPartitsperduts() {
		return partitsperduts;
	}

	public void setPartitsperduts(int par) {
		this.partitsperduts = partitsperduts + par;
	}

	public int getPartitsempatats() {
		return partitsempatats;
	}

	public void setPartitsempatats(int par) {
		this.partitsempatats = partitsempatats + par;
	}

}
