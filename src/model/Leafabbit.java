package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Leafabbit extends Pokemon{
	PImage pokeImagen;
	public Leafabbit(int posX, int posY,int nivel,PApplet app) {
		super(posX,posY,nivel,app);
		this.pokeImagen = app.loadImage("../images/LeafabbitAtras.png");
	}
	public void drawPokemon() {
		this.app.image(this.pokeImagen,this.posX,this.posY);
	}

}


