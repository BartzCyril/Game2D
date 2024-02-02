package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss extends Monster {

	public Boss(Player player) {
		super(33*64, 85*64, "Boss", 500, 500, 5000, false, true, player, 2, 66, 92, false);
		getImage();
	}

	@Override
	public void getImage() {
		try {
			this.up = ImageIO.read(new File("images\\\\images\\\\boss_devant.png"));
			this.down = ImageIO.read(new File("images\\\\images\\\\boss_derriere.png"));
			this.left = ImageIO.read(new File("images\\\\images\\\\boss_gauche.png"));
			this.right = ImageIO.read(new File("images\\\\images\\\\boss_droite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
