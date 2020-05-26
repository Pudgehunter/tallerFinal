package model;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Pokemon  {
	
	protected PApplet app;
	protected int posX,posY;
	//Estos son los atributos importantes de los pokemones
	protected int vidaPokemon, dañoPokemon, velocidadPokemon, exp, nivel;
	
	public Pokemon(int posX, int posY,int nivel, PApplet app) {
		this.app = app;
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

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

//	public PImage getPokeImagen() {
//		return pokeImagen;
//	}
//
//	public void setPokeImagen(PImage pokeImagen) {
//		this.pokeImagen = pokeImagen;
//	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDañoPokemon() {
		return dañoPokemon;
	}

	public void setDañoPokemon(int dañoPokemon) {
		this.dañoPokemon = dañoPokemon;
	}

	public int getVelocidadPokemon() {
		return velocidadPokemon;
	}

	public void setVelocidadPokemon(int velocidadPokemon) {
		this.velocidadPokemon = velocidadPokemon;
	}
	

	
}
