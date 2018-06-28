package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

/**
 * Assumes an image is paired with a corresponding radio button, and the image was clicked its corresponding radio button also gets clicked
 * 
 * @author Michael Shur
 *
 */
public class ImageButtonListener implements ActionListener
{
	private final JRadioButton radioButton;
	
	public ImageButtonListener(JRadioButton radioButton)
	{
		this.radioButton = radioButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//performs a "click"
		radioButton.doClick();
	}
}
