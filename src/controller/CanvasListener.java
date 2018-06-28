package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import enums.ShapeType;
import enums.RadioButtonType;
import model.Canvas;

/**
 * Controller that is "attached" to the Canvas GUI, which "listens" for user input (mouse clicks, etc) 
 * 
 * @author Michael Shur
 *
 */
public class CanvasListener extends MouseAdapter
{
	private final Canvas canvas;

	//the X and Y values of the mouse when it is first pressed
	private int originalX;
	private int originalY;
		
	public CanvasListener(Canvas canvas) 
	{
		this.canvas=canvas;
	}
	
	public void mousePressed(MouseEvent e) 
	{
		int x=e.getX(),y=e.getY();
		
		RadioButtonType type = canvas.getCurrentlySelectedRadioButtonType();
		
		if(type.isBrush())
		{
			canvas.set_X(x);
			canvas.set_Y(y);	
		}
		else
		{
			setDataForNonBrushShape(type,x,y);
		}
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		if(!canvas.getCurrentlySelectedRadioButtonType().isBrush())
		{
			canvas.addDrawing(canvas.getColor(),canvas.getItem(), canvas.getFill(), canvas.get_X(), canvas.get_Y(), canvas.get_X2(), canvas.get_Y2(),canvas.getBrushList().getStrokeSize(canvas.getCurrentlySelectedRadioButtonType()));			
		}
		
		canvas.setItem(null);
	}
	
	@Override
	public void mouseDragged(MouseEvent a)
	{

		RadioButtonType type=canvas.getCurrentlySelectedRadioButtonType();
		int x=a.getX(), y=a.getY();

		if(type.isBrush())
		{	
			int brushSize = canvas.getBrushList().getStrokeSize(type);
			canvas.set_X(x-brushSize/2);
			canvas.set_Y(y-brushSize/2);

			canvas.getBrushList().putIntoBrushMap(type,true);

		}
		else
		{
			setEndPointsOfShape(x,y);
		}
	}
	
	private void setDataForNonBrushShape(RadioButtonType type, int x ,int y)
	{
		canvas.setItem(type.getCorrespondingShapeType());
		
		originalX = x;

		originalY = y;

		canvas.set_X(x);
		canvas.set_Y(y);

		canvas.set_X2(x);
		canvas.set_Y2(y);
	}
	
	
	
	private void setEndPointsOfShape(int x, int y)
	{

		if(canvas.getItem()==ShapeType.LINE)
		{
			canvas.set_X2(x);
			canvas.set_Y2(y);
			return;
		}

		if(x>=originalX && y>=originalY)
		{
			canvas.set_X2(x);

			canvas.set_Y2(y);
		}

		else if(x<originalX && y>=originalY)
		{
			canvas.set_X(x);

			canvas.set_X2(originalX);

			canvas.set_Y2(y);

		}

		else if(x>=originalX && y<originalY)
		{
			canvas.set_Y(y);
			canvas.set_Y2(originalY);
			
			canvas.set_X2(x);
		}

		else 
		{
			canvas.set_Y(y);

			canvas.set_X(x);

			canvas.set_Y2(originalY);

			canvas.set_X2(originalX);
		}

	}
}
