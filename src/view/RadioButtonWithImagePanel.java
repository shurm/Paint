package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.ImageButtonListener;
import util.ImageIconFactory;

/**
 * Panel which contains a radio button along with a corresponding descriptive image
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class RadioButtonWithImagePanel extends JPanel
{
	//default width and height of the descriptive image
	private static final int IMAGE_WIDTH=50, IMAGE_HEIGHT=50;
	
	//how far apart the radio button and image are
	private static final int HORIZONTAL_GAP = 10;
	
	private final JRadioButton radioButton = new JRadioButton();
	
	//the width and height of the descriptive image
	private final int imageWidth, imageHeight;
	
	
	public RadioButtonWithImagePanel(ButtonGroup group, ActionListener onclickListener, String imageFilePath) 
	{
		//calls other constructor
		this(group, onclickListener, imageFilePath, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	public RadioButtonWithImagePanel(ButtonGroup group, ActionListener onclickListener, String imageFilePath, int imageWidth, int imageHeight) 
	{
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, 0));
		
		radioButton.addActionListener(onclickListener);
		if(group!=null)
			group.add(radioButton);
		
		this.add(radioButton);
		this.add(createCoolImageButton(imageFilePath));
	}
	
	private JRadioButton createCoolImageButton(String imagePath) 
	{
		JRadioButton imageButton= new JRadioButton();			
			
		ImageIcon newIcon = ImageIconFactory.createScaledIcon(imagePath, imageWidth, imageHeight);
		
		imageButton.setIcon(newIcon);

		imageButton.addActionListener(new ImageButtonListener(radioButton));
		
		return imageButton;
	}

	public void pressButton() 
	{
		radioButton.doClick();	
	}
}
