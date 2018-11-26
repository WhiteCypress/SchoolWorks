/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_spaceinvaders;

/**
 *
 * @author cstuser
 */
public class Enemy extends GameObject{
    
    public Enemy(Vector2D position, Vector2D velocity, Vector2D objectSize) {
        super(position, velocity, objectSize);
    }
    
    public Vector2D getObjectSize(){
        return objectSize;
    }
    
    public Vector2D getPosition(){
        return position;
    }
    
    public Vector2D getVelocity(){
        return velocity;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
    
    

}
