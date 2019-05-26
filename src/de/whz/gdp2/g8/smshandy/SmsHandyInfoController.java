package de.whz.gdp2.g8.smshandy;

import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.model.SmsHandy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SmsHandyInfoController {
	
	private SmsHandy phone;
	
	@FXML
	private Label numberLabel;
	
	@FXML
	private Label providerLabel;
	
	@FXML
	private Label balanceLabel;
	
	@FXML
	private Label tarifLabel;
	
	
	public SmsHandyInfoController() {
		
	}
	
	@FXML
	private void initialize() {
		try {
			balanceLabel.setText(phone.getProvider().getCreditForSmsHandy(phone.getNumber()) + "");
			numberLabel.setText(phone.getNumber());
			providerLabel.setText(phone.getProvider().getName());
			tarifLabel.setText(phone.getClass().getSimpleName());
		} catch (NumberNotGivenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setSmsHandy(SmsHandy phone) {
		this.phone = phone;
	}
	
	
}
