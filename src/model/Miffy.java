package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Miffy extends Pokemon{
	public Miffy(PImage pokeImagen,int posX, int posY,int nivel,PApplet app) {
		super(pokeImagen,posX,posY,nivel,app);
		this.nombres = "Miffy";
		this.pokemonValidar = 1;
	}
	public void drawPokemon() {
		app.image(this.pokeImagen,this.posX,this.posY);
	}
	
	public void drawPokedex() {
		app.image(this.pokeImagen,this.posX,this.posY,50,50);
	}
	
}

