/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * Agent.java
 * updated version: on a grid with int values
 */

import java.awt.Graphics;

public class Agent {
    
    int x;
    int y;

    /**
     * a constructor that sets the position
     */
    public Agent(int x0, int y0) {
        x = x0;
        y = y0;
    }

    /**
     * returns the x position
     */
    public int getCol() {
        return x;
    }

    /**
     * returns the y position
     */
    public int getRow() {
        return y;
    }

    /**
     * sets the x position
     */
    public void setCol(int newX) {
        x = newX;
    }

    /**
     * sets the y position
     */
    public void setRow(int newY) {
        y = newY;
    }

    /**
     * returns a String containing the x and y positions
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw(Graphics g, int scale) {

    }

    public int getCategory() {
        return 0;
    }

    public static void main(String[] args) {
        Agent newagent = new Agent(4, 5);

        System.out.println(newagent.getCol());
        System.out.println(newagent.getRow());

        newagent.setCol(17);
        newagent.setRow(29);

        System.out.println(newagent);
    }
}