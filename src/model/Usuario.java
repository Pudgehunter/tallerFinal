package model;

import processing.core.PApplet;

public class Usuario {

	private PApplet app;
	private String nombreUsuario;
	
	public Usuario(String nombreUsuario, PApplet app) {
		this.app = app;
		this.nombreUsuario = nombreUsuario;
	}
	
}
