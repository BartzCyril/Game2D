package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Goblin extends Monster {

	public Goblin(int x, int y, boolean play, Player player, int mH, int mB) {
		super(x*tailleEntity,  y*tailleEntity, "Goblin", 50.0, 50.0, 525.0, play, true, player, 2, mH, mB, false);
		getImage();
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\\\images\\\\goblin_devant.png"));
			this.down = ImageIO.read(new File("images\\\\images\\\\goblin_derriere.png"));
			this.left = ImageIO.read(new File("images\\\\images\\\\goblin_gauche.png"));
			this.right = ImageIO.read(new File("images\\\\images\\\\goblin_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
