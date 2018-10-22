/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperatureconverterfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cstuser
 */
public class TemperatureConverterFX extends Application {
    private Scene scene1 = null;
    private Scene scene2 = null;
    private int nb;
    
    @Override
    public void start(Stage primaryStage) {
        Label scene1Label = new Label("Scene 1");
        Label Flabel = new Label();
        TextField txt = new TextField();
        
        Button btn1 = new Button();
        btn1.setText("Convert");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                nb = Integer.parseInt(txt.getText());
                nb = nb*9/5+32;
                Flabel.setText("" + nb);
                primaryStage.setScene(scene2);
            }
        });
       
        Label scene12Label = new Label("Scene 2");
        Button btn2 = new Button();
        btn2.setText("Back");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene1);
            }
        });
        
        
        
        VBox root = new VBox();
        root.getChildren().add(scene12Label);
        root.getChildren().add(txt);
        root.getChildren().add(btn1);
        
        VBox root2 = new VBox();
        root2.getChildren().add(scene1Label);
        root2.getChildren().add(Flabel);
        root2.getChildren().add(btn2);
        
        
        scene1 = new Scene(root, 300, 250);
        scene2 = new Scene(root2, 500, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
