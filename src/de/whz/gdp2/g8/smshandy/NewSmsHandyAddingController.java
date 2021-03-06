package de.whz.gdp2.g8.smshandy;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewSmsHandyAddingController {
    private Main mainClass;
    @FXML
    private ComboBox<String> tariffsComboBox;
    @FXML
    private TextField newNumberTextField;
    @FXML
    private Button createPhoneButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button infoTariffButton;

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

        tariffsComboBox.getItems().addAll("TariffPlanSmsHandy", "PrepaidSmsHandy");
        tariffsComboBox.getSelectionModel().select(0);

        createPhoneButton.setOnMouseClicked(e -> {
            createNewPhone(tariffsComboBox.getValue());
        });

        cancelButton.setOnMouseClicked(e -> {
            mainClass.showProviderInfo(provider);
        });

        infoTariffButton.setOnMouseClicked(e -> {
            infoAboutTariff(tariffsComboBox.getValue());
        });

    }

    public void createNewPhone(String selectedTariffPlan) {
        try {
        	if(newNumberTextField.getText().isEmpty()) {
        		System.out.println(newNumberTextField.getText());
        		throw new IllegalArgumentException();
        	}
            if (selectedTariffPlan == "TariffPlanSmsHandy") {
                new TariffPlanSmsHandy(newNumberTextField.getText(), provider);
            } else if (selectedTariffPlan == "PrepaidSmsHandy") {
                new PrepaidSmsHandy(newNumberTextField.getText(), provider);
            }
            mainClass.showProviderInfo(provider);
        } catch (NumberExistsException | NumberNotExistException | ProviderNotGivenException e) {
            AlertUtil.showAlert(e.getMessage(), mainClass);
        }catch (IllegalArgumentException e) {
        	AlertUtil.showAlert("Number not given, please enter a number", mainClass);		
        }
    }

    private void infoAboutTariff(String selectedTariffPlan) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Information about selected tariff plan");
        if (selectedTariffPlan == "TariffPlanSmsHandy") {
            alert.setContentText("TariffPlanSmsHandy provides 100 free Sms");
        }
        if (selectedTariffPlan == "PrepaidSmsHandy") {
            alert.setContentText("TariffPlanSmsHandy deposit in amount 100 units.\nOne Sms costs 10 units");
        }
        alert.showAndWait();
    }
}
