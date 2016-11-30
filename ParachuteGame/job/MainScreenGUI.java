
package job;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This is the main class
 * The images are loaded, along with their actions
 * The boat is loaded with key binding with the left and right keys
 * The plane is loaded with a timer that moves it constantly and automatically.
 * The plane changes directions and generates a parachute at random.
 */
public class MainScreenGUI{
    
    public static int livesRemaining = ProjectConstants.NUM_OF_LIVES;
    public static int score = 0;
    private JFrame content;
    
    private static final MainScreenGUI mainScreen = new MainScreenGUI( );
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            mainScreen.content = new JFrame();
            mainScreen.content.setLayout(null);
            mainScreen.loadImages();
        });
    }
    
    public JFrame getContent(){ return this.content;}
    
    public void loadImages()
    {
        this.loadBackground();
        JLabel boatComponent = this.createBoat();
        this.createPlaneAndParachute(boatComponent);
        this.createShark();
        this.displayScreen();
    }
    
    public void loadBackground()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.content.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.content.setExtendedState(this.content.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.content.setLocationByPlatform( true );
        try{
            JLabel contentPane = ImageMotion.createImageLabel(ProjectConstants.BACKGROUND_IMG, "background image", new Point(0, 0));
            this.content.setContentPane( contentPane );
        } catch (Exception e) {}
    }
    
    public JLabel createBoat()
    {
        JLabel boatComponent = ImageMotion.createImageLabel(ProjectConstants.BOAT_IMG, "boat image", ProjectConstants.INITIAL_BOAT_LOCATION);
        this.content.add(boatComponent);
        MotionWithKeyBindings.addMotionSupport(boatComponent, ProjectConstants.BOAT_DELTA_X, 0);
        return boatComponent;
    }
    
    public void createPlaneAndParachute(JLabel boatComponent)
    {
        //create plane component
        JLabel planeComponent = ImageMotion.createImageLabel(ProjectConstants.PLANE_IMG, "plane image", ProjectConstants.INITIAL_PLANE_LOCATION);
        this.content.add(planeComponent);
        
        //create parachute component
        ImageIcon parachuteIcon = ImageMotion.createImageIcon(ProjectConstants.PARACHUTE_IMG, "parachute image"); 
        JLabel parachuteComponent = new JLabel(parachuteIcon);
        parachuteComponent.setSize(parachuteComponent.getPreferredSize());

        ImageMotion.AutoMotionAction planeMovementAction = new ImageMotion.AutoMotionAction(this, planeComponent, boatComponent, parachuteIcon, ProjectConstants.PLANE_DELTA_X);
        new Timer(25, planeMovementAction).start(); 
    }
    
    public void createShark()
    {
        try{
            ImageIcon shark = ImageMotion.createImageIcon(ProjectConstants.SHARK_IMG, "shark image");
            JLabel sharkContent = new JLabel(shark);
            sharkContent.setSize( sharkContent.getPreferredSize() );
            sharkContent.setLocation(200, 50);
            this.content.add(sharkContent);
        }catch (Exception e) {System.err.println("Not loading shark");}
    }
   
    public void displayScreen() {this.content.setVisible(true);}
}
