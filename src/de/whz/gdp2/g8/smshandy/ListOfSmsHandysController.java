package de.whz.gdp2.g8.smshandy;

import java.io.IOException;
import java.util.List;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	private BorderPane rootLayout;
	private Stage primaryStage;

	public ListOfSmsHandysController() {
		
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
		list = FXCollections.observableArrayList();
		list.setAll(provider.getPhones());
		listSmsHandysView.setItems(list);
		listSmsHandysView.getSelectionModel().select(0);
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
			addNewPhone();
		});
		loadBalanceButton.setOnMouseClicked(e -> {
			loadBalance();
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

	public void addNewPhone() {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/NewSmsHandyAdding.fxml"));
			AnchorPane listOfSmsHandys = (AnchorPane) loader.load();

			NewSmsHandyAddingController controller = loader.getController();
			controller.setMainClass(this.mainClass);
			controller.setProvider(provider);

			rootLayout.setCenter(listOfSmsHandys);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
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
		
		secondaryLayout.setAlignment(label, Pos.TOP_CENTER);
		secondaryLayout.setAlignment(unitsAmountField, Pos.CENTER_LEFT);
		secondaryLayout.setAlignment(loadUnitsButton, Pos.BOTTOM_CENTER);
		
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
				phone.deposit(Integer.parseInt(unitsAmountField.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberNotGivenException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			newWindow.close();
		});
	}

}
