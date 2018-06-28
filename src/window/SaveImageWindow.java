package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.OutputImageListener;
import enums.ImageType;
import model.Canvas;
import util.MyUIManager;

/**
 * The Window that appears when the user wants to save his/her canvas drawing
 * 
 * @author Michael Shur
 */
public class SaveImageWindow extends AbstractApplicationWindow
{
	//this window's width and height
	private static final int WINDOW_WIDTH = 300, WINDOW_HEIGHT = 250;

	//this window's title
	private static final String TITLE = "Enter Image Details";

	//spacing between the image radio button options
	private static final int VERTICAL_SPACING = 10;

	
	private final JTextField fileNameText = new JTextField(15);

	private final ButtonGroup radioButtons = new ButtonGroup();

	//the canvas the new image will be retrieved from
	private final Canvas canvas;
	
	private final String filePath;

	public SaveImageWindow(Canvas canvas, String filePath)
	{
		super(TITLE, JFrame.DISPOSE_ON_CLOSE, WINDOW_WIDTH, WINDOW_HEIGHT, false);

		this.canvas = canvas;
		this.filePath = filePath;
		
		MyUIManager.changeLookAndFeel(MyUIManager.UITypes.Nimbus);
		
		this.setLayout(new BorderLayout());

		this.add(createTopLabelPanel(), BorderLayout.NORTH);

		this.add(createConfirmationButtonPanel(),  BorderLayout.SOUTH);

		this.add(createTextFieldPanel(),  BorderLayout.EAST);

		this.add(createRadioButtonPanel(),BorderLayout.WEST);
		
		MyUIManager.revertLookAndFeel();
	}

	private JPanel createTopLabelPanel() 
	{
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new FlowLayout(FlowLayout.CENTER));

		wrapper.add(new JLabel(TITLE));

		return wrapper;
	}

	private JPanel createRadioButtonPanel()
	{
		ImageType[] recognizedImageTypes = ImageType.values();

		JPanel radioButtonPanel = new JPanel();

		GridLayout bl=new GridLayout(recognizedImageTypes.length+1,1,0,VERTICAL_SPACING);

		radioButtonPanel.setLayout(bl);

		radioButtonPanel.add(new JLabel("Select Image type."));

		for(ImageType imageType : recognizedImageTypes)
		{
			JRadioButton radioButton = new JRadioButton(imageType.name());
			radioButtons.add(radioButton);
			radioButtonPanel.add(radioButton);
		}

		return getWrapper(radioButtonPanel);
	}

	private JPanel createTextFieldPanel()
	{
		JPanel radioButtonPanel = new JPanel();

		GridLayout bl=new GridLayout(2,1,0,VERTICAL_SPACING);

		radioButtonPanel.setLayout(bl);

		radioButtonPanel.add(new JLabel("Give your picture a name:"));

		radioButtonPanel.add(fileNameText);
		
		return getWrapper(radioButtonPanel);
	}
	
	private JPanel getWrapper(JPanel content)
	{
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
		wrapper.add(content);
		return wrapper;
	}

	private JPanel createConfirmationButtonPanel()
	{
		JPanel confirmationButtonPanel = new JPanel();
		confirmationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton ok = new JButton("ok"), cancel = new JButton("cancel");

		ok.addActionListener(new OutputImageListener(this));
		cancel.addActionListener(new CancelButtonListener());

		confirmationButtonPanel.add(ok);

		confirmationButtonPanel.add(cancel);

		return confirmationButtonPanel;
	}

	public String getEnteredName() 
	{
		String fileNameInput=fileNameText.getText();

		int endIndex = fileNameInput.length()-1;

		//gets rid of trailing space
		while(endIndex>=0 && Character.isWhitespace(fileNameInput.charAt(endIndex)))
		{
			endIndex--;
		}

		//tests if all the characters entered are alphanumeric (letters or digits)
		StringBuilder stringBuilder = new StringBuilder();
		for(int a=0;a<=endIndex;a++)
		{
			char c=fileNameInput.charAt(a);

			if(!Character.isLetterOrDigit(c))
				return null;
			stringBuilder.append(c);
		}

		String returnString = stringBuilder.toString(); 

		if(returnString.isEmpty())
			return null;

		return returnString;
	}

	/**
	 * Determines which radio button is selected
	 * 
	 * @return
	 */
	public ImageType getSelectedType() 
	{
		Enumeration<AbstractButton> radioButtonEnumeration = radioButtons.getElements();

		int index=0;
		while(radioButtonEnumeration.hasMoreElements())
		{
			AbstractButton b = radioButtonEnumeration.nextElement();
			if(b.isSelected())
			{
				return ImageType.values()[index];
			}
			index++;
		}
		return null;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public String getFilePath() {
		return filePath;
	}

	private class CancelButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dispose();
		}
	}
}
