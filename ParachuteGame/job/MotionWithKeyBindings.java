/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

/**
 *
 * @author Noam
 */
import javax.swing.*;
import job.ImageMotion.MotionAction;

public class MotionWithKeyBindings
{
	private final JComponent component;

	public MotionWithKeyBindings(JComponent component)
	{
		this.component = component;
	}

	//  Move the component to its new location. The component will stop
	//  moving when it reaches the bounds of its container


	public MotionAction addAction(String name, int deltaX, int deltaY)
	{
		MotionAction action = new MotionAction(component, deltaX, deltaY);

		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(pressedKeyStroke, name);
		component.getActionMap().put(name, action);

		return action;
	}

	public static void addMotionSupport(JComponent component, int deltaX, int deltaY)
	{
		MotionWithKeyBindings motion = new MotionWithKeyBindings(component);
		motion.addAction("LEFT", -deltaX, deltaY);
		motion.addAction("RIGHT", deltaX, deltaY);
	}
}
