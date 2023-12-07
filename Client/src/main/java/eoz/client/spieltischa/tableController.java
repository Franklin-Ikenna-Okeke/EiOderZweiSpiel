package eoz.client.spieltischa;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class tableController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView myImageView;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void bindImageViewSize() {
        // Bind ImageView width to stage width
        myImageView.fitWidthProperty().bind(primaryStage.widthProperty());

        // Bind ImageView height to stage height
        myImageView.fitHeightProperty().bind(primaryStage.heightProperty());
    }

    public void setPlayerName(String username) {
        // Assuming you have a Label to display the username
        //playerNameLabel.setText(username);
    }

}