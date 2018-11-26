/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realtimesimulation;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;

    private double lastFrameTime = 0.0;
    private ArrayList<Circle> circleList = new ArrayList<>();
    private ArrayList<Vector2D> circleVelocityList = new ArrayList<>();

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        AssetManager.preloadAllAssets();

        // Add 20 random circles
        for (int i = 0; i < 20; ++i) {
            Random rng = new Random();
            int width = (int) pane.getPrefWidth();
            int height = (int) pane.getPrefHeight();
            int x = rng.nextInt(width);
            int y = rng.nextInt(height);
            int radius = rng.nextInt(20) + 10;

            Circle c = new Circle(0, 0, radius);
            c.setCenterX(x);
            c.setCenterY(y);
            circleList.add(c);
            circleVelocityList.add(new Vector2D((rng.nextDouble() - 0.5) * 400, (rng.nextDouble() - 0.5) * 400));
            addToPane(c);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                // Move circles every frame
                for (int i = 0; i < circleList.size(); i++) {
                    Circle c = circleList.get(i);
                    Vector2D position = new Vector2D(c.getCenterX(), c.getCenterY());
                    Vector2D v = circleVelocityList.get(i);
                    position = position.add(v.mult(frameDeltaTime));
                    c.setCenterX(position.getX());
                    c.setCenterY(position.getY());

                    // collision with edges
                    if (c.getCenterX() - c.getRadius() < 0) {
                        v.setX(Math.abs(v.getX()));
                        //make a sound when collide
                        AudioClip clip = AssetManager.getPopSound();
                        clip.play();
                    }

                    if (c.getCenterX() + c.getRadius() > pane.getWidth()) {
                        v.setX(-Math.abs(v.getX()));
                        //make a sound when collide
                        AudioClip clip = AssetManager.getPopSound();
                        clip.play();
                    }

                    if (c.getCenterY() - c.getRadius() < 0) {
                        v.setY(Math.abs(v.getY()));
                        //make a sound when collide
                        AudioClip clip = AssetManager.getPopSound();
                        clip.play();
                    }

                    if (c.getCenterY() + c.getRadius() > pane.getHeight()) {
                        v.setY(-Math.abs(v.getY()));
                        //make a sound when collide
                        AudioClip clip = AssetManager.getPopSound();
                        clip.play();
                    }
                }

                // TODO - Collision detection and response
                for (int i = 0; i < circleList.size(); i++) {
                    for (int j = i + 1; j < circleList.size(); j++) {
                        Circle circle1 = circleList.get(i);
                        Circle circle2 = circleList.get(j);

                        Vector2D c1 = new Vector2D(circle1.getCenterX(), circle1.getCenterY());
                        Vector2D c2 = new Vector2D(circle2.getCenterX(), circle2.getCenterY());

                        Vector2D n = c2.sub(c1);
                        double distance = n.magnitude();

                        if (distance < circle1.getRadius() + circle2.getRadius()) {
                            //compute normal and tangent vectors
                            n.normalize();
                            Vector2D t = new Vector2D(-n.getY(), n.getX());

                            //separate circles
                            double overlap = distance - (circle1.getRadius() + circle2.getRadius());
                            c1 = c1.add(n.mult(overlap / 2));
                            c2 = c2.sub(n.mult(overlap / 2));
                            circle1.setCenterX(c1.getX());
                            circle1.setCenterY(c1.getY());
                            circle2.setCenterX(c2.getX());
                            circle2.setCenterY(c2.getY());

                            //decompose velocities, project them on n and t
                            Vector2D v1 = circleVelocityList.get(i);
                            Vector2D v2 = circleVelocityList.get(j);

                            Vector2D v1N = n.mult(v1.dot(n));
                            Vector2D v2N = n.mult(v2.dot(n));

                            Vector2D v1T = n.mult(v1.dot(t));
                            Vector2D v2T = n.mult(v2.dot(t));

                            //change Velocity
                            v1.set(v1T.add(v2N));
                            v2.set(v2T.add(v1N));

                            //make a sound when collide
                            AudioClip clip = AssetManager.getPopSound();
                            clip.play();
                        }
                    }
                }

            }
        }.start();
    }

}
