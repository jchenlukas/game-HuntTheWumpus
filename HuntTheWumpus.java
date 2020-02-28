/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * HuntTheWumpus.java
 * the main game controller class
 */

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HuntTheWumpus {
    
    private Graph graph;
    private Hunter hunter;
    private Wumpus wumpus;
    private Wumpus moreWumpus;
    private Teleport teleport;
    private Trap trap;
    private Landscape scape;
    private Vertex[][] grid;
    private LandscapeDisplay display;
    private enum PlayState {NORMAL, TELEPORT, PLAY, OVER, QUIT}
    private PlayState state;
    // private LandscapePanel canvas;
	// private JPanel panel;
	// protected JButton quit;
    // protected JButton reset;
    // protected JFrame win;
    // private int scale;

    /**
     * contructor that builds the graph, inserts the vertices, Hunter, and Wumpus into the Landscape 
     * and adds any other user interface elements
     */
    public HuntTheWumpus() {
        scape = new Landscape(720, 720);
        display = new LandscapeDisplay(scape, 90);
        graph = new Graph();
        grid = new Vertex[8][8];

        //lay out the game
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Vertex(i * 90, j * 90);
                scape.addBackgroundAgent(grid[i][j]);
            }
        }

      // draw edges that connect the vertices in the grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                graph.addEdge(grid[i][j], Vertex.Direction.SOUTH, grid[i][j+1]);   //vertical
                graph.addEdge(grid[j][i], Vertex.Direction.EAST, grid[j+1][i]);    //horizontal
            }
        }

        Random rnd = new Random();
        state = PlayState.NORMAL;

        //initialize the position of the hunter, wumpus, and the teleport place
        int hX = rnd.nextInt(8);
        int hY = rnd.nextInt(8);
        int wX = rnd.nextInt(8);
        int wY = rnd.nextInt(8);
        int tX = rnd.nextInt(8);
        int tY = rnd.nextInt(8);
        int trX = rnd.nextInt(8);
        int trY = rnd.nextInt(8);


        while((wX == hX) && (wY == hY)) {
            wX = rnd.nextInt(8);
            wY = rnd.nextInt(8);
        }
        hunter = new Hunter(grid[hX][hY]);
        wumpus = new Wumpus(grid[wX][wY]);

        while((tX == hX && tY == hY) || (tX == wX && tY == wY)) {
            tX = rnd.nextInt(8);
            tY = rnd.nextInt(8);
        }
        teleport = new Teleport(grid[tX][tY]);

        while((trX == hX && trY == hY) || (trX == wX && trY == wY) || (trX == tX && trY == tY)) {
            trX = rnd.nextInt(8);
            trY = rnd.nextInt(8);
        }
        trap = new Trap(grid[trX][trY]);

        scape.addForegroundAgent(hunter);
        scape.addForegroundAgent(wumpus);
        scape.addForegroundAgent(teleport);
        scape.addForegroundAgent(trap);
        graph.shortestPath(wumpus.getLocation());
        
        //construct the window of the game
        KeyStrokes strokes = new KeyStrokes();
        display.win.addKeyListener(strokes);
        display.win.setFocusable(true);
        display.win.requestFocus();
        display.quit.addActionListener(strokes);
        display.reset.addActionListener(strokes);
    }
    
    public void reset() {
        display.win.dispose();

        scape = new Landscape(720, 720);
        display = new LandscapeDisplay(scape, 90);
        graph = new Graph();
        grid = new Vertex[8][8];

        //lay out the game
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Vertex(i * 90, j * 90);
                scape.addBackgroundAgent(grid[i][j]);
            }
        }

      // draw edges that connect the vertices in the grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                graph.addEdge(grid[i][j], Vertex.Direction.SOUTH, grid[i][j+1]);   //vertical
                graph.addEdge(grid[j][i], Vertex.Direction.EAST, grid[j+1][i]);    //horizontal
            }
        }

        Random rnd = new Random();
        state = PlayState.NORMAL;
        //initialize the position of the hunter and wumpus
        int hX = rnd.nextInt(8);
        int hY = rnd.nextInt(8);
        int wX = rnd.nextInt(8);
        int wY = rnd.nextInt(8);
        int tX = rnd.nextInt(8);
        int tY = rnd.nextInt(8);
        int trX = rnd.nextInt(8);
        int trY = rnd.nextInt(8);


        while((wX == hX) && (wY == hY)) {
            wX = rnd.nextInt(8);
            wY = rnd.nextInt(8);
        }
        hunter = new Hunter(grid[hX][hY]);
        wumpus = new Wumpus(grid[wX][wY]);

        while((tX == hX && tY == hY) || (tX == wX && tY == wY)) {
            tX = rnd.nextInt(8);
            tY = rnd.nextInt(8);
        }
        teleport = new Teleport(grid[tX][tY]);

        while((trX == hX && trY == hY) || (trX == wX && trY == wY) || (trX == tX && trY == tY)) {
            trX = rnd.nextInt(8);
            trY = rnd.nextInt(8);
        }
        trap = new Trap(grid[trX][trY]);

        scape.addForegroundAgent(hunter);
        scape.addForegroundAgent(wumpus);
        scape.addForegroundAgent(teleport);
        scape.addForegroundAgent(trap);
        graph.shortestPath(wumpus.getLocation());

        //construct the window of the game
        KeyStrokes strokes = new KeyStrokes();
        display.win.addKeyListener(strokes);
        display.win.setFocusable(true);
        display.win.requestFocus();
        display.quit.addActionListener(strokes);
        display.reset.addActionListener(strokes);
    }



    private class KeyStrokes extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {

            if (state == PlayState.NORMAL) {
                //check if the hunter can teleport
                if (hunter.getLocation().equals(teleport.getLocation())) {
                    Random rnd = new Random();
                    int nX = rnd.nextInt(8);
                    int nY = rnd.nextInt(8);
                    Vertex newLocation = grid[nX][nY];
                    while(newLocation.equals(wumpus.getLocation())) {
                        nX = rnd.nextInt(8);
                        nY = rnd.nextInt(8);
                        newLocation = grid[nX][nY];
                    }
                    hunter.setLocation(newLocation);
                    display.repaint();
                }
                //check if the hunter steps on the trap
                else if(hunter.getLocation().equals(trap.getLocation())) {
                    Random rnd = new Random();
                    int nX = rnd.nextInt(8);
                    int nY = rnd.nextInt(8);
                    Vertex newLocation = grid[nX][nY];
                    while(newLocation.equals(wumpus.getLocation()) || newLocation.equals(teleport.getLocation())) {
                        nX = rnd.nextInt(8);
                        nY = rnd.nextInt(8);
                        newLocation = grid[nX][nY];
                    }
                    moreWumpus = new Wumpus(newLocation);
                    scape.addForegroundAgent(moreWumpus);
                    // moreWumpus.setLocation(newLocation);
                    moreWumpus.setVisible(true);
                    trap.setVisible(true);
                    display.repaint();

                }

                if(("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    Vertex nNeighbor = hunter.getLocation().getNeighbor(Vertex.Direction.NORTH);
                    if (nNeighbor != null) {
                        nNeighbor.setVisible(true);
                        hunter.setLocation(nNeighbor);
                    }
                }
    
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    Vertex sNeighbor = hunter.getLocation().getNeighbor(Vertex.Direction.SOUTH);
                    if (sNeighbor != null) {
                        sNeighbor.setVisible(true);
                        hunter.setLocation(sNeighbor);
                    }
                }
    
                else if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    Vertex wNeighbor = hunter.getLocation().getNeighbor(Vertex.Direction.WEST);
                    if (wNeighbor != null) {
                        wNeighbor.setVisible(true);
                        hunter.setLocation(wNeighbor);
                    }
                }

                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    Vertex eNeighbor = hunter.getLocation().getNeighbor(Vertex.Direction.EAST);
                    if (eNeighbor != null) {
                        eNeighbor.setVisible(true);
                        hunter.setLocation(eNeighbor);
                    }
                }
    
                //hunter lost
                if (hunter.getLocation().equals(wumpus.getLocation())) {
                    wumpus.setVisible(true);
                    hunter.setAlive(false);
                    state = PlayState.OVER;
                }
    
                //fire the weapon
                else if (("" + e.getKeyChar()).equalsIgnoreCase(" ")) {
                    hunter.setArmed(true);
                    state = PlayState.PLAY;
                }
            }
    
            else if (state == PlayState.PLAY) {
                //cancel the weapon
                if (("" + e.getKeyChar()).equalsIgnoreCase(" ")) {
                    hunter.setArmed(false);
                    state = PlayState.NORMAL;
                }
    
                if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    if (hunter.getLocation().getNeighbor(Vertex.Direction.NORTH).equals(wumpus.getLocation())) {
                        wumpus.setAlive(false);
                        wumpus.setVisible(true);
                    }
                    else {  //lost
                        wumpus.setVisible(true);
                        hunter.setAlive(false);
                        wumpus.getLocation().setVisible(true);
                    }        
                    state = PlayState.OVER;    
                }
                if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    if (hunter.getLocation().getNeighbor(Vertex.Direction.SOUTH).equals(wumpus.getLocation())) { // hunter wins
                        wumpus.setAlive(false);
                        wumpus.setVisible(true);
                    }
                    else {  //lost
                        wumpus.setVisible(true);
                        hunter.setAlive(false);
                        wumpus.getLocation().setVisible(true);
                    }         
                    state = PlayState.OVER;   
                }
    
                if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    if (hunter.getLocation().getNeighbor(Vertex.Direction.WEST).equals(wumpus.getLocation())) {//hunter wins
                        wumpus.setAlive(false);
                        wumpus.setVisible(true);
                    }
                    else {  //lost
                        wumpus.setVisible(true);
                        hunter.setAlive(false);
                        wumpus.getLocation().setVisible(true);
                    }         
                    state = PlayState.OVER;   
                }
            
                if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    if (hunter.getLocation().getNeighbor(Vertex.Direction.EAST).equals(wumpus.getLocation())) {// hunter wins
                        wumpus.setAlive(false);
                        wumpus.setVisible(true);
                    }
                    else {  //lost
                        wumpus.setVisible(true);
                        hunter.setAlive(false);
                        wumpus.getLocation().setVisible(true);
                    }         
                    state = PlayState.OVER;   
                }
            }
    
            else if (state == PlayState.OVER) {
                System.out.println("End of game!");
                if (hunter.getAlive())
                    System.out.println("Victory!");
                return;
            }
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equalsIgnoreCase("Quit"))
                state = PlayState.QUIT;
            if (event.getActionCommand().equalsIgnoreCase("Restart"))
                reset();
            }
        }

    public static void main(String[] argv) throws InterruptedException {
      HuntTheWumpus game = new HuntTheWumpus();
      while (game.state != PlayState.QUIT) {
          game.display.repaint();

      }
      game.display.win.dispose();
    }
}