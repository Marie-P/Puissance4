import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		/**************************** INITIALISATION ****************************/
		Grille grille = new Grille(6,7);
		Joueur joueur1 = new Joueur('X');
		Joueur joueur2 = new Joueur('O');
		Joueur joueurCourant = new Joueur();
		Scanner scanner = new Scanner(System.in);
		String input;
		boolean continuerJeu = true;
		
		/********************************* MENU *********************************/
		System.out.println("BIENVENUE SUR PUISSANCE 4 !");
		System.out.println("Avant de commencer entrez le nom du premier joueur : ");
		input = scanner.nextLine();
		joueur1.setNom(input);
		System.out.println("Maintenant le nom du deuxième joueur : ");
		input = scanner.nextLine();
		joueur2.setNom(input);
		System.out.println("C'est parti !\n");
		
		/******************************** DEBUT *********************************/
		grille.initialisation();
		grille.affiche();
		
		for (int coups = 0; continuerJeu; coups++)
		{
			if(coups % 2 == 0)
				joueurCourant = joueur1;
			else
				joueurCourant = joueur2;
			
			System.out.println("\n" + joueurCourant.getNom() + " à toi de jouer !");
			System.out.println("Entre un numéro entre 1 et " + grille.getNbColonnes());
			
			grille.placeJeton(joueurCourant);
			grille.affiche();
			
			// Jeu s'arrête si la grille est pleine ou que un des joueurs a gagner
			if(grille.estPlein())
			{
				System.out.println("Match nul !");
				continuerJeu = false;
			}
			
			else if(grille.gagne(joueurCourant))
			{
				System.out.println(joueurCourant.getNom() + " a gagné !");
				continuerJeu = false;
			}
				
		}
	}
}
