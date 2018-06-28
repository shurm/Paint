package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.ChangeBackgroundColorListener;
import controller.OpenColorFrameListener;
import controller.OpenPopupMenuListener;
import model.Canvas;
import util.ChangeBackgroundColorInterface;
import util.ImageIconFactory;

/**
 * @author Michael Shur
 *
 */
public class ColorButtonsPanel 
{
	//the colors 
	private LinkedList<Color> allColors = new LinkedList<>(Arrays.asList(new Color[]{Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, 
			Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow }));
	
	private List<PopupPair> popupPairs = new LinkedList<>();
	
	private static final String COLOR_IMAGE_PATH = "/images/color.jpg";
	private static final int COLOR_IMAGE_DIMENSION = 60;
	
	
	private JButton colorButton;
	
	public ColorButtonsPanel(JPanel container, Canvas canvas)
	{
		JPanel colorPanel =new JPanel();
		colorPanel.setLayout(new GridLayout(1,2));
	
		colorButton = new JButton();
		JButton setBackgroundColor= new JButton("Background Color");
		
		JPopupMenu pm1 = new JPopupMenu(), pm2 =new JPopupMenu();
		
		
		final JButton colorB = colorButton;
		
		popupPairs.add(new PopupPair(pm1,new ChangeBackgroundColorInterface(){

			@Override
			public void setColor(Color c) {
				colorB.setBackground(c);
				canvas.setColor(c);
				
			}
			
		}));
		
		popupPairs.add(new PopupPair(pm2,new ChangeBackgroundColorInterface(){

			@Override
			public void setColor(Color c) {
				canvas.setBackground(c);
			}
		}));
	
		
		colorButton.addMouseListener(new OpenPopupMenuListener(pm1, colorButton));
		setBackgroundColor.addMouseListener(new OpenPopupMenuListener(pm2, setBackgroundColor));
		
		
		
		colorButton.setIcon(ImageIconFactory.createScaledIcon(COLOR_IMAGE_PATH, COLOR_IMAGE_DIMENSION, COLOR_IMAGE_DIMENSION));
	
		
		JButton makeNewColorB= new JButton("custom color");
		makeNewColorB.addActionListener(new OpenColorFrameListener(this));
	
		colorPanel.add(colorButton);
		colorPanel.add(makeNewColorB);
	
		container.add(colorPanel);
	
		container.add(setBackgroundColor);
	
		
		refreshColorMenus();
	}

	private void refreshColorMenus()
	{
		for(Iterator<PopupPair> iterator = popupPairs.iterator(); iterator.hasNext();)
		{
			rePopulatePopupMenu(iterator.next());
		}
	}
	
	private void rePopulatePopupMenu(PopupPair pp)
	{
		pp.popupMenu.removeAll();
		
		pp.popupMenu.setLayout(new GridLayout((allColors.size()/3+1),3));
		
		for(Iterator<Color> iterator = allColors.iterator(); iterator.hasNext();)
		{
			Color c=iterator.next();
			
			JButton colorButton= new JButton(" ");
			colorButton.setBackground(c);

			colorButton.addActionListener(new ChangeBackgroundColorListener(c, pp.changeBackground));
			
			pp.popupMenu.add(colorButton);
		}
	}
	
	/*
	 * adds a custom color to the list and displays the PopupMenu
	 */
	public void addColorToPopups(Color c)
	{
		allColors.add(c);
		refreshColorMenus();
		JPopupMenu menu=popupPairs.get(0).popupMenu;
		menu.show(colorButton, 20, 20);
	}
	
	private static class PopupPair
	{
		JPopupMenu popupMenu;
		
		ChangeBackgroundColorInterface changeBackground;
		
		public PopupPair(JPopupMenu popupMenu, ChangeBackgroundColorInterface changeBackground) {
			this.popupMenu = popupMenu;
			this.changeBackground = changeBackground;
		}
	}
}
