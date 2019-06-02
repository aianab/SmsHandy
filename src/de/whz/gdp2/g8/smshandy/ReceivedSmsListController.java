package de.whz.gdp2.g8.smshandy;

import de.whz.gdp2.g8.smshandy.model.Message;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReceivedSmsListController {
    @FXML
    private TableView<Message> receivedTable;
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

    private Stage primaryStage;
    private Main mainClass;

    public void setPhone(SmsHandy phone) {
        this.phone = phone;
    }

    public void setMainClass(Main main) {
        this.mainClass = main;
        backButton.setOnMouseClicked(ev -> {
            mainClass.showSmsHandyInfo(phone);
        });
        mainClass.getPrimaryStage().setOnCloseRequest(e -> {
            mainClass.showFirstLayout();
        });
    }

    public void initTable() {
        ObservableList<Message> receivedList = FXCollections.observableArrayList();
        receivedList.addAll(phone.getReceived());
        recieverColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("to"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("from"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("date"));
        receivedTable.setItems(receivedList);
    }

}
