package de.whz.gdp2.g8.smshandy;

import javafx.scene.control.TextField	;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class NewSmsHandyAddingController {
	private Main mainClass;
	@FXML
	private ComboBox<String> tariffsComboBox;
	@FXML
	private TextField newNumberTextField; 
	@FXML
	private Button createPhoneButton;
	private Provider provider;
	private String newNumber;
	private String selectedTariff;

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
	}
	
	@FXML
	private void initialize() {
		 newNumber = newNumberTextField.getText();
		
		tariffsComboBox.getItems().addAll(
			   "TariffPlanSmsHandy",
			   " PrepaidSmsHandy");
			

		createPhoneButton.setOnMouseClicked(e -> {
			createNewPhone(newNumber, tariffsComboBox.getValue());
		});
	
	}
	
	public void createNewPhone(String number, String selectedTariffPlan) {
		if(selectedTariffPlan == "TariffPlanSmsHandy") {
			try {
				new TariffPlanSmsHandy(number, provider);
			} catch (NumberExistsException | NumberNotExistException | ProviderNotGivenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				new PrepaidSmsHandy(number, provider);
			} catch (NumberExistsException | NumberNotExistException | ProviderNotGivenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
