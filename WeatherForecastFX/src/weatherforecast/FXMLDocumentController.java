/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherforecast;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button button2;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        button2.setText("cant be unclicked");
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        try {
            File imageFile = new File("./images/weatherReport.jpg");
            Image image = new Image(imageFile.toURI().toString());
            weatherIcon.setImage(image);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    
    @FXML
    private void CloseItem(ActionEvent event) {
        System.out.println("close file!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialized!");
    }

}
