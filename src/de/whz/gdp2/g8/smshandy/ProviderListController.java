package de.whz.gdp2.g8.smshandy;

import java.util.Arrays;
import java.util.List;

import de.whz.gdp2.g8.smshandy.model.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ProviderListController {
	@FXML
	private ListView<Provider> providerListView;
	
	public ProviderListController() {

	}
	
	@FXML
	private void initialize() {
		final ObservableList<Provider> list = FXCollections.observableArrayList();
		initProviders();
		list.addAll(Provider.providerList);
		providerListView.setItems(list);
	}
	
	
	private void initProviders() {
		new Provider("Beeline");
		new Provider("O!");
		new Provider("Megacom");
	}
}
