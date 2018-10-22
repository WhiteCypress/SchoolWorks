/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userparsing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label streetAddressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label stateLabel;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String json = readJsonFile("./user.json");
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();
        
        String firstName = root.get("firstName").getAsString();
        String lastName = root.get("lastName").getAsString();
        int age = root.get("age").getAsInt();
        
        JsonObject address = root.get("address").getAsJsonObject();
        String streetAdress = address.get("streetAddress").getAsString();
        String city = address.get("city").getAsString();
        String state = address.get("state").getAsString();
        
        firstNameLabel.setText(firstName);
        lastNameLabel.setText(lastName);
        ageLabel.setText(Integer.toString(age));
        streetAddressLabel.setText(streetAdress);
        cityLabel.setText(city);
        stateLabel.setText(state);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private String readJsonFile(String filename) {
        try {
            String jsonString = "";
            FileInputStream fileInput = new FileInputStream(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInput));
            String inputLine = "";
            while((inputLine = in.readLine()) != null) {
                jsonString += inputLine;
            }
            
            in.close();
            fileInput.close();
            
            return jsonString;
        }catch(Exception e) {
            System.out.println("Error reading file!");
            return "";
        }
    }
    
}
