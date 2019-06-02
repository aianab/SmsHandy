package de.whz.gdp2.g8.smshandy;

import de.whz.gdp2.g8.smshandy.config.DBConfig;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import de.whz.gdp2.g8.smshandy.util.AlertUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    public static void main(String[] args) {
        try {
            DBConfig.initDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void showSmsHandyInfo(SmsHandy phone) {
        try {
            Stage primaryStage = getPrimaryStage();
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/SmsHandyInfo.fxml"));

            AnchorPane smsHandyInfo = (AnchorPane) loader.load();

            SmsHandyInfoController controller = loader.getController();
            controller.setSmsHandy(phone);
            controller.setMainClass(this);

            setRootPane(smsHandyInfo);
            primaryStage.show();
        } catch (IOException e) {
            AlertUtil.showAlert(e.getMessage(), this);
        }
    }

    public void showSentSmsList(SmsHandy phone) {
        try {
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/SentSmsList.fxml"));

            AnchorPane sentSmsList = (AnchorPane) loader.load();
            SentSmsListController sentController = loader.getController();
            sentController.setMainClass(this);
            sentController.setPhone(phone);
            sentController.initTable();
            rootLayout.setCenter(sentSmsList);
            primaryStage.show();
        } catch (IOException e1) {
            AlertUtil.showAlert("Something went wrong showing Sms list!", this);
        }
    }

    public void showReceivedSmsList(SmsHandy phone) {
        try {
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ReceivedSmsList.fxml"));

            AnchorPane receivedSmsList = (AnchorPane) loader.load();
            ReceivedSmsListController receivedController = loader.getController();
            receivedController.setMainClass(this);
            receivedController.setPhone(phone);
            receivedController.initTable();
            rootLayout.setCenter(receivedSmsList);
            primaryStage.show();
        } catch (IOException e1) {
            AlertUtil.showAlert("Something went wrong showing Sms list!", this);
        }
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }
}
