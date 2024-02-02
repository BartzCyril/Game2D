package map;

import java.io.FileWriter;
import java.io.IOException;

public class MapGenerator {

	private int width = 65;
	private int height = 33;
	
	/**
	 * procédure qui écrit dans le fichier map.txt la nouvelle map 
	 * qui contient les deux map générées aléatoirement + la map du boss 
	 */
	public void writeFile() {
		    try {
		      FileWriter writer = new FileWriter("maps\\\\maps\\\\map.txt", false);

		      int[][] miniMap = generation();
		      int[][] miniMap2 = generation();
		      for(int j=1 ; j < (width-1); j++) {
		    	  miniMap2[0][j] = 0;
		      }
		      int[][] miniMap3 = getBossMap(33, 65);
		      
		      for(int i=0 ; i < height; i++) {
					for (int j=0; j < width; j++) {
						writer.write(Integer.toString(miniMap[i][j]));
					}
					writer.write("\n");
				}
		      
		      for(int i=0 ; i < height; i++) {
					for (int j=0; j < width; j++) {
						writer.write(Integer.toString(miniMap2[i][j]));
					}
					writer.write("\n");
				}
		      
		      for(int i=0 ; i < height; i++) {
					for (int j=0; j < width; j++) {
						writer.write(Integer.toString(miniMap3[i][j]));
					}
					writer.write("\n");
				}
		      
		      writer.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  
	}

	/**
	 * renvoie un tableau bidimensionnel qui représente une map du niveau 1 ou 2 générée aléatoirement
	 * @pre @code height && width > 0
	 * @post @code tableau bidimensionnel généré
	 * @return int[][] map du niveau 1 ou 2 générée aléatoirement
	 */
	private int[][] generation() {
		int[][] miniMap = new int[height][width];
		for(int i=0 ; i < height; i++) {
			for (int j=0; j < width; j++ ) {
				if (j % 2 == 1 ) {
					miniMap[i][j] = 0;
				} else 
					miniMap[i][j] = 1;
			}
		}

		for (int j=0; j < height; j++)
			miniMap[j][0] = 1;
		for (int j=0; j < width; j++)
			miniMap[0][j] = 1;
		for (int j=0; j < width; j++)
			miniMap[height-1][j] = 1;
		
		for(int i=1 ; i < (height-2); i++) {
			for (int j=0; j < width; j++ ) {
				if ((i % 2) == 0) {
					miniMap[i][j] = 1;
				}
			}
		}

		int nb = 1;

		for(int i=0 ; i < height; i++) {
			for (int j=0; j < width; j++ ) {
				if (miniMap[i][j] == 0) {
					nb++;
					miniMap[i][j] = nb;
				}
			}
		}
		
		while (!isValide(miniMap)) {

		int x = 0;
		int y = 0;
		
		x = (int) (Math.random() * (height - 2)) + 1;
		
		if (x % 2 == 0)
		    y = ((int) (Math.random() * ((width - 1) / 2))) * 2 + 1;
		else
		    y = ((int) (Math.random() * ((width - 2) / 2))) * 2 + 2;
		
		int voisin1 = 0;
		int voisin2 = 0;
		
		if (miniMap[x-1][y] == 1) {
			voisin1 = miniMap[x][y-1];
			voisin2 = miniMap[x][y+1];
		} else {
			voisin1 = miniMap[x-1][y];
			voisin2 = miniMap[x+1][y];
		}
		
		if (voisin1 != voisin2) {
			miniMap[x][y] = voisin1;
			for(int i=1 ; i < height; i+=2) {
				for (int j=1; j < width; j+=2) {
					if (miniMap[i][j] == voisin2) {
						miniMap[i][j] = voisin1;
					}
				}
			}
		}
		
		for(int i=1 ; i < (height-1); i++) {
			for (int j=1; j < (width-1); j++ ) {
				if (miniMap[i][j] != 1) {
					int valeur = miniMap[i][j];
					if (miniMap[i+1][j] != valeur && miniMap[i+1][j] != 1) {
						miniMap[i+1][j] = valeur;
					}
					if (miniMap[i-1][j] != valeur && miniMap[i-1][j] != 1) {
						miniMap[i-1][j] = valeur;
					}
					if (miniMap[i][j+1] != valeur && miniMap[i][j+1] != 1) {
						miniMap[i][j+1] = valeur;
					}
					if (miniMap[i][j-1] != valeur && miniMap[i][j-1] != 1) {
						miniMap[i][j-1] = valeur;
					}
				}
			}
		}
		
		x = 0;
		y = 0;
		
		}
		
		for(int i=1 ; i < (height-1); i++) {
			for (int j=1; j < (width-1); j++ ) {
				if (miniMap[i][j] != 1) {
					miniMap[i][j] = 0;
				}
			}
		}
		
		miniMap[height-1][1] = 2;
		return miniMap;
	}
	
	/**
	 * Renvoie un tableau bidimensionnel qui represente la map qui contient le boss
	 * @pre @code height && width > 0
	 * @post @code tableau bidimensionnel généré
	 * @return int[][] tableau bidimensionnel qui represente la map qui contient le boss
	 */
	private int[][] getBossMap(int height, int width) {
		int[][] miniMap = new int[height][width];
		for(int i=0 ; i < height; i++) {
			for (int j=0; j < width; j++ ) {
				miniMap[i][j] = 0;
			}
		}
		for (int j=0 ; j < width; j++) {
			miniMap[height-1][j] = 1;
		}
		for (int i = 0; i < height; i++) {
			miniMap[i][0] = 1;
		}
		for (int i = 0; i < height; i++) {
			miniMap[i][width-1] = 1;
		}
		
		return miniMap;
		
	}
	
	/**
	 * renvoie vrai tant que toutes les cases qui ne sont pas des murs (1) n'ont pas le même nombre
	 * @pre @code !empty(miniMap)
	 * @param miniMap
	 * @return Boolean
	 */
	private Boolean isValide(int[][] miniMap) {

		int nb = 0;

		for(int i=0 ; i < height; i++) {
			for (int j=0; j < width; j++ ) {
				if (miniMap[i][j] != 1) {
					if (nb == 0)
						nb = miniMap[i][j];
					else {
						if (nb != miniMap[i][j])
							return false;
					}
				} 
			}
		}
		return true;
	}


}
