package quadroControl.Entity;

import java.awt.Color;
import java.util.Random;

public class Graph {
	
	int xPos;
	int yPos = 150;
	int newYPos = 150;
	int newXPos;
	Color color;
	Random random = new Random();
	
	public Graph(Color color) {
		this.color = color;
	}
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getNewYPos() {
		return newYPos;
	}
	public void setNewYPos(int newYPos) {
		this.newYPos = newYPos;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void newPos() {
		if(random.nextInt(2) == 1) {
			newYPos = random.nextInt(180 / 2) + 150;
		} else {
			newYPos = random.nextInt(180 / 2) * -1 + 150;
		}
	}

	public int getNewXPos() {
		return newXPos;
	}

	public void setNewXPos(int newXPos) {
		this.newXPos = newXPos;
	}
	
	
}
