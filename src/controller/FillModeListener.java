package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Canvas;

/**
 * Controller which listens for mouse clicks on the fill radio button 
 * 
 * @author Michael Shur
 *
 */
public class FillModeListener implements ActionListener
{
	private final Canvas canvas;
	
	public FillModeListener(Canvas canvas) {
		this.canvas = canvas;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//negates previous boolean value
		canvas.setFill(!canvas.getFill());
	}

}
