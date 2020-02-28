/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * Wumpus.java
 * represents the Wumpus
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wumpus extends Agent {

    private Vertex location;
    private boolean visible;
    private boolean alive;
    private BufferedImage monster;
    private BufferedImage deadmonster;

    public Wumpus(Vertex v) {
        super(v.getCol(), v.getRow());
        location = v;
        visible = false;
        alive = true;

        try {
            monster = ImageIO.read(new File("monster.png"));
            deadmonster = ImageIO.read(new File("deadmonster.png"));
        } 
        catch (IOException e) {
            System.out.print("Reading Image: failed");
        }
    }

    /**
     * get the field alive
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * get the field visible
     */
    public boolean getVisible() {
        return visible;
    }

    /** 
     * gets the field location 
     */
    public Vertex getLocation() {
        return location;
    }

    /**
     * set the field alive
     */
    public void setAlive(boolean a) {
        alive = a;
    }

    /**
     * set the field visible
     */
    public void setVisible(boolean v) {
        visible = v;
    }

    /**
     * set the field location
     */
    public void setLocation(Vertex v) {
        location = v;
    }

    /**
     * overrides the parent draw method
     */
    public void draw(Graphics g, int scale) {
        location.draw(g, scale);
        if (visible) {
            if(alive) {
                g.drawImage(monster, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
            }
            else {
                g.drawImage(deadmonster, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
            }
        }
    }
}