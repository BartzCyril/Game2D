package graphique;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean z,s,q,d,e,t;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_Z: 
			z = true;
			break;
		case KeyEvent.VK_S: 
			s = true;
			break;
		case KeyEvent.VK_Q: 
			q = true;
			break;
		case KeyEvent.VK_D: 
			d = true;
			break;
		case KeyEvent.VK_E: 
			this.e = true;
			break;
		case KeyEvent.VK_T: 
			this.t = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_Z: 
			z = false;
			break;
		case KeyEvent.VK_S: 
			s = false;
			break;
		case KeyEvent.VK_Q: 
			q = false;
			break;
		case KeyEvent.VK_D: 
			d = false;
			break;
		case KeyEvent.VK_E: 
			this.e = false;
			break;
		case KeyEvent.VK_T: 
			this.t = false;
			break;
		}
	}
	
	public boolean isT() {
		return t;
	}
	
	public boolean isZ() {
		return z;
	}

	public boolean isS() {
		return s;
	}

	public boolean isQ() {
		return q;
	}

	public boolean isD() {
		return d;
	}
	
	public boolean isE() {
		return e;
	}

	

}
