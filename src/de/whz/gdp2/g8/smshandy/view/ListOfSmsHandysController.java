package de.whz.gdp2.g8.smshandy.view;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOfSmsHandysController {
	
	@FXML
	private Label providerName;
	@FXML
	private Button addNewPhoneButton;
	
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
		initSmsHandys();
		
	}
	
	private void initSmsHandys() {
		for(Provider p : Provider.providerList) {
			try {
				new PrepaidSmsHandy("123", p);
				new TariffPlanSmsHandy("456", p);
			} catch (NumberExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProviderNotGivenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
