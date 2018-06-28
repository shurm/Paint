package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Canvas;

/**
 * Removes drawings from the Canvas if they were drawn in the last TIME_INTERVAL_IN_MILLISECONDS
 * 
 * @author Michael Shur
 *
 */
public class UndoButtonListener implements ActionListener
{
	
	private static final int TIME_INTERVAL_IN_MILLISECONDS = 900;
	
	private Canvas canvas;
	public UndoButtonListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(!canvas.hasDrawings())
			return;
		
		canvas.deleteDrawings(TIME_INTERVAL_IN_MILLISECONDS);
	}
}
