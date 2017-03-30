package Controler;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import metier.connexion;

public class CreateCompanyControler {


	@FXML
	TextField nameField;
	@FXML
	PasswordField passwrdField; 
	@FXML
	TextField telField;
	@FXML
	TextField mailField;
	@FXML
	TextField cityField;
	@FXML
	TextField streetField;
	@FXML
	TextField postalField;
	@FXML
	TextField domaineField;


	@FXML
	Button btnajouter=new Button();
	@FXML
	Button btnannuler = new Button();


	@FXML
	ImageView  entreprise;

	Connection connection = (Connection) connexion.connection();



	public void add(){
		btnajouter.setOnAction(e->{
			Alert dialogC = new Alert(AlertType.CONFIRMATION);
			dialogC.setTitle("A confirmation ");
			dialogC.setHeaderText(null);
			dialogC.setContentText("Ajouter entreprise ?");
			Optional<ButtonType> answer = dialogC.showAndWait();
			if (answer.get() == ButtonType.OK) {

				PreparedStatement st =null;

				try {
					String sql = "INSERT INTO `entreprise`( `name`, `passwrd`, `tel`, `mail`, `ville`, `rue`, `postale`, `domaine`) VALUES (?,?,?,?,?,?,?,?)";
					st = (PreparedStatement)connection.prepareStatement(sql);	
					st.setString(1,nameField.getText());
					st.setString(2,passwrdField.getText());
					st.setString(3,telField.getText());
					st.setString(4,mailField.getText());
					st.setString(5,cityField.getText());
					st.setString(6,streetField.getText());
					st.setString(7,postalField.getText());
					st.setString(8,domaineField.getText());
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
				cityField.setText("");
				streetField.setText("");
				postalField.setText("");
				domaineField.setText("");

			}
		});
	}

	public void annuler(){

		btnannuler.setOnAction(e->{

			nameField.setText("");
			passwrdField.setText("");
			telField.setText("");
			mailField.setText("");
			cityField.setText("");
			streetField.setText("");
			postalField.setText("");
			domaineField.setText("");
		});}
}
