package de.whz.gdp2.g8.smshandy;

import java.io.IOException;
import java.util.Date;
import java.util.Observer;

import de.whz.gdp2.g8.smshandy.model.Message;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SentSmsListController {
	@FXML
	private TableView<Message> sentTable;
	@FXML
	private TableColumn<Message, String> senderColumn;
	@FXML
	private TableColumn<Message, String> recieverColumn;
	@FXML
	private TableColumn<Message, String> contentColumn;
	@FXML
	private TableColumn<Message, String> dateColumn;
	@FXML
	private Button backButton;

	private Message message;

	private SmsHandy phone;

	private Main mainClass;

	public void setPhone(SmsHandy phone) {
		this.phone = phone;
	}

	public void setMainClass(Main main) {
		this.mainClass = main;
		backButton.setOnMouseClicked(ev -> {
			System.out.println("hwefj");
			mainClass.showSmsHandyInfo(phone);
		});
		mainClass.getPrimaryStage().setOnCloseRequest(e -> {
			mainClass.showFirstLayout();
		});
	}

	public void initTable() {
		ObservableList<Message> sentList = FXCollections.observableArrayList();
//		sentList.add(new Message("123", "456", "qwerty", new Date()));
		sentList.addAll(phone.getSent());
		senderColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("from"));
		recieverColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("to"));
		contentColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("date"));
		sentTable.setItems(sentList);
	}
}
