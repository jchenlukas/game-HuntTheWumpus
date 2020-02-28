/**
 * Jiyao Chen
 * CS231 Spring
 * Project 9
 * Vertex.java
 * each vertex will represent a room and will connect to up to four other rooms (vertices)
 * one in each of the cardinal directions (north, east, south, west)
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Graphics;
import java.awt.Color;

public class Vertex extends Agent implements Comparable<Vertex> {

    private Vertex[] neighborVertices;
    private int cost;
    private boolean marked;
    private boolean visible;

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }

    /**
     * constructor
     */
    public Vertex(int x0, int y0) {
        super(x0, y0);
        neighborVertices = new Vertex[4];
        cost = 0;
        marked = false;
        visible = false;
    }

    /**
     * returns the compass opposite of a direction (i.e. South for North...)
     */
    public static Direction opposite(Direction d) {
        if(d == Direction.NORTH) {
            return Direction.SOUTH;
        }
        else if (d == Direction.EAST) {
            return Direction.WEST;
        }
        else if (d == Direction.SOUTH) {
            return Direction.NORTH;
        }
        else {
            return Direction.EAST;
        }
    }

    /**
     * modify the object's adjacency list/map 
     * so that it connects with @param other
     */
    public void connect(Vertex other, Direction dir) {
        neighborVertices[dir.ordinal()] = other;
    }

    /**
     * returns the Vertex in the specified direction or null
     */
    public Vertex getNeighbor(Direction dir) {
        return neighborVertices[dir.ordinal()];
    }

    /**
     * returns a Collection, which could be an ArrayList, of all of the object's neighbors
     */
    public ArrayList<Vertex> getNeighbors() {
        ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
        for (Vertex entry: neighborVertices) {
            if (entry != null) {
                neighbors.add(entry);
            }
        }

        return neighbors; 
    }

    /**
     * returns the field cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * returns the field marked
     */
    public boolean getMarked() {
        return marked;
    }

    /**
     * sets the field cost
     */
    public void setCost(int c) {
        cost = c;
    }

    /**
     * sets the field marked
     */
    public void setMarked(boolean m) {
        marked = m;
    }

    /**
     * sets the field visible
     */
    public void setVisible(boolean v) {
        visible = v;
    }

    /**
     * basic implementation of draw method that draw on @param g with @param scale
     */
    public void draw(Graphics g, int scale) {
        if (!this.visible) {
            return;
        }
		
		int xpos = getCol();
		int ypos = getRow();
		int border = 2;
		int half = scale / 2;
		int eighth = scale / 8;
		int sixteenth = scale / 16;
		
		// draw rectangle for the walls of the cave
		if (this.cost <= 2)
			// wumpus is nearby
			g.setColor(Color.red);
		else
			// wumpus is not nearby
			g.setColor(Color.black);
			
		g.drawRect(xpos + border, ypos + border, scale - 2, scale - 2);
		
		// draw doorways as boxes
        g.setColor(Color.black);
        
		if (this.neighborVertices[0] != null)
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.neighborVertices[1] != null)
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                eighth + sixteenth, eighth);
		if (this.neighborVertices[2] != null)
			g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
				  eighth, eighth + sixteenth);
		if (this.neighborVertices[3] != null)
			g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
    }

    /**
     * overrides the abstract method compareTo 
     */
    public int compareTo(Vertex v) {
        return cost - v.getCost();
    }

    /**
     * return a String containing (at least) the number of neighbors, the cost, and the marked flag
     */
    public String toString() {
        return "Neighbors num: " + neighborVertices.length + "Cost: " + getCost() + "Marked: " + getMarked();
    }

    /**
     * test methods
     */
    public static void main(String[] args) {
        Vertex test1 = new Vertex(1, 1);
        test1.setCost(10);

        Vertex test2 = new Vertex(2, 2);
        test2.setCost(20);

        System.out.println(opposite(Direction.SOUTH));
    }
}

/** 
 * comparator class
 * compares cost
 */
class VertexComparator implements Comparator<Vertex> {
    public int compare(Vertex v1, Vertex v2) {
        if (v1.getCost() > v2.getCost())
            return 1;
        if (v1.getCost() < v2.getCost())
            return -1;
        else
            return 0;
    }
}