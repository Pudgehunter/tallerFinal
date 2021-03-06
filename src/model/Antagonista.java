package model;

import processing.core.PApplet;

public class Antagonista {

	private int posX,posY,mX,mY,tamaņoMatriz;
	private PApplet app;
	
	public Antagonista(int posX,int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.mX = 0;
		this.mY = 0;
		this.tamaņoMatriz = 50;
	}
	
	public void dibujarEnemigo() {
		app.fill(255,0,0);
		app.ellipse(this.posX,this.posY,tamaņoMatriz,tamaņoMatriz);
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

	public int getTamaņoMatriz() {
		return tamaņoMatriz;
	}

	public void setTamaņoMatriz(int tamaņoMatriz) {
		this.tamaņoMatriz = tamaņoMatriz;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}
	
}
