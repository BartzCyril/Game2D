package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import equipments.Apple;
import equipments.Chest;
import equipments.Inventaire;
import equipments.MegaHealingPotions;
import equipments.MegaShield;
import equipments.SmallHealingPotions;
import equipments.SmallShield;
import equipments.Sword;
import equipments.Weapons;
import graphique.Keyboard;
import map.Map;

public class Player extends Entity{

	private Keyboard keyboard;
	private final int screenX = 1024 / 2 - tailleEntity / 2;
	private final int screenY = 768 / 2 - tailleEntity / 2;
	private final int map[][] = Map.getMap();
	private Inventaire inventaire = new Inventaire();
	private AnimalDomestique animalDomestique = new AnimalDomestique(1, 1, "Poule", this);
	private boolean success = false;
	private boolean level2 = false;
	private boolean level3 = false;

	public Player(String name, Keyboard keyboard,int x, int y) {
		super(x*tailleEntity,y*tailleEntity,name,100,100,1000,true,4);
		this.keyboard = keyboard;
		getImage();
	}

	public AnimalDomestique getAnimalDomestique() {
		return animalDomestique;
	}

	public void draw(Graphics2D graphics2) {
		BufferedImage image = null;
		switch(this.direction) {
		case 'u' : {
			image = this.up;
			break;
		}
		case 'd' : {
			image = this.down;
			break;
		}
		case 'l' : {
			image = this.left;
			break;
		}
		case 'r' : {
			image = this.right;
			break;
		}
		}
		graphics2.drawImage(image, screenX, screenY, tailleEntity, tailleEntity, null);
	}

	public void update(ArrayList<Monster> monsters, ArrayList<Chest> chest) {
		if (!level2 && y >= 2112) {
			System.out.println("Vous êtes au niveau 2");
			level2 = true;
		}
		if (!level3 && y >= 4224) {
			System.out.println("Vous êtes au niveau 3");
			level3 = true;
		}
		detectObjet(chest);
		if (keyboard.isZ()) {
			this.direction = 'u';
			if (notColission('u', monsters))
				y-=vitesse;
		}

		else if (keyboard.isS()) {
			this.direction = 'd';
			if (notColission('d', monsters))
				y+=vitesse;
		}

		else if (keyboard.isQ()) {
			this.direction = 'l';
			if (notColission('l', monsters))
				x-=vitesse;
		}

		else if (keyboard.isD()) {
			this.direction = 'r';
			if (notColission('r', monsters))
				x+=vitesse;
		}

		else if (keyboard.isE()) {
			if (inventaire.getItems().size() == 3) {
				Map.setMap(x/tailleEntity, y/tailleEntity, 5);
				inventaire.deleteGoldApple();
			}
		}

	}

