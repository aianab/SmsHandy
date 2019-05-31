package de.whz.gdp2.g8.smshandy;

import java.io.IOException;

import de.whz.gdp2.g8.smshandy.model.Message;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SentSmsListController {
	@FXML
	private TableView<ObservableList<Message>> sentTable;
	@FXML
    private TableColumn<ObservableList<Message>, String> senderColumn;
	@FXML
    private TableColumn<ObservableList<Message>, String> recieverColumn;
	@FXML
    private TableColumn<ObservableList<Message>, String> contentColumn;
	@FXML
    private TableColumn<ObservableList<Message>, String> dateColumn;
	
	private Stage primaryStage;
	
	private BorderPane rootLayout;
	
	private Main mainClass;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setMainClass(Main main) {
	this.mainClass = main;
	mainClass.getPrimaryStage().setOnCloseRequest(e -> {
		mainClass.showFirstLayout();
	});

}
