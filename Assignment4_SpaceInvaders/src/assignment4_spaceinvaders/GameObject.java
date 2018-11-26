package assignment4_spaceinvaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class GameObject {
    protected Rectangle rectangle;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D objectSize;
    
    public GameObject(Vector2D position, Vector2D velocity, Vector2D objectSize)
    {
        this.position = position;
        this.velocity = velocity;
        this.objectSize = objectSize;
        
        rectangle = new Rectangle(position.getX(), position.getY(), objectSize.getX(), objectSize.getY());
    }
    
    
    public Rectangle getRectangle()
    {
        return rectangle;
    }
    
    public void update(double dt)
    {
        // Update position
        position = position.add(velocity.mult(dt));
        rectangle.setX(position.getX());
        rectangle.setY(position.getY());
    }
}