	/**
	 * renvoie vrai si le joueur peut avancer sur la map
	 * @param direction
	 * @param monsters
	 * @return Boolean
	 */
	public Boolean notColission(char direction, ArrayList<Monster> monsters) {
		switch(direction) {
		case 'r' :
			if ((map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.ceil((double)y/tailleEntity)] == 1) || (map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.floor((double)y/tailleEntity)] == 1) )
				return false;
			return true;
		case 'l' : 
			if ((map[(x-vitesse)/tailleEntity][(int)Math.ceil((double)y/tailleEntity)] == 1) || (map[(x-vitesse)/tailleEntity][(int)Math.floor((double)y/tailleEntity)] == 1))
				return false;
			return true;
		case 'd' : 
			if (map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1 || map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y+vitesse)/tailleEntity)] == 1 || map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1) {
				return false;
			}
			return true;
		case 'u' : 
			if (map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1 || map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1)
				return false;
			return true;
		}
		return true;
	}

	/**
	 * procedure qui detecte si le joueur est en contact avec un objet
	 * @param chest
	 */
	public void detectObjet(ArrayList<Chest> chest) {
		int xTailleEntity = x/tailleEntity;
		int yTailleEntity = y/tailleEntity;
		if (map[xTailleEntity][yTailleEntity] == 3) {
			Sword sword = new Sword(x, y);
			inventaire.setInventaire(this, sword);
			Map.setMap(xTailleEntity, yTailleEntity, 0);
			attack+=sword.getAttack();
			System.out.println("Vous avez ramasée une épee !");
		}
		if (map[xTailleEntity][yTailleEntity] == 4 && inventaire.getItems().size() < 3) {
			inventaire.setInventaire(this, new Apple(x, y));
			Map.setMap(xTailleEntity, yTailleEntity, 0);
			System.out.println("Vous avez ramasée une pomme !");
		}
		try {
			if ((map[xTailleEntity+1][yTailleEntity] == 10 || 
					map[xTailleEntity-1][yTailleEntity] == 10 || 
					map[xTailleEntity][yTailleEntity+1] == 10 || 
					map[xTailleEntity][yTailleEntity-1] == 10 ||
					map[xTailleEntity+2][yTailleEntity] == 10 || 
					map[xTailleEntity-2][yTailleEntity] == 10 || 
					map[xTailleEntity][yTailleEntity+2] == 10 || 
					map[xTailleEntity][yTailleEntity-2] == 10) && 
					keyboard.isT()) {
				for (int i=0 ; i < chest.size(); i++) {
					if  (xTailleEntity+1 == chest.get(i).getX() && yTailleEntity == chest.get(i).getY())
						Map.setMap(xTailleEntity+1, yTailleEntity, chest.get(i).getEquipment().getNumPicture());
					else if  (xTailleEntity-1 == chest.get(i).getX() && yTailleEntity == chest.get(i).getY())
						Map.setMap(xTailleEntity-1, yTailleEntity, chest.get(i).getEquipment().getNumPicture());
					else if  (xTailleEntity+2 == chest.get(i).getX() && yTailleEntity == chest.get(i).getY())
						Map.setMap(xTailleEntity+2, yTailleEntity, chest.get(i).getEquipment().getNumPicture());
					else if  (xTailleEntity-2 == chest.get(i).getX() && yTailleEntity == chest.get(i).getY())
						Map.setMap(xTailleEntity-2, yTailleEntity, chest.get(i).getEquipment().getNumPicture());
					else if (yTailleEntity+1 == chest.get(i).getY() && xTailleEntity == chest.get(i).getX())
						Map.setMap(xTailleEntity, yTailleEntity+1, chest.get(i).getEquipment().getNumPicture());
					else if (yTailleEntity+2 == chest.get(i).getY() && xTailleEntity == chest.get(i).getX())
						Map.setMap(xTailleEntity, yTailleEntity+2, chest.get(i).getEquipment().getNumPicture());
					else if (yTailleEntity-1 == chest.get(i).getY() && xTailleEntity == chest.get(i).getX())
						Map.setMap(xTailleEntity, yTailleEntity-1, chest.get(i).getEquipment().getNumPicture());
					else if (yTailleEntity-2 == chest.get(i).getY() && xTailleEntity == chest.get(i).getX())
						Map.setMap(xTailleEntity, yTailleEntity-2, chest.get(i).getEquipment().getNumPicture());
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		if (map[xTailleEntity][yTailleEntity] == 6) {
			SmallShield shield = new SmallShield(x, y);
			inventaire.setInventaire(this, shield);
			Map.setMap(xTailleEntity, yTailleEntity, 0);
			defense+=shield.getGuard();
			System.out.println("Vous avez ramasée une petite protection !");
		}
		if (map[xTailleEntity][yTailleEntity] == 7) {
			MegaShield shield = new MegaShield(x, y);
			inventaire.setInventaire(this, shield);
			Map.setMap(xTailleEntity,yTailleEntity, 0);
			defense+=shield.getGuard();
			System.out.println("Vous avez ramasée une grosse protection !");
		}
		if (map[xTailleEntity][yTailleEntity] == 8) {
			SmallHealingPotions heal = new SmallHealingPotions(x, y);
			inventaire.setInventaire(this, heal);
			Map.setMap(x/tailleEntity, yTailleEntity, 0);
			defense+=heal.getHealth();
			System.out.println("Vous avez ramasée une petite fiole de soin !");
		}
		if (map[xTailleEntity][yTailleEntity] == 9) {
			MegaHealingPotions heal = new MegaHealingPotions(x, y);
			inventaire.setInventaire(this, heal);
			Map.setMap(xTailleEntity, yTailleEntity, 0);
			defense+=heal.getHealth();
			System.out.println("Vous avez ramasée une grosse fiole de soin !");
		}
		if (map[xTailleEntity][yTailleEntity] == 11) {
			hp = 0;
		}
	}

	/**
	 * procedure qui permet au joueur d'attaquer les monstres si c'est à lui de jouer
	 * @param monsters
	 */
	public void attack(ArrayList<Monster> monsters) {
		if (play) {
			double distance, attack_distance = 0;
			for (int i=0 ; i < monsters.size(); i++) {
				Point point = new Point(x, y);
				distance = point.distance(monsters.get(i).x, monsters.get(i).y);
				attack_distance = 0;
				if (((Weapons) inventaire.getInventaire()[0]) != null) {
					attack_distance = 70 +  ((Weapons) inventaire.getInventaire()[0]).getAttack_distance();
				} 
				else {
					attack_distance = 70;
				}
				if (distance <= attack_distance) {
					monsters.get(i).hp = (monsters.get(i).hp - attack) + monsters.get(i).defense;
					if (monsters.get(i) instanceof Boss)
						if (monsters.get(i).hp <= 0) {
							success = true;
						}
					if (monsters.get(i).hp > 0) {
						System.out.println("Vous avez attaqué " + monsters.get(i).name + " il lui reste " + monsters.get(i).hp + " hp");
						monsters.get(i).play = true;
						if (monsters.get(i).amical)
							play = true;
						else 
							play = false;
						animalDomestique.play = true;
					} else {
						System.out.println("Vous avez tué " + monsters.get(i).name);
						hp+=100;
						attack+=100;
						defense+=50;
						System.out.println("Vous avez gagné 100 hp, 100 d'attaque et 50 défense. Voici vos nouvelles statistiques : " + "hp = " + hp + " attaque = " + attack + " défense = " + defense);
						play = true;
						animalDomestique.play = false;
						monsters.remove(monsters.get(i));
					}
				}
			}
		}
	}

	public void setAnimalDomestique() {
		animalDomestique.play = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\images\\player_derriere.png"));
			this.down = ImageIO.read(new File("images\\images\\player_devant.png"));
			this.left = ImageIO.read(new File("images\\images\\player_gauche.png"));
			this.right = ImageIO.read(new File("images\\images\\player_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public boolean isSuccess() {
		return success;
	}
	
}



