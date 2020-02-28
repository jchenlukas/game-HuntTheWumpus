/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * Hunter.java
 * represents the player moving around the cave
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Hunter extends Agent {

    private Vertex location;
    private boolean alive;
    private boolean armed;
    private BufferedImage unarmed;
    private BufferedImage hunterArmed;
    private BufferedImage failed;

    public Hunter(Vertex v) {
        super(v.getCol(), v.getRow());
        location = v;
        location.setVisible(true);
        alive = true;
        armed = false;

        try {
            unarmed = ImageIO.read(new File("UnArmed.jpg"));
            hunterArmed = ImageIO.read(new File("Armed.png"));
            failed = ImageIO.read(new File("Failed.png"));
        } 
        catch (IOException e) {
            System.out.print("Reading Image: failed");
        }
    }

    /**
     * overrides the parent draw method
     */
    public void draw(Graphics g, int scale) {

        location.draw(g, scale);
        if (alive) {
            if (armed) {
                g.drawImage(hunterArmed, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
            }
            else {
                g.drawImage(unarmed, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
            }
        }
        else {
            g.drawImage(failed, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
        }
    }

    /**
     * get the field alive
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * get the field armed
     */
    public boolean getArmed() {
        return armed;
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
     * set the field armed
     */
    public void setArmed(boolean a) {
        armed = a;
    }

    /**
     * set the field location
     */
    public void setLocation(Vertex v) {
        location = v;
    }
}