package realtimesimulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Circle circle;
    private Vector2D circlePosition;
    private Vector2D circleVelocity;
    
    private double lastFrameTime = 0.0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double circlePosX = circle.getCenterX();
        double circlePosY = circle.getCenterY();
        
        circlePosition = new Vector2D(circlePosX, circlePosY);
        circleVelocity = new Vector2D(0.0f, 0.0f);
        
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        final Vector2D acceleration = new Vector2D(0.0, 980);
        
        new AnimationTimer()
        {
            @Override
            public void handle(long now) {

                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double  frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                
                // Euler Integration
                // Update velocity
                Vector2D frameAcceleration = acceleration.mult(frameDeltaTime);
                circleVelocity = circleVelocity.add(frameAcceleration);
                circlePosition = circlePosition.add(circleVelocity.mult(frameDeltaTime));
                
                // Update position
                circle.setCenterX(circlePosition.getX());
                circle.setCenterY(circlePosition.getY());
                
                
                if (circle.getCenterY() + circle.getRadius() > circle.getParent().prefHeight(0))
                {
                    circleVelocity.setY(-Math.abs(circleVelocity.getY()));
                }
            }
        }.start();        
    }    
    
}
