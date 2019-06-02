package de.whz.gdp2.g8.smshandy;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProviderListController {
	
	private Main mainClass;
	
	static {
		initProviders();
	}
	
	@FXML
	private ListView<Provider> providerListView;
	
	@FXML
	private Button newButton;
	
	@FXML
	private Button detailsButton;
	
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
		
		list.addAll(Provider.providerList);
		providerListView.setItems(list);
		providerListView.getSelectionModel().select(0);
		
		newButton.setOnMouseClicked(e -> {
			showAddNewProviderWindow(list);
		});
		
		removeButton.setOnMouseClicked(e -> {
			removeProvider(list);
		});
		
		detailsButton.setOnMouseClicked(e -> {
			mainClass.showProviderInfo(providerListView.getSelectionModel().getSelectedItem());
		});
		
	}
	
	
	private static void initProviders() {
		
		try {
			Provider b = new Provider("Beeline");
			new PrepaidSmsHandy("lksdjf", b);
			
			Provider o = new Provider("O!");
			new TariffPlanSmsHandy("123", o);
			
			Provider m = new Provider("Megacom");
			new TariffPlanSmsHandy("ewrr3", m);
			
		} catch (NumberExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProviderNotGivenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeProvider(ObservableList<Provider> observableList) {
		Provider p = providerListView.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Do you really want to delete this provider?");
		alert.setResizable(false);
		alert.setContentText("Select okay to confirm deletion");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			Provider.providerList.remove(p);
			observableList.remove(p);
		}
		else if(result.get() == ButtonType.CANCEL) {
		}
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
