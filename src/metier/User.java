package metier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

	private boolean connected = false;
	private SimpleIntegerProperty id;
	private SimpleStringProperty nom;
	private SimpleStringProperty mdp;
	private SimpleStringProperty tel;
	private SimpleStringProperty mail;
	
	Connection connection = null;
	
	

	public User(int id2, String nom2, String mdp2, String tel2, String mail2){
		this.id = new SimpleIntegerProperty(id2);
		this.nom = new SimpleStringProperty(nom2);
		this.mdp = new SimpleStringProperty(mdp2);
		this.tel = new SimpleStringProperty(tel2);
		this.mail = new SimpleStringProperty(mail2);
	}

	public int getId() {
		return id.get();
	}
	
	public void setId(int i) {
		id.set(i); 
	}

	public String getNom() {
		return nom.get();
	}

	public String getTel() {
		return tel.get();
	}

	public void setTel(String t) {
		tel.set(t);
	}

	public String getMail() {
		return mail.get();
	}

	public void setMail(String m) {
		mail.set(m);
	}

	public void setNom(String n) {
		nom.set(n);
	}

	public String getMdp() {
		return mdp.get();
	}

	public void setMdp(String m) {
		mdp.set(m);
	}

	public boolean getState(){
		return connected;
	}
	public void setState(){
		connected = !connected;
	}
	
}
