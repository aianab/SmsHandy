package de.whz.gdp2.g8.smshandy.view;

import de.whz.gdp2.g8.smshandy.Main;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOfSmsHandysController {
	
	@FXML
	private Label providerName;
	@FXML
	private Button addNewPhoneButton;
	private Main mainClass;
	
	public ListOfSmsHandysController() {
		
	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
	}
	
	@FXML
	private void initialize() {
		final ObservableList<SmsHandy> list = FXCollections.observableArrayList();
		initSmsHandys();
		
	}
	
	private void initSmsHandys() {
		for(Provider p : Provider.providerList) {
			new SmsHandy(p.toString() + " dklsf", p);
		}
		new Provider("O!");
		new Provider("Megacom");
	}
	
}
