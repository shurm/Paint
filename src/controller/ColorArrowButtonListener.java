package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import window.CustomColorWindow;
import window.CustomColorWindow.ArrowButtonType;
import window.CustomColorWindow.ChangeColorFrameComponentsThread;

/**
 * 
 * Controller which listens for mouse presses and releases on the "arrow" buttons, which are displayed in the CustomColorWindow 
 * 
 * @author Michael Shur
 *
 */
public class ColorArrowButtonListener extends MouseAdapter
{
	private final ArrowButtonType arrowType;
	
	private final int colorComponentIndex;

	private ChangeColorFrameComponentsThread thread;
	
	private CustomColorWindow customColorWindow;
	
	public ColorArrowButtonListener(CustomColorWindow customColorWindow, int colorComponentIndex, ArrowButtonType arrowType) 
	{
		this.customColorWindow = customColorWindow;
		this.colorComponentIndex = colorComponentIndex;
		this.arrowType = arrowType;
	}
	
	
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		if(thread==null || thread.isAlive()==false)
		{	
			
			//start a new thread which will change the current color component values
			customColorWindow.setCurrentArrowButtonType(arrowType);

			customColorWindow.setColorComponentIndex(colorComponentIndex);

			thread = customColorWindow.new ChangeColorFrameComponentsThread();
			thread.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		if(thread!=null && thread.isAlive())
		{
			//ends the thread
			thread.endThread();
			thread=null;
		}
	}
}