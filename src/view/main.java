package view;

import control.Control;
import processing.core.PApplet;

public class main extends PApplet {

	Control control;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("view.main");
	}
	
	public void settings() {
		size(800,600);
	}
	
	public void setup() {
		this.control = new Control(this);
	}
	
	public void draw() {
		background(255);
		fill(255);
		this.control.pantallas();
	}
	
	public void keyPressed() {
		this.control.keyPressed();
	}
	
	public void mouseClicked() {
		this.control.mouseClicked();
	}

}
