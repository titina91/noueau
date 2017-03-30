package metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Offer {

	private SimpleIntegerProperty id;
	private SimpleStringProperty intitule;
	private SimpleStringProperty entreprise;
	private SimpleStringProperty description;
	private SimpleStringProperty domaine;
	private SimpleStringProperty debut;
	private SimpleIntegerProperty duree;
	private SimpleIntegerProperty lvl;
	private SimpleIntegerProperty place;
	private SimpleIntegerProperty etudiant;

	public Offer(int i, String e, String inti, String desc, String dom, String deb, int dur, int l, int p, int etu){
		this.id = new SimpleIntegerProperty(i);
		this.intitule = new SimpleStringProperty(inti);
		this.entreprise = new SimpleStringProperty(e);
		this.description = new SimpleStringProperty(desc);
		this.domaine = new SimpleStringProperty(dom);
		this.debut = new SimpleStringProperty(deb);
		this.duree = new SimpleIntegerProperty(dur);
		this.lvl = new SimpleIntegerProperty(l);
		this.place = new SimpleIntegerProperty(p);
		this.etudiant = new SimpleIntegerProperty(etu);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int i) {
		id.set(i);
	}

	public String getIntitule() {
		return intitule.get();
	}

	public void setIntitule(String i) {
		intitule.set(i);
	}

	public String getEntreprise() {
		return entreprise.get();
	}

	public void setEntreprise(String e) {
		entreprise.set(e);
	}

	public String getDomaine() {
		return domaine.get();
	}

	public void setDomaine(String d) {
		domaine.set(d);
	}

	public int getLvl() {
		return lvl.get();
	}

	public void setLvl(int n) {
		lvl.set(n);
	}
	
	public String getDescription() {
		return description.get();
	}

	public void setDescription(String d) {
		description.set(d);
	}

	public String getDebut() {
		return debut.get();
	}

	public void setDebut(String d) {
		debut.set(d);
	}

	public int getDuree() {
		return duree.get();
	}

	public void setDuree(int d) {
		duree.set(d);
	}

	public int getPlace() {
		return place.get();
	}

	public void setPlace(int p) {
		place.set(p);
	}
	
	public int getEtudiant(){
		return etudiant.get();
	}
	
	public void setEtudiant(int e){
		etudiant.set(e);
	}
	
}
