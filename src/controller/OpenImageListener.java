package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import model.Canvas;

/**
 * Controller which listens for mouse clicks on the open button, which prompts the user for an image file.
 * If the user select an image file, that image is drawn on the canvas as a background
 * 
 * @author Michael Shur
 *
 */
public class OpenImageListener implements ActionListener
{
	//Window that is able to capture an image file
	private final JFileChooser getImage= new JFileChooser();
			
	private Canvas canvas;
	
	public OpenImageListener(Canvas canvas) 
	{
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(getImage.isShowing()==false)
		{
			//Open the getImage Window at the center of the screen
			int a=getImage.showOpenDialog(null);

			//if you selected File
			if(a==JFileChooser.APPROVE_OPTION)
			{
				//gets the File you selected
				File inFile=getImage.getSelectedFile();

				//if the File is an image it will be stored into the variable image
				BufferedImage image = null;
				try {
					image = ImageIO.read(inFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				canvas.setBackgroundImage(image);
			}
		}
	}
}
