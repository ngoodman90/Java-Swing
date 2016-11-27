/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
public class MainScreen{
    
    private JFrame content;
    
    public static void main(String[] args)
    {
//        DisplayMode displayMode = new DisplayMode(1920, 1080, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
          MainScreen mainScreen = new MainScreen();
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                    //  Create a panel with a component to be moved
//                    Screen screen = new Screen();
//                    screen.setFullScreen(displayMode, mainScreen);
                mainScreen.content = new JFrame();
                mainScreen.content.setLayout(null);
                    
                mainScreen.loadBackground();
                mainScreen.createBoat();
                mainScreen.createPlane();
                mainScreen.createParachute();
                
                
                JFrame.setDefaultLookAndFeelDecorated(true);
                mainScreen.content.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                mainScreen.content.setSize(ProjectConstants.WIDTH, ProjectConstants.HEIGHT);
                mainScreen.content.setLocationByPlatform( true );
                mainScreen.content.setVisible(true);
            }
        });
    }
    
    public void loadBackground()
    {
        try{
            ImageIcon background = ImageMotion.createImageIcon(ProjectConstants.BACKGROUND_IMG, "background image");
            JLabel contentPane = new JLabel();
            contentPane.setIcon( background );
            contentPane.setLayout( new BorderLayout() );
            this.content.setContentPane( contentPane );
        } catch (Exception e) {}
    }
    public void createBoat()
    {
        ImageIcon boatIcon = ImageMotion.createImageIcon(ProjectConstants.BOAT_IMG, "boat image"); 
        JLabel boatComponent = new JLabel( boatIcon );
        boatComponent.setSize( boatComponent.getPreferredSize() );
        boatComponent.setLocation(500, 500);
        this.content.add(boatComponent);
        MotionWithKeyBindings.addMotionSupport(boatComponent, 10, 0);
    }
    
    public void createPlane()
    {
        ImageIcon planeIcon = ImageMotion.createImageIcon(ProjectConstants.PLANE_IMG, "plane image"); 
        JLabel planeComponent = new JLabel( planeIcon );
        planeComponent.setSize( planeComponent.getPreferredSize() );
        planeComponent.setLocation(40, 40);
        this.content.add(planeComponent);
        ImageMotion.AutoMotionAction planeMovementAction = new ImageMotion.AutoMotionAction(planeComponent, 15, 0);
        new Timer(50, planeMovementAction).start(); 
    }
    
    public void createParachute()
    {
        ImageIcon parachuteIcon = ImageMotion.createImageIcon(ProjectConstants.PARACHUTE_IMG, "parachute image"); 
        JLabel parachuteComponent = new JLabel( parachuteIcon );
        parachuteComponent.setSize( parachuteComponent.getPreferredSize() );
        parachuteComponent.setLocation(100, 100);
        this.content.add(parachuteComponent);
        ImageMotion.AutoMotionAction parachuteMovementAction = new ImageMotion.AutoMotionAction(parachuteComponent, 0, 10);
        new Timer(15, parachuteMovementAction).start(); 
    }
}
