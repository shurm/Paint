package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.FillModeListener;
import model.Canvas;
import util.MyUIManager;

/**
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class LowerButtonPanel extends JPanel
{
	private static final int ROWS = 5;
	private static final String fillBucketImagePath = "/images/bucket.png";
	

	public LowerButtonPanel(Canvas canvas)
	{
		this.setLayout(new GridLayout(ROWS,1));

		addColorButtons(canvas);

		//adds the fill radio button and corresponding fill bucket image
		RadioButtonWithImagePanel fillPanel = new RadioButtonWithImagePanel(null, new FillModeListener(canvas), 
				fillBucketImagePath);
		this.add(fillPanel);

		//
		MyUIManager.changeLookAndFeel(MyUIManager.UITypes.Nimbus);

		this.add(new DeleteDrawingButtonsPanel(canvas));

		this.add(new ImageIOButtonPanel(canvas));

		MyUIManager.revertLookAndFeel();
	}

	//adds the color buttons to this Panel
	private void addColorButtons(Canvas canvas)
	{
		new ColorButtonsPanel(this,canvas);
	}
}
