package me.theosoch.personnages;

public class Humain {
	
	private static final int CONNAISSANCES_MAX = 30;
	
	//	

	private String nom;
	private String boissonFavorite;
	
	private int argent;
	
	private int indiceFinConnaissances = 0;
	private Humain[] connaissances = new Humain[Humain.CONNAISSANCES_MAX];
	
	//
	
	public Humain(String nom, String boissonFavorite, int argent) {
		this.nom = nom;
		this.boissonFavorite = boissonFavorite;
		this.argent = argent;
	}
	
	//
	
	public String getNom() { return this.nom; }
	
	// Nécéssaire pour les classes héritiées
	protected int getArgent() { return this.argent; }
	
	public int nombreConnaissances() {
		final Humain[] currentConnaissances = this.connaissances();
		return currentConnaissances[currentConnaissances.length-1] == null ? this.indiceFinConnaissances : currentConnaissances.length;
	}
	
	public final Humain[] connaissances() { return this.connaissances; }
	
	//
	
	protected void parler(String texte) {
		System.out.println("(" + this.getNom() + ") - " + texte);
	}
	
	//	
	
	public void direBonjour() {
		this.parler("Bonjour ! Je m'appelle " + this.getNom() + " et j'aime boire du " + this.boissonFavorite + ".");
	}
	
	public void boire() {
		this.parler("Mmmm, un bon verre de " + this.boissonFavorite + " ! GLOUPS !");
	}
	
	public void acheter(String bien, int prix) {
		int currentArgent = this.getArgent();
		
		if(currentArgent >= prix) {
			this.parler("J'ai " + currentArgent + " sous en poche. Je vais pouvoir m'offrir " + bien + " à " + prix + " sous.");
		}
		else {
			this.parler("Je n'ai que " + currentArgent + " sous en poche. Je ne peux même pas m'offrir " + bien + " à " + prix + " sous.");
		}
		this.perdreArgent(prix);
	}
	
	public void gagnerArgent(int gain) {
		this.argent += gain;
	}
	
	public void perdreArgent(int perdre) {
		this.argent -= perdre;
	}
	
	//
	
	public void faireConnaissance(Humain humain) {
		this.direBonjour();
		humain.repondre(this);
		this.memoriser(humain);
	}
	
	public void repondre(Humain humain) {
		this.direBonjour();
		this.memoriser(humain);
	}
	
	protected void memoriser(Humain humain) {
		final int nbConnaissancesMax = this.connaissances().length;
		
		this.connaissances[this.indiceFinConnaissances] = humain;
		this.indiceFinConnaissances = (this.indiceFinConnaissances + 1) % nbConnaissancesMax;
	}
	
	//
	
	public void listerConnaissances() {
		StringBuilder texteBuilder = new StringBuilder();
		texteBuilder.append("Je connais beaucoup de monde dont :");

		final int nbConnaissances = this.nombreConnaissances();
		
		for(int i = 0; i < nbConnaissances; i++) {
			texteBuilder.append("\n - " + this.connaissances[i].getNom());
		}
		
		this.parler(texteBuilder.toString());
	}
	
}
