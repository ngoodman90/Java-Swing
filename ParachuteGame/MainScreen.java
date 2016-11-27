/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Noam
 */
public class MainScreen extends JFrame{
    
    private ArrayList<JLabel> imageArray = new ArrayList<>();
    private boolean imagesLoaded = false;
    
    public static void main(String[] args)
    {
        DisplayMode displayMode = new DisplayMode(1920, 1080, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        MainScreen mainScreen = new MainScreen();
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                    //  Create a panel with a component to be moved
                    Screen screen = new Screen();
                    screen.setFullScreen(displayMode, mainScreen);
                    JPanel content = new JPanel();
                    content.setLayout(null);
                    
                    mainScreen.loadBackground(content);
                    mainScreen.createBoat(content);
                    mainScreen.createPlane(content);
                    mainScreen.createParachute(content);
                    mainScreen.imagesLoaded = true;
                    
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JFrame frame = new JFrame("Main Screen");
                    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                    frame.add( content );
                    frame.setSize(ProjectConstants.WIDTH, ProjectConstants.HEIGHT);
                    frame.setLocationByPlatform( true );
                    frame.setVisible(true);
            }
        });
    }
    
    public void loadBackground(JPanel content)
    {
        try{
        ImageIcon background = ImageMotion.createImageIcon(ProjectConstants.BACKGROUND_IMG, "background image");
        JLabel backgroundComponent = new JLabel( background );
        backgroundComponent.setSize( backgroundComponent.getPreferredSize() );
        backgroundComponent.setLocation(0, 0);
        content.add(backgroundComponent);
        } catch (Exception e) {}
    }
    public void createBoat(JPanel content)
    {
        ImageIcon boatIcon = ImageMotion.createImageIcon(ProjectConstants.BOAT_IMG, "boat image"); 
        JLabel boatComponent = new JLabel( boatIcon );
        boatComponent.setSize( boatComponent.getPreferredSize() );
        boatComponent.setLocation(40, 40);
        content.add(boatComponent);
        MotionWithKeyBindings.addMotionSupport(boatComponent, 10, 0);
    }
    
    public void createPlane(JPanel content)
    {
        ImageIcon planeIcon = ImageMotion.createImageIcon(ProjectConstants.PLANE_IMG, "plane image"); 
        JLabel planeComponent = new JLabel( planeIcon );
        planeComponent.setSize( planeComponent.getPreferredSize() );
        planeComponent.setLocation(500, 500);
        content.add(planeComponent);
        ImageMotion.MotionAction planeMovementAction = new ImageMotion.MotionAction(planeComponent, 1, 0);
        new Timer(50, planeMovementAction).start(); 
    }
    
    public void createParachute(JPanel content)
    {
        ImageIcon parachuteIcon = ImageMotion.createImageIcon(ProjectConstants.PARACHUTE_IMG, "parachute image"); 
        JLabel parachuteComponent = new JLabel( parachuteIcon );
        parachuteComponent.setSize( parachuteComponent.getPreferredSize() );
        parachuteComponent.setLocation(100, 100);
        content.add(parachuteComponent);
        ImageMotion.MotionAction parachuteMovementAction = new ImageMotion.MotionAction(parachuteComponent, 0, 5);
        new Timer(50, parachuteMovementAction).start(); 
    }
}
