package model;

import processing.core.PApplet;

public class Personaje {

	private int posX,posY,mX,mY,tama�oMatriz;
	private PApplet app;
	
	public Personaje(int posX,int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.mX = 0;
		this.mY = 0;
		this.tama�oMatriz = 50;
	}
	
	public void dibujarPersonaje() {
		app.fill(0,255,0);
		app.ellipse(this.posX,this.posY,tama�oMatriz,tama�oMatriz);
	}
	
	//Metodos de mover
	public void moveUp() {
		this.mY = this.mY - 1;
		this.posY = this.posY - this.tama�oMatriz;
	}
	public void moveDown() {
		this.mY = this.mY + 1;
		this.posY = this.posY + this.tama�oMatriz;
	}
	public void moveLeft() {
		this.mX = this.mX - 1;
		this.posX = this.posX - this.tama�oMatriz;
	}
	public void moveRight() {
		this.mX = this.mX + 1;
		this.posX = this.posX + this.tama�oMatriz;
	}
	public void dontMove() {
		this.mX = this.mX;
		this.posX = this.posX;
	}

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

	public int getmX() {
		return mX;
	}

	public void setmX(int mX) {
		this.mX = mX;
	}

	public int getmY() {
		return mY;
	}

	public void setmY(int mY) {
		this.mY = mY;
	}
	
	
	
	
}
