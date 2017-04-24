package metier;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Entreprise  extends User implements implementsEntreprise{
	
	private SimpleStringProperty ville;
	private SimpleStringProperty rue;
	private SimpleIntegerProperty postale;
	private SimpleStringProperty domaine;
	daoEntreprise dao = new daoEntreprise();

	public Entreprise( int id, String nom, String mdp, String tel, String mail, String v, String r, int p,  String d){
		super(id, nom, mdp, tel, mail);
		this.ville = new SimpleStringProperty(v);
		this.rue = new SimpleStringProperty(r);
		this.postale = new SimpleIntegerProperty(p);
		this.domaine = new SimpleStringProperty(d);
	}

	public String getVille() {
		return ville.get();
	}

	public void setVille(String v) {
		ville.set(v);;
	}

	public String getRue() {
		return rue.get();
	}

	public void setRue(String r) {
		rue.set(r);
	}

	public int getPostale() {
		return postale.get();
	}

	public void setPostale(int p) {
		postale.set(p);
	}

	public String getDomaine() {
		return domaine.get();
	}

	public void setDomaine(String d) {
		domaine.set(d);
	}

	public void deposerOffre(Stage s) {
		dao.deposerOffre(s);
	}

	public void supprimerOffre(int id) {
		dao.supprimerOffre(id);
	}

	public void search(ArrayList<String> list) {
		dao.search(list);
	}

	public void connect(int i, String passwrd) {
		dao.connect(i, passwrd);
		setState();
	}

	
}
