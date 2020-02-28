/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * Teleport.java
 * represents the teleport place
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Teleport extends Agent {

    private Vertex location;
    private boolean visible;
    private BufferedImage place;

    public Teleport(Vertex v) {
        super(v.getCol(), v.getRow());
        location = v;
        visible = true;

        try {
            place = ImageIO.read(new File("Teleport.png"));
        } 
        catch (IOException e) {
            System.out.print("Reading Image: failed");
        }
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
     * set the field visible
     */
    public void setVisible(boolean v) {
        visible = v;
    }

    /**
     * overrides the parent draw method
     */
    public void draw(Graphics g, int scale) {
        location.draw(g, scale);
        if (visible) {
            g.drawImage(place, location.getCol()+13, location.getRow()+13, scale - 25, scale - 25, null);
        }
    }
}