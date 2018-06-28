package view;

import java.awt.BorderLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.OpenPopupMenuListener;
import enums.RadioButtonType;
import model.Canvas;

/**
 * Panel that contains a Button that when clicked causes a BrushShapePopupMenu to appear 
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class PopupMenuButtonPanel extends JPanel
{
	@SuppressWarnings("unchecked")
	public PopupMenuButtonPanel(Canvas canvas, Class<? extends MyPopupMenu> popupMenu, RadioButtonType type)
	{
		this.setLayout(new BorderLayout());
		
		addPaddingToPanel();
		
		JButton button = new JButton(" ");
		try 
		{
			Constructor<MyPopupMenu> c = (Constructor<MyPopupMenu>) popupMenu.getConstructor(new Class[]{canvas.getClass(),type.getClass()});
		
			button.addMouseListener(new OpenPopupMenuListener(c.newInstance(canvas,type), button));
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		this.add(button,BorderLayout.CENTER);
	}
	
	//adds some padding for aesthetics 
	private void addPaddingToPanel()
	{
		final String [] borderLayoutConstants = {BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.EAST,BorderLayout.WEST};
		
		final JPanel [] fillers= new JPanel[borderLayoutConstants.length];
		
		for(int a=0;a<fillers.length;a++)
		{
			fillers[a]= new JPanel();
			this.add(fillers[a], borderLayoutConstants[a]);
		}
	}
	
}
