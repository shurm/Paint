package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ColorButtonsPanel;
import window.CustomColorWindow;

/**
 * Controller which listens for mouse clicks on the custom button, which opens a window that enables the user to create a custom color
 * 
 * @author Michael Shur
 *
 */
public class OpenColorFrameListener implements ActionListener
{
	private ColorButtonsPanel cbp;
	
	public OpenColorFrameListener(ColorButtonsPanel cbp) 
	{
		this.cbp = cbp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Window that allows you to build your own custom color
		new CustomColorWindow(cbp).show();
	}
}
