package Controler;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import metier.daoUser;

public class EtudiantControler implements Initializable{
	
	

    @FXML
    private Hyperlink logout;
    @FXML
    private TextField lmField;
    @FXML
    private TableView<?> offerTableView;
    @FXML
    private Label offerLvl;
    @FXML
    private Text offerDescription;
    @FXML
    private Label companyName;
    @FXML
    private Label companyTel;
    @FXML
    private TextField cvField;
    @FXML
    private Label companyMail;
    @FXML
    private AnchorPane demandPane;
    
    // Button
    
    @FXML
    private Button postulBtn;
    @FXML
    private Button broswerLM;
    @FXML
    private Button browserCV;
    
    
    daoUser dU = new daoUser();
    

    @FXML
    void disconnect(ActionEvent event) {
    	dU.disconnect(event);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
    
	@FXML
	public void broswer(ActionEvent event){
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);

		
		fc.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"),
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
				
		if (selectedFile != null){
			System.out.println("fonctionne");
			cvField.setText(selectedFile.getAbsolutePath());
			
		}
		else{
			System.out.println("error");

		}
	}
	
	@FXML
	public void postuler(ActionEvent event){
		
	}
    

}
