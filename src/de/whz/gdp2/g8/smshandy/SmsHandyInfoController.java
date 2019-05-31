package de.whz.gdp2.g8.smshandy;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.beans.binding.SetBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SmsHandyInfoController {
	
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
	private Button backButton;
	
	public SmsHandyInfoController() {
		
	}
	
	@FXML
	private void initialize() {
		changeProviderButton.setOnMouseClicked(e -> {
			showChangeProviderView();
		});
		
	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
		backButton.setOnMouseClicked(e -> {
			mainClass.showProviderInfo(phone.getProvider());
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
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);


        newWindow.setX( mainClass.getPrimaryStage().getX() + 200);
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
}
