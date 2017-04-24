package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import metier.connexion;

public class CreateStudentControler {
	@FXML
	TextField nameField;
	@FXML
	TextField passwrdField;
	@FXML
	TextField telField;
	@FXML
	TextField mailField;
	@FXML
	TextField firstNameField;
	@FXML
	TextField fillField;
	@FXML
	TextField lvlField;

	@FXML
	Hyperlink returnBtn = new Hyperlink();
	@FXML
	Button creeBtn =new Button();
	@FXML
	Button annuleBtn = new Button();
	
	@FXML
	ImageView  students;
	Connection connection;

	@SuppressWarnings("null")
	public void ajout(){
		creeBtn.setOnAction(e->{
			Alert dialogC = new Alert(AlertType.CONFIRMATION);
			dialogC.setTitle("A confirmation ");
			dialogC.setHeaderText(null);
			dialogC.setContentText("confirmation");
			Optional<ButtonType> answer = dialogC.showAndWait();
			if (answer.get() == ButtonType.OK) {	
				PreparedStatement st =null;
				connection = (Connection) connexion.connection();

				try {

					Class.forName("com.mysql.jdbc.Driver");
					System.out.print("ok");
				}catch (Exception e1) {
					System.out.println("no");
				}

				try {
					String sql =" INSERT INTO etudiant"
							+ " (`id`, `name`, `passwrd`, `tel`, `mail`, `firstName`, `fill`, `lvl`)"
							+ " VALUES ("
							+ "NULL,'"
							+ nameField.getText() +"','" 
							+ passwrdField.getText() +"','"
							+ telField.getText() +"','" 
							+ mailField.getText() +"','" 
							+ firstNameField.getText() +"','" 
							+ fillField.getText() +"','" 
							+ lvlField.getText() +"')";

					st = (PreparedStatement)connection.prepareStatement(sql);
					st.executeUpdate();	 
				} catch (Exception e1) {

					e1.printStackTrace();
				}finally{}


			}
			else {
				nameField.setText("");
				passwrdField.setText("");
				telField.setText("");
				mailField.setText("");
				firstNameField.setText("");
				fillField.setText("");
				lvlField.setText("");

			}

		});


	}

	public void annuler(){


		annuleBtn.setOnAction(e->{
			nameField.setText("");
			nameField.setText("");
			passwrdField.setText("");
			telField.setText("");
			mailField.setText("");
			firstNameField.setText("");
			fillField.setText("");
			lvlField.setText("");

		});


	}
	
	public void retourner(){
		returnBtn.setOnAction(e->{
			try{
				System.out.println("retour à l'acceuil");
				Parent homeParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/login.fxml"));
				Scene homeScene = new Scene(homeParent);
				Stage homeStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				homeStage.hide();
				homeStage.setScene(homeScene);
				homeStage.show();
			}catch(Exception ee){
				ee.printStackTrace();
			}
		});
	}

}
