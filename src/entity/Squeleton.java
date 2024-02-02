package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import equipments.Bow;

public class Squeleton extends Monster {

	private Bow bow;
	private int countArrow = 30;
	
	public Squeleton(int x, int y, boolean play, Player player, int mH, int mB) {
		super(x*tailleEntity, y*tailleEntity, "Squeleton", 80.0, 50.0, 10.0, play,true, player, 2, mH, mB, false); // a changer mettre dans les parametres
		this.bow  = new Bow(x, y);
		getImage();
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\\\images\\\\squelette_devant.png"));
			this.down = ImageIO.read(new File("images\\\\images\\\\squelette_derriere.png"));
			this.left = ImageIO.read(new File("images\\\\images\\\\squelette_gauche.png"));
			this.right = ImageIO.read(new File("images\\\\images\\\\squelette_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCountArrow() {
		return countArrow;
	}
	
	public void setCountArrow(int countArrow) {
		this.countArrow = countArrow;
	}

	public Bow getBow() {
		return bow;
	}
	
	
	
	

}
