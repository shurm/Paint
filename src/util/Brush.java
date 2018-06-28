package util;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * @author Michael Shur
 *
 */
public class Brush extends Stroke
{
	
	private final AtomicBoolean drawOnCanvas = new AtomicBoolean(false);

	private final BrushColorInterface brushColorInterface;
	
	public Brush(BrushColorInterface brushColorInterface) 
	{
		this.brushColorInterface = brushColorInterface;
	}


	public boolean isBeingDrawnOnCanvas() {
		return drawOnCanvas.get();
	}

	public void setDrawOnCanvas(boolean b) {
		this.drawOnCanvas.set(b);
	}

	public Color getColor() 
	{
		return brushColorInterface.getColor();
	}
	
	/**
	 * How a brush determines its color
	 */
	public static interface BrushColorInterface 
	{
		public Color getColor();
	}
}
