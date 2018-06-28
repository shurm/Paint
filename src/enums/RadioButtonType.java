package enums;

import java.net.URL;

/**
 * All the radioButtons that will be displayed on the button panel. Simply add another type to see another radioButton
 * 
 * @author Michael Shur
 *
 */
public enum RadioButtonType 
{
	SQUARE(ShapeType.RECT,"rect.gif"), CIRCLE(ShapeType.OVAL,"circle.gif"), LINE(ShapeType.LINE,"line.gif"), ERASER("eraser.png"), BRUSH("brush.jpg");
	
	private static final String imageFilePathPrefix ="/images/";
	
	private final String fileName;
	
	private final ShapeType shapeType;

	private RadioButtonType(ShapeType shapeType, String fileName)
	{
		this.shapeType = shapeType;
		this.fileName = fileName;
	}
	
	private RadioButtonType(String fileName)
	{
		this(null, fileName);
	}
	
	public String getImageFilePath()
	{
		return imageFilePathPrefix+fileName;
	}

	public boolean isBrush() {
		return shapeType==null;
	}
	
	public ShapeType getCorrespondingShapeType() {
		return shapeType;
	}
}
