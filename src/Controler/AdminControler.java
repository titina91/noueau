package Controler;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import metier.Entreprise;
import metier.Etudiant;
import metier.Refresher;
import metier.connexion;
import metier.daoUser;

public class AdminControler /* implements Initializable */{


	daoUser dU = new daoUser();

	// Bouttons d'affichage

	@FXML
	Button createUserBtn = new Button();
	@FXML
	Button showUserBtn = new Button();
	@FXML
	Button showStudentBtn = new Button();
	@FXML
	Button showCompanyBtn = new Button();
	@FXML
	Button showOfferBtn = new Button();
	@FXML
	Button showDemandBtn = new Button();

	// bouttons de modification

	@FXML
	Button setStudentBtn = new Button();
	@FXML
	Button setCompany = new Button();
	@FXML
	Hyperlink logoutLink = new Hyperlink();

	boolean studentVisibleModifier = false;
	boolean companyVisibleModifier = false;

	//ArrayList<TableView> tables = new ArrayList<TableView>();

	// Tableau Etudiant

	@FXML
	TableView<Etudiant> studentTableView = new TableView<Etudiant>();
	@FXML
	TableColumn<Etudiant, String> idStudentColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> nameStudentColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> passwrdStudentColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> telStudentColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> mailStudentColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> firstNameColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> fillColumn = new TableColumn<Etudiant, String>();
	@FXML
	TableColumn<Etudiant, String> lvlColumn = new TableColumn<Etudiant, String>();


	// Tableau entreprise

	@FXML
	TableView<Entreprise> companyTableView = new TableView<Entreprise>();
	@FXML
	TableColumn<Entreprise, String> idCompanyColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> nameCompanyColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> passwrdCompanyColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> telCompanyColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> mailCompanyColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> cityColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> streetColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> postaleColumn = new TableColumn<Entreprise, String>();
	@FXML
	TableColumn<Entreprise, String> fieldColumn = new TableColumn<Entreprise, String>();


	// StudentModifier

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
	@FXML
	Button updateStudentButton = new Button();
	@FXML
	Button annuleStudentButton = new Button();


	@FXML
	Button newBtn = new Button();



	private final ObservableList<Etudiant> studentData =
			FXCollections.observableArrayList(
					/*new Etudiant(10,"nom5", "mdp5", "tel5", "mail5", "p5", "f", 5)*/);


	/*ObservableList<Etudiant> data = FXCollections.observableArrayList(Etudiant.getResellers());
	reseller_table.setItems(data);*/

	private final ObservableList<Entreprise> companyData =
			FXCollections.observableArrayList(
					new Entreprise(10, "nom", "mdp", "tel", "mail", "ville", "rue", 91159, "domaine"));

	// 


	// Connection

	Connection connection = (Connection) connexion.connection();
	Statement st = null;

	// vérifie si les données ont déjà été chargé dans les tables

	boolean studentLoad = false;
	boolean userLoad = false;
	boolean companyLoad = false;

	Etudiant etu = null;

	TableViewSelectionModel<Etudiant> selectStudent = studentTableView.getSelectionModel();

	Refresher refresher = new Refresher(selectStudent, newId, newName, newPasswrd, newTel, newMail, newFill, newLvl);
	/*
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		while(true){
			etu = selectStudent();
		}
	}
	 */

	// 

	// permet de se déconnecter

	@FXML
	void disconnect(ActionEvent event) {
		dU.disconnect(event);
	}


	// gère l'affichage du panel de modication de l'Etudiant

	@FXML
	public void showStudentModifier(ActionEvent event){
		//refresher.start();
		if(studentVisibleModifier == false){
			studentModifier.setVisible(!companyVisibleModifier);
			studentVisibleModifier = !studentVisibleModifier;
		}else{
			studentModifier.setVisible(companyVisibleModifier);
			studentVisibleModifier = !studentVisibleModifier;
		}
		//selectStudent();
		System.out.println(studentVisibleModifier);

		try{
			//notify();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("problème dans la pause du refresher");
		}
	}

	// permet de selectionner l'objet étudiant de la table correspondant

	@FXML
	public Etudiant selectStudent(){
		setStudentBtn.setOnMouseClicked(new EventHandler< MouseEvent>(){

			@Override
			public void handle(MouseEvent event){
				etu = studentTableView.getSelectionModel().getSelectedItem();
				//refresher.start(etu);
				System.out.println(etu.getId());
				System.out.println(etu.getNom());
				System.out.println(etu.getMdp());
				System.out.println(etu.getTel());
				System.out.println(etu.getMail());
				System.out.println(etu.getPrenom());
				System.out.println(etu.getFiliaire());
				System.out.println(etu.getNiveauEtude());
				loadStudentPrevData(etu);

			}

		});
		return etu;
	}

	public void  loadStudentPrevData(Etudiant e){
		//newId.setText(Integer.toString(e.getId()));
		newId.setText(Integer.toString(e.getId()));
		newName.setText(e.getNom());
		newPasswrd.setText(e.getMdp());
		newTel.setText(e.getTel());
		newMail.setText(e.getMail());
		newFirstName.setText(e.getPrenom());
		newFill.setText(e.getFiliaire());
		newLvl.setText(Integer.toString(e.getNiveauEtude()));
	}


	// initialise les données dans les champs du modifier





	// effectue les modification en BD

