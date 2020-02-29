public class Joueur {
	private char symbole;
	private String nom;
	
	public Joueur() {
		super();
	}
	
	public Joueur(char symbole) {
		super();
		this.symbole = symbole;
	}

	public char getSymbole() {
		return symbole;
	}

	public void setSymbole(char symbole) {
		this.symbole = symbole;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
