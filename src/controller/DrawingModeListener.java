package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.RadioButtonType;
import model.Canvas;

/**
 * Changes the type of drawing the user will be able to draw on the Canvas, by recording the "most recently clicked" radio button
 * 
 * @author Michael Shur
 *
 */
public class DrawingModeListener implements ActionListener
{

	private final Canvas canvas;
	private final RadioButtonType type;

	public DrawingModeListener(Canvas canvas, RadioButtonType type) 
	{
		this.canvas = canvas;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		canvas.setCurrentlySelectedRadioButtonType(type);
	}
}
