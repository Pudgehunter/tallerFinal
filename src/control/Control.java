package control;

import model.Logic;
import processing.core.PApplet;

public class Control {
	Logic logica;
	private PApplet app;
	
	public Control(PApplet app) {
		this.app = app;
		this.logica = new Logic(app);
	}
	
	public void pantallas() {
		this.logica.pantallas();
	}
	
	public void keyPressed() {
		this.logica.moverPersonaje();
		this.logica.talkRival();
	}
	
	public void mouseClicked() {
		this.logica.click();
	}
	
	public void pantallaMenu() {
		this.logica.pantallaMenu();
	}
	
	public void sortList(char c) {
		this.logica.sortList(c);
	}
	

}
