package metier;

import java.applet.Applet;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Pane;

public class Refresher extends Thread {

	private Etudiant etudiant = null;
	private TableViewSelectionModel<Etudiant> selectedEtudiant;
	@FXML
	Pane studentModifier = new Pane();
	@FXML
	TextField newId = new TextField();
	@FXML
	TextField newName = new TextField();
	@FXML
	TextField newPasswrd = new TextField();
	@FXML
	TextField newTel = new TextField();
	@FXML
	TextField newMail = new TextField();
	@FXML
	TextField newFill = new TextField();
	@FXML
	TextField newLvl = new TextField();
	@FXML
	TextField newFirstName = new TextField();

	private Thread refresher = null;

	public Refresher(TableViewSelectionModel e, TextField id, TextField name, TextField mdp, TextField tel, TextField mail, TextField fill, TextField lvl){

		this.selectedEtudiant = e;

	}

	public void start(Etudiant etu){
		if(refresher == null){
			etudiant = etu;
			refresher = new Thread(this, "String");
			myRun(etu);
		}
	}



	public void myRun(Etudiant e){
		int i=0;
		while(true){
			try{
				System.out.println("le nom est: " + e.getNom());
				loadStudentPrevData(e);
				wait();
			}catch(Exception ex){
				ex.printStackTrace();

				//newId.setText(Integer.toString(etudiant.getId()));

				//System.out.println(selectedEtudiant.getSelectedItems().get(1));
				//System.out.println(selectedEtudiant.getSelectionMode().g);
				//String nom = selectedEtudiant.getSelectedItem().getNom();

				//newName.setText(etudiant.getNom());
				/*
			newPasswrd.setText(etudiant.getMdp());
			newTel.setText(etudiant.getTel());
			newMail.setText(etudiant.getMail());
			newFirstName.setText(etudiant.getPrenom());
			newFill.setText(etudiant.getFiliaire());
			newLvl.setText(Integer.toString(etudiant.getNiveauEtude()));
				 */

			}
		}
	}
	
	public void  loadStudentPrevData(Etudiant e){
		//newId.setText(Integer.toString(e.getId()));
		//newId.setV
		newName.setText(e.getNom());
		newPasswrd.setText(e.getMdp());
		newTel.setText(e.getTel());
		newMail.setText(e.getMail());
		newFirstName.setText(e.getPrenom());
		newFill.setText(e.getFiliaire());
		newLvl.setText(Integer.toString(e.getNiveauEtude()));
	}
}
