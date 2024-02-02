package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Demon extends Monster {

	public Demon(int x, int y, boolean play,
			Player player, int mH, int mB) {
		super(x*tailleEntity, y*tailleEntity, "Demon", 100.0, 100.0, 1200.0, play, true, player, 3, mH, mB, false);
		getImage();
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\\\images\\\\demon_devant.png"));
			this.down = ImageIO.read(new File("images\\\\images\\\\demon_derriere.png"));
			this.left = ImageIO.read(new File("images\\\\images\\\\demon_gauche.png"));
			this.right = ImageIO.read(new File("images\\\\images\\\\demon_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
