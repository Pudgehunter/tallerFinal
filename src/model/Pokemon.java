package model;

import java.util.LinkedList;

import excepciones.exceptions;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Pokemon implements Comparable<Pokemon>, Runnable {
	
	protected PApplet app;
	protected int posX,posY;
	protected PImage pokeImagen;
	protected String nombres;
	protected boolean turnos;
	protected int dirX,velocidad;
	//Estos son los atributos importantes de los pokemones
	protected int vidaPokemon, da�oPokemon, velocidadPokemon, exp, nivel;
	protected int vidaTotal;
	protected int bonificacionesVida,bonificacionesDamage,bonificacionesVelocidad;
	protected int pokemonSolo;
	protected int pokemonValidar;
	
	public Pokemon(PImage pokeImagen,int posX, int posY,int nivel, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.vidaPokemon = (int) app.random(38,80);
		this.vidaTotal = this.vidaPokemon;
		this.da�oPokemon = (int) app.random(5,10);
		this.velocidadPokemon = (int) app.random(1,5);
		this.nivel = nivel;
		this.exp = 0;
		this.pokeImagen = pokeImagen;
		this.turnos = false;
		this.pokemonSolo = 0;
		
		//Pokemones que se mueven
		this.dirX = 1;
		this.velocidad = 1;
		
		//Niveles, que se aplique solamente una vez y no todas las veces
		this.bonificacionesVida = (int) app.random(1,5);
		this.bonificacionesDamage = (int) app.random(1,5);
		this.bonificacionesVelocidad = (int) app.random(1,5);
	}
	
	public abstract void drawPokemon();

	public abstract void drawPokedex();
	
	//Me acorde los comparable, voy a ver si funciona esta funci�n
	public void atacar(LinkedList<Pokemon> aliado,LinkedList<Pokemon> enemigo) {
				//Solo estoy intentando que se pueda hacerlo un poco como pokemon
				aliado.get(pokemonSolo).turnos = false;
				enemigo.get(pokemonSolo).turnos = false;
				//Si la velocidad de mi pokemon es m�s r�pido que el enemigo entonces...
				if(aliado.get(pokemonSolo).velocidadPokemon > enemigo.get(pokemonSolo).velocidadPokemon) {
					aliado.get(pokemonSolo).turnos = true;
					enemigo.get(pokemonSolo).turnos = false;
					if(aliado.get(pokemonSolo).turnos == true) {
					enemigo.get(pokemonSolo).vidaPokemon = enemigo.get(pokemonSolo).vidaPokemon - aliado.get(0).da�oPokemon;
					try {
						throw new exceptions("Tu pokemon ataco de primero"+ aliado.get(pokemonSolo).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					enemigo.get(pokemonSolo).turnos = true;
					}
					//M�s tarde busco un metodo que haga esto m�s lento, pues que salga primero el error y luego esta cosa.
					if(enemigo.get(pokemonSolo).turnos = true) {
						aliado.get(pokemonSolo).vidaPokemon = aliado.get(pokemonSolo).vidaPokemon - enemigo.get(pokemonSolo).da�oPokemon;
					try {
						throw new exceptions("El enemigo ataco despu�s y quito: "+ enemigo.get(pokemonSolo).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}
				}else 
				//Si el enemigo tiene m�s velocidad que tu...
				if(aliado.get(pokemonSolo).velocidadPokemon > enemigo.get(pokemonSolo).velocidadPokemon) {
					aliado.get(pokemonSolo).turnos = false;
					enemigo.get(pokemonSolo).turnos = true;
					if(enemigo.get(pokemonSolo).turnos == true) {
					aliado.get(pokemonSolo).vidaPokemon = aliado.get(pokemonSolo).vidaPokemon - enemigo.get(pokemonSolo).da�oPokemon;
					try {
						throw new exceptions("El enemigo ataco primero y quito: "+ enemigo.get(pokemonSolo).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					} aliado.get(pokemonSolo).turnos = true;
					}
					if(aliado.get(pokemonSolo).turnos == true) {
					enemigo.get(pokemonSolo).vidaPokemon = enemigo.get(pokemonSolo).vidaPokemon - aliado.get(pokemonSolo).da�oPokemon;
					try {
						throw new exceptions("El aliado ataco despu�s y quito: "+ aliado.get(pokemonSolo).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}

				}else {
					aliado.get(pokemonSolo).vidaPokemon = aliado.get(pokemonSolo).vidaPokemon - enemigo.get(pokemonSolo).da�oPokemon/2;
					enemigo.get(pokemonSolo).vidaPokemon = enemigo.get(pokemonSolo).vidaPokemon - aliado.get(pokemonSolo).da�oPokemon/2;
				}
	}
	public void atacarRival(LinkedList<Pokemon> aliado,LinkedList<Pokemon> rival) {
				//Solo estoy intentando que se pueda hacerlo un poco como pokemon
				aliado.get(0).turnos = false;
				rival.get(pokemonSolo).turnos = false;
				//Si la velocidad de mi pokemon es m�s r�pido que el enemigo entonces...
				if(aliado.get(0).velocidadPokemon > rival.get(pokemonSolo).velocidadPokemon) {
					aliado.get(0).turnos = true;
					rival.get(pokemonSolo).turnos = false;
					if(aliado.get(0).turnos == true) {
						rival.get(pokemonSolo).vidaPokemon = rival.get(pokemonSolo).vidaPokemon - aliado.get(0).da�oPokemon;
					try {
						throw new exceptions("Tu pokemon ataco de primero"+ aliado.get(0).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					rival.get(pokemonSolo).turnos = true;
					}
					//M�s tarde busco un metodo que haga esto m�s lento, pues que salga primero el error y luego esta cosa.
					if(rival.get(pokemonSolo).turnos = true) {
						aliado.get(0).vidaPokemon = aliado.get(0).vidaPokemon - rival.get(pokemonSolo).da�oPokemon;
					try {
						throw new exceptions("El enemigo ataco despu�s y quito: "+ rival.get(pokemonSolo).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}
				}else 
				//Si el enemigo tiene m�s velocidad que tu...
				if(aliado.get(0).velocidadPokemon > rival.get(pokemonSolo).velocidadPokemon) {
					aliado.get(0).turnos = false;
					rival.get(pokemonSolo).turnos = true;
					if(rival.get(pokemonSolo).turnos == true) {
					aliado.get(0).vidaPokemon = aliado.get(0).vidaPokemon - rival.get(pokemonSolo).da�oPokemon;
					try {
						throw new exceptions("El enemigo ataco primero y quito: "+ rival.get(0).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					} aliado.get(0).turnos = true;
					}
					if(aliado.get(0).turnos == true) {
						rival.get(pokemonSolo).vidaPokemon = rival.get(pokemonSolo).vidaPokemon - aliado.get(0).da�oPokemon;
					try {
						throw new exceptions("El aliado ataco despu�s y quito: "+ aliado.get(0).da�oPokemon);
					} catch (exceptions e) {
						System.out.println(e.getMessage());
						app.fill(0);
						app.textSize(30);
						app.text(e.getMessage(),200,200);
					}
					}

				}else {
					aliado.get(0).vidaPokemon = aliado.get(0).vidaPokemon - rival.get(pokemonSolo).da�oPokemon/2;
					rival.get(pokemonSolo).vidaPokemon = rival.get(pokemonSolo).vidaPokemon - aliado.get(0).da�oPokemon/2;
				}
			}

	public void nivelAliado(LinkedList<Pokemon> aliado) {
		for (int i = 0; i < aliado.size(); i++) {
			//Los pokemones consiguen bonificaciones randoms...
			aliado.get(i).vidaPokemon = aliado.get(i).vidaPokemon + aliado.get(i).nivel * bonificacionesVida;
			aliado.get(i).vidaTotal = aliado.get(i).vidaPokemon;
			aliado.get(i).da�oPokemon = aliado.get(i).da�oPokemon + aliado.get(i).nivel * bonificacionesDamage;
			aliado.get(i).velocidadPokemon = aliado.get(i).velocidadPokemon + aliado.get(i).nivel * bonificacionesVelocidad;
		}	
	}
	public void nivelRivalStatic(LinkedList<Pokemon> rival) {
		for (int i = 0; i < rival.size(); i++) {
			//Los pokemones consiguen bonificaciones randoms...
			rival.get(i).vidaPokemon = rival.get(i).vidaPokemon + rival.get(i).nivel * 2;
			rival.get(i).da�oPokemon = rival.get(i).da�oPokemon + rival.get(i).nivel * 3;
			rival.get(i).velocidadPokemon = rival.get(i).velocidadPokemon + rival.get(i).nivel * 1;
		}	
	}

	public void run() {
		try {
			Thread.sleep(1000);
			mover();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			}
	}
	
	public void mover() {
		this.posX = this.posX + this.dirX*this.velocidad;
		if(this.posX >= 20 || this.posX <= 200 || this.posX >= 400 || this.posX <= 500) {
			this.dirX = this.dirX*-1;
		}
	}
	
	//Comparar por nivel
	public int compareTo (Pokemon poke) {
		return this.nivel - poke.getNivel();
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

	public int getDa�oPokemon() {
		return da�oPokemon;
	}

	public void setDa�oPokemon(int da�oPokemon) {
		this.da�oPokemon = da�oPokemon;
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

	public int getPokemonSolo() {
		return pokemonSolo;
	}

	public void setPokemonSolo(int pokemonSolo) {
		this.pokemonSolo = pokemonSolo;
	}

	public PImage getPokeImagen() {
		return pokeImagen;
	}

	public void setPokeImagen(PImage pokeImagen) {
		this.pokeImagen = pokeImagen;
	}

	public int getPokemonValidar() {
		return pokemonValidar;
	}

	public void setPokemonValidar(int pokemonValidar) {
		this.pokemonValidar = pokemonValidar;
	}
	
	
	
	
	
	
	
	
}
