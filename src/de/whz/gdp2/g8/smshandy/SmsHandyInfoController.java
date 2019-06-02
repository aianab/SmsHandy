package de.whz.gdp2.g8.smshandy;


import java.io.IOException;


import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.Message;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import de.whz.gdp2.g8.smshandy.util.AnimationUtil;
import javafx.beans.binding.SetBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmsHandyInfoController {
	
	@FXML
	private ListView<SmsHandy> listSmsHandysView;

	private SmsHandy phone;

	private Main mainClass;

	@FXML
	private Label numberLabel;

	@FXML
	private Label providerLabel;

	@FXML
	private Label balanceLabel;

	@FXML
	private Label tarifLabel;

	@FXML
	private Button changeProviderButton;
	@FXML
	private Button sentButton;
	@FXML
	private Button receivedButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<ObservableList<Message>> sentTable;
	@FXML
	private Button newMessageButton;
	
	public void setPhone(SmsHandy phone) {
		this.phone = phone;
	}

	@FXML
	private void initialize() {
		changeProviderButton.setOnMouseClicked(e -> {
			showChangeProviderView();
		});
			
		newMessageButton.setOnMouseClicked(e -> {
			showNewMessageView();
		});
	}

	public void setMainClass(Main main) {
		this.mainClass = main;
		backButton.setOnMouseClicked(e -> {
			mainClass.showProviderInfo(phone.getProvider());
		});
		sentButton.setOnMouseClicked(e -> {
			mainClass.showSentSmsList(phone);
		});
		receivedButton.setOnMouseClicked(e -> {
			mainClass.showReceivedSmsList(phone);
		});		
	}

	public void setSmsHandy(SmsHandy phone) {
		this.phone = phone; 
		try {
			balanceLabel.setText(phone.getProvider().getCreditForSmsHandy(phone.getNumber()) + "");
			numberLabel.setText(phone.getNumber());
			providerLabel.setText(phone.getProvider().getName());
			tarifLabel.setText(phone.getClass().getSimpleName());
		} catch (NumberNotGivenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showChangeProviderView() {
		StackPane secondaryLayout = new StackPane();
		Label label = new Label("Please select the provider");
		secondaryLayout.getChildren().add(label);
		ListView<Provider> providerListView = new ListView<Provider>();
		providerListView.setItems(FXCollections.observableArrayList(Provider.providerList));
		providerListView.getSelectionModel().select(0);
		Button addProviderButton = new Button("Change provider");

		secondaryLayout.getChildren().add(1, providerListView);
		secondaryLayout.getChildren().add(2, addProviderButton);

		secondaryLayout.setAlignment(label, Pos.TOP_CENTER);
		secondaryLayout.setAlignment(providerListView, Pos.CENTER_LEFT);
		secondaryLayout.setAlignment(addProviderButton, Pos.BOTTOM_CENTER);

		Scene secondScene = new Scene(secondaryLayout, 300, 400);

		Stage newWindow = new Stage();
		newWindow.setTitle("Change provider");
		newWindow.setScene(secondScene);

		newWindow.setX(mainClass.getPrimaryStage().getX() + 200);
		newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

		newWindow.show();

		addProviderButton.setOnMouseClicked(e -> {
			phone.getProvider().removeSmsHandy(phone.getNumber());
			phone.setProvider(providerListView.getSelectionModel().getSelectedItem());
			try {
				phone.getProvider().register(phone);
			} catch (NumberExistsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			providerLabel.setText(phone.getProvider().getName());
			newWindow.close();
		});
	}

	private void showNewMessageView() {
		StackPane secondaryLayout = new StackPane();
		Label label = new Label("Write new SMS");

		TextField toField = new TextField();
		toField.setText("to");
		TextArea msgArea = new TextArea("Message");
		Button sendButton = new Button("Send");
		Button cancelButton = new Button("Cancel");

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(0, 20, 10, 20));
		vbox.getChildren().addAll(label, toField, msgArea);
		
		HBox xbox = new HBox();
		xbox.getChildren().addAll(sendButton, cancelButton);
		xbox.setSpacing(10);
		
		vbox.getChildren().add(xbox);

		Scene secondScene = new Scene(vbox, 300, 250);

		Stage newWindow = new Stage();

		newWindow.setTitle("Send message");
		newWindow.setScene(secondScene);

		newWindow.setX(mainClass.getPrimaryStage().getX() + 200);
		newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

		newWindow.show();
		
		sendButton.setOnMouseClicked(e -> {
			try {
				phone.sendSms(toField.getText(), msgArea.getText());
				AnimationUtil.showSendSmsAnimation(mainClass);
			} catch (NotEnoughBalanceException | NumberNotExistException
					| NumberNotGivenException ex) {
				AlertUtil.showAlert(ex.getMessage(), mainClass);
			} catch (IOException e1) {
				AlertUtil.showAlert("Message sent successfully!", mainClass);
			} catch (ProviderNotGivenException e1) {
				AlertUtil.showAlert(new NumberNotExistException().getMessage(), mainClass);
			}
		});
		
		cancelButton.setOnMouseClicked(e -> {
			newWindow.close();
		});
	}
	
}
