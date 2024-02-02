package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bat extends Monster{

	public Bat(int x, int y, boolean play,
			Player player, int mH, int mB) {
		super(x*tailleEntity, y*tailleEntity, "Bat", 120.0, 10.0, 100.0, play, true, player, 5, mH, mB, false);
		getImage();
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\\\images\\\\bat_devant.png"));
			this.down = ImageIO.read(new File("images\\\\images\\\\bat_derriere.png"));
			this.left = ImageIO.read(new File("images\\\\images\\\\bat_gauche.png"));
			this.right = ImageIO.read(new File("images\\\\images\\\\bat_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
