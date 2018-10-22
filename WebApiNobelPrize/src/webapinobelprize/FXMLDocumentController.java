/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapinobelprize;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    
    @FXML
    private ComboBox yearComboBox;
    
    @FXML
    private ComboBox categoryComboBox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        NobelPrizeController.NobelPrizeLaureate[] array = NobelPrizeController.getNobelPrizeLaureates(categoryComboBox.getValue().toString(), Integer.parseInt(yearComboBox.getValue().toString()));
        String output = "";
        
        int counter = 0;
        for(NobelPrizeController.NobelPrizeLaureate n : array){
            if(n != null){
                counter ++;
            }
        }
        
        for(int i = 0; i < counter; i++) {
            output += "First name: " + array[i].getFirstname() + "\nSurname: " + array[i].getSurname() + "\nCategory: " + array[i].getCategory() + "\n\n";
        }
        label.setText(output);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryComboBox.getItems().addAll("chemistry", "economics", "medicines", "peace", "physics");
        
        categoryComboBox.setValue("chemistry");
        
        ArrayList<String> yearList = new ArrayList<>();
        for(int i = 2018; i >= 1901; --i) {
            yearList.add(i + "");
        }
        yearComboBox.getItems().addAll(yearList);
        
        yearComboBox.setValue("2018");
    }    
}
