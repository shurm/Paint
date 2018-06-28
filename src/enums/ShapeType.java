package enums;

/**
 * The different shapes you can draw
 * 
 * @author Michael Shur
 *
 */
public enum ShapeType 
{
	RECT,OVAL,LINE(true);
	private final boolean hasThickness;
	private ShapeType()
	{
		this(false);
		
	}
	
	private ShapeType(boolean b)
	{
		hasThickness=b;
	}

	public boolean HasThickness() {
		return hasThickness;
	}
}
