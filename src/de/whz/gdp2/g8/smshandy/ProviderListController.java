package de.whz.gdp2.g8.smshandy;



import java.util.Arrays;
import java.util.List;

import de.whz.gdp2.g8.smshandy.model.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProviderListController {
	
	private Main mainClass;
	
	@FXML
	private ListView<Provider> providerListView;
	
	@FXML
	private Button newButton;
	
	public ProviderListController() {

	}
	
	public void setMainClass(Main main) {
		this.mainClass = main;
	}
	
	@FXML
	private void initialize() {
		final ObservableList<Provider> list = FXCollections.observableArrayList();
		initProviders();
		list.addAll(Provider.providerList);
		providerListView.setItems(list);
		
		newButton.setOnMouseClicked(e -> {
			showAddNewProviderWindow(list);
		});
	}
	
	
	private void initProviders() {
		new Provider("Beeline");
		new Provider("O!");
		new Provider("Megacom");
	}
	
	private void showAddNewProviderWindow(ObservableList<Provider> observableList) {
		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(new Label("Hello"));
		TextField providerNameField = new TextField();
		Button addProviderButton = new Button("Add Provider");
		addProviderButton.setOnMouseClicked(e -> {
			Provider p = new Provider(providerNameField.getText());
			observableList.add(p);
			providerListView.setItems(observableList);
		});
		
		Scene secondScene = new Scene(secondaryLayout, 230, 100);
		
		 // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX( mainClass.getPrimaryStage().getX() + 200);
        newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

        newWindow.show();
	}
	
	
}
