package metier;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Etudiant extends User {
	private SimpleStringProperty prenom;
	private SimpleStringProperty filiaire;
	private SimpleIntegerProperty niveauEtude;
	private ArrayList<Offer> offres = new ArrayList<Offer>();
	
	
	public Etudiant(int id,String nom, String mdp, String tel, String mail, String p, String f, int lvl){
		super(id, nom, mdp, tel, mail);
		this.prenom = new SimpleStringProperty(p);
		this.filiaire = new SimpleStringProperty(f);
		this.niveauEtude = new SimpleIntegerProperty(lvl);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String p) {
		prenom.set(p);
	}

	public String getFiliaire() {
		return filiaire.get();
	}

	public void setFiliaire(String fil) {
		filiaire.set(fil);
	}

	public int getNiveauEtude() {
		return niveauEtude.get();
	}

	public void setNiveauEtude(int lvl) {
		niveauEtude.set(lvl);;
	}
	
	public ArrayList<Offer> getOffers(){
		return this.offres;
	}
}
