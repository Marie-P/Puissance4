import java.util.Scanner;

public class Grille {
	private char [][] grille;
	private int nbLignes;
	private int nbColonnes;

	public Grille(int nbLignes, int nbColonnes) {
		this.grille = new char[nbLignes][nbColonnes];
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
	}
	
	public int getNbColonnes() {
		return nbColonnes;
	}
	
	public int getNbLignes() {
		return nbLignes;
	}

	public void initialisation() {
		for (int line = 0; line < nbLignes; line++)
			for (int col = 0; col < nbColonnes; col++)
				grille[line][col] = '_';
	}

	public void affiche() {
		//Affiche Les numéros des colonnes
		for (int i = 0; i < nbColonnes; i++) {
			System.out.print("  " + (i+1));
		}
		System.out.println();
		
		// Pour un affichage ergonomique
		for (int i = 0; i < nbColonnes + 2 + 2 * nbColonnes; i++)
			System.out.print('_');
		System.out.println();
		
		for (int line = 0; line < nbLignes; line++)
		{
			System.out.print('|');
			for (int col = 0; col < nbColonnes; col++) 
				System.out.print(" " + grille[line][col]+ " " );
				
			System.out.print('|');
			System.out.println();
		}
		
		for (int i = 0; i < nbColonnes + 2 + 2 * nbColonnes; i++)
			System.out.print('_');
		System.out.println();
	}
	
	public void placeJeton(Joueur joueur) {
		Scanner scanner = new Scanner(System.in);
		boolean jetonPlace = false; // Va nous permettre de vérifier si le jeton est placé
		int emplacement = -1; // Pour vérifier si il y a quelque chose dans emplacement
		
		while(!jetonPlace)
		{
			String input = scanner.nextLine();
			
			// On verifie l'entree de l'utilisateur
			try
			{
				emplacement = Integer.valueOf(input);
				
				if(emplacement >= 1 && emplacement <= getNbColonnes())
					if(grille[0][emplacement-1] != '_') // On test si la colonne est pleine
						System.out.println("Cette colonne est pleine !");
				
					else
						jetonPlace = true;
			}
			
			catch (Exception e)
			{
				System.out.println("Recommencez ! ");
			}
		}
		
		// Pour placer le jeton en haut de la colonne
		int ligne = getNbLignes() - 1;
		while(grille[ligne][emplacement - 1] != '_')
			ligne--;
		grille[ligne][emplacement - 1] = joueur.getSymbole();
			
		
	}
	
	public boolean gagne(Joueur joueur) {
		// On va regarder autour du jeton qu'on vient de placer
		
		int maxNbJetonsAlignes = 0, nbSymbolesIdentique = 0;
		
		// verification verticale
		for (int col = 0; col < getNbColonnes(); col++) 
		{
			nbSymbolesIdentique = 0;
			for (int line = getNbLignes() - 1; line >= 0 && nbSymbolesIdentique < 4; line--) 
			{
				if(grille[line][col] == joueur.getSymbole()) 
					nbSymbolesIdentique++;
				else
					nbSymbolesIdentique = 0;
			}
			if(nbSymbolesIdentique > maxNbJetonsAlignes)
				maxNbJetonsAlignes = nbSymbolesIdentique;
		}
		
		// verification horizontale
		for (int line = 0; line < getNbLignes()  && nbSymbolesIdentique < 4; line++) {
			nbSymbolesIdentique = 0;
			for (int col = 0; col < getNbColonnes() && nbSymbolesIdentique < 4; col++) {
				if(grille[line][col] == joueur.getSymbole()) 
					nbSymbolesIdentique++;
				else
					nbSymbolesIdentique = 0;	
			}
	
			if(nbSymbolesIdentique > maxNbJetonsAlignes)
				maxNbJetonsAlignes = nbSymbolesIdentique;
		}
		
		//Verfication diagonale (commence en haut à gauche)
		for (int col = 0; col < getNbColonnes()  && nbSymbolesIdentique < 4; col++) {
			for (int line = 0; line < getNbLignes() && nbSymbolesIdentique < 4; line++) {
				nbSymbolesIdentique = 0;
				for (int x = line, y = col; x < getNbLignes() && y < getNbColonnes()  && nbSymbolesIdentique < 4; x++, y++) {
					if(grille[x][y] == joueur.getSymbole()) 
						nbSymbolesIdentique++;	
					else
						nbSymbolesIdentique = 0;
				}
				if(nbSymbolesIdentique > maxNbJetonsAlignes)
					maxNbJetonsAlignes = nbSymbolesIdentique;
			}
		}
		
		//Verfication diagonale (commence en haut à droite)
		for (int col = getNbColonnes() - 1; col >= 0  && nbSymbolesIdentique < 4; col--) {
			for (int line = 0; line < getNbLignes() && nbSymbolesIdentique < 4; line++) {
				nbSymbolesIdentique = 0;
				for (int x = line, y = col; x < getNbLignes() && y >= 0; x++, y--) {
					if(grille[x][y] == joueur.getSymbole()) 
						nbSymbolesIdentique++;
					else
						nbSymbolesIdentique = 0;	
				}
				if(nbSymbolesIdentique > maxNbJetonsAlignes)
					maxNbJetonsAlignes = nbSymbolesIdentique;
			}
		}
		
		if (maxNbJetonsAlignes >= 4)
			return true;
		return false;
	}
	
	
	public boolean estPlein() {
		
		for (int line = 0; line < nbLignes; line++)
			for (int col = 0; col < nbColonnes; col++)
				if(grille[line][col] == '_')
					return false;
		return true;
	}
}


