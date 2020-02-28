/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * Landscape.java
 * updated version: stores the agents to they can be drawn
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;

public class Landscape {

    int width;
    int height;
    LinkedList<Agent> foregroundAgents = new LinkedList<Agent>();
    LinkedList<Agent> backgroundAgents = new LinkedList<Agent>();

    /**
     * constructor that sets the width and height fields, and initializes the agent list
     */
    public Landscape(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * returns the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * add an agent at the beginning of foregroundAgent
     */
    public void addForegroundAgent(Agent a) {
        foregroundAgents.addFirst(a);
    }
    
    /**
     * add an agent at the beginning of backgroundAgent
     */
    public void addBackgroundAgent(Agent a) {
        backgroundAgents.addFirst(a);
    }

    /**
     * returns a String representing the Landscape
     */
    public String toString() {
        String str = "";
        return str + (foregroundAgents.size() + backgroundAgents.size());
    }

    /**
     * Calls the draw method of all the agents on the Landscape
     * with @param scale
     */
    public void draw(Graphics g, int scale) {
        for(Agent element : backgroundAgents) {
            element.draw(g, scale);
        }
        for(Agent element : foregroundAgents) {
            element.draw(g, scale);
        }
    }

    public static void main(String[] args) {
        Landscape scape = new Landscape(500, 200);

        System.out.println(scape.getWidth());
        System.out.println(scape.getHeight());
    }
    
}