package de.whz.gdp2.g8.smshandy;


import de.whz.gdp2.g8.smshandy.config.DBConfig;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Optional;

public class ProviderListController {

    private Main mainClass;

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


        newWindow.setX(mainClass.getPrimaryStage().getX() + 200);
        newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

        newWindow.show();

        addProviderButton.setOnMouseClicked(e -> {
        	try {
        		if(providerNameField.getText().isEmpty()) {
        			throw new ProviderNotGivenException();
        		}
        		observableList.add(new Provider(providerNameField.getText()));
			} catch (ProviderNotGivenException e1) {
				AlertUtil.showAlert(e1.getMessage(), mainClass);
				}            
            newWindow.close();
        });
    }


}
