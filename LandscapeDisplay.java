/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * LandscapeDisplay.java
 * modified after InteractiveLandscapeDisplay.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.util.*;


public class LandscapeDisplay {
    protected JFrame win;
	private Landscape scape;
    private LandscapePanel canvas;
	private int scale;
	private JPanel bottom;
	protected JButton quit;
	protected JButton reset;

	/**
	 * constructor
	 */
	public LandscapeDisplay(Landscape scape, int scale) {
		this.scale = scale;
        this.scape = scape;
        
		this.win = new JFrame("Hunt The Wumpus Game");

		win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

		this.canvas = new LandscapePanel(this.scape.getWidth(), this.scape.getHeight());

		quit = new JButton("Quit");
		reset = new JButton("Restart");
		bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.add( quit );
		bottom.add( reset );
			
		this.win.add( bottom, BorderLayout.SOUTH);
		this.win.pack();

		win.add(this.canvas, BorderLayout.CENTER);
		win.add(bottom, BorderLayout.SOUTH);
		win.pack();
		win.setVisible(true);
	}


	/**
	 * private class that can create a LandscapePanel object
	 */
	private class LandscapePanel extends JPanel {
		
	/**
	 * Creates the panel
	 * @param height the height of the panel in pixels
	 * @param width the width of the panel in pixels
	 **/
		public LandscapePanel(int height, int width) {
			super();
			this.setPreferredSize( new Dimension( width, height ) );
			this.setBackground(Color.white);
		}

		/**
		 * Method overridden from JComponent that is responsible for
		 * drawing components on the screen.  The supplied Graphics
		 * object is used to draw.
		 * 
		 * @param g		the Graphics object used for drawing
		 */
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			scape.draw( g, scale );
		}
    } // end class

	/**
	 * repain the canvas
	 */
	public void repaint()
	{
		this.win.repaint();
	}

    public static void main(String[] args) throws InterruptedException {
    
        Landscape scape = new Landscape(512, 512);
        Vertex v = new Vertex(0, 0);
        v.setVisible(true);
        Hunter hunter = new Hunter(v);
        
        scape.addBackgroundAgent(v);
        scape.addForegroundAgent(hunter);
        
        LandscapeDisplay display = new LandscapeDisplay(scape, 64);
        display.repaint();
    }
	
} 