package entity;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	protected int x;
	protected int y;
	protected String name;
	protected double attack;
	protected double defense;
	protected double hp;
	protected boolean play;
	protected BufferedImage up,down,left,right;
	char direction = 'u';
	protected int vitesse;
	protected static int tailleEntity = 64;
	
	public Entity(int x, int y, String name, double attack, double defense, double hp, boolean play, int vitesse) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.play = play;
		this.vitesse = vitesse;
	}
	
	public double getHp() {
		return hp;
	}



	public BufferedImage getUp() {
		return up;
	}

	public void setUp(BufferedImage up) {
		this.up = up;
	}

	public void setDown(BufferedImage down) {
		this.down = down;
	}

	public void setLeft(BufferedImage left) {
		this.left = left;
	}

	public void setRight(BufferedImage right) {
		this.right = right;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	/**
	 * procedure qui stock les images haut,bas,gauche,droite
	 */
	public abstract void getImage();
	
}
