package model;

import processing.core.PApplet;
import processing.core.PImage;

public class RedPanda extends Pokemon{

	public RedPanda(int posX, int posY,int nivel,PApplet app) {
		super(posX,posY,nivel,app);
		this.pokeImagen = app.loadImage("./images/Redpanda.png");
	}
	
	public void drawPokemon() {
		app.image(this.pokeImagen, this.posX, this.posY);
	}
}
