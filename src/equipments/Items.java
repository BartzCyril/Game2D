package equipments;

import java.util.Random;
import map.Map;

public abstract class Items extends Equipments {

	public Items(int x, int y,  int numPicture) {
		super(x, y, numPicture);
	}

	/**
	 * procedure qui genere x pomme sur la map
	 * @param count
	 * @param limitSecondMapHeight
	 * @param mapWidth
	 */
	public static void appleGenerator(int count, int limitSecondMapHeight, int mapWidth) {
		Random rand = new Random();
		int x,y = 0;
		for (int i = 0 ; i < count ; i++) {
			x = rand.nextInt(limitSecondMapHeight);
			y = rand.nextInt(mapWidth) ;
			while (Map.getMap()[x][y] != 0) {
					x = rand.nextInt(limitSecondMapHeight);
					y = rand.nextInt(mapWidth);
			}
			Map.setMap(x, y, 4);
		}
	}
}
