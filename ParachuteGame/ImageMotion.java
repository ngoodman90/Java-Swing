/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author Noam
 */
public class ImageMotion {
    
    
 
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path, String description) 
    {
        Image image;
        try { 
            image = javax.imageio.ImageIO.read(new File(path));
            return new ImageIcon(image, description);
        }
        catch (Exception e) {}

        return null;
    }
    
    /* Moves a component according to deltas*/
    public static void move(JComponent component, int deltaX, int deltaY)
    {
            int componentWidth = component.getSize().width;
            int componentHeight = component.getSize().height;

            Dimension parentSize = component.getParent().getSize();
            
            int parentWidth  = parentSize.width;
            int parentHeight = parentSize.height;

            //  Determine next X position

            int nextX = Math.max(component.getLocation().x + deltaX, 0);

            if ( nextX + componentWidth > parentWidth)
            {
                    nextX = parentWidth - componentWidth;
            }

            //  Determine next Y position

            int nextY = Math.max(component.getLocation().y + deltaY, 0);

            if ( nextY + componentHeight > parentHeight)
            {
                    nextY = parentHeight - componentHeight;
            }

            //  Move the component

            component.setLocation(nextX, nextY);
    }
    
    public static class MotionAction extends AbstractAction implements ActionListener
    {
        private JComponent component;
        private int deltaX;
        private int deltaY;

        public MotionAction(JComponent component, int deltaX, int deltaY)
        {
                this.component = component;
                this.deltaX = deltaX;
                this.deltaY = deltaY;
        }

        public void actionPerformed(ActionEvent e)
        {
            move(component, deltaX, deltaY);
        }
    }
    
    
    public static class AutoMotionAction extends AbstractAction implements ActionListener
    {
        private JComponent component;
        private int deltaX;
        private int deltaY;

        public AutoMotionAction(JComponent component, int deltaX, int deltaY)
        {
                this.component = component;
                this.deltaX = deltaX;
                this.deltaY = deltaY;
        }

        public void actionPerformed(ActionEvent e)
        {
            int componentWidth = component.getSize().width;
            int componentHeight = component.getSize().height;

            Dimension parentSize = component.getParent().getSize();
            
            int parentWidth  = parentSize.width;
            int parentHeight = parentSize.height;
            
            double x = Math.random();
            if ((0.045 < x &&  x < 0.05) || (0.225 < x &&  x < 0.23) || (0.785 < x && x < 0.79) || (0.885 < x && x < 0.89) || (0.985 < x && x < 0.99))
            {
                this.deltaX *= -1;
                this.deltaY *= -1;
            }
            
            int nextX = Math.max(component.getLocation().x + deltaX, 0);

            //check if component is at right edge
            if ( nextX + componentWidth > parentWidth)
            {
                    nextX = parentWidth - componentWidth;
                    this.deltaX *= -1;//flip direction
            }
            //check if component is at left edge
            if (nextX == 0)
                this.deltaX *= -1;//flip direction

            //  Determine next Y position

            int nextY = Math.max(component.getLocation().y + deltaY, 0);

            if ( nextY + componentHeight > parentHeight)
            {
                    nextY = parentHeight - componentHeight;
                    this.deltaY *= -1;//flip direction
            }
            //check if component is at top edge
            if (nextY == 0)
                this.deltaY *= -1;//flip direction

            //  Move the component

            component.setLocation(nextX, nextY);
        }
    }
}
