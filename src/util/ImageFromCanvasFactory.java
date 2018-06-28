package util;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import enums.ImageType;
import model.Canvas;

/**
 * Creates a new image from the Drawings which are currently on the specified Canvas
 *  
 * @author Michael Shur
 *
 */
public class ImageFromCanvasFactory 
{	
	private final ImageType type;
	private final String name;
	private final int width;
	private final int height;
	private final Canvas paintCanvas;
	
	/**
	 * 
	 * @param name the name of the image
	 * @param type the image's file type (.png, .jpg, etc)
	 * @param width the image's width
	 * @param height the image's height
	 * @param canvas
	 */
	public ImageFromCanvasFactory(String name, ImageType type, int width, int height, Canvas canvas)
	{
		this.type = type;
		this.name = name;
		this.width = width;
		this.height = height;
		this.paintCanvas = canvas;
	}
	
	public void outputImageToFile()
	{

		// Constructs a BufferedImage of one of the predefined image types.
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Create a graphics which can be used to draw into the buffered image
		Graphics2D graphicsContext = bufferedImage.createGraphics();

		graphicsContext.setColor(paintCanvas.getBackground());
		graphicsContext.fillRect(0, 0, width, height);
		
		
		//store canvas drawings into graphicsContext
		paintCanvas.drawShapes(graphicsContext);
		
		
		// Disposes of this graphics context and releases any system resources that it is using.
		graphicsContext.dispose();
		
		//output to file
		File file = new File(name+type.getExtension());
		try {
			ImageIO.write(bufferedImage, type.getExtension(), file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
