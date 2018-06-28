package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ColorButtonsPanel;
import window.CustomColorWindow;

/**
 * 
 * @author Michael Shur
 *
 */
public class CreateNewColorListener implements ActionListener
{
	private final CustomColorWindow colorFrame;
	private final ColorButtonsPanel cbp;
	
	
	public CreateNewColorListener(CustomColorWindow colorFrame, ColorButtonsPanel cbp) 
	{
		this.colorFrame = colorFrame;
		this.cbp = cbp;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		cbp.addColorToPopups(colorFrame.createColor());
		
		//closes the CustomColorWindow
		colorFrame.dispose();
	}
}