package view;

import enums.RadioButtonType;
import model.Canvas;

@SuppressWarnings("serial")
public class LinePopupMenu extends MyPopupMenu
{
	/*
	 * CONSTANTS
	 */
	//the different shapes that a Brush can be
	private static final RadioButtonType [] TOTAL_SHAPES = {RadioButtonType.SQUARE};
	
	//how many brush sizes there are
	private static final int TOTAL_SIZES = 3;
	
	//MUST BE LESS THAN TOTAL_SIZES 
	private static final int DEFAULT_SIZE_INDEX = 0;
	
	//MUST BE LESS THAN the length of TOTAL_SHAPES 
	private static final int DEFAULT_SHAPE_INDEX = 0;
		
	//how much the previous brush size differs from the next
	private static final int DIMENSION_DELTA = 10;
	
	
	public LinePopupMenu(Canvas canvas, RadioButtonType brush) 
	{
		super(TOTAL_SHAPES, TOTAL_SIZES, DEFAULT_SIZE_INDEX, DEFAULT_SHAPE_INDEX, DIMENSION_DELTA, canvas, brush);
	}
}
