package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Canvas;

/**
 * Controller which listens for mouse clicks on the clear button, which in turn clears the canvas
 * 
 * @author Michael Shur
 *
 */
public class ClearButtonListener implements ActionListener
{
	private final Canvas canvas;

	public ClearButtonListener(Canvas canvas) 
	{
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		canvas.reset();
	}
}
