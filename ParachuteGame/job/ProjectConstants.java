/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.Point;

/**
 *
 * @author Noam
 */
public class ProjectConstants {
    
    
    //Images
    public static final String BACKGROUND_IMG = "images/background.jpg";
    public static final String BOAT_IMG = "images/transparentBoat.png";
    public static final String PLANE_IMG = "images/transparentPlane.png";
    public static final String PARACHUTE_IMG = "images/transparentParachute.png";
    public static final String SHARK_IMG = "images/transparentShark.png";
    
    //Sizes
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //location
    public static final int INITIAL_BOAT_WIDTH = 200;
    public static final int INITIAL_BOAT_HEIGHT = 1000;
    public static final Point INITIAL_BOAT_LOCATION = new Point(INITIAL_BOAT_WIDTH, INITIAL_BOAT_HEIGHT);
    public static final Point INITIAL_PLANE_LOCATION = new Point(700, 100);
    
    //lives
    public static final int NUM_OF_LIVES = 3;
    
    //deltas
    public static final int BOAT_DELTA_X = 10;
    public static final int PLANE_DELTA_X = 5;
    public static final int PARACUTE_DELTA_Y = 5;
    
    
}
