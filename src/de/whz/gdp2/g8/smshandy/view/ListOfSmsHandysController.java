package de.whz.gdp2.g8.smshandy.view;

import java.util.List;

import de.whz.gdp2.g8.smshandy.Main;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOfSmsHandysController {
	
	@FXML
	private Label providerName;
	@FXML
	private Button addNewPhoneButton;
	@FXML
	private ListView<SmsHandy> listSmsHandysView;
	
	private Provider provider;
	private Main mainClass;
	
	
	public ListOfSmsHandysController() {
		
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
	}
	
	@FXML
	private void initialize() {
		final ObservableList<SmsHandy> list = FXCollections.observableArrayList();
		list.setAll(provider.getPhones());
		listSmsHandysView.setItems(list);
	}
	
	private void deletePhone(ObservableList<SmsHandy> observableList) {
		SmsHandy phone = listSmsHandysView.getSelectionModel().getSelectedItem();
		provider.removeSmsHandy(phone.getNumber());
		observableList.remove(phone);
	}
	
	
}
