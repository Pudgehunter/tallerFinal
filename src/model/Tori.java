package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Tori extends Pokemon{
	public Tori(PImage pokeImagen,int posX, int posY,int nivel,PApplet app) {
		super(pokeImagen,posX,posY,nivel,app);
		this.nombres = "Tori";
		this.pokemonValidar = 4;
	}
	
	public void drawPokemon() {
		app.image(this.pokeImagen,this.posX,this.posY);
	}
	
	public void drawPokedex() {
		app.image(this.pokeImagen,this.posX,this.posY,50,50);
	}
	
}

