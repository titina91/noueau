package Controler;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import metier.connexion;

public class CreateCompanyControler {
	@FXML
	TextField nameField;
	@FXML
	TextField passwrdField;
	@FXML
	TextField telField;
	@FXML
	TextField mailField;
	@FXML
	TextField cityField;
	@FXML
	TextField streetField;
	@FXML
	TextField postaleField;
	@FXML
	TextField domainField;
	@FXML
	Button btnajouter=new Button();
	@FXML
	Button btnannuler = new Button();
	@FXML
	Hyperlink returnBtn = new Hyperlink();
	@FXML
	ImageView  entreprise;
	
	Connection connection = connexion.connection();




	//@SuppressWarnings("null")
	public void ajout(){
		btnajouter.setOnAction(e->{
			Alert dialogC = new Alert(AlertType.CONFIRMATION);
			dialogC.setTitle("A confirmation ");
			dialogC.setHeaderText(null);
			dialogC.setContentText("Ajouter entreprise ?");
			Optional<ButtonType> answer = dialogC.showAndWait();
			
			if (answer.get() == ButtonType.OK) {

				PreparedStatement st =null;
				
				try {
					
					String sql =
							" INSERT INTO entreprise "
									+ "(`id`, `name`, `passwrd`, `tel`, `mail`, `ville`, `rue`, `postale`, `domaine`) "
									+ "VALUES (NULL,'"
									+ nameField.getText() +"','"
									+ passwrdField.getText() +"','" 
									+ telField.getText() +"','" 
									+ mailField.getText() +"','" 
									+ cityField.getText() +"','" 
									+ streetField.getText() +"','" 
									+ postaleField.getText() +"','" 
									+ domainField.getText() +"')";
	
					st = (PreparedStatement)connection.prepareStatement(sql);	
					System.out.println(sql);
					st.executeUpdate();
					
					System.out.println(nameField.getText());
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			}

			else {
				nameField.setText("");
				passwrdField.setText("");
				telField.setText("");
				mailField.setText("");
				cityField.setText("");
				streetField.setText("");
				postaleField.setText("");
				domainField.setText("");
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
			postaleField.setText("");
			domainField.setText("");

		});}
	
	public void retourner(){
		returnBtn.setOnAction(e->{
			try{
				System.out.println("retour � l'acceuil");
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
