package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class OfferPane extends Pane{

	@FXML
	Label intitule;
	@FXML
	Label entreprise;
	@FXML
	Label debut;
	@FXML
	Label duree;
	
	public OfferPane(String i, String e, String deb, int dur){
		this.intitule.setText(i);
		this.entreprise.setText(e);
		this.debut.setText(deb);
		this.duree.setText(Integer.toString(dur));
	}
}
