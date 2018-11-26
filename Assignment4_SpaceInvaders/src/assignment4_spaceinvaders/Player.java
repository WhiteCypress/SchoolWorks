/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_spaceinvaders;

/**
 *
 * @author zoewong
 */
public class Player extends GameObject {

    protected int lives;

    public Player(Vector2D position, Vector2D velocity, Vector2D objectSize) {
        super(position, velocity, objectSize);
    }

    public Player(Vector2D position, Vector2D velocity, Vector2D objectSize, int lives) {
        super(position, velocity, objectSize);
        this.lives = 3;
    }

    public Vector2D getObjectSize() {
        return objectSize;
    }

    public Vector2D getPosition() {
        return position;
    }
}
