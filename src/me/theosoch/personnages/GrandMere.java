package me.theosoch.personnages;

import java.util.Random;

public class GrandMere extends Humain {

	private static int NOMBRE_CONNAISSANCES_LIMITE = 5;
	
	private enum TypeHumain {
		HABITANT("habitant"),
		COMMERCANT("commercant"),
		YAKUZA("yakuza"),
		RONIN("ronin"),
		SAMOURAI("samourai"),
		GRANDMERE("grand-mère"),
		
		/* En dernier : ainsi, dans l'ordre des valeurs de l'enum, ce type se trouvera en dernier de la liste
		et pourra plus facilement être exclu des choix */
		TRAITRE("traitre");
		
		private final String nom;
		
		private TypeHumain(String nom) {
			this.nom = nom;
		}
		
		public String getNom() { return this.nom; }
	}
	
	//
	
	private final Random random = new Random();
	
	//	
	
	public GrandMere(String nom, int argent) {
		super(nom, "tisane", argent);
	}
	
	//
	
	@Override
	protected void memoriser(Humain h) {
		if(this.nombreConnaissances() < NOMBRE_CONNAISSANCES_LIMITE) { super.memoriser(h); }
		else { this.parler("Oh ma tête ! Je ne peux plus retenir le nom d'une personne supplémentaire !"); }
	}
	
	//	
	
	private String humainHasard() {
		TypeHumain[] types = TypeHumain.values();
		
		return types[this.random.nextInt(0, types.length-1)].getNom();
	}
	
	public void ragoter() {
		Humain[] connaissances = this.connaissances();
		int nombreConnaissances = this.nombreConnaissances();
		
		for(int i = 0; i < nombreConnaissances; i++) {
			Humain connaissance = connaissances[i];
			
			if(connaissance instanceof Traitre) {
				this.parler("Je sais que " + connaissance.getNom() + " est un " + TypeHumain.TRAITRE.getNom() + ". Petit chenapan !");
			}
			else {
				this.parler("Je crois que " + connaissance.getNom() + " est un " + this.humainHasard());
			}
		}
	}
	
}
