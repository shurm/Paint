package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import controller.PopupMenuItemListener;
import enums.RadioButtonType;
import model.Canvas;
import util.ImageIconFactory;

@SuppressWarnings("serial")
public abstract class MyPopupMenu extends JPopupMenu
{
	private JButton [][] shapesMenu;
	
	public MyPopupMenu(RadioButtonType [] TOTAL_SHAPES, int TOTAL_SIZES, int DEFAULT_SIZE_INDEX, int DEFAULT_SHAPE_INDEX, int DIMENSION_DELTA, Canvas canvas, RadioButtonType brush) 
	{
		if(DEFAULT_SIZE_INDEX >= TOTAL_SIZES || DEFAULT_SHAPE_INDEX>=TOTAL_SHAPES.length)
			throw new IllegalArgumentException("DEFAULT_SIZE_INDEX must be less than TOTAL_SIZES. DEFAULT_SHAPE_INDEX must be less than TOTAL_SHAPES.length");
		
		shapesMenu = new JButton[TOTAL_SIZES][TOTAL_SHAPES.length];
		
		this.setLayout(new GridLayout(TOTAL_SIZES,TOTAL_SHAPES.length));

		for(int a=0; a<TOTAL_SIZES; a++)
		{
			for(int b=0; b<TOTAL_SHAPES.length; b++)
			{
				shapesMenu[a][b] = new JButton();

				int imageDimension = (a+1)*DIMENSION_DELTA;
				
				shapesMenu[a][b].addActionListener(new PopupMenuItemListener(this, shapesMenu[a][b], brush, imageDimension, canvas, TOTAL_SHAPES[b].getCorrespondingShapeType()));
				
				shapesMenu[a][b].setIcon(ImageIconFactory.createScaledIcon(TOTAL_SHAPES[b].getImageFilePath(), imageDimension, imageDimension));

				this.add(shapesMenu[a][b]);	
			}
		}
		
		shapesMenu[DEFAULT_SIZE_INDEX][DEFAULT_SHAPE_INDEX].doClick();
	}
	
	/**
	 * sets this menu's buttons to their default background
	 */
	public void resetMenuItems()
	{
		for(int a=0; a<shapesMenu.length; a++)
		{
			for(int b=0; b<shapesMenu[0].length; b++)
			{
				shapesMenu[a][b].setBackground(null);
			}
		}
	}
}
