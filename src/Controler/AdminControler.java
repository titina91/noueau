package Controler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Entreprise;
import metier.Etudiant;
import metier.connexion;
import metier.daoUser;

public class AdminControler {

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
	
	
	
	
	
	
	private final ObservableList<Etudiant> studentData =
			FXCollections.observableArrayList(
					new Etudiant(10,"nom5", "mdp5", "tel5", "mail5", "p5", "f", 5));
	
	private final ObservableList<Entreprise> companyData =
			FXCollections.observableArrayList(
					new Entreprise(10, "nom", "mdp", "tel", "mail", "ville", "rue", 91159, "domaine"));
	
	// 
	
	Etudiant etu = null;
	
	@FXML
    void disconnect(ActionEvent event) {
		dU.disconnect(event);
    }

	public void setStudent(){
		setStudentBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				etu = studentTableView.getSelectionModel().getSelectedItem();
				System.out.println(etu.getId());
			}
			
		});
	}
	
	// Connection
	
	Connection connection = (Connection) connexion.connection();
	Statement st = null;
	
	// vérifie si les données ont déjà été chargé dans les tables

	boolean studentLoad = false;
	boolean userLoad = false;
	boolean companyLoad = false;
	
	
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
					studentTableView.setItems(studentData);
					studentTableView.getItems().clear();
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
			}

		});
	}
	
	public void showCompany(){
		showCompanyBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				System.out.println("Affichage Entreprise");
				
				// change l'affichage des tableaux
				
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
