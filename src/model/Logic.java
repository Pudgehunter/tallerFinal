package model;

import java.util.LinkedList;

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
	
	//Mi protagonista, antagonista y pokemones
	Personaje protagonista;
	Antagonista antagonista;
	
	//Mi cerebro no funciono para un Linkedlist
	LinkedList<Pokemon> listpokemon;
	LinkedList<Pokemon> enemigos;
	LinkedList<Pokemon> listRival;
	
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
	leafabbitSelect,leafabbitClick;
	
	//Prueba pokemon Arraylist
	private int miListaPokemon;
	
	public Logic(PApplet app) {
		this.app = app;
		setupLogic();
		createMap();
		listpokemon = new LinkedList<Pokemon>();
		enemigos = new LinkedList<Pokemon>();
		listRival = new LinkedList<Pokemon>();
	}
	public void setupLogic() {
		pImagePokemones();
		pantalla = 0;
		pantallaJuego = 0;
		pantallaMenu = 0;
		choosePokemon = 0;	
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
				validarRandomEnemigos();
				break;
			default:
				System.out.println("te salvaste!");
				break;
			}
		}
	}
	
	public void validarRandomEnemigos() {
		if(pantallaJuego == 1) {
			int enemyRandom = (int) app.random(0,3);
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
				for (int i = 0; i < listpokemon.size(); i++) {
					listpokemon.get(i).nivelAliado(listpokemon);
				}
				break;
			}
			System.out.println(enemigos);
			System.out.println(enemigos.size());
		}
	}
	
	public void batallaPokemon() {
		for (int i = 0; i < listpokemon.size(); i++) {
			listpokemon.get(i).drawPokemon();
			app.fill(0);
			app.textSize(20);
			app.text(this.listpokemon.get(i).getNombres(),54,350);
			app.text("vida: "+this.listpokemon.get(i).getVidaPokemon(),400,400);
			app.text("Exp: "+this.listpokemon.get(i).getExp(),400,460);
		}
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).drawPokemon();
			app.text(this.enemigos.get(i).getNombres(),504,350);
			app.text("vida: "+this.enemigos.get(i).getVidaPokemon(),54,100);
			if(this.enemigos.get(i).getVidaPokemon() <= 0) {
				this.enemigos.remove();
				int recuperarVida = this.listpokemon.get(i).getVidaPokemon();
				int recuperarTotalVida = recuperarVida;
				recuperarTotalVida = this.listpokemon.get(i).getVidaTotal();
				return;
			}
		}
		app.text("Atacar",600,400);
		app.text("Atrapar",600,450);
		app.text("Huir",600,500);
		
	}
	
	public void clickBatalla() {
		for (int i = 0; i < listpokemon.size(); i++) {
			for (int j = 0; j < enemigos.size(); j++) {
				//Atacar
				if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 380 && app.mouseY < 400) {
					System.out.println("velocidad Aliado: " + listpokemon.get(i).getVelocidadPokemon());
					System.out.println("velocidad Enemigo: " + enemigos.get(j).getVelocidadPokemon());
					System.out.println("vida Enemiga: " + this.enemigos.get(i).getVidaPokemon());
					this.listpokemon.get(i).atacar(listpokemon, enemigos);
				}
				//Atrapar
				if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 380 && app.mouseY < 400) {
					
				}
				//Huir
				if(app.mouseX > 595 && app.mouseX < 667 && app.mouseY > 380 && app.mouseY < 400) {
			
				}
			}
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
		}
	}
	
	//Dibujar las pantallas
	public void pantallaMenu() {
		switch(pantallaMenu) {
		case 0:
			app.image(intro,0,0);
			if(app.mouseX > 204 && app.mouseX < 331 && app.mouseY > 372 && app.mouseY < 461) {
				app.image(introclick,0,0);
			}
			break;
		case 1:
			//nombre del jugador 
			app.image(name,0,0);
			if(app.mouseX > 128 && app.mouseX < 681 && app.mouseY > 370 && app.mouseY < 460) {
				app.image(nameclick,0,0);
			}
			break;
		case 2:
			//explicacion del juego
			app.image(explicacion,0,0);
			if(app.mouseX > 74 && app.mouseX < 720 && app.mouseY > 331 && app.mouseY < 424) {
				app.image(explicacionclick,0,0);
			}
			break;
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
				if(app.mouseX > 128 && app.mouseX < 681 && app.mouseY > 370 && app.mouseY < 460) {
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
					listpokemon.add(new RedPanda(myRedPanda,25,375,5,app));
					System.out.println(listpokemon);
					for (int i = 0; i < listpokemon.size(); i++) {
						listpokemon.get(i).nivelAliado(listpokemon);
					}
					choosePokemon = 1;
					
				}
				if(app.mouseX > 297 && app.mouseX < 496 && app.mouseY > 170 && app.mouseY < 503) {
					listpokemon.add(new Miffy(myMiffy,25,375,5,app));
					System.out.println(listpokemon);
					for (int i = 0; i < listpokemon.size(); i++) {
						listpokemon.get(i).nivelAliado(listpokemon);
					}
					choosePokemon = 2;
				}
				if(app.mouseX > 511 && app.mouseX < 710 && app.mouseY > 170 && app.mouseY < 503) {
					listpokemon.add(new Leafabbit(myLeafabbit,25,375,5,app));
					System.out.println(listpokemon);
					for (int i = 0; i < listpokemon.size(); i++) {
						listpokemon.get(i).nivelAliado(listpokemon);
					}
					choosePokemon = 3;
				}
			}else {
				if(app.mouseX > 110 && app.mouseX < 705 && app.mouseY > 441 && app.mouseY < 517) {
					this.pantalla = 2;
				}
			}
		break;
		case 2:
			switch(pantallaJuego) {
			case 0:
				break;
			case 1:
				clickBatalla();
				break;
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
	
	}
}
