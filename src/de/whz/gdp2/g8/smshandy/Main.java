package de.whz.gdp2.g8.smshandy;

import java.io.IOException;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SmsHandy");
		initRootLayout();
		showFirstLayout();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void showFirstLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProviderListOverview.fxml"));
			AnchorPane providerListView = (AnchorPane) loader.load();

			rootLayout.setCenter(providerListView);

			ProviderListController controller = loader.getController();
			controller.setMainClass(this);
		} catch (Exception e) {
			AlertUtil.showAlert(e.getMessage(), this);
		}
	}

	public void showProviderInfo(Provider p) {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ListOfSmsHandys.fxml"));
			AnchorPane listOfSmsHandys = (AnchorPane) loader.load();

			ListOfSmsHandysController controller = loader.getController();
			controller.setMainClass(this);
			controller.setProvider(p);
			
			rootLayout.setCenter(listOfSmsHandys);
			primaryStage.show();

		} catch (Exception e) {
			AlertUtil.showAlert(e.getMessage(), this);
		}
	}

    public void setRootPane(AnchorPane pane) {
    	rootLayout.setCenter(pane);
    }

	public BorderPane getRootLayout() {
		return rootLayout;
	}
}
