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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Entreprise;
import metier.Etudiant;
import metier.Offer;
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
	Button setCompanyBtn = new Button();
	@FXML
	Hyperlink logoutLink = new Hyperlink();

	// vérifie la visibilité des panes

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


	// Tableau offre
	
	@FXML
	TableView<Offer> offerTableView = new TableView<Offer>();
	@FXML
	TableColumn<Offer, String> idOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> intituleOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> companyOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> descriptionOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> fieldOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> debutOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> durationOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Offer, String> lvlOfferColumn = new TableColumn<Offer, String>();
	@FXML
	TableColumn<Entreprise, String> fieldColumn = new TableColumn<Entreprise, String>();

	// studentModifier

	@FXML
	Pane studentModifier = new Pane();
	@FXML
	TextField newSId = new TextField();
	@FXML
	TextField newSName = new TextField();
	@FXML
	TextField newSPasswrd = new TextField();
	@FXML
	TextField newSTel = new TextField();
	@FXML
	TextField newSMail = new TextField();
	@FXML
	TextField newFill = new TextField();
	@FXML
	TextField newLvl = new TextField();
	@FXML
	TextField newFirstName = new TextField();

	@FXML
	Button updateStudentBtn = new Button();
	@FXML
	Button annuleStudentBtn = new Button();
	@FXML
	Button newBtn = new Button();


	// companyModifier

	@FXML
	Pane companyModifier = new Pane();
	@FXML
	TextField newCId = new TextField();
	@FXML
	TextField newCName = new TextField();
	@FXML
	TextField newCPasswrd = new TextField();
	@FXML
	TextField newCTel = new TextField();
	@FXML
	TextField newCMail = new TextField();
	@FXML
	TextField newCity = new TextField();
	@FXML
	TextField newStreet = new TextField();
	@FXML
	TextField newPostale = new TextField();
	@FXML
	TextField newDomain = new TextField();

	//
	
	@FXML
	Button updateCompanyBtn = new Button();
	@FXML
	Button annuleCompanyBtn = new Button();

	
	// listes d'élements insérés dans les tables

	private final ObservableList<Etudiant> studentData =

			FXCollections.observableArrayList();

	private final ObservableList<Entreprise> companyData =

			FXCollections.observableArrayList();

	
	
	// Connection

	Connection connection = (Connection) connexion.connection();
	
	Statement st = null;

	
	// vérifie si les données ont déjà été chargé dans les tables

	boolean studentLoad = false;
	boolean userLoad = false;
	boolean companyLoad = false;
	
	
	// on se sert de ses objets uniquement pour effectuer des requêtes

	Etudiant etu = null;
	
	Entreprise ent = null;
	
	
	

	// permet de récupérer le modèle pour pouvoir récupérer ensuite l'étudiant/ l'entreprise sélectionné

	TableViewSelectionModel<Etudiant> selectStudent = studentTableView.getSelectionModel();
	
	TableViewSelectionModel<Entreprise> selectCompany = companyTableView.getSelectionModel();
	
	TableViewSelectionModel<Entreprise> selectOffer = companyTableView.getSelectionModel();


	Refresher refresher = new Refresher(selectStudent, newSId, newSName, newSPasswrd, newSTel, newSMail, newFill, newLvl);


	
	
	// permet de se déconnecter
	
	@FXML
	void disconnect(ActionEvent event) {
		dU.disconnect(event);
	}

	// fonction annule
	
	@FXML
	public void annule(){
		annuleCompanyBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				companyVisibleModifier = false;
				companyModifier.setVisible(false);
			}
			
		});
		
		annuleStudentBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				studentVisibleModifier = false;
				studentModifier.setVisible(false);
			}
			
		});
	}
		
	// créer un nouvel objet
	
	public void create(){
		createUserBtn.setOnAction(e->{
			try{
				Stage secondStage = new Stage();
				Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("View/choice.fxml"));
				secondStage.setScene(new Scene(root2));
				secondStage.setResizable(false);
				secondStage.show();
			}catch(Exception ee){
				ee.printStackTrace();
			}
		});
	}
	
	
	// affiche le pane de modication de l'Etudiant

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

	// selectionne l'étudiant de la table

	@FXML
	public Etudiant selectStudent(){
		setStudentBtn.setOnMouseClicked(new EventHandler< MouseEvent>(){

			@Override
			public void handle(MouseEvent event){
				etu = studentTableView.getSelectionModel().getSelectedItem();
				//refresher.start(etu);
				System.out.println("l'étudiant sélectionné est: ");
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

	// charge les informations de l'étudiant selectionné dans les champs de modifications

	public void  loadStudentPrevData(Etudiant e){
		//newId.setText(Integer.toString(e.getId()));
		newSId.setText(Integer.toString(e.getId()));
		newSName.setText(e.getNom());
		newSPasswrd.setText(e.getMdp());
		newSTel.setText(e.getTel());
		newSMail.setText(e.getMail());
		newFirstName.setText(e.getPrenom());
		newFill.setText(e.getFiliaire());
		newLvl.setText(Integer.toString(e.getNiveauEtude()));

	}

	// effectue la modification de l'étudiant

	public void updateStudent(ActionEvent event){

		String update = "UPDATE etudiant SET name= \"" + newSName.getText() + "\", passwrd= \"" + newSPasswrd.getText() + "\", tel= \"" + newSTel.getText() + "\", mail= \"" + newSMail.getText() + "\", firstName= \""+ newFirstName.getText() +"\", fill= \"" + newFill.getText() + "\",lvl= \"" + Integer.parseInt(newLvl.getText()) + "\" WHERE id = "+ newSId.getText() +";";

		System.out.println("-----------------------------------------"+newSName.getText());

		PreparedStatement statement = null;



		try{

			statement = connection.prepareStatement(update);

			System.out.println(update);

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

	// rempli la table d'étudiants
	
	public void showStudent(){

		showStudentBtn.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent event) {

				System.out.println("Affichage Etudiant");

				// change l'affichage des tableaux

				companyTableView.setVisible(false);
				studentTableView.setVisible(true);


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

	
	
	
	
	// selectionne l'entreprise de la table
	
	public Entreprise selectCompany(){

		setCompanyBtn.setOnMouseClicked(new EventHandler< MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ent = companyTableView.getSelectionModel().getSelectedItem();
				System.out.println(ent.getId());
				System.out.println(ent.getNom());
				System.out.println(ent.getMdp());
				System.out.println(ent.getTel());
				System.out.println(ent.getMail());
				System.out.println(ent.getVille());
				System.out.println(ent.getRue());
				System.out.println(ent.getPostale());
				System.out.println(ent.getDomaine());
				loadCompanyPrevData(ent);


			}

		});
		return ent;
	}

	// affiche le pane de modification d'entreprise
	
	@FXML
	public void showCompanyModifier(ActionEvent event){

		//refresher.start();

		if(companyVisibleModifier == false){
			companyModifier.setVisible(!companyVisibleModifier);
			companyVisibleModifier = !companyVisibleModifier;
		}else{
			companyModifier.setVisible(!companyVisibleModifier);
			companyVisibleModifier = !companyVisibleModifier;
		}

		//selectStudent();

		System.out.println(companyVisibleModifier);

		try{
			//notify();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("problème dans la pause du refresher");
		}
	}
	
	// charge les informations de l'entreprise selectionné dans les champs de modifications
	
	public void  loadCompanyPrevData(Entreprise c){
		//newId.setText(Integer.toString(e.getId()));
		newCId.setText(Integer.toString(c.getId()));
		newCName.setText(c.getNom());
		newCPasswrd.setText(c.getMdp());
		newCTel.setText(c.getTel());
		newCMail.setText(c.getMail());
		newCity.setText(c.getVille());
		newStreet.setText(c.getRue());
		newPostale.setText(Integer.toString(c.getPostale()));
		newDomain.setText(c.getDomaine());

	}


	// effectue la modification de l'entreprise
	
	public void updateCompany(ActionEvent event){

		String update = "UPDATE entreprise SET name= \"" + newCName.getText() + "\", passwrd= \"" + newCPasswrd.getText() + "\", tel= \"" + newCTel.getText() + "\", mail= \"" + newCMail.getText() + "\", ville= \""+ newCity.getText() +"\", rue= \"" + newStreet.getText() + "\",postale= \"" + Integer.parseInt(newPostale.getText()) + "\" WHERE id = "+ newCId.getText() +";";

		System.out.println("-----------------------------------------"+newSName.getText());

		PreparedStatement statement = null;



		try{

			statement = connection.prepareStatement(update);

			System.out.println(update);

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

	
	// rempli la table d'entreprises
	
	public void showCompany(){

		showCompanyBtn.setOnAction(new EventHandler<ActionEvent>(){


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