package main;

import graphique.Window;
import map.MapGenerator;

public class DonjonGame {

	public static void main(String[] args) {
		
		// génération de la map
		MapGenerator generator = new MapGenerator();
		generator.writeFile();
		
		// affichage de la fenêtre
		Window window = new Window();
		window.loadParameters();
		
		System.out.println("Bienvenue sur le jeu DONJON GAME !");
		System.out.println("Le but de ce jeu est de parcourir le labyrinthe en tuant des monstres et de trouver la sortie pour affronter le boss final");
		System.out.println("Commande : " );
		System.out.println("Z pour avancer");
		System.out.println("S pour reculer");
		System.out.println("Q pour aller à gauche");
		System.out.println("D pour aller à droite");
		System.out.println("E pour utiliser la pomme magique");
		System.out.println("T pour ouvrir un coffre");
		System.out.println("Pour obtenir la pomme dorée, il faudra ramasser trois pommes");
		System.out.println("Elle vous permet de changer le comportement du monstre");
		System.out.println("Votre animal domestique vous aide à attaquer les monstres");
		System.out.println("Bonne chance ! :)");
		
	}
	
}
