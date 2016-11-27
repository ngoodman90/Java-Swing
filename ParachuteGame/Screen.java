/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author Noam
 */
public class Screen {
    
    private GraphicsDevice graphicsDevice;
    
    public Screen()
    {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = env.getDefaultScreenDevice();
    }
    
    public void setFullScreen(DisplayMode displayMode, JFrame window)
    {
        window.setUndecorated(true);
        window.setResizable(false);
        graphicsDevice.setFullScreenWindow(window);
        
        if (displayMode != null && graphicsDevice.isDisplayChangeSupported())
        {
            try{
                graphicsDevice.setDisplayMode(displayMode);
            } catch (Exception e){}
        }
    }
    
    public Window getFullScreenWindow()
    {
        return graphicsDevice.getFullScreenWindow();
    }
    
    public void restoreScreen()
    {
        Window w = graphicsDevice.getFullScreenWindow();
        if (w != null)
            w.dispose();
        graphicsDevice.setFullScreenWindow(null);
    }
    
    
    
    
}
