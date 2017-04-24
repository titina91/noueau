package Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ChoiceControler {

	@FXML
	RadioButton studentBtn = new RadioButton();
	@FXML
	RadioButton companyBtn = new RadioButton();
	@FXML
	Button creeBtn = new Button();
	@FXML
	Button annuleBtn = new Button();

	// true -> Etudiant
	// false -> Entreprise
	boolean choice;



	@FXML
	public void create(ActionEvent event){
		creeBtn.setOnAction(e->{
			
			if(studentBtn.isSelected()){
				try{
					System.out.println("creation etudiant");
					Parent companyParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/createStudentScene.fxml"));
					Scene companyScene = new Scene(companyParent);
					Stage companyStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
					companyStage.hide();
					companyStage.setScene(companyScene);
					companyStage.show();
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
			else{
				try{
					System.out.println("creation entreprise");
					Parent companyParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/createCompanyScene.fxml"));
					Scene companyScene = new Scene(companyParent);
					Stage companyStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
					companyStage.hide();
					companyStage.setScene(companyScene);
					companyStage.show();
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
			
		});
	}
}