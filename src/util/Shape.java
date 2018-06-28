package util;

import java.awt.Color;

import enums.ShapeType;
import enums.RadioButtonType;

/**
 * An object that can be represented (using common properties i.e size, location, color) as some form of geometric shape. 
 * 
 * @author Michael Shur
 *
 */
public class Shape 
{
	//dimensions of the 2 dimensional shape
	private final int x, y, width, height;
	
	//the shape's color
	private final Color colorOfShape;
	
	//whether or not the shape is filled
	private final boolean fill;
		
	//what kind of shape it is (rectangle, circle, line)
	private final ShapeType type;
	
	//time when the Object was created
	private final long creationTime;
	
	//RadioButton that was selected at the time of the Shape's creation
	private final RadioButtonType buttonSelectedType;

	private final int thickness;
	
	/**
	 * Constructs a new Shape that will have an appearance based on the arguments provided
	 * 
	 * @param buttonSelectedType radio button that was selected at the time this constructor was called
	 * @param colorOfShape desired color of the shape
	 * @param fill whether or not you want this shape to be filled.
	 * @param shapeType what kind of shape you want this Shape to be (rectangle, circle, line)
	 * @param x top left corner x coordinate
	 * @param y top left corner y coordinate
	 * @param x2 lower right corner x coordinate
	 * @param y2 lower right corner y coordinate
	 */
	public Shape(RadioButtonType buttonSelectedType, Color colorOfShape, boolean fill, ShapeType shapeType, int x, int y, int width, int height, int thickness)
	{
		creationTime = System.currentTimeMillis();
		
		this.buttonSelectedType = buttonSelectedType;
		
		this.colorOfShape = colorOfShape;
		
		this.fill = fill;
		
		type = shapeType;
		
		this.x = x;
		
		this.y = y;
		
		this.width = width;
		
		this.height = height;
		
		this.thickness = thickness;
	}
	
	
	
	public boolean isEraseSquare() {
		return buttonSelectedType==RadioButtonType.ERASER;
	}

	public boolean isBrushStroke() {
		return buttonSelectedType==RadioButtonType.ERASER || buttonSelectedType==RadioButtonType.BRUSH;
	}
	
	
	
	/*
	 * GETTERS
	 */
	public boolean isFilled() {
		return fill;
	}

	public Color getColor() {
		return colorOfShape;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX2() {
		return width;
	}
	
	public int getY2() {
		return height;
	}

	public ShapeType getType() {
		return type;
	}
	
	public long getCreationTime()
	{
		return creationTime;
	}
	
	public int getThickness() 
	{
		return thickness;
	}
}
