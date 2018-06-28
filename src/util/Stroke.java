package util;

import enums.ShapeType;

public class Stroke 
{
	private int size;
	
	private ShapeType shape;

	public int getSize() {
		return size;
	}

	public void setSize(int brushSize) {
		this.size = brushSize;
	}

	public ShapeType getShapeType() {
		return shape;
	}

	public void setShapeType(ShapeType brushShape) {
		this.shape = brushShape;
	}
}