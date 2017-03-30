package metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class daoUser {
 
	User user = null;
	
	Connection connection;
	ArrayList<Offer> offres = new ArrayList<Offer>();
	
	public daoUser(){
		connection = connexion.connection();
	}
	
	public boolean connect(String state, String id, String mdp){
		boolean exist = false;
		
		String sql = "SELECT * FROM " + state + " WHERE " + id + " AND passwrd = '" + mdp + "'" ;
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//user = new 
			while(rs.next()){
				if(rs != null){
					System.out.println("Existe");
					System.out.println("Id:		" + rs.getInt("id"));
					System.out.println("Nom:		" + rs.getString("name"));
					exist = true;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(exist);
		return exist;
		 
	}
	
	
    public void disconnect(ActionEvent event) {
    	
    	try{
			Parent studentParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/login.fxml"));
			Scene studentScene = new Scene(studentParent);
			Stage studentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			studentStage.hide();
			studentStage.setScene(studentScene);
			studentStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    public void concern(String state,int e){
    	String query = "SELECT * FROM `demande` WHERE `etudiant` =  `" + e + "`";
    	
    	Statement st;
    	try{
    		st = connection.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while(rs.next()){
    			
    			
    		}

    	}catch(Exception exc){
    		exc.printStackTrace();
    	}
    }
}
