package Controler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import metier.Offer;
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
    
    daoUser dU = new daoUser();
    

    @FXML
    void disconnect(ActionEvent event) {
    	dU.disconnect(event);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    

}
