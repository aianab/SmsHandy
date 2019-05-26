package de.whz.gdp2.g8.smshandy;



import java.util.Arrays;
import java.util.List;

import de.whz.gdp2.g8.smshandy.model.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
	
	@FXML
	private Button removeButton;
	
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
		
		removeButton.setOnMouseClicked(e -> {
			removeProvider(list);
		});
	}
	
	
	private void initProviders() {
		new Provider("Beeline");
		new Provider("O!");
		new Provider("Megacom");
	}
	
	private void removeProvider(ObservableList<Provider> observableList) {
		Provider p = providerListView.getSelectionModel().getSelectedItem();
		Provider.providerList.remove(p);
		observableList.remove(p);
	}
	
	private void showAddNewProviderWindow(ObservableList<Provider> observableList) {
		StackPane secondaryLayout = new StackPane();
		Label label = new Label("Please type the new provider name");
		secondaryLayout.getChildren().add(label);
		TextField providerNameField = new TextField();
		Button addProviderButton = new Button("Add Provider");
		
		secondaryLayout.getChildren().add(1, providerNameField);
		secondaryLayout.getChildren().add(2, addProviderButton);
		
		secondaryLayout.setAlignment(label, Pos.TOP_CENTER);
		secondaryLayout.setAlignment(providerNameField, Pos.CENTER_LEFT);
		secondaryLayout.setAlignment(addProviderButton, Pos.BOTTOM_CENTER);
		
		Scene secondScene = new Scene(secondaryLayout, 230, 100);
		
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);


        newWindow.setX( mainClass.getPrimaryStage().getX() + 200);
        newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

        newWindow.show();
        
        addProviderButton.setOnMouseClicked(e -> {
			observableList.add(new Provider(providerNameField.getText()));
			newWindow.close();
		});
	}
	
	
}
