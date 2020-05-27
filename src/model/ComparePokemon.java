package model;

import java.util.Comparator;

public class ComparePokemon implements Comparator <Pokemon> {
	
	public int compare(Pokemon o1, Pokemon o2) {
		return o1.getNombres().compareTo(o2.getNombres());
	}

}
