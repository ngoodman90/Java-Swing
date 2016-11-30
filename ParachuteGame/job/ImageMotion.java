/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Noam
 */
public class ImageMotion {
    
    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @param description
     * @return  */
    protected static ImageIcon createImageIcon(String path, String description) 
    {
        Image image;
        try { 
            image = javax.imageio.ImageIO.read(new File(path));
            return new ImageIcon(image, description);
        }
        catch (IOException e) {}

        return null;
    }
    
    protected static JLabel createImageLabel(String path, String description, Point location) 
    {
        try { 
            ImageIcon icon = ImageMotion.createImageIcon(path, description); 
            JLabel component = new JLabel( icon );
            component.setSize( component.getPreferredSize() );
            component.setLocation(location);
            return component;
        }
        catch (Exception e) {}

        return null;
    }
    
    /* Moves a component according to deltas*/
    public static void move(JComponent component, int deltaX, int deltaY)
    {
            int componentWidth = component.getSize().width;
            Dimension parentSize = component.getParent().getSize();
            int parentWidth  = parentSize.width;
            
            int nextX = Math.max(component.getLocation().x + deltaX, 0);
            
            if ( nextX + componentWidth > parentWidth)
                    nextX = parentWidth - componentWidth;
            
            //  Move the component
            component.setLocation(nextX, component.getLocation().y);
    }
    
    public static class MotionAction extends AbstractAction implements ActionListener
    {
        private final JComponent component;
        private final int deltaX;
        private final int deltaY;

        public MotionAction(JComponent component, int deltaX, int deltaY)
        {
                this.component = component;
                this.deltaX = deltaX;
                this.deltaY = deltaY;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            move(component, deltaX, deltaY);
        }
    }
    
    
    public static class AutoMotionAction extends AbstractAction implements ActionListener
    {
        private final MainScreenGUI mainScreen;
        private final JLabel component1;
        private final JLabel component2;
        private final ImageIcon imageIcon;
        private int deltaX;
        private final ArrayList<JLabel> parachutes = new ArrayList<>();

        public AutoMotionAction(MainScreenGUI mainScreen, JLabel component1, JLabel  component2, ImageIcon imageIcon, int deltaX)
        {
                this.mainScreen = mainScreen;
                this.component1 = component1;
                this.component2 = component2;
                this.imageIcon = imageIcon;
                this.deltaX = deltaX;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int component1Width = 600;
            Dimension parentSize = component1.getParent().getSize();
            int parentWidth  = parentSize.width;
            
            
            int nextX = Math.max(component1.getLocation().x + deltaX, 0);
            //check if component is at right edge
            if ( nextX + component1Width > parentWidth)
            {
                    nextX = parentWidth - component1Width;
                    this.deltaX *= -1;//flip direction
            }
            //check if component is at left edge
            if (nextX == 0)
                this.deltaX *= -1;//flip direction
            
            if (switchDirection())
                this.deltaX *= -1;
            
            if (createNewParachute())
            {
                JLabel newComponent = new JLabel( imageIcon );
                newComponent.setSize( newComponent.getPreferredSize() );
                parachutes.add(newComponent);
                newComponent.setLocation(component1.getLocation());
                this.mainScreen.getContent().add(newComponent);
            }
            
            
            //update parachutes
            for (int i = 0; i < parachutes.size(); i++)
            {
                JLabel parachute = parachutes.get(i);
                if (parachute.getLocation().y  >= ProjectConstants.INITIAL_BOAT_HEIGHT)
                {
                    if (!intersects(parachute, this.component2))
                    {
                        MainScreenGUI.livesRemaining--;
                        if (MainScreenGUI.livesRemaining == 0)
                        {
                            System.out.println("Game Over");
                            System.out.println("Score: " + MainScreenGUI.score);
                            System.exit(0);
                        }
                    }
                    else
                        MainScreenGUI.score += 10;
                    parachute.getParent().remove(parachute);
                    parachutes.remove(parachute);
                }
                else
                    parachute.setLocation(parachute.getLocation().x,parachute.getLocation().y + ProjectConstants.PARACUTE_DELTA_Y);
            }

            component1.setLocation(nextX, component1.getLocation().y);
        }
        
        public boolean switchDirection()
        {
            double x = Math.random();//Make this image switch directions at random
            return ((0.045 < x &&  x < 0.05) || (0.785 < x && x < 0.79));
        }
        
        public boolean createNewParachute()
        {
            double x = Math.random();//Make this image switch directions at random
            return ((0.275 < x &&  x < 0.28) || (0.585 < x && x < 0.59));
        }
        
        public boolean intersects(JLabel component1, JLabel component2){
            Area areaA = new Area(component1.getBounds());
            Area areaB = new Area(component2.getBounds());
            return areaA.intersects(areaB.getBounds2D());
        }
    }
}
