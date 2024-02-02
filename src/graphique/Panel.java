package graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

import entity.AnimalDomestique;
import entity.Boss;
import entity.Monster;
import entity.Player;
import equipments.Chest;
import equipments.Equipments;
import equipments.Items;
import equipments.Sword;
import map.Map;


public class Panel extends JPanel implements Runnable, MouseListener {

	private static final long serialVersionUID = -3914695927248483883L;
	private Thread thread;
	private Keyboard keyboard = new Keyboard();
	private final int screenHeight = 768; // 64 * 12
	private final int screenWidth = 1024; // 64 * 16
	private  Map map = new Map();
	private Player player = new Player("Toto", keyboard, 1, 1);
	private AnimalDomestique animalDomestique = player.getAnimalDomestique();
	private ArrayList<Monster> monsters = new ArrayList<>();
	private ArrayList<Equipments> equipments = new ArrayList<>();
	private ArrayList<Chest> chest = new ArrayList<>();
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.blue);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyboard);
		this.setFocusable(true);
		this.addMouseListener(this);
		equipments.add(new Sword(2*64, 2*64));
		Map.setMap(2, 2, 3);
		Items.appleGenerator(50, 66, 66);
		Monster.addRandomMonsters(1, 34, 66, monsters, player, 31,1);
		Monster.addRandomMonsters(2, 34, 66, monsters, player,64,33);
		monsters.add(new Boss(player));
		Chest.generateChest(chest, 10, 66, 66);
		Chest.drawChest(chest);
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * permet d'avoir 60 fps
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000/60);
				update();
				repaint();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * met à jour les actions du jeu
	 * @throws InterruptedException
	 */
	public void update() throws InterruptedException {
		player.update(monsters, chest);
		if (player.getHp() <= 0) {
			System.out.println("GAME OVER ! ");
			System.exit(0);
		}
		for (int i=0 ; i < monsters.size(); i++) {
			monsters.get(i).update(monsters);
		}
		if (player.isSuccess())	 {
			System.out.println("Vous avez gagné ! ");
			System.exit(0);
		}
	}

	/**
	 * dessine le jeu
	 */
	public void paint(Graphics graphics) {
		super.paintComponents(graphics);
		Graphics2D graphics2 = (Graphics2D)graphics;
		try {
			map.draw(graphics2, player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.draw(graphics2);
		animalDomestique.draw(graphics2, monsters);
		for(Monster monster : monsters) {
			monster.attackMonster(monster, graphics2);
			monster.draw(graphics2, monsters);
		}
		graphics2.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int bouton = e.getButton();
		if(bouton == MouseEvent.BUTTON1)
		{
			player.attack(monsters);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

