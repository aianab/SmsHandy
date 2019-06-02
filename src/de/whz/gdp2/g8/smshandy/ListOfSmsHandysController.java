package de.whz.gdp2.g8.smshandy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ListOfSmsHandysController {

	@FXML
	private Label providerNameLabel;
	@FXML
	private Button addNewPhoneButton;
	@FXML
	private Button removePhoneButton;
	@FXML
	private Button showSmsHandyInfoButton;
	@FXML
	private Button loadBalanceButton;
	@FXML
	private Button backButton;

	@FXML
	private ListView<SmsHandy> listSmsHandysView;

	private Provider provider;
	private Main mainClass;
	private ObservableList<SmsHandy> list;


	public ListOfSmsHandysController() {
		
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
		list = FXCollections.observableArrayList();
		list.setAll(provider.getPhones());
		listSmsHandysView.setItems(list);
		listSmsHandysView.getSelectionModel().select(0);
		providerNameLabel.setText(provider.getName());
	}

	public void setMainClass(Main main) {
		this.mainClass = main;
		mainClass.getPrimaryStage().setOnCloseRequest(e -> {
			mainClass.showFirstLayout();
		});
		
		backButton.setOnMouseClicked(e -> {
			mainClass.showFirstLayout();
		});
	}



	@FXML
	private void initialize() {
		removePhoneButton.setOnMouseClicked(e -> {
			deletePhone(listSmsHandysView.getSelectionModel().getSelectedItem());
		});

		showSmsHandyInfoButton.setOnMouseClicked(e -> {
			mainClass.showSmsHandyInfo(listSmsHandysView.getSelectionModel().getSelectedItem());
		});

		addNewPhoneButton.setOnMouseClicked(e ->

		{
			addNewPhone();
		});
		loadBalanceButton.setOnMouseClicked(e -> {
			loadBalance();
		});
		
	}

	private void deletePhone(SmsHandy phone) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Do you really want to delete this phone?");
		alert.setResizable(false);
		alert.setContentText("Select okay to confirm deletion");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			provider.removeSmsHandy(phone.getNumber());
			list.remove(phone);
		}
		else if(result.get() == ButtonType.CANCEL) {
		}
	}



	public void addNewPhone() {
		try {
			mainClass.getPrimaryStage().close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/NewSmsHandyAdding.fxml"));
			AnchorPane listOfSmsHandys = (AnchorPane) loader.load();

			NewSmsHandyAddingController controller = loader.getController();
			controller.setMainClass(this.mainClass);
			controller.setProvider(provider);

			mainClass.getRootLayout().setCenter(listOfSmsHandys);
			mainClass.getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.showAlert(e.getMessage(), mainClass);
		}
	}
	
	public void loadBalance() {
		StackPane secondaryLayout = new StackPane();
		Label label = new Label("Please enter amount of units you want to load");
		secondaryLayout.getChildren().add(label);
		TextField unitsAmountField = new TextField();
		Button loadUnitsButton = new Button("Load");
		
		secondaryLayout.getChildren().add(1, unitsAmountField);
		secondaryLayout.getChildren().add(2, loadUnitsButton);
		
		StackPane.setAlignment(label, Pos.TOP_CENTER);
		StackPane.setAlignment(unitsAmountField, Pos.CENTER_LEFT);
		StackPane.setAlignment(loadUnitsButton, Pos.BOTTOM_CENTER);
		
		Scene secondScene = new Scene(secondaryLayout, 250, 100);
		
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);


        newWindow.setX( mainClass.getPrimaryStage().getX() + 200);
        newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

        newWindow.show();
        
        loadUnitsButton.setOnMouseClicked(e -> {
        	PrepaidSmsHandy phone = (PrepaidSmsHandy) listSmsHandysView.getSelectionModel().getSelectedItem();
        	
        	try {
        		if(Integer.parseInt(unitsAmountField.getText()) < 0){
    				throw new IllegalArgumentException();
    			}
				phone.deposit(Integer.parseInt(unitsAmountField.getText()));
				
			} catch (NumberFormatException e1) {
				AlertUtil.showAlert("Please enter only positive digits", mainClass);
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				AlertUtil.showAlert("Please enter only positive digits", mainClass);
				
				e1.printStackTrace();
			}catch (NumberNotGivenException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			newWindow.close();
		});
	}

}
