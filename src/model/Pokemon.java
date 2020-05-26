package model;

import java.util.LinkedList;

import excepciones.exceptions;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Pokemon  {
	
	protected PApplet app;
	protected int posX,posY;
	protected PImage pokeImagen;
	protected String nombres;
	protected boolean turnos;
	//Estos son los atributos importantes de los pokemones
	protected int vidaPokemon,vidaTotal, dañoPokemon, velocidadPokemon, exp, nivel;
	
	public Pokemon(PImage pokeImagen,int posX, int posY,int nivel, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.vidaPokemon = (int) app.random(38,50);
		int a = this.vidaPokemon;
		this.vidaTotal = a;
		this.dañoPokemon = (int) app.random(18,25);
		this.velocidadPokemon = (int) app.random(1,5);
		this.nivel = nivel;
		this.exp = 0;
		this.pokeImagen = pokeImagen;
		this.turnos = false;
	}
	
	public abstract void drawPokemon();

	//Me acorde los comparable, voy a ver si funciona esta función
	public void atacar(LinkedList<Pokemon> aliado,LinkedList<Pokemon> enemigo) {
		for (int i = 0; i < aliado.size(); i++) {
			for (int j = 0; j < enemigo.size(); j++) {
				//Solo estoy intentando que se pueda hacerlo un poco como pokemon
				aliado.get(i).turnos = false;
				enemigo.get(i).turnos = false;
				//Si la velocidad de mi pokemon es más rápido que el enemigo entonces...
				if(aliado.get(i).velocidadPokemon > enemigo.get(i).velocidadPokemon) {
					aliado.get(i).turnos = true;
					enemigo.get(j).turnos = false;
					if(aliado.get(i).turnos == true) {
					enemigo.get(j).vidaPokemon = enemigo.get(j).vidaPokemon - aliado.get(i).dañoPokemon;
					try {
						throw new exceptions("Tu pokemon ataco de primero"+ aliado.get(i).dañoPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					enemigo.get(j).turnos = true;
					}
					//Más tarde busco un metodo que haga esto más lento, pues que salga primero el error y luego esta cosa.
					if(enemigo.get(j).turnos = true) {
						aliado.get(i).vidaPokemon = aliado.get(i).vidaPokemon - enemigo.get(j).dañoPokemon;
					try {
						throw new exceptions("El enemigo ataco después y quito: "+ enemigo.get(j).dañoPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}
				}else 
				//Si el enemigo tiene más velocidad que tu...
				if(aliado.get(i).velocidadPokemon > enemigo.get(i).velocidadPokemon) {
					aliado.get(i).turnos = false;
					enemigo.get(j).turnos = true;
					if(enemigo.get(j).turnos == true) {
					aliado.get(i).vidaPokemon = aliado.get(i).vidaPokemon - enemigo.get(j).dañoPokemon;
					try {
						throw new exceptions("El enemigo ataco primero y quito: "+ enemigo.get(j).dañoPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					} aliado.get(i).turnos = true;
					}
					if(aliado.get(i).turnos == true) {
					enemigo.get(j).vidaPokemon = enemigo.get(j).vidaPokemon - aliado.get(i).dañoPokemon;
					try {
						throw new exceptions("El aliado ataco después y quito: "+ aliado.get(i).dañoPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}

				}else {
					aliado.get(i).vidaPokemon = aliado.get(i).vidaPokemon - enemigo.get(j).dañoPokemon/2;
					enemigo.get(j).vidaPokemon = enemigo.get(j).vidaPokemon - aliado.get(i).dañoPokemon/2;
				}
			}
			}
		
	}

	public void nivelAliado(LinkedList<Pokemon> aliado) {
		for (int i = 0; i < aliado.size(); i++) {
			//Los pokemones consiguen bonificaciones randoms...
			int bonificacionesVida = (int) app.random(1,5);
			int bonificacionesDamage = (int) app.random(1,5);
			int bonificacionesVelocidad = (int) app.random(1,5);
			aliado.get(i).vidaPokemon = aliado.get(i).vidaPokemon + aliado.get(i).nivel * bonificacionesVida;
			aliado.get(i).dañoPokemon = aliado.get(i).dañoPokemon + aliado.get(i).nivel * bonificacionesDamage;
			aliado.get(i).velocidadPokemon = aliado.get(i).velocidadPokemon + aliado.get(i).nivel * bonificacionesVelocidad;
		}	
	}
	public void nivelEnemigo(LinkedList<Pokemon> enemigo) {
		for (int i = 0; i < enemigo.size(); i++) {
			//Los pokemones consiguen bonificaciones randoms...
			int bonificacionesVida = (int) app.random(1,5);
			int bonificacionesDamage = (int) app.random(1,5);
			int bonificacionesVelocidad = (int) app.random(1,5);
			enemigo.get(i).vidaPokemon = enemigo.get(i).vidaPokemon + enemigo.get(i).nivel * bonificacionesVida;
			enemigo.get(i).dañoPokemon = enemigo.get(i).dañoPokemon + enemigo.get(i).nivel * bonificacionesDamage;
			enemigo.get(i).velocidadPokemon = enemigo.get(i).velocidadPokemon + enemigo.get(i).nivel * bonificacionesVelocidad;
		}	
	}

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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public boolean isTurnos() {
		return turnos;
	}

	public void setTurnos(boolean turnos) {
		this.turnos = turnos;
	}

	public int getVidaTotal() {
		return vidaTotal;
	}

	public void setVidaTotal(int vidaTotal) {
		this.vidaTotal = vidaTotal;
	}
	
	
	
}
