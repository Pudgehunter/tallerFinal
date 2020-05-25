package model;

import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Logic {
	
	private PApplet app;
	//Matriz del mapa
	private int[][] matriz;
	
	//Atributo de logica
	private int pantalla, pantallaJuego, HorizontalMap, VerticalMap;
	
	//pokemonEscogido
	private int pokemonInicial;
	
	//pokemonEscoger?
	private int choosePokemon;
	
	//Mi protagonista, antagonista y pokemones
	Personaje protagonista;
	Antagonista antagonista;
	LinkedList<Pokemon> pokemon;
	
	//Todas las imagenes
	PImage myRedPanda,myMiffy,myLeafabbit,
	mySealmon,myOrogan,myTori,
	rivalRedPanda,rivalMiffy,rivalLeafabbit,
	enemySealmon,enemyOrogan,enemyTori,
	fondo;
	
	//Prueba pokemon Arraylist
	private int miListaPokemon;
	
	public Logic(PApplet app) {
		this.app = app;
		createMap();
		this.pokemon = new LinkedList<Pokemon>();
	}
	
	public void createMap() {
		//Visualizando el mapa para que quede bonito xd
		HorizontalMap = 800/50;
		VerticalMap = 600/50;
		pantalla = 1;
		pantallaJuego = 0;
		choosePokemon = 0;
		//La cantidad de pokemon que tengo ahora;
		miListaPokemon  = 0;
		
		//Los arreglos de pokemones
		this.matriz = new int[HorizontalMap][VerticalMap];
		pImagePokemones();
		dibujarPersonaje();
		dibujarEnemigo();
	}
	
	
	//Aca es la función de las pantallas.
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
			switch(pantallaJuego) {
			case 0:
				drawMap();
				dibujarEnLaMatrizPersonaje();
				dibujarEnemigoEnLaMatriz();
				//System.out.println(this.protagonista.getmX());
				//System.out.println(this.protagonista.getmY());
				break;
				//Pokemon salvaje aparece!
			case 1:
				batallaPokemon();
				break;
				//Rival salvaje aparece!
			case 2:
				
				break;
				//Opciones para cambiar pokimon y esas vainas
			case 3:
				
				break;
			}
			
			break;
		}
		
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
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				int posX = (50*15+25);
				int posY = (50*11+25);
				this.antagonista = new Antagonista(posX,posY,app);
			}
		}
	}
	
	//"dibujar el personaje en el matriz"
	public void dibujarEnLaMatrizPersonaje() {
		this.protagonista.dibujarPersonaje();
	}
	
	//dibujar el rival de toda su vida
	public void dibujarEnemigoEnLaMatriz() {
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				this.antagonista.dibujarEnemigo();
			}
		}
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
				System.out.println("funciona");
				//pantallaJuego = 2;
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
				break;
			default:
				System.out.println("te salvaste!");
				break;
			}
		}
	}
	
	public void batallaDibujarPokemon() {
		switch(choosePokemon) {
		case 1:
			for (int i = 0; i < this.pokemon.size(); i++) {
				this.pokemon.get(i).drawPokemon();
			}
			break;
		case 2:
			for (int i = 0; i < this.pokemon.size(); i++) {
				this.pokemon.get(i).drawPokemon();
			}
			break;
		case 3:
			for (int i = 0; i < this.pokemon.size(); i++) {
				this.pokemon.get(i).drawPokemon();
			}
			break;
		}
	}
	
	public void batallaPokemon() {
		//Cliquear los atacar, items y correr
		batallaDibujarPokemon();
		for (int i = 0; i < pokemon.size(); i++) {
			//vida enemigo
			app.fill(0);
			app.textSize(50);
			app.text("Vida: ",100,100);
			//vida aliado
			app.text("Vida: ",100,100);
			//exp aliado
		}
	}
	public void visualizarPokemones() {
		if(choosePokemon == 1) {
			this.pokemon.add(new RedPanda(20,400,5,app));
		}
		if(choosePokemon == 2) {
			this.pokemon.add(new Miffy(myMiffy,20,400,5,app));
		}
		if(choosePokemon == 3) {
			this.pokemon.add(new Leafabbit(myLeafabbit,20,400,5,app));
		}
	}
	
	public void clickBatalla() {
		if(app.mouseX > 800/2 && app.mouseX < 800/2 + 60 && app.mouseY > 400 && app.mouseY < 410) {
			
		}
	}
	
	
	//Aca van los dibujos de las pantallas... debi crear una clase para evitar todo este proceso tan mamon y llenar la logica
	//Dibujar las pantallas
	public void pantallaMenu() {
		app.background(0);
		app.fill(255);
		app.textSize(20);
		app.text("Pokemon Fan Made",800/2,100);
		app.textSize(10);
		app.text("Iniciar",800/2,400);
		app.text("salir",800/2,450);
	}
	
	public void pantallaEscoger() {
		switch(choosePokemon) {
		case 0:
			app.textSize(20);
			app.fill(0);
			app.text("Elige tu pokemon",800/2,100);
			app.rect(100,200,150,300);
			app.rect(300,200,150,300);
			app.rect(500,200,150,300);
			break;
		case 1:
			//Feedback de qué pokemon eligio
			app.fill(255,0,0);
			app.rect(100,200,150,300);
			app.textSize(10);
			app.fill(0);
			app.text("escogiste a red panda, y tu rival ha escogido Miffy! Suerte en tu batalla contra él",50,100);
			app.text("seguir",800/2,400);
			break;
		case 2:
			//Feedback de qué pokemon eligio
			app.fill(0,255,0);
			app.rect(100,200,150,300);
			app.textSize(10);
			app.fill(0);
			app.text("escogiste a leafabbit, y tu rival ha escogido red panda! Suerte en tu batalla contra él",50,100);
			app.text("seguir",800/2,400);
			break;
		case 3:
			//Feedback de qué pokemon eligio
			app.fill(0,255,255);
			app.rect(100,200,150,300);
			app.textSize(10);
			app.fill(0);
			app.text("escogiste a Miffy, y tu rival ha escogido leafabbit! Suerte en tu batalla contra él",50,100);
			app.text("seguir",800/2,400);
			break;
		}
		
	}
	
	
	
	//Aca van la mayoria del metodo mouseClicked()
	public void clickTutorial() {
		switch(pantalla) {
		//Menu Interface 
		case 0:
		if(app.mouseX > 800/2 && app.mouseX < 800/2 + 60 && app.mouseY > 400 && app.mouseY < 410) {
			pantalla = 1;
			}
		break;
		//Escoger tu pokemon inicial
		case 1:
			if(choosePokemon == 0) {
			if(app.mouseX > 100 && app.mouseX < 100 + 150 && app.mouseY > 200 && app.mouseY < 200 + 300) {
			choosePokemon = 1;
			}
			if(app.mouseX > 300 && app.mouseX < 300 + 150 && app.mouseY > 200 && app.mouseY < 200 + 300) {
			choosePokemon = 2;
			}
			if(app.mouseX > 500 && app.mouseX < 500 + 150 && app.mouseY > 200 && app.mouseY < 200 + 300) {
			choosePokemon = 3;
			}
			}else {
				if(app.mouseX > 800/2 && app.mouseX < 800/2 + 60 && app.mouseY > 400 - 60 && app.mouseY < 400) {
					visualizarPokemones();
					this.pantalla = 2;
				}
			}
		break;
			}
	}
	
	
	//Cargar las Imagenes
	public void pImagePokemones() {
		//Imagenes de pokemones
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
	}
}
