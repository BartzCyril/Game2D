package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import graphique.MapElements;

public class Map {
	private final static int height = 100;
	private final static int width = 66;
	private static int[][] tab = new int[width][height];
	private static MapElements[] elementsMap = new MapElements [12];

	public Map() {
		try {
			map();
			setElementsMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * procédure qui lit des images et les stock dans le tableau elementsMap
	 * @pre @code ∃ elementsMap
	 * @post @code tableau remplie
	 * @throws IOException
	 */
	public void setElementsMap() throws IOException {
		elementsMap[0] = new MapElements();
		elementsMap[0].image = ImageIO.read(new File("images\\images\\terre.png"));
		elementsMap[1] = new MapElements();
		elementsMap[1].image = ImageIO.read(new File("images\\images\\pierre.png"));
		elementsMap[2] = new MapElements();
		elementsMap[2].image = ImageIO.read(new File("images\\images\\porte.png"));
		elementsMap[3] = new MapElements();
		elementsMap[3].image = ImageIO.read(new File("images\\images\\epee.png"));
		elementsMap[4] = new MapElements();
		elementsMap[4].image = ImageIO.read(new File("images\\images\\apple.png"));
		elementsMap[5] = new MapElements();
		elementsMap[5].image = ImageIO.read(new File("images\\images\\goldapple.png"));
		elementsMap[6] = new MapElements();
		elementsMap[6].image = ImageIO.read(new File("images\\images\\protection1.png"));
		elementsMap[7] = new MapElements();
		elementsMap[7].image = ImageIO.read(new File("images\\images\\protection2.png"));
		elementsMap[8] = new MapElements();
		elementsMap[8].image = ImageIO.read(new File("images\\images\\soin1.png"));
		elementsMap[9] = new MapElements();
		elementsMap[9].image = ImageIO.read(new File("images\\images\\soin2.png"));
		elementsMap[10] = new MapElements();
		elementsMap[10].image = ImageIO.read(new File("images\\images\\coffre.png"));
		elementsMap[11] = new MapElements();
		elementsMap[11].image = ImageIO.read(new File("images\\images\\piege.png"));
	}

	/**
	 * retourne un tableau bidimensionnel qui représente la map du jeu
	 * @pre @code ∃ tab
	 * @post @code tableau bidimensionnel retourné
	 * @return int[][]
	 */
	public static int[][] getMap() {
		return tab;
	}

	/**
	 * procédure qui lit chaque caractère d'un fichier et les stock dans le tableau bidimensionnel tab qui représente la map
	 * @post @code ∃ file && ∃ tab
	 * @pre @code map du jeu stocké dans le tableau bidimensionnel
	 * @throws IOException
	 */
	public void map() throws IOException {
		File file = new File("maps\\maps\\map.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int col = 0;
			int row = 0;
			int c = 0;
			while ((c = br.read()) != -1) {
				switch (c) {
				case '0':
					tab[col][row] = 0;
					col++;
					break;
				case '1':
					tab[col][row] = 1;
					col++;
					break;
				case '2':
					tab[col][row] = 2;
					col++;
					break;
				}
				if (col == (width-1)) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * procédure qui change un élément du tableu bidimensionnel tab à une position x et y
	 * @pre @code x && y >= 0 && ∃ objet 
	 * @post @code élément changé
	 * @param x 
	 * @param y
	 * @param objet
	 */
	public  static void setMap(int x, int y, int objet)  {
		tab[x][y] = objet;
	}

	/**
	 * procédure qui dessine les éléments présents dans le tableau bidimensionnel représentant la map
	 * @pre @code ∃graphics2d && ∃player && !empty(tab) && !empty(elementsMap)
	 * @param graphics2d
	 * @param player
	 * @throws IOException
	 */
	public void draw(Graphics2D graphics2d, Player player) throws IOException {
		for (int i = 0; i < (width-1);i++) {
			for(int j=0;j < (height-1) ;j++) {
				int x = i * 64;
				int y = j * 64;
				int screenX = x - player.getX() + player.getScreenX();
				int screenY = y - player.getY() + player.getScreenY();
				switch (tab[i][j]) {
				case 0:
					graphics2d.drawImage(elementsMap[0].image, screenX, screenY, 64, 64, null);
					break;
				case 1:
					graphics2d.drawImage(elementsMap[1].image, screenX, screenY, 64, 64, null);
					break;
				case 2:
					graphics2d.drawImage(elementsMap[2].image, screenX, screenY, 64, 64, null);
					break;
				case 3:
					graphics2d.drawImage(elementsMap[3].image, screenX, screenY, 64, 64, null);
					break;
				case 4:
					graphics2d.drawImage(elementsMap[4].image, screenX, screenY, 64, 64, null);
					break;
				case 5:
					graphics2d.drawImage(elementsMap[5].image, screenX, screenY, 64, 64, null);
					break;
				case 6:
					graphics2d.drawImage(elementsMap[6].image, screenX, screenY, 64, 64, null);
					break;
				case 7:
					graphics2d.drawImage(elementsMap[7].image, screenX, screenY, 64, 64, null);
					break;
				case 8:
					graphics2d.drawImage(elementsMap[8].image, screenX, screenY, 64, 64, null);
					break;
				case 9:
					graphics2d.drawImage(elementsMap[9].image, screenX, screenY, 64, 64, null);
					break;
				case 10:
					graphics2d.drawImage(elementsMap[10].image, screenX, screenY, 64, 64, null);
					break;
				case 11:
					graphics2d.drawImage(elementsMap[11].image, screenX, screenY, 64, 64, null);
					break;
				}

			}
		}
	}
}