	public void updateStudent(ActionEvent event){
		String update = "UPDATE etudiant SET name= \"" + newName.getText() + "\", passwrd= \"" + newPasswrd.getText() + "\", tel= \"" + newTel.getText() + "\", mail= \"" + newMail.getText() + "\", firstName= \""+ newFirstName.getText() +"\", fill= \"" + newFill.getText() + "\",lvl= \"" + Integer.parseInt(newLvl.getText()) + "\" WHERE id = "+ newId.getText() +";";
		System.out.println("-----------------------------------------"+newName.getText());
		System.out.println(update);
		/*
		int id = Integer.parseInt(newId.getText());
		String nom = newName.getText();
		String mdp = newPasswrd.getText();
		String tel = newTel.getText();
		String mail = newMail.getText();
		String fname = newFirstName.getText();
		String fill = newFill.getText();
		int lvl = Integer.parseInt(newLvl.getText());
		
		String update = "UPDATE `etudiant` SET `name`=" + nom + 
				",`passwrd`= " + mdp + 
				", `tel`= " + tel + 
				", `mail`=  " + mail + 
				", `firstName`=  " + fname + 
				", `fill`=  " + fill + 
				",`lvl`=  " + lvl + 
				" WHERE 22";
		*/
		PreparedStatement statement = null;

		try{
			/*
			statement = connection.prepareStatement(update);
			statement.setString(1, newName.getText());
			statement.setString(2, newPasswrd.getText());
			statement.setString(3, newTel.getText());
			statement.setString(4, newMail.getText());
			statement.setString(5, newFirstName.getText());
			statement.setString(6, newFill.getText());
			statement.setInt(7, Integer.parseInt(newLvl.getText()));
			//statement.setInt(8, etu.getId());
			 * 
			 */
			statement = connection.prepareStatement(update);
			statement.executeUpdate(update);
			System.out.println("update effectué");


		}catch(SQLException e){
			e.printStackTrace();
		}
		studentTableView.getItems().clear();
		studentTableView.setItems(studentData);
		showStudent();
		studentModifier.setVisible(false);

	}


	// Comportements provoqués par les boutons

	public void showStudent(){
		showStudentBtn.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent event) {
				System.out.println("Affichage Etudiant");

				// change l'affichage des tableaux

				companyTableView.setVisible(false);
				studentTableView.setVisible(true);

				//

				String loadStudent = "SELECT * FROM `etudiant`";
				studentTableView.setEditable(true);
				try{
					st = (Statement) connection.createStatement();
					ResultSet rs = st.executeQuery(loadStudent);
					studentTableView.getItems().clear();
					studentTableView.setItems(studentData);
					while(rs.next()){
						//Etudiant(int id,String nom, String mdp, String tel, String mail, String p, String f, int lvl)
						studentData.add(new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)));

						System.out.println(rs.getInt(1));
						System.out.println(rs.getString(2));
						System.out.println(rs.getString(3));
						System.out.println(rs.getString(4));
						System.out.println(rs.getString(5));
						System.out.println(rs.getString(6));
						System.out.println(rs.getString(7));
						System.out.println(rs.getInt(8));

					}
					//studentTableView.setItems(studentData);

				}catch(SQLException e){
					e.printStackTrace();
				}
				//studentTableView.getItems().addAll(etudiants);

				// les parametres correspondent aux noms de variables de l'objet

				idStudentColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("id"));
				nameStudentColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
				passwrdStudentColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("mdp"));
				telStudentColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("tel"));
				mailStudentColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("mail"));
				firstNameColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
				fillColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("filiaire"));
				lvlColumn.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("niveauEtude"));

				studentTableView.setItems(null);
				studentTableView.setItems(studentData);
				//FireTableChanged(null);

			}

		});
	}

	public void showCompany(){
		showCompanyBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				System.out.println("Affichage Entreprise");

				// change l'affichage des tableaux
				studentModifier.setVisible(false);
				companyTableView.setVisible(true);
				studentTableView.setVisible(false);

				//
				String loadCompany = "SELECT * FROM `entreprise`";
				companyTableView.setEditable(true);
				try{
					st = (Statement) connection.createStatement();
					ResultSet rs = st.executeQuery(loadCompany);
					companyTableView.setItems(companyData);
					companyTableView.getItems().clear();
					while(rs.next()){
						//Entreprise( int id, String nom, String mdp, String t, String m, String v, String r, int p,  String d)
						companyData.add(new Entreprise(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8), rs.getString(9)));

						System.out.println(rs.getInt(1));
						System.out.println(rs.getString(2));
						System.out.println(rs.getString(3));
						System.out.println(rs.getString(4));
						System.out.println(rs.getString(5));
						System.out.println(rs.getString(6));
						System.out.println(rs.getString(7));
						System.out.println(rs.getInt(8));
						System.out.println(rs.getString(9));

					}
					//studentTableView.setItems(studentData);

				}catch(SQLException e){
					e.printStackTrace();
				}
				//studentTableView.getItems().addAll(etudiants);

				// les parametres correspondent aux noms de variables de l'objet

				idCompanyColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("id"));
				nameCompanyColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("nom"));
				passwrdCompanyColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("mdp"));
				telCompanyColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("tel"));
				mailCompanyColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("mail"));
				cityColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("ville"));
				streetColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("rue"));
				postaleColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("postale"));
				fieldColumn.setCellValueFactory(new PropertyValueFactory<Entreprise,String>("domaine"));

				companyTableView.setItems(null);
				companyTableView.setItems(companyData);

			}

		});


	}




}
