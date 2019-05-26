package de.whz.gdp2.g8.smshandy;

import java.io.IOException;
import java.util.List;

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
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class ListOfSmsHandysController {

	@FXML
	private Label providerName;
	@FXML
	private Button addNewPhoneButton;
	@FXML
	private Button removePhoneButton;

	@FXML
	private Button showSmsHandyInfoButton;
	
	@FXML
	private Button backButton;

	@FXML
	private ListView<SmsHandy> listSmsHandysView;

	private Provider provider;
	private Main mainClass;
	private ObservableList<SmsHandy> list;

	private BorderPane rootLayout;
	private Stage primaryStage;

	public ListOfSmsHandysController() {
		
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
		list = FXCollections.observableArrayList();
		list.setAll(provider.getPhones());
		listSmsHandysView.setItems(list);
		
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

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void initialize() {
		removePhoneButton.setOnMouseClicked(e -> {
			deletePhone(listSmsHandysView.getSelectionModel().getSelectedItem());
		});

		showSmsHandyInfoButton.setOnMouseClicked(e -> {
			showSmsHandyInfo();
		});

		addNewPhoneButton.setOnMouseClicked(e ->

		{
			addNewPhone(provider);
		});
		
	}

	private void deletePhone(SmsHandy phone) {
		provider.removeSmsHandy(phone.getNumber());
		list.remove(phone);
	}

	private void showSmsHandyInfo() {
		try {
			Stage primaryStage = mainClass.getPrimaryStage();
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/SmsHandyInfo.fxml"));

			AnchorPane smsHandyInfo = (AnchorPane) loader.load();

			SmsHandyInfoController controller = loader.getController();
			controller.setSmsHandy(listSmsHandysView.getSelectionModel().getSelectedItem());
			controller.setMainClass(mainClass);

			mainClass.setRootPane(smsHandyInfo);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNewPhone(Provider p) {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/NewSmsHandyAdding.fxml"));
			AnchorPane listOfSmsHandys = (AnchorPane) loader.load();

			NewSmsHandyAddingController controller = loader.getController();
			controller.setMainClass(this.mainClass);
//    		controller.setProvider(p);

			rootLayout.setCenter(listOfSmsHandys);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
