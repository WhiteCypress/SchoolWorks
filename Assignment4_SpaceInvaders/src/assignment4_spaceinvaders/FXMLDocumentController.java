/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_spaceinvaders;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;
    private double lastFrameTime = 0.0;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private int enemySpeed;
    int moveCounter = 0;
    double lastTime = 0;

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setBackground(Background.EMPTY);

        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        //AssetManager.preloadAllAssets();

        for (int i = 0; i < 70; ++i) {                                          //14 column 5 row, total 70, not sure for the values here
            int enemyY = (i / 14) * 40;
            int enemyX = i % 14 * 40;
            Vector2D enemyPosition = new Vector2D(enemyX, enemyY);
            Vector2D enemyVelocity = new Vector2D(30, 0);
            Vector2D enemySize = new Vector2D(30, 30);

            Enemy enemy = new Enemy(enemyPosition, enemyVelocity, enemySize);
//            enemy.setX(x);
//            enemy.setY(y);
            enemyList.add(enemy);
            addToPane(enemy.getRectangle());
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                for (GameObject obj : enemyList) {
                    obj.update(frameDeltaTime);
                }

                if (currentTime - lastTime > 2 && moveCounter <= 2) {
                    // move the enemy
                    for (Enemy e : enemyList) {
                        e.setPosition(new Vector2D((e.getPosition().getX() + e.getVelocity().getX()), e.getPosition().getY())); 
                    }
                    moveCounter++;
                }else {
                    for (Enemy e : enemyList) {
                        e.setPosition(new Vector2D((e.getPosition().getX()), e.getPosition().getY() + e.getVelocity().getY()));
                        e.setVelocity(new Vector2D(-(e.getVelocity().getX()), e.getVelocity().getY()));
                    }
                    moveCounter = 0;
                }

            }
        }.start();

    }

    @FXML
    private void onMouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    @FXML
    private void onMouseClicked(MouseEvent e) {
        System.out.println("mouse clicked: " + e.getButton());
    }

}
