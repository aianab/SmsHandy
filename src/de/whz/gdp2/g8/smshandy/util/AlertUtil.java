package de.whz.gdp2.g8.smshandy.util;

import de.whz.gdp2.g8.smshandy.Main;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.Provider;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertUtil {
	
	public static void showAlert(String msg, Main mainClass) {
		StackPane secondaryLayout = new StackPane();
		Label label = new Label(msg);
		Button okButton = new Button("OK");

		secondaryLayout.getChildren().add(label);
		secondaryLayout.getChildren().add(1, okButton);
		
		secondaryLayout.setAlignment(label, Pos.TOP_CENTER);
		secondaryLayout.setAlignment(okButton, Pos.CENTER);
		
		Scene secondScene = new Scene(secondaryLayout, 300, 150);

		Stage newWindow = new Stage();

		newWindow.setTitle("Warning!");
		newWindow.setScene(secondScene);

		newWindow.setX(mainClass.getPrimaryStage().getX() + 200);
		newWindow.setY(mainClass.getPrimaryStage().getY() + 100);

		newWindow.show();
		
		okButton.setOnMouseClicked(e -> {
			newWindow.close();
		});
	}
}
