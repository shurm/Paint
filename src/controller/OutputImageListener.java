package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import enums.ImageType;
import util.ImageFromCanvasFactory;
import window.SaveImageWindow;

/**
 * Gets information from the user about the image they wish to create/save
 * 
 * @author Michael Shur
 *
 */
public class OutputImageListener implements ActionListener
{
	private SaveImageWindow saveImageWindow;
	
	public OutputImageListener(SaveImageWindow saveImageWindow)
	{
		this.saveImageWindow = saveImageWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ImageType imageType = saveImageWindow.getSelectedType();

		String fileName = saveImageWindow.getEnteredName();

		/*
		 * Error checks
		 */
		if(fileName==null)
		{
			JOptionPane.showMessageDialog(null, "Error, the picture's name may only contain alphanumeric (letters or digits) characters", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(imageType==null)
		{
			JOptionPane.showMessageDialog(null, "Error, please select an image type", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		new ImageFromCanvasFactory(saveImageWindow.getFilePath()+"/"+fileName, imageType, saveImageWindow.getCanvas().getWidth(), saveImageWindow.getCanvas().getHeight(), saveImageWindow.getCanvas()).outputImageToFile();

		saveImageWindow.dispose();
	}
}