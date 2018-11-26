/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realtimesimulation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;

    private double lastFrameTime = 0.0;
    private ArrayList<Circle> circleList;
    private ArrayList<Vector2D> circlePositonList;
    private ArrayList<Vector2D> circleVelocityList;

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        circleList = new ArrayList<Circle>();
        circlePositonList = new ArrayList<Vector2D>();
        circleVelocityList = new ArrayList<Vector2D>();
        

        final Vector2D acceleration = new Vector2D(0.0, 980);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                Circle c;

                // Add random circles every second
                if ((int) currentTime > circleList.size()) {
                    Random rng = new Random();
                    int x = rng.nextInt((int) pane.getWidth());
                    int y = rng.nextInt((int) pane.getHeight());
                    int radius = rng.nextInt(50);

                    c = new Circle(0, 0, radius);
                    c.setCenterX(x);
                    c.setCenterY(y);

                    circleList.add(c);
                    circlePositonList.add(new Vector2D(c.getCenterX(), c.getCenterY()));
                    circleVelocityList.add(new Vector2D(0.0f, 0.0f));
                    addToPane(c);
                }
                
                int i = 0;
                for(Circle cir: circleList){
                    //Vector2D circlePosition = new Vector2D(cir.getCenterX(), cir.getCenterY());
                    Vector2D circlePosition = circlePositonList.get(i);
                    Vector2D circleVelocity = circleVelocityList.get(i);

                    //added part from here
                    // Euler Integration
                    // Update velocity
                    Vector2D frameAcceleration = acceleration.mult(frameDeltaTime);
                    circleVelocity = circleVelocity.add(frameAcceleration);
                    circleVelocityList.set(i, circleVelocity);
                    circlePosition = circlePosition.add(circleVelocity.mult(frameDeltaTime));
                    circlePositonList.set(i, circlePosition);

                    // Update position
                    cir.setCenterX(circlePosition.getX());
                    cir.setCenterY(circlePosition.getY());

                    if (cir.getCenterY() + cir.getRadius() > cir.getParent().prefHeight(0)) {
                        circleVelocityList.get(i).setY(-Math.abs(circleVelocity.getY()));
                    }
                    
                    if ()
                    
                    i++;
                }
                
//                for(int j = 0; j < (int)(circleList.get(0).getParent().prefWidth(0)); j++){
//                    Circle cle = new Circle(0, 0, 3);
//                    addToPane(cle);
//                }
            }
        }.start();
    }

}
