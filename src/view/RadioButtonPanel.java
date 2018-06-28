package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import controller.DrawingModeListener;
import enums.RadioButtonType;
import model.Canvas;
import util.MyUIManager;

/**
 * Panel which contains all the radio buttons/options for creating all the various types of drawings the user can apply to the canvas
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class RadioButtonPanel extends JPanel
{	
	public RadioButtonPanel(Canvas canvas)
	{
		RadioButtonType [] allTypes = RadioButtonType.values();
		
		this.setLayout(new GridLayout(allTypes.length,1));
				
		ButtonGroup group= new ButtonGroup();
		
		for(int a=0; a<allTypes.length; a++)
		{
			JPanel wrapper = new JPanel();
			wrapper.setLayout(new BorderLayout());
			
			//adds a radio button along with a corresponding descriptive image to this panel
			RadioButtonWithImagePanel radioButtonWithImagePanel = new RadioButtonWithImagePanel(group, new DrawingModeListener(canvas, allTypes[a]), allTypes[a].getImageFilePath());
			
			wrapper.add(radioButtonWithImagePanel, BorderLayout.WEST);

			if(allTypes[a].isBrush())
			{
				//adds a brushSizeMenu using a special UI look
				MyUIManager.changeLookAndFeel(MyUIManager.UITypes.Motif);
				wrapper.add(new PopupMenuButtonPanel(canvas, BrushShapePopupMenu.class, allTypes[a]), BorderLayout.EAST);
				MyUIManager.revertLookAndFeel();
			}
			else if(allTypes[a].getCorrespondingShapeType().HasThickness())
			{
				//adds a brushSizeMenu using a special UI look
				MyUIManager.changeLookAndFeel(MyUIManager.UITypes.Motif);
				wrapper.add(new PopupMenuButtonPanel(canvas, LinePopupMenu.class, allTypes[a]), BorderLayout.EAST);
				MyUIManager.revertLookAndFeel();
			}
			
			this.add(wrapper);
			
			if(allTypes[a]==canvas.getCurrentlySelectedRadioButtonType())
				radioButtonWithImagePanel.pressButton();
		}
	}
}
