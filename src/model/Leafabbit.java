package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Leafabbit extends Pokemon{
	public Leafabbit(PImage pokeImagen,int posX, int posY,int nivel,PApplet app) {
		super(pokeImagen,posX,posY,nivel,app);
		this.nombres = "Leafabbit";
		this.pokemonValidar = 2;
	}
	public void drawPokemon() {
		this.app.image(this.pokeImagen,this.posX,this.posY);
	}
	
	public void drawPokedex() {
		app.image(this.pokeImagen,this.posX,this.posY,50,50);
	}
	
	

}


