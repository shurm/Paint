package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.OpenImageListener;
import controller.SaveImageListener;
import model.Canvas;

/**
 * Panel which contains the save (capable of saving your drawings on the canvas) and open (capable of placing a background image on the canvas) buttons
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class ImageIOButtonPanel extends JPanel
{
	public ImageIOButtonPanel(Canvas canvas)
	{
		//button that when pressed will allow you to save the picture you created
		JButton saveImage= new JButton("Save Image");

		saveImage.addActionListener(new SaveImageListener(canvas));

		this.add(saveImage);

		//button that when pressed will allow you to get a image file and 
		//apply it to the paint canvas
		JButton openImage= new JButton("Open Image");

		openImage.addActionListener(new OpenImageListener(canvas));

		this.add(openImage);
	}
}
