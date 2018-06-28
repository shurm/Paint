package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import enums.RadioButtonType;
import enums.ShapeType;
import model.Canvas;
import view.MyPopupMenu;

/**
 * 
 * @author Michael Shur
 *
 */
public class PopupMenuItemListener implements ActionListener
{
	private RadioButtonType brush;
	private ShapeType shapeType;
	private int size;
	private Canvas canvas;
	private MyPopupMenu popupMenu;
	private JButton button;

	public PopupMenuItemListener(MyPopupMenu popupMenu, JButton button, RadioButtonType brush, int size, Canvas canvas, ShapeType shapeType) 
	{
		this.popupMenu = popupMenu;
		this.button = button;
		this.brush = brush;
		this.size = size;
		this.canvas = canvas;
		this.shapeType = shapeType;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		popupMenu.resetMenuItems();
		button.setBackground(Color.yellow);
		canvas.getBrushList().setStrokeSize(brush, size);
		canvas.getBrushList().setStrokeShape(brush, shapeType);
		
	}
}