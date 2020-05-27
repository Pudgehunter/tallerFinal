package model;

import processing.core.PApplet;
import processing.core.PImage;

public class RedPanda extends Pokemon{
	public RedPanda(PImage pokeImagen,int posX, int posY,int nivel,PApplet app) {
		super(pokeImagen,posX,posY,nivel,app);
		this.nombres = "Red Panda";
		this.pokemonValidar = 0;
		//this.pokeImagen = app.loadImage("../images/RedpandaAtras.png");
	}
	
	public void drawPokemon() {
		app.image(this.pokeImagen,this.posX,this.posY);
	}
	
	public void drawPokedex() {
		app.image(this.pokeImagen,this.posX,this.posY,50,50);
	}
}
