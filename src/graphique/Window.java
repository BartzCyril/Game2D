package graphique;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Panel panel = new Panel();
	
	public void loadParameters() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Donjon GAME");
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		panel.start();
	}
	
	public static void close() {
		Window window = new Window();
		window.dispose();
	}
	
}
