package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class AnimalDomestique extends Entity {

	protected Player player;

	public AnimalDomestique(int x, int y, String name, Player player) {
		super(x*tailleEntity, y*tailleEntity, name, 15.0, 10.0, 20.0, false, 5);
		this.player = player;
		getImage();
	}

	public void draw(Graphics2D graphics2, ArrayList<Monster> monsters) {
		BufferedImage image = null;
		double distance = 0;
		int dx, dy = 0;
		Point point = new Point(x, y);
		if (play) {
			Monster monster = getMonsterNearest(monsters);
			distance = point.distance(monster.x, monster.y);
			dx = (int) (((monster.x - x) * vitesse) / distance);
			dy = (int) (((monster.y - y) * vitesse) / distance);
			if (distance <= 75) {
				if ((monster.hp - attack) + monster.defense < monster.hp)
					monster.hp = (monster.hp - attack) + monster.defense;
				if (monster.hp <= 0) {
					System.out.println("Votre animal domestique à tué " + monster.name);
					monsters.remove(monster);
				}
				else 
					System.out.println("Votre animal domestique à ataqué " + monster.name);
				play = false;
			}
		} else {
			distance = point.distance(player.getX(), player.getY());
			dx = (int) (((player.getX() - x) * vitesse) / distance);
			dy = (int) (((player.getY() - y) * vitesse) / distance);
		}
		image = this.up;
		if (distance >= 70) {
			this.setX(x + dx);
			this.setY(y + dy);
		}
		if (dx == 1) {
			image = this.right;
		} else if (dx == -1) {
			image = this.left;
		}
		if (dy == 1) {
			image = this.up;
		} else if (dy == -1) {
			image = this.down;
		}
		int screenX = this.x - this.player.getX() + this.player.getScreenX();
		int screenY = this.y - this.player.getY() + this.player.getScreenY();
		graphics2.drawImage(image, screenX, screenY, tailleEntity, tailleEntity, null);
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\images\\poule_derriere.png"));
			this.down = ImageIO.read(new File("images\\images\\poule_devant.png"));
			this.left = ImageIO.read(new File("images\\images\\poule_gauche.png"));
			this.right = ImageIO.read(new File("images\\images\\poule_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * renvoie le monstre le plus proche pour permettre à la poule de l'attaquer
	 * @param monsters
	 * @return Monster
	 */
	public Monster getMonsterNearest(ArrayList<Monster> monsters) {
		Point point = new Point(x, y);
		int indexMonstre = 0;
		double distance = point.distance(monsters.get(0).x, monsters.get(0).y);
		for (int i = 1 ; i < monsters.size(); i++) {
			if (distance > point.distance(monsters.get(i).x, monsters.get(i).y)) {
				distance = point.distance(monsters.get(i).x, monsters.get(i).y);
				indexMonstre = i;
			}
		}
		return monsters.get(indexMonstre);
	}

}
