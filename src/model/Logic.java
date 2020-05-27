package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import controlP5.ControlP5;
import controlP5.Textfield;
import excepciones.exceptions;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Logic {
	
	private PApplet app;
	//Matriz del mapa
	//private int[][] matriz;
	
	//Atributo de logica
	private int pantalla, pantallaMenu, pantallaJuego, HorizontalMap, VerticalMap;
	
	//pokemonEscogido
	private int pokemonInicial;
	
	//pokemonEscoger?
	private int choosePokemon;
	
	//Random pokemons to chooseYourPokemon
	private int enemyRandom;
	
	//Mi protagonista, antagonista y pokemones
	Personaje protagonista;
	Antagonista antagonista;
	
	//Mi cerebro no funciono para un Linkedlist
	LinkedList<Pokemon> listpokemon;
	LinkedList<Pokemon> enemigos;
	LinkedList<Pokemon> listRival;
	LinkedList<Pokemon> listPokedex;
	
	//Todas las imagenes
	PImage myRedPanda,myMiffy,myLeafabbit,
	mySealmon,myOrogan,myTori,
	rivalRedPanda,rivalMiffy,rivalLeafabbit,
	enemySealmon,enemyOrogan,enemyTori,
	fondo;
	PImage intro,introclick,
	name,nameclick,
	explicacion,explicacionclick,
	pokemonSelectivo,rojoSelect,greenSelect,blueSelect,
	redpandaSelect,redPandaClick,
	miffySelect,miffyClick,
	leafabbitSelect,leafabbitClick,
	introducciones,pokedex,win;
	
	PImage[] pokeCambio;
	
	//La lista de pokemon
	private int rivalPokemon;
	
	//Prueba pokemon Arraylist
	private int miListaPokemon;
	
	//Boolean
	private boolean atrapar;
	
	//Boolean
	private boolean soloUnPokemon;
	
	//Para poder comparar
	ComparePokemon comparePokemon;
	
	//Para guardar el nombre del jugador
	ControlP5 control;
	
	//El usuario donde se guarda
	String nombres;
	
	public Logic(PApplet app) {
		this.app = app;
		setupLogic();
		createMap();
		//Todos los linkedlist
		listpokemon = new LinkedList<Pokemon>();
		enemigos = new LinkedList<Pokemon>();
		listRival = new LinkedList<Pokemon>();
		listPokedex = new LinkedList<Pokemon>();
		
		//Escritura
		control = new ControlP5(app);
		registroControl();
		
		
	}
	
	public void setupLogic() {
		pImagePokemones();
		pantalla = 0;
		pantallaJuego = 0;
		pantallaMenu = 0;
		choosePokemon = 0;
		rivalPokemon = 0;
		enemyRandom = 0;
		
		//Booleans
		atrapar = false;
		soloUnPokemon = true;
		
		pokeCambio = new PImage[6];
		comparePokemon = new ComparePokemon();
		
	}
	
	public void registroControl() {
		control.addTextfield("nombre")
		.setPosition(150,250)
		.setSize(500,100)
		.setColorBackground(255);
	}
	
	public void createMap() {
		//Visualizando el mapa para que quede bonito xd
		//HorizontalMap = 800/50;
		//VerticalMap = 600/50;
		//Los arreglos de pokemones
		//this.matriz = new int[HorizontalMap][VerticalMap];
		dibujarPersonaje();
		dibujarEnemigo();
	}
	
	public void drawMap() {
		app.image(fondo,0,0);
	}
	
	public void dibujarPersonaje() {
				int posX = (0+25);
				int posY = (0+25);
				this.protagonista = new Personaje(posX,posY,app);
	}
	
	public void dibujarEnemigo() {
				int posX = (50*15+25);
				int posY = (50*11+25);
				this.antagonista = new Antagonista(posX,posY,app);
	}
	
	//"dibujar el personaje en el matriz"
	public void dibujarEnLaMatrizPersonaje() {
		this.protagonista.dibujarPersonaje();
	}
	
	//dibujar el rival de toda su vida
	public void dibujarEnemigoEnLaMatriz() {
				this.antagonista.dibujarEnemigo();
	}
	
	//Mover el personaje en el matriz
	public void moverPersonaje() {
		if(pantalla == 2 && pantallaJuego == 0) {
		if(app.keyCode == PConstants.UP) {
			if(this.protagonista.getmY() - 1 < 12 && this.protagonista.getmY() - 1 >= 0) {
				this.protagonista.moveUp();
				//Puse los arbusto en mousePressed para que solamente aparezca esa opción una vez
				arbusto();
			}			
		}
		if(app.keyCode == PConstants.DOWN) {
			
			//Este dont Move es para deternerlo y no entre en el cuerpo del rival xd
			if(this.protagonista.getmX() == 15 && this.protagonista.getmY() == 10) {
				this.protagonista.dontMove();
			}else {
			if(this.protagonista.getmY() + 1 < 12 && this.protagonista.getmY() + 1 >= 0){
				this.protagonista.moveDown();
				//Puse los arbusto en mousePressed para que solamente aparezca esa opción una vez
				arbusto();
			}
			}
		}
		if(app.keyCode == PConstants.LEFT) {
			
			if(this.protagonista.getmX() - 1 < 16 && this.protagonista.getmX() - 1 >= 0){
				this.protagonista.moveLeft();
				//Puse los arbusto en mousePressed para que solamente aparezca esa opción una vez
				arbusto();
			}
		}
		if(app.keyCode == PConstants.RIGHT) {
			//Este dont move es para detenerlo de parte derecha y no entre en el cuerpo del rival xd
			if(this.protagonista.getmX() == 14 && this.protagonista.getmY() == 11) {
				this.protagonista.dontMove();
			}else {
				if(this.protagonista.getmX() + 1 < 16 && this.protagonista.getmX() + 1 >= 0){
				this.protagonista.moveRight();
				//Puse los arbusto en mousePressed para que solamente aparezca esa opción una vez
				arbusto();
			}
			}
		}
		}
	}
	
	
	//Validar la posicion del rival para pelearle :v
	public void talkRival() {
		if(this.protagonista.getmX() == 14 
		&& this.protagonista.getmY() == 11 
		|| this.protagonista.getmX() == 15 
		&& this.protagonista.getmY() == 10) {
			if(app.keyCode == 'a' || app.keyCode == 'A') {
				pantallaJuego = 2;
				validarExp();
				System.out.println(listRival.size());
			}
		}
	}
	
	public void arbusto() {
		//Hice todo esto manual, porque la función que yo pensaba hacer NO FUNCIONO! y me dio rabia entonces lo hare LITERALMENTE
		//Todo MANUAL porque no tengo más opción. (Mi manera de hacerlo era Matriz[][] = (int) random(0); -> Error de Java Lang...
		//error vacio.
		
		if(
		//Arbusto inicial Arriba		
		this.protagonista.getmX() == 9 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 13 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 14 && this.protagonista.getmY() == 1
		|| this.protagonista.getmX() == 9 && this.protagonista.getmY() == 2
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 2
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 2
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 2
		|| this.protagonista.getmX() == 13 && this.protagonista.getmY() == 2
		|| this.protagonista.getmX() == 14 && this.protagonista.getmY() == 2
		//Arbusto abajo
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 5
		|| this.protagonista.getmX() == 1 && this.protagonista.getmY() == 5
		|| this.protagonista.getmX() == 2 && this.protagonista.getmY() == 5
		|| this.protagonista.getmX() == 3 && this.protagonista.getmY() == 5
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 6
		|| this.protagonista.getmX() == 1 && this.protagonista.getmY() == 6
		|| this.protagonista.getmX() == 2 && this.protagonista.getmY() == 6
		|| this.protagonista.getmX() == 3 && this.protagonista.getmY() == 6
		//Arbusto final
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 8
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 9
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 0 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 1 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 2 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 3 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 4 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 5 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 6 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 7 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 7 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 8 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 8 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 9 && this.protagonista.getmY() == 8
		|| this.protagonista.getmX() == 9 && this.protagonista.getmY() == 9
		|| this.protagonista.getmX() == 9 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 9 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 8
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 9
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 10 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 8
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 9
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 11 && this.protagonista.getmY() == 11
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 8
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 9
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 10
		|| this.protagonista.getmX() == 12 && this.protagonista.getmY() == 11
		) {
			//Este seria el porcentaje que puede salir los pokimones randoms
			int randomBushesEnemyOrSaves = (int) app.random(0,10);
			switch(randomBushesEnemyOrSaves) {
			case 0:
				System.out.println("te salio un enemigo, valiste verga");
				pantallaJuego = 1;
				validarExp();
				validarRandomEnemigos();
				System.out.println(listRival.size());
				break;
			default:
				System.out.println("te salvaste!");
				break;
			}
		}
	}
	
	public void validarRandomEnemigos() {
		if(pantallaJuego == 1) {
			int dinamico = (int) app.random(0,3);
			enemyRandom = dinamico;
			switch(enemyRandom) {
			case 0:
				enemigos.add(new Orogan(enemyOrogan,500,25,2,app));
				for (int i = 0; i < enemigos.size(); i++) {
					enemigos.get(i).nivelAliado(enemigos);
				}
				break;
			case 1:
				enemigos.add(new Tori(enemyTori,500,25,2,app));
				for (int i = 0; i < enemigos.size(); i++) {
					enemigos.get(i).nivelAliado(enemigos);
				}
				break;
			case 2:
				enemigos.add(new Sealmon(enemySealmon,500,25,2,app));
				for (int i = 0; i < enemigos.size(); i++) {
					enemigos.get(i).nivelAliado(enemigos);
				}
				break;
			default:
				enemigos.add(new Sealmon(enemySealmon,500,25,2,app));
				for (int i = 0; i < enemigos.size(); i++) {
					enemigos.get(i).nivelAliado(enemigos);
				}
				break;
			}
			System.out.println(enemigos);
			System.out.println(enemigos.size());
		}
	}
	public void validarExp() {
		for (int i = 0; i < listpokemon.size(); i++) {
			if(this.listpokemon.get(0).getExp() > 100) {
				this.listpokemon.get(0).setNivel(this.listpokemon.get(0).getNivel() + 1);
			this.listpokemon.get(0).setExp(0);
			listpokemon.get(0).nivelAliado(listpokemon);
			System.out.println(listRival.size());
			}
		}
	}
	
	
	public void batallaPokemon() {
		
		for (int i = 0; i < listpokemon.size(); i++) {
			listpokemon.get(i).setPosX(25);
			listpokemon.get(i).setPosY(375);
			new Thread(listpokemon.get(0)).start();
			new Thread(enemigos.get(0)).start();
		}
			listpokemon.get(0).drawPokemon();
			app.fill(0);
			app.textSize(20);
			app.text(this.listpokemon.get(0).getNombres(),54,350);
			app.text("nivel: "+this.listpokemon.get(0).getNivel(),184,350);
			app.text("vida: "+this.listpokemon.get(0).getVidaPokemon(),400,400);
			app.text("Exp: "+this.listpokemon.get(0).getExp(),400,460);
			if(this.listpokemon.get(0).getVidaPokemon() <= 0) {
				new Thread(listpokemon.get(0)).stop();
				new Thread(listRival.get(0)).stop();
				pantalla = 4;
				this.listpokemon.removeAll(this.listpokemon);
				this.enemigos.remove();
				return;
		}
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).drawPokemon();
			app.text(this.enemigos.get(i).getNombres(),504,350);
			app.text("nivel: "+this.enemigos.get(i).getNivel(),584,350);
			app.text("vida: "+this.enemigos.get(i).getVidaPokemon(),54,100);
			if(this.enemigos.get(i).getVidaPokemon() <= 0) {
				this.enemigos.remove();
				int recuperarVida = this.listpokemon.get(0).getVidaTotal();
				this.listpokemon.get(0).setVidaPokemon(recuperarVida);
				this.listpokemon.get(0).setExp(this.listpokemon.get(0).getExp() + 20);
				System.out.println(this.listpokemon.get(0).getExp());
				try {
					throw new exceptions("Subiste de nivel! tu nivel: " + this.listpokemon.get(0).getNivel());
				} catch (exceptions e) {
					System.out.println(e.getMessage());
					app.fill(0);
					app.textSize(30);
					app.text(e.getMessage(),200,200);
				}
				new Thread(listpokemon.get(0)).stop();
				new Thread(listRival.get(0)).stop();
				pantallaJuego = 0;
				System.out.println(enemigos.size());
				System.out.println(listRival.size());
				return;
			}
		}
		app.text("Atacar",600,400);
		app.text("Atrapar",600,450);
		app.text("Huir",600,500);
		
	}
	
	public void batallaRival() {
		
		
		
		for (int i = 0; i < listpokemon.size(); i++) {
			listpokemon.get(i).setPosX(25);
			listpokemon.get(i).setPosY(375);
			//Thread
			new Thread(listpokemon.get(0)).start();
			new Thread(listRival.get(0)).start();
		}
		
			listpokemon.get(0).drawPokemon();
			app.fill(0);
			app.textSize(20);
			app.text(this.listpokemon.get(0).getNombres(),54,350);
			app.text("nivel: "+this.listpokemon.get(0).getNivel(),184,350);
			app.text("vida: "+this.listpokemon.get(0).getVidaPokemon(),400,400);
			app.text("Exp: "+this.listpokemon.get(0).getExp(),400,460);
			if(this.listpokemon.get(0).getVidaPokemon() <= 0) {
				for (int i = 0; i < listpokemon.size(); i++) {
					if(this.listpokemon.get(0).getVidaPokemon() <= 0 && listpokemon.get(i).getVidaPokemon() <= 0) {
						pantalla = 4;
						new Thread(listpokemon.get(0)).stop();
						new Thread(listRival.get(0)).stop();
						this.listpokemon.removeAll(listpokemon);
						}
				}
				return;
		}
		app.text("Atacar",600,400);
		app.text("Atrapar",600,450);
		app.text("Huir",600,500);

		for (int j = 0; j < listRival.size(); j++) {
			listRival.get(0).drawPokemon();
			app.text(this.listRival.get(listRival.get(0).getPokemonSolo()).getNombres(),504,350);
			app.text("nivel: "+this.listRival.get(listRival.get(0).getPokemonSolo()).getNivel(),584,350);
			app.text("vida: "+this.listRival.get(listRival.get(0).getPokemonSolo()).getVidaPokemon(),54,100);
			if(this.listRival.get(0).getVidaPokemon() <= 0) {
				if(listRival.size() == 1 && this.listRival.get(0).getVidaPokemon() <= 0) {
					pantalla = 5;
					new Thread(listpokemon.get(0)).stop();
					new Thread(listRival.get(j)).stop();
					return;
				}
				listRival.remove(listRival.get(0));
				listRival.get(0).setPokemonSolo(0);
				listRival.get(0).drawPokemon();
			}
		}		
	}

	
	public void clickBatalla() {
		
				//Atacar
				for (int i = 0; i < listpokemon.size(); i++) {
					listpokemon.get(i).setPosX(25);
					listpokemon.get(i).setPosY(375);
				}
				switch(pantallaJuego) {
				case 1:
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 380 && app.mouseY < 420) {
						System.out.println("velocidad Aliado: " + listpokemon.get(0).getVelocidadPokemon());
						System.out.println("velocidad Enemigo: " + enemigos.get(0).getVelocidadPokemon());
						System.out.println("vida Enemiga: " + this.enemigos.get(0).getVidaPokemon());
						System.out.println("daño aliado: "+ listpokemon.get(0).getDañoPokemon());
						this.listpokemon.get(0).atacar(listpokemon, enemigos);
					}
					//Atrapar
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 433 && app.mouseY < 470) {
						capturarPokemon();
						}
					//Huir
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 482 && app.mouseY < 520) {
						enemigos.remove();
						int recuperarVida = this.listpokemon.get(0).getVidaTotal();
						this.listpokemon.get(0).setVidaPokemon(recuperarVida);
						pantallaJuego = 0;
						}
					break;
					
				case 2:
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 380 && app.mouseY < 400) {
						System.out.println("esta funcionando esta función");
						this.listpokemon.get(0).atacarRival(listpokemon, listRival);
						System.out.println(listRival.size());
						System.out.println(listRival);
					}
					//Atrapar
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 433 && app.mouseY < 450) {
						try {
							throw new exceptions("No se puede atrapar pokemones que sean de otros");
						} catch (exceptions e) {
							System.out.println(e.getMessage());
							app.fill(0);
							app.textSize(30);
							app.text(e.getMessage(),200,200);
						}
					}
					//Huir
					if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 482 && app.mouseY < 500) {
						try {
							throw new exceptions("No puedes huir en un combate entre personas");
						} catch (exceptions e) {
							System.out.println(e.getMessage());
							app.fill(0);
							app.textSize(30);
							app.text(e.getMessage(),200,200);
						}
					}
					break;
				}
	}
	
	//Aca van los dibujos de las pantallas... debi crear una clase en el view 
	//para evitar todo este proceso tan mamon y llenar la logica
	//Aca es la función de las pantallas. Se que debi hacerlo en el view, pero para no complicarme tanto la vida lo hice aqui.
	public void pantallas() {
		switch(pantalla) {
		//Case 0 es la pantalla de Menu
		case 0:
			pantallaMenu();
			break;
		//Case 1 es la pantalla de Inicio, donde sale los personajes.
		case 1:
			pantallaEscoger();
			break;
		//Case 2 es la pantalla de Juego, solo creare un mapa.
		case 2:
			pantallaJuego();
//			System.out.println(app.mouseX);
//			System.out.println(app.mouseY);
			break;
		case 3:
			break;
		case 4:
			app.fill(0);
			app.text("Has perdido, tu pokemon ha fallecido y te toca reiniciar el juego", 30,100);
			app.text("reiniciar?",350,500);
			break;
		case 5:
			app.image(win,0,0);
		}
	}
	
	//Dibujar las pantallas
	public void pantallaMenu() {
		switch(pantallaMenu) {
		case 0:
			control.hide();
			app.image(intro,0,0);
			if(app.mouseX > 204 && app.mouseX < 331 && app.mouseY > 372 && app.mouseY < 461) {
				app.image(introclick,0,0);
			}
			break;
		case 1:
			//nombre del jugador 
			control.show();
			app.image(name,0,0);
			if(app.mouseX > 128 && app.mouseX < 681 && app.mouseY > 370 && app.mouseY < 460) {
				app.image(nameclick,0,0);
			}
			//nombre de usuario?
			nombres = "cómo te llamas"; 
			break;
		case 2:
			//explicacion del juego
			control.hide();
			app.image(explicacion,0,0);
			if(app.mouseX > 74 && app.mouseX < 720 && app.mouseY > 331 && app.mouseY < 424) {
				app.image(explicacionclick,0,0);
			}
			break;
		}
		
	}

	
	public void pokemonesRivales() {
			switch(choosePokemon) {
			case 1:
				listRival.add(new Miffy(rivalMiffy,500,25,5,app));
				for (int i = 0; i < listRival.size(); i++) {
					listRival.get(i).nivelRivalStatic(listRival);
				}
				break;
			case 2:
				listRival.add(new Leafabbit(rivalLeafabbit,500,25,5,app));
				for (int i = 0; i < listRival.size(); i++) {
					listRival.get(i).nivelRivalStatic(listRival);
				}
				break;
			case 3:
				listRival.add(new RedPanda(rivalRedPanda,500,25,5,app));
				for (int i = 0; i < listRival.size(); i++) {
					listRival.get(i).nivelRivalStatic(listRival);
				}
				break;
			}
			listRival.add(new Orogan(enemyOrogan,500,25,10,app));
			for (int i = 0; i < listRival.size(); i++) {
				listRival.get(i).nivelRivalStatic(listRival);
			}
			listRival.add(new Tori(enemyTori,500,25,10,app));
			for (int i = 0; i < listRival.size(); i++) {
				listRival.get(i).nivelRivalStatic(listRival);
			}
	}
	
	public void pantallaEscoger() {
		switch(choosePokemon) {
		case 0:
			app.image(pokemonSelectivo,0,0);
			if(app.mouseX > 88 && app.mouseX < 288 && app.mouseY > 170 && app.mouseY < 503) {
				app.image(rojoSelect,0,0);
			}
			if(app.mouseX > 297 && app.mouseX < 496 && app.mouseY > 170 && app.mouseY < 503) {
				app.image(blueSelect,0,0);
			}
			if(app.mouseX > 511 && app.mouseX < 710 && app.mouseY > 170 && app.mouseY < 503) {
				app.image(greenSelect,0,0);
			}
			break;
		case 1:
			//Feedback de qué pokemon eligio
			app.image(redpandaSelect,0,0);
			break;
		case 2:
			//Feedback de qué pokemon eligio
			app.image(miffySelect,0,0);
			break;
		case 3:
			//Feedback de qué pokemon eligio
			app.image(leafabbitSelect,0,0);
			break;
		}
		
	}
	
	public void pantallaJuego() {
		switch(pantallaJuego) {
		case 0:
			drawMap();
			dibujarEnLaMatrizPersonaje();
			dibujarEnemigoEnLaMatriz();
			app.textSize(20);
			app.fill(0);
			app.text("Ver mis Pokemones",600,50);
			break;
			//Pokemon salvaje aparece!
		case 1:
			batallaPokemon();
			break;
			//Rival salvaje aparece!
		case 2:
			batallaRival();
			break;
			//Opciones para cambiar pokimon y esas vainas
		case 3:
			app.image(pokedex,0,0);
			misPokemones();
			app.textSize(20);
			app.fill(0);
			app.text("ordenar nombre: a",20,20);
			app.text("ordenar nivel: n",320,20);
			app.text("Regresar al mapa",600,50);
			for (int i = 0; i < listpokemon.size(); i++) {
				app.textSize(20);
				app.text("Nombre: "+listpokemon.get(i).getNombres()+" Nivel: "+listpokemon.get(i).getNivel(),200,70+i*100);
			}
			break;
		case 5:
			app.image(introducciones,0,0);
		}
	}
	
	
	public void misPokemones() {
		for (int i = 0; i < listpokemon.size(); i++) {
			listpokemon.get(i).drawPokedex();
		}
	}
	
	//Aqui mezclo la info que tengo de pokedex...
	public void validarPokedex() {
		for (int i = 0; i < listpokemon.size(); i++) {
			switch(choosePokemon) {
			case 1:
				listPokedex.add(new RedPanda(rivalRedPanda,50,50+i*100,listpokemon.get(0).getNivel(),app));
				break;
			case 2:
				listPokedex.add(new Miffy(rivalMiffy,50,50+i*100,listpokemon.get(0).getNivel(),app));
				break;
			case 3:
				listPokedex.add(new Leafabbit(rivalLeafabbit,50,50+i*100,listpokemon.get(0).getNivel(),app));
				break;
			}
		}
	}
	
	public void prueba() {
		for (int i = 0; i < listpokemon.size(); i++) {
			switch(choosePokemon) {
			case 1:
				if(listpokemon.get(i).getPokemonValidar() == 0) {
				pokeCambio[0] = rivalRedPanda;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 2:
				if(listpokemon.get(i).getPokemonValidar() == 1) {
				pokeCambio[0] = rivalMiffy;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 3:
				if(listpokemon.get(i).getPokemonValidar() == 2) {
				pokeCambio[0] = rivalLeafabbit;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			}
			switch(enemyRandom) {
			case 0:
				if(listpokemon.get(i).getPokemonValidar() == 3) {
				pokeCambio[i] = enemyOrogan;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 1:
				if(listpokemon.get(i).getPokemonValidar() == 4) {
				pokeCambio[i] = enemyTori;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 2:
				if(listpokemon.get(i).getPokemonValidar() == 5) {
				pokeCambio[i] = enemySealmon;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			}
				listpokemon.get(i).setPosX(50);
				listpokemon.get(i).setPosY(50+100*i);
			}
	}
	
	public void prueba2() {
		for (int i = 0; i < listpokemon.size(); i++) {
			switch(choosePokemon) {
			case 1:
				if(listpokemon.get(i).getPokemonValidar() == 0) {
				pokeCambio[0] = myRedPanda;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 2:
				if(listpokemon.get(i).getPokemonValidar() == 1) {
				pokeCambio[0] = myMiffy;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 3:
				if(listpokemon.get(i).getPokemonValidar() == 2) {
				pokeCambio[0] = myLeafabbit;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			}
			switch(enemyRandom) {
			case 0:
				if(listpokemon.get(i).getPokemonValidar() == 3) {
				pokeCambio[i] = myOrogan;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 1:
				if(listpokemon.get(i).getPokemonValidar() == 4) {
				pokeCambio[i] = myTori;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			case 2:
				if(listpokemon.get(i).getPokemonValidar() == 5) {
				pokeCambio[i] = mySealmon;
				listpokemon.get(i).setPokeImagen(pokeCambio[i]);
				}
				break;
			}
				listpokemon.get(i).setPosX(25);
				listpokemon.get(i).setPosY(375);
			}
	}
	
	public void capturarPokemon() {
		if(enemigos.get(0).getVidaPokemon() <= 20) {
			atrapar = true;
			if(atrapar == true) 
					switch(enemyRandom) {
					case 0:
						listpokemon.add(new Orogan(myOrogan,25,375,enemigos.get(0).getNivel(),app));
						break;
					case 1:
						listpokemon.add(new Tori(myTori,25,375,enemigos.get(0).getNivel(),app));
						break;
					case 2:
						listpokemon.add(new Sealmon(mySealmon,25,375,enemigos.get(0).getNivel(),app));
						break;
					default:
						listpokemon.add(new Sealmon(mySealmon,25,375,enemigos.get(0).getNivel(),app));
						break;
					}
				int recuperarVida = this.listpokemon.get(0).getVidaTotal();
				this.listpokemon.get(0).setVidaPokemon(recuperarVida);
				this.enemigos.removeAll(enemigos);
				System.out.println(listpokemon.size());
				pantallaJuego = 0;
			}
	}
	
	//Aca van la mayoria del metodo mouseClicked()
	public void click() {
		switch(pantalla) {
		//Menu Interface 
		case 0:
			switch(pantallaMenu) {
			case 0:
				if(app.mouseX > 204 && app.mouseX < 331 && app.mouseY > 372 && app.mouseY < 461) {
					pantallaMenu = 1;
					}
				break;
			case 1:
				//pantalla Interface de escribir el nombresito
				nombres = control.get(Textfield.class,"nombre").getText();
				
				try {
					throw new exceptions("te llamas: "+nombres);
				} catch(exceptions e) {
					System.out.println(e.getMessage());
				}
				
				if(app.mouseX > 128 && app.mouseX < 681 && app.mouseY > 370 && app.mouseY < 460) {
					
					//Aqui lo intente guardar pero salia error, entonces preferi comentarlo...
					//app.saveStream("./datas/nombres.txt", nombres);
					pantallaMenu = 2;
				}
				break;
			case 2:
				pantalla = 1;
				break;
			}
		break;
		//Escoger tu pokemon inicial
		case 1:
			if(choosePokemon == 0) {
				if(app.mouseX > 88 && app.mouseX < 288 && app.mouseY > 170 && app.mouseY < 503) {
					listpokemon.add(new RedPanda(myRedPanda,25,375,1000,app));
					System.out.println(listpokemon);
					listpokemon.get(0).nivelAliado(listpokemon);
					choosePokemon = 1;
					
				}
				if(app.mouseX > 297 && app.mouseX < 496 && app.mouseY > 170 && app.mouseY < 503) {
					listpokemon.add(new Miffy(myMiffy,25,375,5,app));
					System.out.println(listpokemon);
					listpokemon.get(0).nivelAliado(listpokemon);
					choosePokemon = 2;
				}
				if(app.mouseX > 511 && app.mouseX < 710 && app.mouseY > 170 && app.mouseY < 503) {
					listpokemon.add(new Leafabbit(myLeafabbit,25,375,5,app));
					System.out.println(listpokemon);
					listpokemon.get(0).nivelAliado(listpokemon);
					choosePokemon = 3;
				}
			}else {
				if(app.mouseX > 110 && app.mouseX < 705 && app.mouseY > 441 && app.mouseY < 517 ) {
					pokemonesRivales();
					//validarPokedex();
					this.pantalla = 2;
					this.pantallaJuego = 5;
				}
			}
		break;
		case 2:
			switch(pantallaJuego) {
			case 0:
				if(app.mouseX > 600 && app.mouseX < 750 && app.mouseY > 20 && app.mouseY < 50) {
					pantallaJuego = 3;
					prueba();
				}
				break;
			case 1:
				clickBatalla();
				break;
			case 2:
				clickBatalla();
				break;
			case 3:
				if(app.mouseX > 600 && app.mouseX < 750 && app.mouseY > 20 && app.mouseY < 50) {
					pantallaJuego = 0;
					prueba2();
				}
				break;
			case 5:
				pantallaJuego = 0;
				break;
			}
			break;
			//pokedex
		case 3:
			break;
			//Perdiste
		case 4:
			pantalla = 0;
			pantallaJuego = 0;
			pantallaMenu = 0;
			choosePokemon = 0;
			protagonista.setmX(0);
			protagonista.setmY(0);
			protagonista.setPosX(25);
			protagonista.setPosY(25);
			listRival.removeAll(listRival);
			break;
		case 5:
			//Ganaste
			app.exit();
			break;
			}
	}
	
	
	
	//Para ordenamiento use estos metodos xd
	public void sortList(char c) {
		switch(c) {
		case 'n':
			Collections.sort(listpokemon);
			for (int i = 0; i < listpokemon.size(); i++) {
				listpokemon.get(i).setPosY(50+i*100);
			}
			break;
		case 'a':
			Collections.sort(listpokemon, comparePokemon);
			for (int i = 0; i < listpokemon.size(); i++) {
				listpokemon.get(i).setPosY(50+i*100);
			}
			break;
		}
		
	}
	
	
	
	
	
	
	
	//Cargar las Imagenes
	public void pImagePokemones() {
		//Imagenes de pokemones
		this.myRedPanda = app.loadImage("./images/RedpandaAtras.png");
		this.myMiffy = app.loadImage("./images/MiffyAtras.png");
		this.myLeafabbit = app.loadImage("./images/LeafabbitAtras.png");
		this.mySealmon = app.loadImage("./images/SealmonAtras.png");
		this.myOrogan = app.loadImage("./images/OroganAtras.png");
		this.myTori = app.loadImage("./images/ToriAtras.png");
		
		//Pokemones rivales o salvajes
		this.rivalRedPanda = app.loadImage("./images/Redpanda.png");
		this.rivalMiffy = app.loadImage("./images/Miffy.png");
		this.rivalLeafabbit = app.loadImage("./images/Leafabbit.png");
		this.enemySealmon = app.loadImage("./images/Sealmon.png");
		this.enemyOrogan = app.loadImage("./images/Orogan.png");
		this.enemyTori = app.loadImage("./images/Tori.png");
		
		//Imagenes de fondo
		this.fondo = app.loadImage("./images/Mapa.jpg");
		
		//Imagenes de Menus y otros interfaces
		this.intro = app.loadImage("./images/Intro.jpg");
		this.introclick = app.loadImage("./images/IntroClick.jpg");
		this.name = app.loadImage("./images/name.jpg");
		this.nameclick = app.loadImage("./images/nameClick.jpg");
		this.explicacion = app.loadImage("./images/explicacion.jpg");
		this.explicacionclick = app.loadImage("./images/explicacionClick.jpg");
		this.pokemonSelectivo = app.loadImage("./images/pokemonSelectivo.jpg");
		this.rojoSelect = app.loadImage("./images/rojoSelect.jpg");
		this.greenSelect = app.loadImage("./images/greenSelect.jpg");
		this.blueSelect = app.loadImage("./images/blueSelect.jpg");
		this.redpandaSelect = app.loadImage("./images/redPandaSelect.jpg");
		this.redPandaClick = app.loadImage("./images/redpandaClick.jpg");
		this.miffySelect = app.loadImage("./images/miffySelect.jpg");
		this.miffyClick = app.loadImage("./images/miffyClick.jpg");
		this.leafabbitSelect = app.loadImage("./images/leafabbitSelect.jpg");
		this.leafabbitClick = app.loadImage("./images/leafabbitClick.jpg");
		
		//Las ultimas imagenes que saque
		this.win = app.loadImage("./images/win.jpg");
		this.pokedex = app.loadImage("./images/pokedex.jpg");
		this.introducciones = app.loadImage("./images/Introducciones.jpg");
	
	}
}
