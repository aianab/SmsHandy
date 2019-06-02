package de.whz.gdp2.g8.smshandy.util;

import de.whz.gdp2.g8.smshandy.Main;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class AnimationUtil {
	public static void showSendSmsAnimation(Main mainClass) throws IOException {
		Image phoneImage = new Image(new FileInputStream("./phone.png"));
		Image messageImage = new Image(new FileInputStream("./message.png"));
		
		ImageView phoneFromView = new ImageView(phoneImage);
		phoneFromView.setX(40);
		phoneFromView.setY(70);
		phoneFromView.setFitHeight(50);
		phoneFromView.setFitWidth(40);
		
		ImageView phoneToView = new ImageView(phoneImage);
		phoneToView.setX(200);
		phoneToView.setY(70);
		phoneToView.setFitHeight(50);
		phoneToView.setFitWidth(40);
		
		ImageView messageView = new ImageView(messageImage);
		messageView.setX(50);
		messageView.setY(80);
		messageView.setFitHeight(50);
		messageView.setFitWidth(40);
		
		
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(3));
		transition.setToX(150);
		transition.setNode(messageView);
		transition.play();
		
		Pane root = new Pane();
		root.getChildren().add(messageView);
		root.getChildren().add(phoneFromView);
		root.getChildren().add(phoneToView);
		Scene scene = new Scene(root, 300, 300);
		
		Stage newWindow = new Stage();
		
		newWindow.setTitle("Sending status");
		newWindow.setScene(scene);
		newWindow.show();
		
		transition.setOnFinished(e -> {
			AlertUtil.showAlert("Message has been delivered successfully!", mainClass);
			newWindow.close();
		});
	}
}
