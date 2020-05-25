package model;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Pokemon {
	
	public PApplet app;
	public PImage pokeImagen;
	public int posX,posY;
	//Estos son los atributos importantes de los pokemones
	public int vidaPokemon, dañoPokemon, velocidadPokemon, exp, nivel;
	
	public Pokemon(int posX, int posY,int nivel, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.vidaPokemon = (int) app.random(38,50);
		this.dañoPokemon = (int) app.random(18,25);
		this.velocidadPokemon = (int) app.random(1,5);
		this.nivel = nivel;
		this.exp = 0;
	}
	
	public abstract void drawPokemon();

	public int getVidaPokemon() {
		return vidaPokemon;
	}

	public void setVidaPokemon(int vidaPokemon) {
		this.vidaPokemon = vidaPokemon;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	
}
