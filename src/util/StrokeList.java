package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import enums.RadioButtonType;
import enums.ShapeType;
import model.Canvas;

/**
 * List of all the Strokes that the can be drawn on the Canvas
 * 
 * @author Michael Shur
 *
 */
public class StrokeList 
{
	//enables O(1) retrieval time of brush information
	private Map<RadioButtonType,Stroke> strokeMap = new HashMap<>();
	
	//list of strokes which are brushes
	private List<Brush> brushes = new LinkedList<>();
	
	public StrokeList(Canvas canvas)
	{
		Brush.BrushColorInterface[] colorMethods = { ()->{return canvas.getBackground();}, ()->{return canvas.getColor();} };
		
		int i=0;
		for(RadioButtonType type : RadioButtonType.values())
		{
			
			if(type.isBrush())
			{
				Brush newBrush = new Brush(colorMethods[i++]);
				strokeMap.put(type, newBrush);
				brushes.add(newBrush);
			}
			else if (type.getCorrespondingShapeType().HasThickness())
			{
				Stroke s = new Stroke();
				strokeMap.put(type, s);
			}
		}
	}

	public Iterator<Brush> iterator() 
	{
		return brushes.iterator();
	}

	public void putIntoBrushMap(RadioButtonType type, boolean b) 
	{
		((Brush) strokeMap.get(type)).setDrawOnCanvas(b);
	}

	public int getStrokeSize(RadioButtonType brush) 
	{
		if(strokeMap.containsKey(brush))
			return strokeMap.get(brush).getSize();
		
		return 0;
	}

	public void setStrokeSize(RadioButtonType brush, int size) 
	{
		strokeMap.get(brush).setSize(size);
	}

	public void setStrokeShape(RadioButtonType brush, ShapeType shapeType) 
	{
		strokeMap.get(brush).setShapeType(shapeType);
	}

	public ShapeType getStrokeShape(RadioButtonType brush)
	{
		return strokeMap.get(brush).getShapeType();
	}
}
