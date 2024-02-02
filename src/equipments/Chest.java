package equipments;

import java.util.ArrayList;
import java.util.Random;

import map.Map;

public class Chest {

	protected int x;
	protected int y;
	protected Equipments equipment;

	public Chest(int x, int y, Equipments equipment) {
		this.x = x;
		this.y = y;
		this.equipment = equipment;
	}

	/**
	 * renvoie un coffre qui contient un objet aléatoire
	 * @param limitSecondMapHeight
	 * @param mapWidth
	 * @return
	 */
	public static Chest getChest(int limitSecondMapHeight, int mapWidth) {
		Random rand = new Random();
		int x,y = 0;
		x = rand.nextInt(limitSecondMapHeight);
		y = rand.nextInt(mapWidth) ;
		while (Map.getMap()[x][y] != 0 && Map.getMap()[x][y] != 10) {
			x = rand.nextInt(limitSecondMapHeight);
			y = rand.nextInt(mapWidth) ;
		}
		int numEquipment = rand.nextInt(7);
		switch(numEquipment) {
		case 0:
			return new Chest(x, y, new Sword(x, y));
		case 1:
			return new Chest(x, y, new Apple(x, y));
		case 2:
			return new Chest(x, y, new MegaHealingPotions(x, y));
		case 3:
			return new Chest(x, y, new MegaShield(x, y));
		case 4:
			return new Chest(x, y, new SmallHealingPotions(x, y));
		case 5:
			return new Chest(x, y, new SmallShield(x, y));
		default:
			return new Chest(x, y, new Trap(x, y)); 
		}
	}
	
	/**
	 * procedure qui genere x coffres et les stock dans une liste
	 * @param chest
	 * @param count
	 * @param limitSecondMapHeight
	 * @param mapWidth
	 */
	public static void generateChest(ArrayList<Chest> chest, int count, int limitSecondMapHeight, int mapWidth) {
			for (int i=0 ; i < count; i++) {
				chest.add(Chest.getChest(limitSecondMapHeight, mapWidth));
			}
	}
	
	/**
	 * procédure qui dessine le coffre
	 * @param chests
	 */
	public static void drawChest(ArrayList<Chest> chests) {
		for (Chest chest : chests) {
			Map.setMap(chest.x, chest.y, 10);
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Equipments getEquipment() {
		return equipment;
	}
	
}
