package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.CanvasListener;
import enums.RadioButtonType;
import enums.ShapeType;
import util.Brush;
import util.Shape;
import util.StrokeList;


/**
 * 
 * The GUI component the user can draw on. (keeps a list of objects the user has drawn and adds/removes to it as needed)
 * 
 * @author Michael Shur
 * 
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel
{
	private RadioButtonType currentlySelectedRadioButtonType = RadioButtonType.LINE;

	private ShapeType currentShapeType = currentlySelectedRadioButtonType.getCorrespondingShapeType();

	//keeps track off the current shape's color status (whether it is filled or not) 
	private AtomicBoolean fill= new AtomicBoolean(false);

	
	//List of shapes that are currently on the canvas
	private LinkedList<Shape> shapesOnCanvas = new LinkedList<>();

	//the canvas's background image (if there is one)
	private BufferedImage backgroundImage = null;

	private Color currentShapeColor = Color.black;

	//the coordinates of the current shape on the canvas 
	private int x = 0, y = 0, x2 = 0, y2 = 0;

	private final StrokeList brushes = new StrokeList(this);

	public Canvas()
	{   
		//makes the canvas background white
		this.setBackground(Color.white);

		//the controller which listens for user interaction involving this canvas
		CanvasListener canvasListener = new CanvasListener(this);

		//attaches canvasListener to this Canvas
		this.addMouseMotionListener(canvasListener);
		this.addMouseListener(canvasListener);
	}


	public void paint( Graphics a )
	{
		Graphics2D g = (Graphics2D) a;

		super.paint(g);

		drawShapes(g);


		if(!currentlySelectedRadioButtonType.isBrush())
		{
			g.setColor(currentShapeColor);

			int width=(x2-x), height=(y2-y);

			drawShape(g, currentShapeType, currentShapeColor, fill.get(), x, y, width, height, brushes.getStrokeSize(currentlySelectedRadioButtonType));
		}
		else
		{
			for(Iterator<Brush> brushIterator = brushes.iterator(); brushIterator.hasNext();)
			{
				Brush brush = brushIterator.next();
				if(brush.isBeingDrawnOnCanvas())
				{
					addDrawing(brush.getColor(), brush.getShapeType(), true, x, y, x+brush.getSize(), y+brush.getSize(),0);

					brush.setDrawOnCanvas(false);

					break;
				}
			}
		} 

		//calls paint again
		repaint();
	}


	/**
	 * 
	 * draws the shapes in the shapesOnCanvas list into g
	 * 
	 * @param g the graphics context the method draws into
	 */
	public void drawShapes(Graphics2D g)
	{
		if(backgroundImage!=null)
			g.drawImage(backgroundImage,null, 0, 0);

		for(Iterator<Shape> iterator=shapesOnCanvas.iterator();iterator.hasNext();)
		{
			Shape currentShape = iterator.next();

			Color color = currentShape.getColor();
			if(currentShape.isEraseSquare())
				color = this.getBackground();

			drawShape(g,currentShape.getType(),color,currentShape.isFilled(),currentShape.getX(), currentShape.getY(), currentShape.getX2(), currentShape.getY2(), currentShape.getThickness());
		}
	}


	/*
	 * Draw the specified Shape onto this Canvas
	 */
	private void drawShape(Graphics2D g, ShapeType type, Color color, boolean filled, int x, int y, int width, int height, int thickness)
	{
		if(type==null)
			return;

		g.setColor(color);
		switch(type)
		{	
			case OVAL: 
			{
				if(!filled)
					g.drawOval(x, y, width, height);
	
				else
					g.fillOval(x, y, width, height);
	
				break;
			}
			case RECT: 
			{
				if(!filled)
					g.drawRect(x, y, width, height);
	
				else 
					g.fillRect(x, y, width, height);
	
				break;
			}
			case LINE: 
			{
	
				double theta = calculateRotation(width, height);
	
				g.rotate(theta,x,y);
	
				g.fillRect(x,y-thickness/2, (int) Math.sqrt(width*width+height*height), thickness);
	
				g.rotate(-1*theta,x,y);
	
				break;
			}
		}
	}

	private double calculateRotation(int width, int height)
	{
		double radians;

		if(width!=0)
		{
			double d=Math.PI;

			if(width>0)
				d=2*Math.PI;

			radians=d+Math.atan((double)(height)/(width));
		}
		else
		{
			if(height>0)
				radians=Math.PI/2;
			else
				radians=3*Math.PI/2;
		}
		return radians;
	}
	
	/**
	 * adds a new Drawing into the shapesOnCanvas list
	 * 
	 * 
	 * 
	 * @param colorOfShape the color of the new Shape
	 * @param shapeType the type of the new Shape
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */

	public synchronized void addDrawing(Color colorOfShape, ShapeType shapeType, boolean filled, int x, int y, int x2, int y2, int thickness)
	{
		try
		{
			Shape shape= new Shape(currentlySelectedRadioButtonType,colorOfShape,filled,shapeType,x,y,x2-x,y2-y, thickness);
			shapesOnCanvas.addLast(shape);
		}
		catch (OutOfMemoryError e)
		{
			new ShowDialogBoxThread().start();
		}
	}

	/**
	 *  Clears this canvas of all previous drawings
	 */
	public void reset()
	{
		x = y = x2 = y2 = 0;

		shapesOnCanvas.clear();
		backgroundImage = null;
	}


	/**
	 * Removes the drawings that were drawn in the last specified timeInterval 
	 *
	 * @param  timeInterval the time in milliseconds
	 */
	public void deleteDrawings(int timeInterval) 
	{
		Shape i = shapesOnCanvas.removeLast();

		long time=i.getCreationTime();
		boolean b = i.isBrushStroke();

		while(!shapesOnCanvas.isEmpty() && b && time-shapesOnCanvas.getLast().getCreationTime() < timeInterval )
		{
			shapesOnCanvas.removeLast();
			b = i.isBrushStroke();
		}
	}

	/**
	 * Determines if drawings are on the canvas
	 * 
	 * @return true if there are drawings on the canvas, false if there are none.
	 */
	public boolean hasDrawings() 
	{
		return !shapesOnCanvas.isEmpty();
	}



	/*
	 * GETTERS AND SETTERS of private variables  
	 */


	public void setFill(boolean s)
	{
		fill.set(s);
	}

	public boolean getFill()
	{
		return fill.get();
	}

	public void setColor(Color color)
	{
		currentShapeColor = color;
	}

	public Color getColor()
	{
		return currentShapeColor;
	}

	public void set_X2(int x2)
	{
		this.x2=x2;
	}

	public void set_Y2(int y2)
	{
		this.y2=y2;
	}

	public int get_X2()
	{
		return this.x2;
	}

	public int get_Y2()
	{
		return y2;
	}

	public void set_X(int x2) 
	{
		x=x2;
	}

	public void set_Y(int y2) 
	{
		y=y2;
	}

	public int get_X() 
	{
		return x;
	}

	public int get_Y() 
	{
		return y;
	}

	public void setItem(ShapeType shapeType)
	{
		currentShapeType = shapeType;
	}

	public ShapeType getItem()
	{
		return currentShapeType;
	}

	public RadioButtonType getCurrentlySelectedRadioButtonType() 
	{
		return currentlySelectedRadioButtonType;
	}

	public void setCurrentlySelectedRadioButtonType(RadioButtonType currentlySelectedRadioButtonType) 
	{
		this.currentlySelectedRadioButtonType = currentlySelectedRadioButtonType;
	}


	public void setBackgroundImage(BufferedImage image)
	{
		backgroundImage = image;
	}

	/*
	 * Gets the list of brushes
	 */
	public StrokeList getBrushList() 
	{
		return brushes;
	}

	//Displays out of memory Error Message
	private class ShowDialogBoxThread extends Thread
	{
		public void run()
		{
			JOptionPane.showMessageDialog(null, "Out of Storage Space. To continue drawing you must either press the "
					+ "undo button or the clear button.","Error, Out of Storage Space. ",JOptionPane.ERROR_MESSAGE);
		}
	}
}