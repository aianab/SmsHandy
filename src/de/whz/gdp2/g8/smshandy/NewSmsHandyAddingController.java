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

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
	}
	
	@FXML
	private void initialize() {
		String newNumber = newNumberTextField.getAccessibleText();
		try {
			tariffsComboBox.getItems().addAll(
			   "TariffPlanSmsHandy",
			   " PrepaidSmsHandy"
		);
	}
	}
}
