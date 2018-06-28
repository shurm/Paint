package util;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Class which acts as a Producer of ImageIcons (Images)
 *  
 * @author Michael Shur
 *
 */
public class ImageIconFactory 
{
	/**
	 * 
	 * Creates a new scaled image
	 * 
	 * @param imageFilePath the filepath where the image is located
	 * @param targetWidth the new width of the image
	 * @param targetHeight the new height of the image
	 * 
	 * @return the new scaled image
	 */
	public static ImageIcon createScaledIcon(String imageFilePath, int targetWidth, int targetHeight)
	{
		URL resourcePath = ImageIconFactory.class.getResource(imageFilePath);
		return new ImageIcon(new ImageIcon(resourcePath).getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH));
	}
}
