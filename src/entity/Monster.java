package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import map.Map;

public abstract class Monster extends Entity {

	private boolean visible;
	protected boolean amical;
	protected Player player;
	protected int[][] map = Map.getMap();
	protected Random rand = new Random();
	protected int mH;
	protected int mB;
	protected int distanceAttack = 70;
	protected boolean changeDirection = true;
	protected int nombreAleatoire = 0;
	

	public Monster(int x, int y, String name, double attack, double defense, double hp, boolean play, boolean visible,
			Player player, int vitesse, int mH, int mB, boolean amical) {
		super(x, y, name, attack, defense, hp, play, vitesse);
		this.player = player;
		this.setVisible(visible);
		this.mB = mB * tailleEntity;
		this.mH = mH * tailleEntity;
		this.amical = amical;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * renvoie vrai si le monstre peut avancer sur la map
	 * @param direction
	 * @param monsters
	 * @return
	 */
	public Boolean notColission(char direction, ArrayList<Monster> monsters) {
		double distance = getDistancePlayer();
		if (distance <= 70)
			return false;
		if ((y < mH) || (y > mB)) {
			return false;
		}
		try {
			switch(direction) {
			case 'r' :
				if ((map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.ceil((double)y/tailleEntity)] == 1) || 
					(map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.floor((double)y/tailleEntity)] == 1) )
					return false;
				return true;
			case 'l' : 
				if ((map[(x-vitesse)/tailleEntity][(int)Math.ceil((double)y/tailleEntity)] == 1) || 
					(map[(x-vitesse)/tailleEntity][(int)Math.floor((double)y/tailleEntity)] == 1))
					return false;
				return true;
			case 'd' : 
				if (map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1 || 
					map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y+vitesse)/tailleEntity)] == 1 || 
					map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1) {
					return false;
				}
				return true;
			default : 
				if (map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || 
					map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1 || 
					map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || 
					map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1)
					return false;
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			monsters.remove(this);
		}
		return false;
	}

	/**
	 * renvoie la distance entre le monstre et le joueur
	 * @return double distance
	 */
	public double getDistancePlayer() {
		Point point = new Point(x, y);
		return point.distance(player.getX(), player.getY());
	}

	/**
	 * procedure qui permet au monstre d'attaquer le joueur si c'est à lui de jouer
	 * @param monster
	 * @param graphics2
	 */
	public void attackMonster(Monster monster, Graphics2D graphics2) {
		double distance = getDistancePlayer();
		double attackDistance = monster.distanceAttack;
		if (monster.play && !monster.amical) {
			if (monster instanceof Squeleton) {
				if (((Squeleton) monster).getCountArrow() != 0) {
					attackDistance+=((Squeleton) monster).getBow().getAttack_distance();
				}
			}
			if (distance <= attackDistance) {
				player.hp = (player.hp - attack) + (player.defense*0.10);
				System.out.println("Le monstre " + name + ", vous a attaqué, il vous reste " + player.hp + " hp");
				if (player.hp > 0) {  
					player.play = true; 
					play= false; 
					player.setAnimalDomestique();
					if (monster instanceof Squeleton) {
						((Squeleton) monster).setCountArrow(-1);
					}

				} 
			}
		}
	}

	public void draw(Graphics2D graphics2, ArrayList<Monster> monsters) {
		BufferedImage image = null;
		if (this instanceof Bat) {
			if (play) {
				double distance = getDistancePlayer();
				int dx = (int) (((player.getX() - x) * vitesse) / distance);
				int dy = (int) (((player.getY() - y) * vitesse) / distance);
				image = this.up;
				if (y >= mH && y <= mB) {
					this.setY(y + dy);
					this.setX(x + dx);
				} else {
					this.setY(y - 5);
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
			} else {
				image = this.up;
				int transformYMb = (int)Math.ceil((double)y/64);
				switch (this.direction) {
				case 'u': {
					if ((int)Math.ceil((((double)y/64))-vitesse) >= mH/64 && (y) <= mB && y-vitesse > 0) {
						image = this.up;
						this.setY(y - vitesse);
					}
					break;
				}
				case 'd': {
					if ((int)Math.ceil((((double)y/64))+vitesse) >= mH/64 && (y) <= mB && y+vitesse < (99*64)) {
						image = this.down;
						this.setY(y + vitesse);
					}
					break;
				}
				case 'l': {
					if ((transformYMb) >= mH/64 && (y) <= mB && (x-vitesse) > 0) {
						image = this.left;
						this.setX(x - vitesse);
					}
					break;
				}
				case 'r': {
					if ((transformYMb) >= mH/64 && (y) <= mB && (x+vitesse) < (66*64)) {
						image = this.right;
						this.setX(x + vitesse);
					}
					break;
				}
				}
			}
		}
		else if (this instanceof Boss) {
			if (player.y > 3840) {
				double distance = getDistancePlayer();
				int dx = (int) (((player.getX() - x) * vitesse) / distance);
				int dy = (int) (((player.getY() - y) * vitesse) / distance);
				image = this.up;
				if (distance >= 75) {
					this.setY(y + dy);
					this.setX(x + dx);
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
			}
		}
		else {
			switch (this.direction) {
			case 'u': {
				image = this.up;
				if (notColission('u', monsters))
					this.setY(y - vitesse);
				break;
			}
			case 'd': {
				image = this.down;
				if (notColission('d', monsters))
					this.setY(y + vitesse);
				break;
			}
			case 'l': {
				image = this.left;
				if (notColission('l', monsters))
					this.setX(x - vitesse);
				break;
			}
			case 'r': {
				image = this.right;
				if (notColission('r', monsters))
					this.setX(x + vitesse);
				break;
			}
			}
		}
		int screenX = this.x - this.player.getX() + this.player.getScreenX();
		int screenY = this.y - this.player.getY() + this.player.getScreenY();
		graphics2.drawImage(image, screenX, screenY, tailleEntity, tailleEntity, null);
	}


	public void update(ArrayList<Monster> monsters) {
		detectGoldApple(monsters);
		if (this instanceof Bat) {
			if (play && player.y < 4096)
				play = false;
			if (!play) {
				double distance = getDistancePlayer();
				if (distance <= 250)
					play = true;
				int nombreAleatoire = rand.nextInt((9 + 1) - 1);
				switch (nombreAleatoire) {
				case 0:
					this.direction = 'r';
					break;
				case 1:
					this.direction = 'l';
					break;
				case 2:
					this.direction = 'd';
					break;
				case 3:
					this.direction = 'd';
					break;
				case 4:
					this.direction = 'u';
					break;
				case 5:
					this.direction = 'r';
					break;
				case 6:
					this.direction = 'u';
					break;
				case 7:
					this.direction = 'l';
					break;
				default:
					this.direction = 'u';
					break;
				}
			}
		} else {
			if (changeDirection)  {
				nombreAleatoire = rand.nextInt(4);
				changeDirection = false;
			}
			if ((y < mH) || (y > mB)) {
				if (this instanceof Goblin || this instanceof Squeleton) {
					this.setY(y-1);
				}
				if (this instanceof Bat || this instanceof Demon) {
					if (y > 3200)
						this.setY(y-1);
					else 
						this.setY(y+1);
					if (y == mH)
						this.setY(y+1);
					if (y == mB)
						this.setY(y-1);
				}
				changeDirection = true;
			}
			try {
				switch (nombreAleatoire) {
				case 0:
					this.direction = 'r';
					if (
							(map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.ceil((double)y/tailleEntity)] == 1) || 
							(map[(int)Math.ceil((double)(x+vitesse)/tailleEntity)][(int)Math.floor((double)y/tailleEntity)] == 1))
					{
						changeDirection = true;
					}
					break;
				case 1:
					this.direction = 'l';
					if (
							(map[(x-vitesse)/tailleEntity][(int)Math.ceil((double)y/tailleEntity)] == 1) || 
							(map[(x-vitesse)/tailleEntity][(int)Math.floor((double)y/tailleEntity)] == 1))
						changeDirection = true;
					break;
				case 2:
					this.direction = 'd';
					if (map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1 || 
							map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y+vitesse)/tailleEntity)] == 1 || 
							map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y+vitesse)/tailleEntity)] == 1)
					{
						changeDirection = true;
					}
					break;
				default:
					this.direction = 'u';
					if (
							map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || 
							map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1 || 
							map[(int)Math.ceil(((double)x)/tailleEntity)][(int)Math.ceil(((double)y-vitesse)/tailleEntity)] == 1 || 
							map[(int)Math.floor(((double)x)/tailleEntity)][(int)Math.floor(((double)y-vitesse)/tailleEntity)] == 1)
						changeDirection = true;
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				monsters.remove(this);
			}
		}
	}

	/**
	 * Ajoute des monstres aléatoirement sur la map 1 ou la map 2 qui représente 2 niveaux
	 * @pre @code level ∈ [1,2] && (mapWidth && mapHeight) > 0
	 * @post @code monstres générés sur la map du niveau
	 * @param level 1 ou 2 
	 * @param mapWidth 
	 * @param mapHeight
	 */
	public static void addRandomMonsters(int level, int mapWidth, int mapHeight, ArrayList<Monster> monsters, Player player, int mB, int mH) { 
		Random rand = new Random();
		int countMonster = 0;
		int countTypeMonster = 0;
		int limit = 0;
		countMonster = (level == 1) ? 15 : 30;
		limit = (level == 1) ? 0 : 33;
		int x,y = 0;
		for (int i = 0 ; i < countMonster; i++) {
			if (level == 1)
				x = rand.nextInt(mapHeight - 1);
			else 
				x = rand.nextInt(mapHeight);
			y = rand.nextInt(mapWidth) + limit;
			while (Map.getMap()[x][y] != 0) {
				if (level == 1)
					x = rand.nextInt(mapHeight - 1);
				else 
					x = rand.nextInt(mapHeight);
				y = rand.nextInt(mapWidth) + limit;
			}
			if (level == 1) {
				if (countTypeMonster < (countMonster / 2)) {
					monsters.add(new Goblin(x, y, false, player, mH, mB));
					countTypeMonster++;
				}
				else 
					monsters.add(new Squeleton(x, y, true ,player, mH, mB));
			} else {
				if (countTypeMonster < (countMonster / 2)) {
					monsters.add(new Demon(x, y, false, player, mH, mB)); 
					countTypeMonster++;
				}
				else {
					monsters.add(new Bat(x, y, false ,player, mH, mB));
				}
			}
		}
	}
	
	/**
	 * procedure qui permet de detecter si le monstre est en contact avec la pomme dorée 
	 * qui change le comportement du monstre en amical c'est à dire qu'il ne peut plus 
	 * jouer
	 * @param monsters
	 */
	public void detectGoldApple(ArrayList<Monster> monsters) {
		try {
			if (map[x/tailleEntity][y/tailleEntity] == 5) {
				Map.setMap(x/tailleEntity, y/tailleEntity, 0);
				amical = true;
				player.setPlay(true);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			monsters.remove(this);
		}
	}
}
